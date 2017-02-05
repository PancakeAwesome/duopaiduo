package com.wfb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 生成二维码
 * @author Administrator
 */
@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
public class WaterPicUtils {

	public static String makeWaterPic(String content, String picType) throws Exception {
		int width = 400;
		int height = 400;
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        String saveDir =  servletContext.getRealPath("/upload/erweima//");
        
        SimpleDateFormat dirFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat picFormat = new SimpleDateFormat("HHmmss");
        String dirPath = saveDir + dirFormat.format(new Date());
        File file =new File(dirPath);
        if  (!file .exists()  && !file .isDirectory()){
        	file.mkdir();
        }
        String picName = picFormat.format(new Date());
        String picPath = dirPath + "/" +picName +"." + picType;
		File outputFile = new File(picPath);
		MatrixToImageWriter.writeToFile(bitMatrix, picType, outputFile);
		
		FileInputStream inputFile = new FileInputStream(outputFile);
		byte[] buffer = new byte[(int) outputFile.length()];
		inputFile.read(buffer);
		inputFile.close();
		
//		OSFileUtils.uploadFile(picType, Base64.getEncoder().encodeToString(buffer));
		
		return picPath.substring(picPath.indexOf("wfb-war")-1, picPath.length()).replace("\\", "/");
	}
	
}