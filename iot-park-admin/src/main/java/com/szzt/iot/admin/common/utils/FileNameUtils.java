package com.szzt.iot.admin.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileNameUtils {
    /**
     * 获取一个文件夹下的所有文件全路径
     *
     * @param path
     * @param listFileName
     */
    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                completNames[i] = path + names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
        for (File a : files) {
            if (a.isDirectory()) {//如果文件夹下有子文件夹，获取子文件夹下的所有文件全路径。
                getAllFileName(a.getAbsolutePath() + "\\", listFileName);
            }
        }
    }

    /**
     * Excel文件的文件名拆分返回
     * @param fileName
     * @return
     */
    public static List<String> fileNameSplit(String fileName) {
        ArrayList<String> list = new ArrayList<>();

        String[] split = fileName.split("-");

        list.add(split[0]);
        list.add(split[1]);
        list.add(split[2]);
        list.add(split[3]);
        list.add(split[4]);
        list.add(split[5]);

        return list;
    }

    public static void readCSV(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                System.out.println(Arrays.toString(split));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
