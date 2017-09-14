package com.demo.service.impl;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private TransportClient client;
	
	@Override
	public GetResponse getProducts() {
		
		
		GetResponse response = client.prepareGet("productcatalog", "product", "prod2138").get(); 
		
		return response;
	}

}
