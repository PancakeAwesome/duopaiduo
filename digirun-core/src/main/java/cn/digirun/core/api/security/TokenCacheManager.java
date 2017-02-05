package cn.digirun.core.api.security;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/** 
 * @ClassName: TokenCacheManager 
 * @Description: 身份令牌缓存操作类
 * @author 管东海
 *  
 */
public class TokenCacheManager {

	private static final String USER_TOKEN = "user-tokens";
	private static final String USER_TOKEN_REF = "user-token-refs";

	@CachePut(cacheNames = USER_TOKEN_REF, key = "#id")
	public String userTokenRefPut(Long id, String token) {
		return token;
	}

	@Cacheable(cacheNames = USER_TOKEN_REF, key = "#id")
	public String userTokenRefGet(Long id) {
		return null;
	}

	@CachePut(cacheNames = USER_TOKEN, key = "#response.token")
	public TokenResponse put(TokenResponse response) {
		return response;
	}

	@CacheEvict(cacheNames = USER_TOKEN, key = "#response.token")
	public void del(TokenResponse response) {

	}
	
	@CacheEvict(cacheNames = USER_TOKEN, key = "#token")
	public void del(String token) {

	}

	@Cacheable(cacheNames = USER_TOKEN, key = "#token")
	public TokenResponse get(String token) {
		return null;
	}
}
