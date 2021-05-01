/**
 * @Description: TODO(描述）
 * @author machaoyi
 * @date 2020-08-02 07:56:15
 */
package com.veasymall.api.utils;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author machaoyi
 *
 */
@Component
@Slf4j
public class ElasticServiceUtils {

	private RestHighLevelClient restHighLevelClient;
	
	@PostConstruct
	private void init() {
		try {
			if(restHighLevelClient != null) {
				restHighLevelClient.close();
			}
			// 节点1和2
			HttpHost node1 = new HttpHost("127.0.0.1", 9200, "http");
			//HttpHost node2 = new HttpHost("127.0.0.1", 9200, "http");
			
			RestClientBuilder builder = RestClient.builder(node1);
            restHighLevelClient = new RestHighLevelClient(builder);
		}catch(IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
}
