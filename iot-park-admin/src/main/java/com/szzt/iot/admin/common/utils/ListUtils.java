package com.szzt.iot.admin.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListUtils {
    /**
     * 筛选一个List<String[]>中String[]里面重复的第一项  并返回一个List<重复项：List<String[]>>
     * @param list
     * @return
     */
    public static List<List<String[]>> repetition(List<String[]> list) {
        if (list != null && list.size() > 0) {
            HashSet<String> set = new HashSet<>();
            for (String[] strings : list) {
                set.add(strings[0]);
            }
            List<Integer> indexs = new ArrayList<>();
            for (String s : set) {
                int num = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i)[0].equals(s)) {
                        num++;
                        if (num >= 2) {
                            indexs.add(i);
                            break;
                        }
                    }
                }
            }
            List<List<String[]>> list1 = new ArrayList<>();
            for (Integer index : indexs) {
                String[] strings = list.get(index);
                List<String[]> strs = new ArrayList<>();
                for (String[] strings1 : list) {
                    if (strings1[0].equals(strings[0])) {
                        strs.add(strings1);
                    }
                }
                list1.add(strs);
            }

            List<List<String[]>> list2 = new ArrayList<>();
            for (String[] strings : list) {
                int num = indexs.size();
                for (Integer index : indexs) {
                    String[] strings1 = list.get(index);
                    if (!(strings1[0].equals(strings[0]))) {
                        num--;
                        if (num == 0) {
                            List<String[]> str = new ArrayList<>();
                            str.add(strings);
                            list2.add(str);
                        }
                    }
                }
            }

            for (List<String[]> strings : list2) {
                list1.add(strings);
            }
            return list1;
        }
        return null;
    }
}
