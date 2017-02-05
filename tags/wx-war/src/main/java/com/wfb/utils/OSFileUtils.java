package com.wfb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import payment.tools.util.GUID;

public class OSFileUtils {

	public static OSSClient os = null;

	static boolean flag = false;

	static OSFileUtils osFileUtils;

	static {
		osFileUtils = new OSFileUtils();
	}

	static {
		os = new OSSClient(Constants.ENDPOINT, Constants.ACCESSKEY_ID, Constants.ACCESSKEY_SECRET);
	}

	public static String uploadFile(String type, String picStr) throws FileNotFoundException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String rightnow = sdf.format(new Date());
		String filePath = OSFileUtils.GenerateImage(type, picStr);
		String fCover = "proj/home/upload/pic/" + rightnow + "/" + filePath;
		putObject(fCover, filePath);
		String picpath = Constants.DOWNLOADURL + fCover;
		return picpath;
	}

	/**
	 * Step-3. 上传Object
	 */
	public static void putObject(String key, String filePath) throws FileNotFoundException {
		// 获取指定文件的输入流
		File file = new File(filePath);
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		// 上传Object.
		PutObjectResult result = os.putObject(Constants.BUCKETNAME, key, content, meta);
		// 打印ETag
		if (!"".endsWith(result.getETag()) && result.getETag() != null) {
			deleteFile(filePath);
		}
	}

	/**
	 * Step-4. 列出所有Object
	 */
	public void listObjects() {
		// 获取指定bucket下的所有Object信息
		ObjectListing listing = os.listObjects(Constants.BUCKETNAME);
		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
		}
	}

	/**
	 * Step-5. 获取指定Object
	 */
	public void getObject(String key) throws IOException {
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = os.getObject(Constants.BUCKETNAME, key);
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();
		// 关闭流
		objectContent.close();
	}

	/**
	 * 获取所有buckets
	 */
	public void getbuckets() {
		List<Bucket> buckets = os.listBuckets();
		// 遍历Bucket
		System.out.println("所有buckets:");
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName() + ",buckets地址:" + os.getBucketLocation(bucket.getName()));
		}
	}

	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		// BASE64Encoder encoder = new BASE64Encoder();
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(data);// 返回Base64编码过的字节数组字符串
	}

	public static String GenerateImage(String type, String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		String picPath = "";
		if (imgStr == null) // 图像数据为空
			return picPath;
		Decoder decoder = Base64.getDecoder();
		try {
			// Base64解码
			byte[] b = decoder.decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			picPath = GUID.getTxNo() + "." + type;
			OutputStream out = new FileOutputStream(picPath);
			out.write(b);
			out.flush();
			out.close();
			return picPath;
		} catch (Exception e) {
			return picPath;
		}
	}

	// 上传完删除文件
	public static boolean deleteFile(String sPath) {

		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}