package com.szzt.iot.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TestFtp {

    public static boolean downFile(
            String url, //FTP服务器hostname
            int port,//FTP服务器端口
            String username, //FTP登录账号
            String password, //FTP登录密码
            String remotePath,//FTP服务器上的相对路径
            String fileName,//要下载的文件名
            String localPath//下载后保存到本地的路径

    ) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            System.out.println("aaa");
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();

            for (FTPFile ff : fs) {
                System.out.println("bb" + fs);

                if (ff.getName().equals(fileName)) {
                    System.out.println("dd");
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    System.out.println("ccc" + ff.getName() + fileName);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public static void main(String[] args) {
        String url = "10.108.218.245"; //FTP服务器hostname
        int port = 21;//FTP服务器端口
        String username = "IUSR"; //FTP登录账号
        String password = "root123"; //FTP登录密码
        String remotePath = "gongdan";//FTP服务器上的相对路径
        String fileName = "模板 - 工单处理-成分-02参数优化-01方案制作 登录账号：dy_zhouqianwen.xlsx";//要下载的文件名
        String localPath = "E://test";//下载后保存到本地的路径
        downFile(url, port, username, password, remotePath, fileName, localPath);

    }
}
