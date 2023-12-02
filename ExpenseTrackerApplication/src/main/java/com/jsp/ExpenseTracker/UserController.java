package com.jsp.ExpenseTracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.ExpenseTracker.dao.UserDao;
import com.jsp.ExpenseTracker.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserDao dao;
	
	@RequestMapping(path = "registerOperation", method = RequestMethod.POST)
	//@ResponseBody
	public ModelAndView register(@ModelAttribute User user, @RequestParam("cpassword") String cpassword) {
		ModelAndView mv=new ModelAndView();
		if(user.getPassword().equals(cpassword)) {
			User userFromDB = dao.registerUser(user);
			if(userFromDB != null) {
				mv.setViewName("instagramlogin");
				mv.addObject("msg","Registration Successful");
				return mv;
				
			}
			mv.setViewName("RegisterPage");
			mv.addObject("msg", "Please Enter Valid Credentials");
			return mv;
			
		}
		mv.setViewName("RegisterPage");
		mv.addObject("msg", "Please Enter Valid Password");
		return mv;
		
	}
	
	@PostMapping("loginOperation")
	public ModelAndView login(@RequestParam("userName") String username ,
											@RequestParam("password") String password,
											HttpServletRequest request )
	{
		User user=dao.loginUser(username, password);
		ModelAndView mv=new ModelAndView();
		if(user!=null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("userData", user);
			System.out.println("User data stored in session object");
			
			
			mv.setViewName("welcomereg");
			//mv.addObject("userInfo" , user);
			return mv;
		}
		mv.setViewName("instagramlogin");
		mv.addObject("msg","Please enter valid credentials");
		return mv;
		
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(session != null) {
			ModelAndView mv=new ModelAndView();
			mv.setViewName("instagramlogin");
			//to destroy session object
			session.invalidate();
			return mv;
		}
		return null;
	}
	
}
