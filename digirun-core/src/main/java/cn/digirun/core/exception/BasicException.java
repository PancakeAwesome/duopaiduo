package cn.digirun.core.exception;

import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: BasicException 
 * @Description: 全局异常
 * @author 管东海
 *  
 */
public class BasicException extends RuntimeException{

	private static final long serialVersionUID = 636227628676654995L;

	private Ret<?> ret;
	
	public BasicException(Ret<?> ret) {
		super();
		this.ret = ret;
	}

	public Ret<?> getRet() {
		return ret;
	}

}
