package com.szzt.iot;

import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScriptSyncTest {

    @Test
    public void syncFolderTest(){
        String url = "http://127.0.0.1:7000/process-robot-script/";
        String folder ="test/";
        File remoteFile = new File(url + folder);
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            urlfile = new URL(url+folder);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
//            bos = new BufferedOutputStream(new FileOutputStream("C:\\scripts"));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
//                bos.write(b, 0, len);
                System.out.println(new String(b));
            }
//            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
//                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
