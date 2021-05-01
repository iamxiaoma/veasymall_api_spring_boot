/**
 * @Description: TODO(描述）
 * @author machaoyi
 * @date 2020-08-02 09:01:56
 */
package com.veasymall.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.elasticsearch.model.Product;
import com.veasymall.api.elasticsearch.repository.ProductRepository;
import com.veasymall.api.pojo.Result;

/**
 * @author machaoyi
 *
 */

@RestController
@RequestMapping("es")
public class ElasticsearchController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	

	
	@RequestMapping("insert")
	public Result insert() {
		

		Product product = new Product();
		
		product.setId(1);
		product.setImg("商品图片");
		product.setName("小米手机");
		product.setDescribe("小米6");
		product.setPrice(new BigDecimal("5999.00"));
		
		
		productRepository.save(product);

		return Result.ok();

	}

}
