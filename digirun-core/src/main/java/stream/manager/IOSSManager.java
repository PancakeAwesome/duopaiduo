package stream.manager;

public interface IOSSManager {
	
	/**
	 * 
	 * @param bucket  域
	 * @param key  
	 * @param path 文件上传服务器的路径
	 * @return 图片url
	 */
	public String  uploadFile(String key,String path);
		
	/**
	 * 获取一小时访问路径
	 * @param bucket
	 * @param key
	 * @return
	 */
	public String  getClientUrl(String key);
	
	/**
	 * 创建bucket
	 * @param bucket
	 */
	public void  createBucket(String bucket);
	
	/**
	 * 删除文件
	 * @param bucket
	 * @param key
	 */
	public void  deleteObject(String key);
	
	/**
	 * 下载文件到本地
	 * @param key 文件名
	 * @param file 本地文件
	 * @param style 样式名，可以为null
	 */
	public void getObject(String key, String filename, String style);
	
	/**
	 * 下载文件到本地
	 * @param key 文件名
	 * @param bucketName 
	 * @param file 本地文件
	 * @param style 样式名，可以为null
	 */
	public void getObject(String key, String bucketName , String filename, String style);
	
	
}
