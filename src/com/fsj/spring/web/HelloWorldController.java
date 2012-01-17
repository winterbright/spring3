package com.fsj.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * 不需要实现任何接口，也不需要继承任何的类
 */
@Controller
public class HelloWorldController {

	/**
	 * 方法都可以接受的参数(参数数量和顺序没有限制)： HttpServletRequest,HttpServletResponse,HttpSession(session必须是可用的) ,PrintWriter,Map,Model,@PathVariable(任意多个)， @RequestParam（任意多个）， @CookieValue （任意多个），@RequestHeader，Object（pojo对象） ,BindingResult等等
	 * 
	 * 返回值可以是：String(视图名)，void（用于直接response），ModelAndView，Map ，Model，任意其它任意类型的对象（默认放入model中，名称即类型的首字母改成小写），视图名默认是请求路径
	 */
	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("message", "Hello World!");
		return mav;
	}
}
