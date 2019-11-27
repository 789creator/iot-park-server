package com.szzt.iot.admin.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel操作的工具类
 */
public class MyExcelUtils {

    /**
     * 导出Excel功能
     * String filename -> 文件名(每次名称可能不一样)
     * String[] heads -> 表头
     * List<String[]> datas -> 表中的数据
     * HttpServletResponse response -> 要下载一定需要这个响应对象
     *
     * @throws Exception
     */
//    HttpServletResponse response
    public static void exportExcel(String filename, String[] heads, List<String[]> datas) throws Exception {
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
//        response.setHeader("Content-disposition", "attachment;filename=" + filename);
//        response.setHeader("Pragma", "No-cache");//设置不要缓存

        //1.在内存中创建了一个excel文件
        SXSSFWorkbook wb = new SXSSFWorkbook();
        //2.创建一张表
        Sheet sheet = wb.createSheet();

        //3.创建第一行(0),设置表头
        //  3.1 创建第一行
        Row headRow = sheet.createRow(0);
        // 3.2  遍历heads,把值放到第一行的格子中
        for (int i = 0; i < heads.length; i++) {
            //3.3  创建一个格子
            Cell cell = headRow.createCell(i);
            //3.4 把值放进去
            cell.setCellValue(heads[i]);
        }

        //4.创建数据行(从第二(1)行开始）)
        // 4.1 直接遍历datas
        for (int i = 0; i < datas.size(); i++) {
            //4.2 创建行，拿到这一行的数据
            Row row = sheet.createRow(i + 1);
            String[] rowDatas = datas.get(i);
            //4.3 遍历这一行数据，把东西放到格子中
            for (int j = 0; j < rowDatas.length; j++) {
                //4.4 创建一个格子
                Cell cell = row.createCell(j);
                //4.5 把数据放到格子中
                cell.setCellValue(rowDatas[j]);
            }
        }

//        OutputStream ouputStream = response.getOutputStream();
        File xlsFile = new File("D:\\工单处理-成分-04外部干扰-01方案制作0415liuxi 468.xlsx");
        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
        wb.write(xlsStream);
//        ouputStream.flush();
//        ouputStream.close();
    }


    /**
     * 读取一个Excel文件，并且返回读取到的数据
     */
    public static List<String[]> importExcel(InputStream is) throws Exception {
        //①.创建返回的集合
        List<String[]> list = new ArrayList<>();
        //一.读取一个excel文件
        Workbook wb = WorkbookFactory.create(is);
        //二.获到到相应的表(sheet)
        Sheet sheet = wb.getSheetAt(0);
        //三.获到到这张表的行
        //3.1 知道它有多少行(获取到最后一行)
        int lastRowNum = sheet.getLastRowNum();
        //注意:不要表头，从1开始
        for (int i = 0; i <= lastRowNum; i++) {
            //3.2 拿到这一行
            Row row = sheet.getRow(i);
            //四.获取到这一行的列(格子)
            // 4.1 这一行有多少个格子
            short lastCellNum = row.getLastCellNum();
            //②.创建一个数组集合(格子多少，数组就有多大)
            String[] strings = new String[lastCellNum];
            // 4.2 拿到这个格子与它里面的数据
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                // System.out.print(cell.getStringCellValue()+" ");
                //③.把值放到数组中
                strings[j] = cell.getStringCellValue();
            }
            //④把数组放到集合中去
            list.add(strings);
        }
        return list;
    }

//    public static void iExcelMethod(String filePath, String endPath, boolean flag, int error) {
//        try {
//            FileInputStream excelIO = new FileInputStream(filePath);
//            List<String[]> strings = importExcel(excelIO);
//
//            List<String[]> result = new ArrayList<>();
//            for (int i = 0; i < strings.size(); i++) {
//                String[] newRow = new String[strings.get(i).length + 2];
//                for (int j = 0; j < strings.get(i).length; j++) {
//                    newRow[j] = strings.get(i)[j];
//                }
//
//                if (i != 0) {
//                    if (flag) {
//                        newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_SUCCESS;
//                        newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_SUCCESS;
//                    } else {
//                        if (error == 1) {
//                            newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_ERROR;
//                            newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_ERROR_1;
//                        } else if (error == 2) {
//                            newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_ERROR;
//                            newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_ERROR_2;
//                        } else if (error == 3) {
//                            newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_ERROR;
//                            newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_ERROR_3;
//                        } else if (error == 4) {
//                            newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_ERROR;
//                            newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_ERROR_4;
//                        } else if (error == 5) {
//                            newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_ERROR;
//                            newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_ERROR_5;
//                        } else if (error == 6) {
//                            newRow[newRow.length - 2] = ExcelEnum.EXCEL_RESULT_ERROR;
//                            newRow[newRow.length - 1] = ExcelEnum.EXCEL_PS_ERROR_6;
//                        }
//                    }
//                } else {
//                    newRow[newRow.length - 2] = ExcelEnum.EXCEL_TITLE_RESULT;
//                    newRow[newRow.length - 1] = ExcelEnum.EXCEL_TITLE_PS;
//                }
//                result.add(newRow);
//            }
//
//            // 创建工作薄
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            // 创建工作表
//            HSSFSheet sheet = workbook.createSheet("sheet1");
//
//            for (int row = 0; row < result.size(); row++) {
//                HSSFRow rows = sheet.createRow(row);
//                for (int col = 0; col < result.get(row).length; col++) {
//                    // 向工作表中添加数据
//                    rows.createCell(col).setCellValue(result.get(row)[col]);
//                }
//            }
//
//            File xlsFile = new File(endPath);
//            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
//            workbook.write(xlsStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
