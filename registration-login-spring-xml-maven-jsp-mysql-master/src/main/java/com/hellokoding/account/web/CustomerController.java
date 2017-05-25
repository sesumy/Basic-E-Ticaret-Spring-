package com.hellokoding.account.web;


import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellokoding.account.model.Address;
import com.hellokoding.account.model.Order;
import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.UserRepository;
import com.hellokoding.account.service.OrderService;
import com.hellokoding.account.service.ProductService;
import com.hellokoding.account.service.SecurityService;
import com.hellokoding.account.service.UserService;

@Controller
public class CustomerController {	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	

	@RequestMapping(value = "/customerOperations", method = RequestMethod.GET)
    public ModelAndView customerOperations(HttpSession session,ModelAndView  model) {
		List<User> userList = userRepository.findAll();
        model.setViewName("customerOperations");
        model.addObject("userList", userList);
        model.addObject("customerSearch", new User());
        
        return model; 
    }
		
	@RequestMapping(value = "/updateCustomers/{id}", method = RequestMethod.GET)
	  public ModelAndView updateCustomers(@PathVariable("id") Long id,ModelAndView model,HttpSession session){
	    User user = userRepository.findOne(id);
	    session.setAttribute("id",id);
	    user.setPassword(null);
	    user.setPasswordConfirm(null);
	    model.addObject("user",user);
	    model.setViewName("registerUpdate");
	  
	    return model;
	}
	@RequestMapping(value = "/updateCustomers", method = RequestMethod.POST)
	  public ModelAndView updateCustomerss(@ModelAttribute("user") User user,ModelAndView model,HttpSession session){
		Long id =(Long) session.getAttribute("id");
		User userUp= userRepository.findOne(id);
		userUp.setPassword(user.getPassword());
		userUp.setRoles(user.getRoles());
		userUp.setUsername(user.getUsername());
			Address address = new Address();
			address.setId(user.getAddress().getId());
			address.setBuilding_no(user.getAddress().getBuilding_no());
			address.setCity(user.getAddress().getCity());
			address.setDoor_no(user.getAddress().getDoor_no());
			address.setDistrict(user.getAddress().getDistrict());
			address.setNeighborhood(user.getAddress().getNeighborhood());
		userUp.setAddress(address);
		
        userService.save(userUp);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

	    model.addObject("user",user);
	    model.setViewName("registerUpdate");
	    return model;
	}
	@RequestMapping(value = "/deleteCustomers/{id}", method = RequestMethod.GET)
	  public String deleteCustomers(@PathVariable("id") Long id,ModelAndView model){
	    User user = userRepository.findOne(id);
	    userRepository.delete(user);
	    return "redirect:/customerOperations";
	}	

	
	
	@RequestMapping(value = "/searchOperations", method = RequestMethod.GET)
	  public ModelAndView searchCustomer(ModelAndView model){
	    model.setViewName("searchOperations");
	    model.addObject("customerSearch", new User());
	    model.addObject("productSearch", new Product());
	    model.addObject("orderSearch", new Order());
	    model.addObject("search", new User());
	    
	    return model;
	}	
	
	@RequestMapping(value = "/searchOp{username}", method = RequestMethod.GET)
	  public ModelAndView searchOp(@PathVariable("username") String username ,@ModelAttribute("search") User search, ModelAndView model){
		if(isNumeric(search.getUsername())){
			model.addObject("productSearch", productService.getProductById(Long.valueOf(search.getUsername()).longValue()));
		    model.addObject("orderSearch", orderService.getOrderById(Long.valueOf(search.getUsername()).longValue()));	
		    model.addObject("customerSearch");
		}
		else{
        model.addObject("customerSearch", userService.findByUsername(search.getUsername()));
        model.addObject("productSearch");
        model.addObject("orderSearch");
		}
		
        model.setViewName("searchOperations");
	    model.addObject("search", new User());
        return model;
	}	
	
	public static boolean isNumeric(String str)
	{
		if(str.toCharArray()==null){
			return false;
		}
	    for (char c : str.toCharArray())
	    {
	    	if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}
