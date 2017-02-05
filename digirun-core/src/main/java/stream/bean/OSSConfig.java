package stream.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ossConfig")  
public class OSSConfig {
	private String endpoint ;
	private String internalEndpoint ;
	private String accessKeyId ;
	private String accessKeySecret ;
	private String bucket;
	private String imgProcessEndPoint;
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getInternalEndpoint() {
		return internalEndpoint;
	}
	public void setInternalEndpoint(String internalEndpoint) {
		this.internalEndpoint = internalEndpoint;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getImgProcessEndPoint() {
		return imgProcessEndPoint;
	}
	public void setImgProcessEndPoint(String imgProcessEndPoint) {
		this.imgProcessEndPoint = imgProcessEndPoint;
	}
	
}
