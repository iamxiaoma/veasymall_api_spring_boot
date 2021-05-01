/**
 * @Description: TODO(描述）
 * @author machaoyi
 * @date 2020-08-02 08:44:20
 */
package com.veasymall.api.elasticsearch.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.github.pagehelper.Page;
import com.veasymall.api.elasticsearch.model.Product;

/**
 * @author machaoyi
 *
 */
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {

	/**
	 * 通过名字查询
	 * 
	 * @param name
	 * @return
	 */
	List<Product> findByName(String name);

	/**
	 * 通过名字分页查询
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<Product> findByName(String name, Pageable pageable);

	/**
	 * 通过名字排序查询
	 * 
	 * @param name
	 * @param sort
	 * @return
	 */
	List<Product> findByName(String name, Sort sort);

}
