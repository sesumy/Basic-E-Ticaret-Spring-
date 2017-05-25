package com.hellokoding.account.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.model.Product;
import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.OrderRepository;
import com.hellokoding.account.repository.UserRepository;
import com.hellokoding.account.service.OrderService;
import com.hellokoding.account.service.ProductService;
import com.hellokoding.account.service.SecurityServiceImpl;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@RequestMapping(value = "/orderOperations", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		List<Order> orders = orderService.findAll();
		List<User> users = userRepository.findAll();
		List<Product> products = productService.getAllProducts();
		model.addObject("orders", orders);
		model.addObject("users", users);
		model.addObject("products", products);
		model.addObject("order", new Order());
		model.setViewName("orderOperations");

		Map<String, String> usersMap = new HashMap<String, String>();
		Map<String, String> productsMap = new HashMap<String, String>();

		for (User user : users) {
			usersMap.put(user.getId().toString(), user.getUsername().toLowerCase());
		}

		for (Product product : products) {
			productsMap.put(product.getId().toString(), product.toString());
		}

		model.addAllObjects(usersMap);
		model.addAllObjects(productsMap);

		return model;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String orderList(HttpSession session, ModelMap model) {
		model.addAttribute("date", new Date());
		model.addAttribute("order", new Order());
		logger.info("Order Listed.");
		return "orderOperations";
	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public String addOrder(@ModelAttribute Order order, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			logger.info("When add order error");
		}

		User user = userRepository.findOne(new Long(order.getUser()));
		Set<User> use = new HashSet<User>();
		use.add(user);
		order.setUsers(use);

		Product product = productService.getProductById(new Long(order.getProduct()));
		Set<Product> pro = new HashSet<Product>();
		pro.add(product);
		order.setProducts(pro);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		order.setDate(dateFormat.format(date));
		order.setState("Gönderilmedi.");

		orderService.addOrder(order);
		logger.info("{} numaralı ID'ye sahip sipariş verildi.", order.getId());
		return "redirect:/orderOperations";
	}

	@RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrder(@PathVariable("id") Integer id, @ModelAttribute Order order, BindingResult result,
			ModelAndView model, HttpServletResponse resp) throws IOException {
		orderService.deleteOrder(id.longValue());
		logger.info("{} id delete", id);
		resp.sendRedirect("/account/orderOperations");
		return null;

	}

	@RequestMapping(value = "/updateOrder/{id}", method = RequestMethod.GET)
	public String updateOrder(@PathVariable("id") Integer id, @ModelAttribute Order order, ModelAndView model,
			BindingResult result, HttpServletResponse resp) throws IOException {
		Order orderOne = orderService.getOrderById(id.longValue());
		orderOne.setState("Gönderildi.");
		orderService.addOrder(orderOne);
		logger.info("{} id update", id);
		resp.sendRedirect("/account/orderOperations");

		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//
	// @RequestMapping(value="/users")
	// private ModelMap selectTag(ModelMap model){
	//
	// }
}
