package com.cloud.cloudapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.cloud.cloudapp.entity.Users;
import com.cloud.cloudapp.service.UserService;

@Controller
public class LoginController 
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error){
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) 
        {
          modelAndView.setViewName("error page");
        } 
        else
        {
        	modelAndView.setViewName("login");
        }

        return modelAndView;
    }
	
	@RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
	  }
	
	@GetMapping("/registration")
	public ModelAndView showRegistrationPage() 
	{
		ModelAndView modelAndView = new ModelAndView();
		Users users = new Users();
		modelAndView.addObject("user", users);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping("/registration")
	public ModelAndView createUser(@Valid Users users, BindingResult bindingResult) 
	{
		System.out.println(bindingResult);
		ModelAndView modelAndView = new ModelAndView();
		Users userExists = userService.findUserByEmail(users.getEmail());
		
		if(userExists != null) 
		{
			bindingResult
				.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		
		if(bindingResult.hasErrors()) 
		{
			modelAndView.setViewName("registration");
		}
		else 
		{
			userService.saveUser(users);
			modelAndView.addObject("successMessage", "User has been registered succesfully");
			modelAndView.addObject("user", new Users());
			modelAndView.setViewName("registration");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/home")
	public ModelAndView home() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users users = userService.findUserByEmail(auth.getName());
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("user", users);
		modelAndView.setViewName("home");

		return modelAndView;
	}
	
}