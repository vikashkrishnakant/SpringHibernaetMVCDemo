package com;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.sql.DataSource;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan("com")
@EnableWebMvc
@PropertySource("classpath:config.properties")

public class SpringConfiguration extends WebMvcConfigurerAdapter{

	   @Autowired
	   private Environment env;
	   
	   @Autowired
	   private  TransportClient elasticSearchTC;
	   
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh");
		
		return resolver;
		
	}
	
    @Bean
	public TransportClient getElasticClient() {
        try {
        	String elasticHost1="localhost";
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			elasticSearchTC = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName(elasticHost1),9300));
			System.out.println(elasticSearchTC.connectedNodes());
			System.out.println("Hashcode at config class:"+elasticSearchTC.hashCode());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return elasticSearchTC;
    }
	
}
