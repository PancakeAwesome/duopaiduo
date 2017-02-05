package cn.digirun.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: PayController 
 * @Description: Pay API
 * @author 管东海
 *  
 */
@Controller
@RequestMapping("/web")
public class WebController {
	
	
	
	@RequestMapping("/index")
	public void index(){}

}
