package com.szzt.iot.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.MalformedURLException;


public class FtpUtils {
    /**
     * ftp服务器地址
     */
    public String hostname = "175.6.40.73";
    /**
     * ftp服务器端口号默认为21
     */
    public Integer port = 21 ;
    /**
     * ftp登录账号
     */
    public String username = "vsftpd";
    /**
     * ftp登录密码
     */
    public String password = "123!@#";

    public FTPClient ftpClient = null;

    public FtpUtils() {
    }

    public FtpUtils(String hostname, Integer port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        //设置系统环境
        FTPClientConfig conf = new FTPClientConfig( FTPClientConfig.SYST_NT);
        ftpClient.configure(conf);
        try {
            System.out.println("connecting...ftp服务器:"+this.hostname+":"+this.port);
            //连接ftp服务器
            ftpClient.connect(hostname, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp服务器:"+this.hostname+":"+this.port);
                return;
            }

            System.out.println("connect success...ftp服务器:"+this.hostname+":"+this.port);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     *  @param originFileName 待上传文件的名称（绝对地址） *
     * @return
     */
    public boolean uploadFile( String pathname, String fileName,String originFileName){
        boolean flag = false;
        InputStream inputStream = null;
        try{
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originFileName));
            initFtpClient();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        }catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
    /**
     * 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public boolean uploadFile(String pathname, String fileName, InputStream inputStream){
        boolean flag = false;
        try{
            System.out.println("开始上传文件");
            initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        }catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 改变目录路径
     * @param directory
     * @return
     */
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
     * @param remote
     * @return
     * @throws IOException
     */
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param path
     * @return
     * @throws IOException
     */
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建目录
     * @param dir
     * @return
     */
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /** * 下载文件 *
     * @param pathname FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localPath 下载后的文件路径 *
     * @return */
    public  boolean downloadFile(String pathname, String filename, String localPath){
        boolean flag = false;
        OutputStream os=null;
        try {
            System.out.println("开始下载文件");
            System.out.println("pathname="+pathname);
            System.out.println("filename"+filename);
            System.out.println("localPath"+localPath);
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localPath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /** * 删除文件 *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return */
    public boolean deleteFile(String pathname, String filename){
        boolean flag = false;
        try {
            System.out.println("开始删除文件");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        FtpUtils ftp =new FtpUtils("10.108.218.245",21,"test","test");
        //ftp.uploadFile("/download", "test.txt", "E://test.txt");
        ftp.downloadFile("test\\gongdan", "test.txt", "E://test");
        //ftp.existFile("/download/test.")
        //ftp.deleteFile("/download", "test.xlsx");
        System.out.println("ok");
    }
}
