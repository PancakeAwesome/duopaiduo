package cn.digirun.core.api;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.digirun.core.exception.BasicException;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: BasicApi 
 * @Description: 基础Api接口
 * @author 管东海
 *  
 */
public class BasicApi {
	
	/**
	 * 异常事件拦截
	 * @author 管东海
	 * @param ex {@link BasicException}
	 * @return
	 */
	@ExceptionHandler(BasicException.class)
	public Ret<?> basicExceptionHandler(BasicException ex) {
		return ex.getRet();
	}

	/**
	 * 异常事件拦截
	 * @author 管东海
	 * @param ex  {@link BindException}
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public Ret<?> bindExceptionHandler(BindException ex) {
		return Ret.bindFail(ex);
	}
}
