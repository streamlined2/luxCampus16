package org.training.campus.onlineshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.training.campus.onlineshop.model.Product;
import org.training.campus.onlineshop.service.ProductService;

@Controller
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
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.remove(id);
		return "redirect:/products";
	}

	@GetMapping("/products/add")
	public String addProduct(Model model) {
		model.addAttribute(PRODUCT_ATTRIBUTE, new Product());
		return "/new-product";
	}

	@PostMapping("/newproduct")
	public String newProduct(@ModelAttribute(PRODUCT_ATTRIBUTE) Product product) {
		productService.persist(product);
		return "redirect:/products";
	}

	@GetMapping("/products/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") Long id) {
		Optional<Product> product = productService.findById(id);
		if(product.isPresent()) {
			model.addAttribute(PRODUCT_ATTRIBUTE, product.get());
			return "/modify-product";
		}
		return "/new-product";
	}

	@PostMapping("/saveproduct")
	public String saveProduct(@ModelAttribute(PRODUCT_ATTRIBUTE) Product product) {
		productService.merge(product);
		return "redirect:/products";
	}

}
