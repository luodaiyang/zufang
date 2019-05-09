package com.xust.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 15:14 2019/5/8
 * @modified By:
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {

        InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName("47.107.237.19"),
                9300
        );

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }

}

/*
    @Bean
    public TransportClient esClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
//                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();

        InetSocketTransportAddress master = new InetSocketTransportAddress(
//                InetAddress.getByName(esHost), esPort
          InetAddress.getByName("47.107.237.19"), 9300
        );

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(master);

        return client;
    }
}*/
