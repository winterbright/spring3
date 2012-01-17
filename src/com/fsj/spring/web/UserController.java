package com.fsj.spring.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.Constants;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private IUserService userService;
	private IDeptService deptService;
	
	public IDeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam String name,@RequestParam String password,Model model,HttpServletRequest request) throws Exception{
		TUser user1 = userService.getUserByName(name);
		if(user1 == null) {
			model.addAttribute("message", "用户不存在");
			return "login";
		}else if(password == null || !password.equals(user1.getPassword()) ){
			model.addAttribute("message", "密码错误");
			return "login";
		}else {
			request.getSession().setAttribute(Constants.USER_INFO_SESSION, user1);
			System.out.println("aa");
			return "welcome";
		}
	}
	
	@RequestMapping(value="/login1",method=RequestMethod.POST)
	public String login1(TUser user,HttpServletRequest request,Model model) throws Exception{
		TUser user1 = userService.getUserByName(user.getName());
		if(user1 == null) {
			model.addAttribute("message", "用户不存在");
			return "login";
		}else if(user.getPassword() == null || !user.getPassword().equals(user1.getPassword()) ){
			model.addAttribute("message", "密码错误");
			return "login";
		}else {
			request.getSession().setAttribute(Constants.USER_INFO_SESSION, user1);
			return "welcome";
		}
	}
	
	@RequestMapping(value="/list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		List<TUser> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		List<TDept> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);
		if(StringUtils.isNotBlank(request.getParameter("resMess")) && StringUtils.isNotBlank(request.getParameter("opeMess"))) {
			model.addAttribute("message",setOperateMessage(request.getParameter("resMess"),request.getParameter("opeMess"),"用户"));
		}
		return "user/list";
	}

	private String setOperateMessage(String resMess,String opeMess,String modMess) {
		//TODO 以后可以和写日志结合在一起
		String ope = "";
		String res = "";
		if(Constants.OPERATE_TYPE_ADD.equals(opeMess)) {
			ope = "增加";
		}else if(Constants.OPERATE_TYPE_UPDATE.equals(opeMess)) {
			ope = "更新";
		}else if(Constants.OPERATE_TYPE_DELETE.equals(opeMess)) {
			ope = "删除";
		}
		
		if(Constants.RESULT_SUCCESS.equals(resMess)) {
			res = "成功";
		}else if(Constants.RESULT_FAILED.equals(resMess)) {
			res = "失败";
		}
		return ope + modMess + res;
	}
	
	/*
	 * 同样的请求路径 user/add 如果是get请求就转到增加页面去，如果是post请求就做add操作
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String toAdd(Model model) throws Exception{
		List<TDept> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);
		return "user/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String doAdd(TUser user,Model model) throws Exception{
		try {
			userService.addUser(user);
			model.addAttribute("resMess", Constants.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("resMess", Constants.RESULT_FAILED);
			throw e;
		}
		model.addAttribute("opeMess", Constants.OPERATE_TYPE_ADD);
		
		//重定向，防止重复提交，当然这样不能完全解决重复提交的问题，只是简单处理一下，若要较好的防止重复提交可以结合token做，
		//以“/”开关，相对于当前项目根路径，不以“/”开关，相对于当前路径
		//return "redirect:/user/list"; 
		return "redirect:list"; 
	}
	
	/*
	 * Restful模式路径：
	 * 注意这里/update/{id}和@PathVariable("id")中id要一致，这样不管用debug模式还是release模式编译都没问题
	 * 也可以简写成@PathVariable int id，但这样只能以debug模式编译的时候正确，如果用release编译就不正确了，因为如果用release模式编译会把参数的名字改变的
	 * 一般IDE工具都是以debug模式编译的，javac是以release模式编译的
	 * 同样的请求路径 user/update 如果是get请求就转到增加页面去，如果是post请求就做update操作
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id") int id, Model model) throws Exception{
		model.addAttribute("user",userService.getUserById(id));
		model.addAttribute("deptList", deptService.getDeptList());
		return "user/update";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String doUpdate(@PathVariable("id") int id, TUser user,Model model) throws Exception{
		try {
			userService.updateUser(user);
			model.addAttribute("resMess", Constants.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("resMess", Constants.RESULT_FAILED);
			throw e;
		}
		model.addAttribute("opeMess", Constants.OPERATE_TYPE_UPDATE);
		//return "redirect:../list"; 
		//重定向，防止重复提交，以“/”开关，相对于当前项目根路径，不以“/”开关，相对于当前路径
		return "redirect:/user/list"; 
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") int id,Model model)throws Exception{
		try {
			userService.deleteUser(id);
			model.addAttribute("resMess", Constants.RESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("resMess", Constants.RESULT_FAILED);
			throw e;
		}
		model.addAttribute("opeMess", Constants.OPERATE_TYPE_DELETE);
		return "redirect:/user/list";//重定向
	}
}
