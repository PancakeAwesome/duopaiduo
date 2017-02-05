package cn.digirun.component.order.service.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
 
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
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber(String sno) {
        
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYMMdd");
        if (StringUtils.isEmpty(sno) || sno.length() < 12) {
            id = formatter.format(date) + "000001";
        } else {
            sno = sno.substring(sno.length()-12,sno.length());
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("000000");
            id = formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(6, 12)));
        }
        return id;
    }
}