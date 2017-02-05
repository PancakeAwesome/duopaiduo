package cn.digirun.component.item.service.util.OSS;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value="oss.config")
public class OSSConfig {
	
	public static String accesskey_id;
	
	public static String accesskey_secret;
	
	public static String endpoint;
	
	public static String bucket_name;
	
	public static String download_url;

	public static String getAccesskey_id() {
		return accesskey_id;
	}

	public static void setAccesskey_id(String accesskey_id) {
		OSSConfig.accesskey_id = accesskey_id;
	}

	public static String getAccesskey_secret() {
		return accesskey_secret;
	}

	public static void setAccesskey_secret(String accesskey_secret) {
		OSSConfig.accesskey_secret = accesskey_secret;
	}

	public static String getEndpoint() {
		return endpoint;
	}

	public static void setEndpoint(String endpoint) {
		OSSConfig.endpoint = endpoint;
	}

	public static String getBucket_name() {
		return bucket_name;
	}

	public static void setBucket_name(String bucket_name) {
		OSSConfig.bucket_name = bucket_name;
	}

	public static String getDownload_url() {
		return download_url;
	}

	public static void setDownload_url(String download_url) {
		OSSConfig.download_url = download_url;
	}
	
}
