package com.demo.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

 
public class PrimaryGenerater {
 
    private static final String SERIAL_NUMBER = "XXXXXX"; // 流水号格式
    private static PrimaryGenerater primaryGenerater = null;
 
    private PrimaryGenerater() {
    }
 
    /**
     * 取得PrimaryGenerater的单例实现
     * 
     * @return
     */
    public static PrimaryGenerater getInstance() {
        if (primaryGenerater == null) {
            synchronized (PrimaryGenerater.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new PrimaryGenerater();
                }
            }
        }
        return primaryGenerater;
    }
 
    /**
     * 生成编号
     */
    public synchronized String generaterNextNumber(String sno) {
        
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYMMdd");
        Random random = new Random();
		int x = random.nextInt(899999);
		String cont = String.valueOf(x + 100000);
         id = formatter.format(date) +sno+ cont;
       
        return id;
    }
}