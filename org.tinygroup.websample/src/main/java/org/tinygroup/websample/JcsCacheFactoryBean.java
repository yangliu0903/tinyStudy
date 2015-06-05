package org.tinygroup.websample;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.tinygroup.cache.Cache;
import org.tinygroup.cache.CacheManager;
import org.tinygroup.cache.jcs.JcsCacheManager;

public class JcsCacheFactoryBean implements FactoryBean, InitializingBean {

	private String cacheRegion;

	private Cache cache;
	private CacheManager cacheManager = JcsCacheManager.getInstance();

	public Object getObject() throws Exception {
		return cache;
	}

	public Class getObjectType() {
		return Cache.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		if (cache == null) {
			cache = cacheManager.createCache(cacheRegion);
		}
	}

	public String getCacheRegion() {
		return cacheRegion;
	}

	public void setCacheRegion(String cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

}
