package com.wfb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 百度地图工具类
 * @author Administrator
 *
 */
public class BaiduMapUtils {

    
    public static void main(String[] args) {
        Map<String, Double> map = BaiduMapUtils.getLngAndLat("南京市春江路");
        
        Map<String, Double> map1 = BaiduMapUtils.getLngAndLat("南京市三山街");
        
        Map<String, Double> map2 = BaiduMapUtils.getLngAndLat("南京市新街口");
        
        double distance = new BaiduMapUtils().GetShortDistance(map.get("lng"), map.get("lat"), map.get("lng"), map.get("lat"));
        System.out.println("离春江路:" + distance);
        
        double distance2 = new BaiduMapUtils().GetShortDistance(map.get("lng"), map.get("lat"), map1.get("lng"), map1.get("lat"));
        System.out.println("离三山街:" + distance2);
        
        double distance3 = new BaiduMapUtils().GetShortDistance(map.get("lng"), map.get("lat"), map2.get("lng"), map2.get("lat"));
        System.out.println("离新街口:" + distance3);
        
        double distance4 = new BaiduMapUtils().GetShortDistance(24.919386, 118.568473, 24.997263, 118.6314410000);
        System.out.println("distance : " + distance4);
        
    }
    
	/**
	 * 根据地址获取经纬度
	 * @param address
	 * @return Map<String, Double> (lng:经度 ; lat:纬度)
	 */
    public static Map<String, Double> getLngAndLat(String address) {
        Map<String, Double> map = new HashMap<String, Double>();
        String url =
            "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=KlwhsjGjH69Aw68ImAj1Hr6h";
        
        // http://api.map.baidu.com/staticimage/v2?ak=KlwhsjGjH69Aw68ImAj1Hr6h&mcode=666666&cen
        String json = loadJSON(url);
        JSONObject obj = JSONObject.parseObject(json);
        if (obj.get("status").toString().equals("0")) {
            double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
            map.put("lng", lng);
            map.put("lat", lat);
            System.out.println(address +":经度:"+lng+"---纬度："+lat);
        }
        else {
            System.out.println("未找到相匹配的经纬度！");
        }
        return map;
    }
    
    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        }
        catch (MalformedURLException e) {
        }
        catch (IOException e) {
        }
        return json.toString();
    }
    
    static double DEF_PI = 3.14159265359; // PI
    
    static double DEF_2PI = 6.28318530712; // 2*PI
    
    static double DEF_PI180 = 0.01745329252; // PI/180.0
    
    static double DEF_R = 6370693.5; // radius of earth
    
    
    /**
     * 返回两地之间的距离（按距离排序用）
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public double GetShortDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2; // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }
    
    public double GetLongDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }
    
}
