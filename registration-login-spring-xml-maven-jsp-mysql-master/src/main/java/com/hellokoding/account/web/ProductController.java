package com.hellokoding.account.web;

import java.io.File;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hellokoding.account.model.Product;
import com.hellokoding.account.repository.ProductRepository;
import com.hellokoding.account.service.ProductService;

@Controller
public class ProductController implements ServletContextAware {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	private ServletContext servletContext;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView getAddProductForm(ModelAndView model) {
		Product newProduct = new Product();
		model.addObject("newProduct", newProduct);
		model.setViewName("addProduct");
		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "addProduct";
		}

		Long i = productService.getAllProducts().get(productService.getAllProducts().size() - 1).getId() + 1;

		String[] supressedFields = result.getSuppressedFields();
		if (supressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(supressedFields));
		}

		MultipartFile productImage = newProduct.getImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (productImage != null && !productImage.isEmpty()) {
			try {

				String s = "C:\\Users\\sesumy\\Desktop\\LoginProjectOrjinal\\registration-login-spring-xml-maven-jsp-mysql-master\\src\\main\\webapp\\resources\\img\\";
				productImage.transferTo(new File(s + i + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		productService.addProduct(newProduct);

		return "redirect:/products";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

	@RequestMapping(value = "products/product/{id}", method = RequestMethod.GET)
	public ModelAndView getProductById(@PathVariable("id") Long id, ModelAndView model) {
		model.addObject("product", productService.getProductById(id));
		model.setViewName("product");
		return model;
	}

	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long id, ModelAndView model) {
		Product product = productService.getProductById(id);
		productRepository.delete(product);
		String s = "C:\\Users\\sesumy\\Desktop\\LoginProjectOrjinal\\registration-login-spring-xml-maven-jsp-mysql-master\\src\\main\\webapp\\resources\\img\\";
		try {
			File file = new File(s + id + ".png");
			file.delete();
		} catch (Exception e) {
			System.out.println("Hata: Resim Silinemedi.  Hata Detayı  : " + e);
		}
		return "redirect:/products";
	}

	@RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.GET)
	public ModelAndView updateProduct(@PathVariable("id") Long id, ModelAndView model, HttpSession session) {
		Product product = productRepository.findOne(id);
		session.setAttribute("id", id);
		product.setDescription(null);
		product.setName(null);
		product.setPrice(null);
		product.setStock(0);

		model.addObject("product", product);
		model.setViewName("updateProduct");
		return model;
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, ModelAndView model, HttpSession session,
			HttpServletRequest request) {
		
		Long id = (Long) session.getAttribute("id");
		Product product1 = productRepository.findOne(id);
		product1.setId(id);
		product1.setDescription(product.getDescription());
		product1.setName(product.getName());
		product1.setPrice(product.getPrice());
		product1.setStock(product.getStock());

		String s = "C:\\Users\\sesumy\\Desktop\\LoginProjectOrjinal\\registration-login-spring-xml-maven-jsp-mysql-master\\src\\main\\webapp\\resources\\img\\";
		
		//SİLME İŞLEMLERİ
		try {
			File file = new File(s + id + ".png");
			file.delete();
			System.out.println("Delete İmage");
		} catch (Exception e) {
			System.out.println("Hata: Resim Silinemedi.  Hata Detayı  : " + e);
		}

		//TEKRAR EKLEME İŞLEMLERİ
		MultipartFile productImage = product.getImage();
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(s + id + ".png"));
				System.out.println("Add İmage");
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		productRepository.save(product1);
		model.addObject("product", product);
		System.out.println("Update Product");
		return "redirect:/products";
	}
}
