package com.me.spring.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.dao.ProductDAO;
import com.me.spring.dao.UserDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.Product;
import com.me.spring.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
/*	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	 */
	

	@RequestMapping(value="/login.htm")
	public ModelAndView register(HttpServletRequest request)
	{
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		 User user=null;
		 UserDAO userDAO = new UserDAO();
		 
		// System.out.println("-------------------------hello---------------------------");
		 
		 user = userDAO.loginCheck(userName, password);
		 if(user==null)
		 {
			 return new ModelAndView("invalidLogin");
		 }
		 
		  String role=user.getRole();
		  String view=user.getView();
		  
		 System.out.println("register.ht, :"+ view);
		  if(userName.equals(user.getUserName()) && password.equals(user.getUserPassword()))
				  {
			        HttpSession requestSession=request.getSession(true);
			        requestSession.setAttribute("userAccount",user);
			        
			        
			  return new ModelAndView(view,"userAccount",user);
				  }
		// if not validated then
		    else
			  return new ModelAndView("invalidLogin"); 
		}
	
	@RequestMapping(value="/invalid.htm")
	public ModelAndView invalidLogin(HttpServletRequest request){
		return new ModelAndView("invalidLogin");
	}
	
	
	
	@RequestMapping(value="/register.htm")
	public ModelAndView signIn(HttpServletRequest request)
	{
		
		
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("password");
		String role=request.getParameter("registerRole");
		String userFname=request.getParameter("firstName");
		String userLname=request.getParameter("lastName");
		String userEmailId=request.getParameter("emailID");
		String userAddress=request.getParameter("userAddress");
		
		User user = new User(userName, userPassword);
		UserDAO userDAO = new UserDAO();
		
		//Setting UserAccount
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setRole(role);
		user.setUserFname(userFname);
		user.setUserLname(userLname);
		user.setUserEmailId(userEmailId);
		user.setUserAddress(userAddress);
          
		if(role.equals("Admin"))
		{
			user.setView("/AdminHomePage");
	    }
		else
			if(role.equals("Customer"))
			{
				user.setView("/CustomerHomePage");
			}
			else
				if(role.equals("Supplier"))
				{
					user.setView("/InventoryManagement");
				}
		
		userDAO.save(user);
		
		HttpSession session=request.getSession();
		session.setAttribute("userAccount", user);
		
		return new ModelAndView(user.getView(),"userAccount",user);
	}
	
	
	
	@RequestMapping(value="/logout.htm")
	public ModelAndView logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.removeAttribute("userAccount");
		session.invalidate();
		return new ModelAndView("login");
	}
	
	
}
