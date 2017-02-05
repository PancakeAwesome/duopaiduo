package cn.digirun.frame.payment.wxpay.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import cn.digirun.frame.payment.core.constant.Thirtypart;
import cn.digirun.frame.payment.wxpay.exception.ResErrorException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressWarnings({ "rawtypes", "unused", "unchecked", "deprecation"})
public class CommonUtil {

	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonObject;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */

	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}

	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getSign(Map<String, String> map, String key) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + key;
		// Util.log("Sign Before MD5:" + result);
		result = MD5.MD5Encode(result).toUpperCase();
		// Util.log("Sign Result:" + result);
		return result;
	}

	public static String mapToXML(Map map) {
		Set set = map.keySet();
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (Iterator it = set.iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = map.get(key);
			if (null == value) {
				value = "";
			}
			/*
			 * if (value.getClass().getName().equals("java.util.ArrayList")) {
			 * ArrayList list = (ArrayList) map.get(key); sb.append("<" + key +
			 * ">").append(value).append("</" + key + ">"); } else {
			 */
			if (value instanceof HashMap) {
				sb.append("<" + key + ">");
				sb.append("</" + key + ">");
			} else {
				sb.append("<" + key + ">" + value + "</" + key + ">");
			}
			// }
		}
		sb.append("</xml>");
		return sb.toString();
	}

	public static String getCodeUrlFromRes(String resXml) {
		Document dd = null;
		String code_url = null;
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			return "";
		}
		if (dd != null) {
			Element root = dd.getRootElement();
			if (root == null) {
				return "";
			}
			Element codeUrl = root.element("code_url");
			if (codeUrl == null) {
				return "";
			}
			code_url = codeUrl.getText(); // 解析 xml 获得 code_url
		}
		makeWaterPic(code_url, "png");
		return code_url;
	}
	
	public static Map getResFromXml(String resXml) {
		Document dd = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			throw new ResErrorException("数据解析异常").setThirtypart(Thirtypart.WxpayJS).setBizCode(001); 
		}
		if (dd != null) {
			Element root = dd.getRootElement();
			List<Element> eles = root.elements();
			for(Element ele : eles){
				map.put(ele.getName(), ele.getStringValue());
			}
		}
		return map;
	}
	
	public static String makeWaterPic(String content, String picType) {
		int width = 400;
		int height = 400;
		
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		String classPath = Class.class.getClass().getResource("/").getPath();
		String filePath = classPath.replace("target", "src").replace("classes", "main") + "/resources/static/pay/";
		String qrImg = "";
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			/*WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	        ServletContext servletContext = webApplicationContext.getServletContext(); */
	        String saveDir =  filePath/*servletContext.getRealPath("/upload/erweima//")*/;
	        
	        SimpleDateFormat dirFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        /*SimpleDateFormat picFormat = new SimpleDateFormat("HHmmss");
	        String dirPath = saveDir + dirFormat.format(new Date());
	        File file =new File(dirPath);
	        if  (!file .exists()  && !file .isDirectory()){
	        	file.mkdir();
	        }
	        String picName = picFormat.format(new Date());*/
	        String picName = dirFormat.format(new Date());
	        String picPath = saveDir + "/" + picName +"." + picType;
			File outputFile = new File(picPath);
			MatrixToImageWriter.writeToFile(bitMatrix, picType, outputFile);
			
			FileInputStream inputFile = new FileInputStream(outputFile);
			byte[] buffer = new byte[(int) outputFile.length()];
			inputFile.read(buffer);
			inputFile.close();
//			result = picPath.substring(picPath.indexOf("wfb-war")-1, picPath.length()).replace("\\", "/");
			String result = picPath.replace("\\", "/");
			System.out.println(result);
			qrImg = "http://localhost:9090/pay/" + picName +"." + picType;
			// 二维码URL
			System.out.println("http://localhost:9090/pay/" + picName +"." + picType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		OSFileUtils.uploadFile(picType, Base64.getEncoder().encodeToString(buffer));
		
		return qrImg;
	}
	
	// 二维码 流
	public static void encodeQrcode(String content, HttpServletResponse response) {
		if (StringUtils.isBlank(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "png", response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static final int BLACK = 0xFF000000;
	
	private static final int WHITE = 0xFFFFFFFF;
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

}