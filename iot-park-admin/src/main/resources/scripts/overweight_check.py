# -*- coding: utf-8 -*-

import argparse
import pandas as pd
import numpy as np
import re
import difflib


def process(input_file, output_file):
    # 读取标准配置分项价格表
    df1 = pd.read_excel(input_file, sheet_name=0, skiprows=3,
                        usecols=["名称", "数量", "不含税单价"])
    df1.rename(columns={"数量": "单套标配数量"}, inplace=True)
    df1 = df1.dropna()

    # 读取订单
    df2 = pd.read_excel(input_file, sheet_name=1, usecols=[
        "物料名称", "订购数量", "不含税单价", "订单扣减配额数据"])
    df2.rename(
        columns={
            "物料名称": "名称",
            "订单扣减配额数据": "配额数量"
        },
        inplace=True
    )
    df2 = df2[df2["名称"].notnull()]

    # 将订单中的物料名称与标准配置表的名称对应
    # 按单价匹配(单价保留小数点后2位, 误差绝对值不大于0.005)
    # 若存在单价相同的不同物品, 选择名称相似度最高的
    eps = 5e-3
    best_match = {}
    price1 = df1["不含税单价"].to_numpy()
    price2 = df2["不含税单价"].to_numpy()
    dist = np.abs(price1.reshape(len(price1), 1) -
                  price2.reshape(1, len(price2)))

    # 文本匹配时忽略括号中的内容
    pattern = re.compile(r"\(.*\)|（.*）")
    names1 = df1["名称"].apply(lambda x: pattern.sub("", x))
    names2 = df2["名称"].apply(lambda x: pattern.sub("", x.split("\\")[-1]))
    for x, y in zip(*np.where(dist <= eps)):
        text1 = names1.iat[x]
        text2 = names2.iat[y]
        sim = difflib.SequenceMatcher(None, text1, text2).ratio()
        if(y in best_match):
            if(sim > best_match[y][1]):
                best_match[y] = (x, sim)
        else:
            best_match[y] = (x, sim)

    for k, v in best_match.items():
        df2["名称"].iat[k] = df1["名称"].iat[v[0]]

    # 合并表格并计算配置差异
    result = pd.merge(df1, df2.drop(columns="不含税单价"), on="名称")
    result["单套下单配置"] = result["订购数量"] / result["配额数量"]
    result["配置差异"] = result["单套下单配置"] - result["单套标配数量"]
    result["超标配不含税金额（整个订单）"] = result["单套标配数量"] * \
                               result["不含税单价"] * result["配置差异"] * result["配额数量"]

    result = result[["名称", "订购数量", "单套下单配置", "配置差异", "超标配不含税金额（整个订单）"]]
    result = result.rename(columns={"订购数量": "订单数量"})

    # 总计
    df2["不含税金额"] = df2["订购数量"] * df2["不含税单价"]
    sum1 = (df2["不含税金额"] / df2["配额数量"]).sum()
    sum2 = result["配置差异"].sum()
    sum3 = result["超标配不含税金额（整个订单）"].sum()

    # 输出结果
    df3 = pd.read_excel(input_file, sheet_name=0, skiprows=3, usecols=[
        "序号", "名称", "单位", "数量", "不含税单价", "不含税合价", "增值税率", "增值税税额", "含税合价"])
    df3 = pd.merge(df3, result, how="left", on="名称")
    df3[df3 == 0] = np.nan
    df3.to_excel(output_file)

    return sum1, sum2, sum3


if(__name__ == "__main__"):
    parser = argparse.ArgumentParser()
    parser.add_argument("--inputfile", "-i", help="输入文件", required=True)
    parser.add_argument("--outputfile", "-o", help="输出文件", required=True)
    args = parser.parse_args()

    process(args.inputfile, args.outputfile)