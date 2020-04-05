package com.cloud.cloudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.cloudapp.entity.Message;
import com.cloud.cloudapp.entity.Users;
import com.cloud.cloudapp.service.UserService;
import com.cloud.cloudapp.service.EmailService;

import java.util.Iterator;
@Controller
public class LoginController 
{
	@Autowired
	private UserService userService;

    @Autowired
    EmailService emailService;
    
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
			String[] strArray2 = {"A","B","C","AXX","BXX","CXX","CAA","aaaaaaaaaaaaaaaax","BccccccccccXXAA","CXXAvvvvvvvvvvvAA","cxzcxzaA","sdassadasB","Cerw432","AXrew321X","BXe12X","Cewq231XX","C43t43AA","A23ewqXXAA","432ewBXXAA","3ewd213CXXAAA"}; 
			for (int i = 0; i < strArray2.length; i++) {
				Users nUs = new Users();
				nUs.setActive(1);
				nUs.setName("smiesznymail@invalid.com"+strArray2[i]);
				nUs.setLastName("smiesznymaxxxxxil@invalid.com"+strArray2[i]);
				nUs.setPassword("hehehehehe");
				nUs.setProvince("wielkopolska");
				nUs.setEmail("xzzzzzz@invalid.com"+strArray2[i]);
				userService.saveUser(nUs);
			}
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
		Users user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		if(user.getType().equalsIgnoreCase("ADMIN")) {
			modelAndView.setViewName("adminPageView");
			modelAndView.addObject("message", new Message());
		} else {
			modelAndView.setViewName("userPageView");
		}
		
		modelAndView.addObject("user", user);
//		modelAndView.setViewName("home");

		return modelAndView;
	}
	
	

	@ResponseBody
	@RequestMapping(value={"/sendAlertToUser"}, method = RequestMethod.POST)
	public void sendAlerToUser(@RequestBody String data, HttpServletRequest request) 
	{
		JSONObject jsonObject = new JSONObject();
		try {
				
			 jsonObject = new JSONObject(data);
			 String province = (String) jsonObject.get("province");
			 String message = (String) jsonObject.get("messageContent");
				List<Users> users = userService.findByProvince(province);
				Iterator iterator = users.iterator();
				while(iterator.hasNext()) {
					Users tempUser = (Users) iterator.next();
					if(!tempUser.getLastNotificationForProvince().equalsIgnoreCase(message)) {
						tempUser.setLastNotificationForProvince(message);
			            emailService.sendSimpleMessage(tempUser.getEmail(),"ALERT",message);
			            userService.saveOnlyUser(tempUser);
					}
				}
		} catch(Exception e) {
			
		}
	}

//	@PostMapping("/sendAlertToUser")
//	public ModelAndView sendAlerToUser(Message message, BindingResult bindingResult) 
//	{
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Users> users = userService.findByProvince(message.getProvince());
//		Iterator iterator = users.iterator();
//		while(iterator.hasNext()) {
//			Users tempUser = (Users) iterator.next();
//			tempUser.setLastNotificationForProvince(message.getNotification());
//            emailService.sendSimpleMessage(tempUser.getEmail(),"ALERT",message.getNotification());
//            userService.saveOnlyUser(tempUser);
//		}
//		ModelAndView modelAndView = new ModelAndView();
//
//		modelAndView.setViewName("adminPageView");
//		return modelAndView;
//	}
}