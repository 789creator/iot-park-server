/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.oss.cloud;

import com.szzt.iot.admin.modules.sys.service.SysParamsService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.utils.SpringContextUtils;

/**
 * 文件上传Factory
 * @author
 */
public final class OSSFactory {
    private static SysParamsService sysParamsService;

    static {
        OSSFactory.sysParamsService = SpringContextUtils.getBean(SysParamsService.class);
    }

    public static AbstractCloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysParamsService.getValueObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.FASTDFS.getValue()){
            return new FastDFSCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.LOCAL.getValue()){
            return new LocalCloudStorageService(config);
        }

        return null;
    }

}