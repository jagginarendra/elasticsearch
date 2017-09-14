package com.demo.controller;

import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.ProductService;

@RestController
public class DemoController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/hello")
	public GetResponse getProduct(){
		
		return productService.getProducts();
		
	}
}
