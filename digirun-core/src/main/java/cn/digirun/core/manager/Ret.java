package cn.digirun.core.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;

/** 
 * @ClassName: Ret 
 * @Description:
 * 	全局业务返回|输出对象
 * 	<pre>
 *       code编码描述
 *       	10000：业务操作成功
 *          10001：业务操作失败
 *          -1：令牌校验失败
 *          -2：签名校验失败
 *          -3：请求body解析失败
 *          -4：参数绑定失败（非法）
 *  </pre>
 * @author 管东海
 * @param <T> 结果集泛型类型
 */
public final class Ret<T> implements Serializable {

	private static final long serialVersionUID = 8434527778909620489L;

	private String code;
	private String message;
	private T data;

	private Ret() {

	}

	/**
	 * 创建
	 * @author 管东海
	 * @param code 全局code
	 * @param message 操作提示
	 * @param data 结果集
	 * @return Ret
	 */
	public static <T> Ret<T> create(String code, String message, T data) {
		return new Ret<T>().setCode(code).setMessage(message).setData(data);
	}

	/**
	 * 业务成功
	 * @author 管东海
	 * @param data 结果集
	 * @return
	 */
	public static <T> Ret<T> success(T data) {
		return create("0", "操作成功", data);
	}

	/**
	 * 业务失败
	 * @author 管东海
	 * @param data 结果集
	 * @return
	 */
	public static <T> Ret<T> fail(T data) {
		return create("10001", "操作失败", data);
	}

	/**
	 * 业务失败
	 * @author 管东海
	 * @param message 操作提示
	 * @return
	 */
	public static <T> Ret<T> fail(String message) {
		return create("10001", message, null);
	}

	/**
	 * 业务失败
	 * @author 管东海
	 * @param message 操作提示
	 * @param data 结果集
	 * @return
	 */
	public static <T> Ret<T> fail(String message, T data) {
		return fail(data).setMessage(message);
	}

	/**
	 * 令牌校验失败
	 * @author 管东海
	 * @param message 操作提示
	 * @return
	 */
	public static <T> Ret<T> tokenFail(String message) {
		return create("-1", message, null);
	}

	/**
	 * 签名校验失效
	 * @author 管东海
	 * @param message 操作提示
	 * @return
	 */
	public static <T> Ret<T> signFail(String message) {
		return create("-2", message, null);
	}

	/**
	 * 请求body解析失败
	 * @author 管东海
	 * @param message 操作提示
	 * @return
	 */
	public static <T> Ret<T> bodyFail(String message) {
		return create("-3", message, null);
	}

	/**
	 * 参数绑定失败
	 * @author 管东海
	 * @param ex spring mvc 参数绑定异常 {@link BindException}
	 * @return
	 */
	public static Ret<List<Map<String, Object>>> bindFail(BindException ex) {
		Ret<List<Map<String, Object>>> ret = Ret.create("-4", "参数绑定失败",
				new ArrayList<Map<String, Object>>(ex.getFieldErrorCount()));

		ex.getFieldErrors().forEach(error -> {
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("field", error.getField());
			fieldMap.put("message", error.getDefaultMessage());
			ret.getData().add(fieldMap);
		});

		return ret;
	}

	public String getCode() {
		return code;
	}

	public Ret<T> setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Ret<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public Ret<T> setData(T data) {
		this.data = data;
		return this;
	}
}
