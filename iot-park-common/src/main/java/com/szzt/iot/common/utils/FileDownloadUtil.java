package com.szzt.iot.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 远程下载文件到本地
 */
public class FileDownloadUtil {

    /**
     * 下载远程文件并保存到本地，默认本地文件名与远程文件名一致
     *
     * @param filePath      远程文件路径
     * @param localFilePath 本地文件路径（带文件名）
     */
    public static boolean downloadFile(String filePath, String localFilePath) {
        Boolean isSuccess = true;
        String filename = null;
        String remoteFilePath = filePath.replace("\\", "/");
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        if (!f.exists()) {
            f.mkdirs();
        }

        try {
//            filename = localFilePath + File.separator + System.currentTimeMillis() + remoteFilePath.substring(remoteFilePath.lastIndexOf("."));
            filename = localFilePath + File.separator + remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(filename));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
            filename = null;
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 下载远程文件并保存到本地，默认本地文件名与远程文件名一致
     *
     * @param filePath      远程文件路径
     * @param localFilePath 本地文件路径（不带文件名）
     */
    public static boolean downloadFileToLocal(String filePath, String localFilePath) {

        String remoteFilePath = filePath.replace("\\", "/");
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        File parentFile = f.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        boolean isSuccess = true;
        try {
            String filename = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public static void main(String[] args) {
        downloadFile("http://172.20.31.12:8080/gongdan/gongdanchuli-chenfen-02cansnuyouhua-01fananzhizuo-20190625-dengluzhanghao-dy_zhouqianwen.xlsx", "E:\\test\\");
    }
}
