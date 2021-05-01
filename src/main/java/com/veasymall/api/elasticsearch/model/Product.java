/**
 * @Description: 搜索实体类
 * @author machaoyi
 * @date 2020-08-02 08:40:21
 */
package com.veasymall.api.elasticsearch.model;

import java.math.BigDecimal;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.ToString;

/**
 * @author machaoyi
 *
 */

@Document(indexName = "product", shards = 1, replicas = 0)
@Data
@ToString
public class Product {

	private long id;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String name;

	private BigDecimal price;

	private String img;

	private String describe;
}
