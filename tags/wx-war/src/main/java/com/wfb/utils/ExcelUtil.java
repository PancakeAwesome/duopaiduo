package com.wfb.utils;

import java.io.File;  
import java.io.IOException;  
  

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
import jxl.write.Label;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;  
import jxl.write.biff.RowsExceededException;

public class ExcelUtil {
	 /** 
     * @param args 
     */  
//    public static void main(String[] args) {  
//        String fileName = "d:" + File.separator + "12.xls";  
//        List<String> datas = ExcelUtil.readExcel(fileName);
//        int i=0;
//        for (String s : datas) {
//        	System.out.println(s);
//        	String[] str =s.split(",");
//        	System.out.println(str[0]);
//        	System.out.println(str[1]);
//        	System.out.println(str[2]);
//        	/*System.out.println("第"+i+"行数据:");
//			for (String string : list) {
//				System.out.println(string);
//			}*/
//		}
//        String fileName1 = "d:" + File.separator + "2.xls";  
//        ExcelUtil.writeExcel(fileName1);  
//    }  
  
    /** 
     * 從excel文件中讀取所有的內容 
     *  
     * @param file 
     *            excel文件 
     * @return excel文件的內容 
     */  
    public static List<String> readExcel(String fileName) {  
    	System.out.println("fileName:"+fileName);
    	File f=new File(fileName);
    	if(!f.exists()){
    		System.out.println("文件"+fileName +"不存在");
    		return null;
    		
    	}
    	
    	
		
		
        StringBuffer sb = new StringBuffer();  
        Workbook wb = null;  
        try {  
            // 构造Workbook（工作薄）对象  
        	 wb = Workbook.getWorkbook(f);  
           
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        if (wb == null)  
            return null;  
  
        // 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了  
        Sheet[] sheet = wb.getSheets();  
        List<String> datas = new ArrayList<String>();
        String rows="";
        if (sheet != null && sheet.length > 0) {  
            // 对每个工作表进行循环  
            for (int i = 0; i < sheet.length; i++) {
            	
            	
                // 得到当前工作表的行数  
                int rowNum = sheet[i].getRows();  
                for (int j = 0; j < rowNum; j++) {  
                	if(j==0){ //第一行是标题
                		continue;
                	}
                	rows="";
                    // 得到当前行的所有单元格  
                    Cell[] cells = sheet[i].getRow(j);  
                    if (cells != null && cells.length > 0) {  
                    	
                    	
                        // 对每个单元格进行循环  
                        for (int k = 0; k < cells.length; k++) {  
                            // 读取当前单元格的值  
                            String cellValue = cells[k].getContents();  
                            sb.append(cellValue + "\t"); 
                            rows+=cellValue+",";
                            
                        }  
                    } 
                    datas.add(rows.substring(0,rows.length()-1));
                }  
            }  
        }  
        // 最后关闭资源，释放内存  
        wb.close();  
        return datas;  
    }  
  
    /** 
     * 把內容寫入excel文件中 
     *  
     * @param fileName 
     *            要寫入的文件的名稱 
     */  
    public static void writeExcel(String fileName) {  
        WritableWorkbook wwb = null;  
        try {  
            // 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象  
            wwb = Workbook.createWorkbook(new File(fileName));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        if (wwb != null) {  
            // 创建一个可写入的工作表  
            // Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置  
            WritableSheet ws = wwb.createSheet("sheet1", 0);  
  
            // 下面开始添加单元格  
            for (int i = 0; i < 10; i++) {  
                for (int j = 0; j < 5; j++) {  
                    // 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行  
                    Label labelC = new Label(j, i, "这是第" + (i + 1) + "行，第"  
                            + (j + 1) + "列");  
                    try {  
                        // 将生成的单元格添加到工作表中  
                        ws.addCell(labelC);  
                    } catch (RowsExceededException e) {  
                        e.printStackTrace();  
                    } catch (WriteException e) {  
                        e.printStackTrace();  
                    }  
  
                }  
            }  
  
            try {  
                // 从内存中写入文件中  
                wwb.write();  
                // 关闭资源，释放内存  
                wwb.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } catch (WriteException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
