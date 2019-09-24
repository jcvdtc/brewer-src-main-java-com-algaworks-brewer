package com.algaworks.brewer.config;

import javax.cache.Caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//códigos dos caracteres => http://www.utf8-chartable.de/
@Configuration
@EnableCaching
//habilitando chamadas assíncronas
@EnableAsync
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public CacheManager cacheManager() throws Exception {
		return new JCacheCacheManager(Caching.getCachingProvider().getCacheManager(
				getClass().getResource("/env/ehcache.xml").toURI(), 
				getClass().getClassLoader())); 
	}
}
