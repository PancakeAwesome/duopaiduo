package cn.digirun.core.api.exception;

import cn.digirun.core.exception.BasicException;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: ApiException 
 * @Description: api基础异常
 * @author 管东海
 *  
 */
public class ApiException extends BasicException{

	private static final long serialVersionUID = 8413691549187410918L;
	
	public ApiException(Ret<?> ret) {
		super(ret);
	}

}
