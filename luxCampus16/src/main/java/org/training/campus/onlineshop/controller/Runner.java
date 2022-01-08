package org.training.campus.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.campus.onlineshop.model.Product;
import org.training.campus.onlineshop.service.ProductService;

@Controller
@RequestMapping("/shop")
public class Runner {

	private static final String PRODUCTS_ATTRIBUTE = "products";
	private static final String PRODUCT_ATTRIBUTE = "product";

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute(PRODUCTS_ATTRIBUTE, productService.getAll());
		return "/product-list";
	}

	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") String id) {
		productService.remove(Long.parseUnsignedLong(id));
		return "redirect:/products";
	}

	@GetMapping("/products/add")
	public String addProduct(Model model) {
		model.addAttribute(PRODUCT_ATTRIBUTE, new Product());
		return "/new-product";
	}

	@PostMapping("/newproduct")
	public String newProduct(@ModelAttribute Product product) {
		productService.persist(product);
		return "redirect:/products";
	}

	@GetMapping("/products/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") String id) {
		model.addAttribute(PRODUCT_ATTRIBUTE, productService.findById(Long.parseUnsignedLong(id)));
		return "/modify-product";
	}

	@PostMapping("/saveproduct")
	public String saveProduct(@ModelAttribute Product product) {
		productService.merge(product);
		return "redirect:/products";
	}

}
