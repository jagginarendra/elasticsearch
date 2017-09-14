package com.demo.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

	
	private String clusterNodes;

	private TransportClient transportClient;
	private PreBuiltTransportClient preBuiltTransportClient;

	@Override
	public void destroy() throws Exception {
		try {
			logger.info("Closing elasticSearch client");
			if (transportClient != null) {
				transportClient.close();
			}
		} catch (final Exception e) {
			logger.error("Error closing ElasticSearch client: ", e);
		}
	}

	@Override
	public TransportClient getObject() throws Exception {
		return transportClient;
	}

	@Override
	public Class<TransportClient> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.clusterNodes="localhost:9300";
		buildClient();
	}

	protected void buildClient() {
		try {
			preBuiltTransportClient = new PreBuiltTransportClient(settings());

			String InetSocket[] = clusterNodes.split(":");
			String address = InetSocket[0];
			Integer port = Integer.valueOf(InetSocket[1]);
			transportClient = preBuiltTransportClient
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));

		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		}
	}

	private Settings settings() {
		// Settings settings = Settings.builder().put("cluster.name",
		// clusterName).put("client.transport.sniff", true).build();
		Settings settings = Settings.builder().put("cluster.name", "productcatalog").build();
		return settings;
	}
}
