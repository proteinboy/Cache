package tmh.cache.test;


import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis测试类
 *
 */
public class ShardedJedisTest {
	
	private ApplicationContext app; 
	private ShardedJedisPool shardedJedisPool;
	
	Logger logger = Logger.getLogger(MemcachedSpringTest.class);
    
    @Before  
    public void init() {  
        app = new ClassPathXmlApplicationContext("applicationContext-redis.xml"); 
        shardedJedisPool = (ShardedJedisPool) app.getBean("shardedJedisPool"); 
    }
	
	@Test  
    public void test() { 
		
		//set  get
		set("test_key", "test_value");
		String str = get("test_key", "123");
		logger.info(str);
		
		//set集合中
//		addSet("key", "value");
//		long len = countSet("key");
//		logger.info(len);
//		Set<String> set = getSet("key");
//		for (String str : set) {  
//			logger.info(str);
//		}  
		
	}
	
	 /** 
     * 添加到Set中 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean addSet(String key, String... value) {  
        if(key == null || value == null){  
            return false;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.sadd(key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("setList error.", ex);  
            returnBrokenResource(shardedJedis);  
        } finally {  
            returnResource(shardedJedis);  
        }  
        return false;  
    }  
	
	/** 
     * 获取Set 
     * @param key 
     * @return 
     */  
    public  Set<String> getSet(String key){  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.smembers(key);  
        } catch (Exception ex) {  
            logger.error("getList error.", ex);  
            returnBrokenResource(shardedJedis);  
        } finally {  
            returnResource(shardedJedis);  
        }  
        return null;  
    }  
	
	/** 
     * 检查Set长度 
     * @param key 
     * @return 
     */  
    public long countSet(String key){  
        if(key == null ){  
            return 0;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.scard(key);  
        } catch (Exception ex) {  
            logger.error("countSet error.", ex);  
            returnBrokenResource(shardedJedis);  
        } finally {  
            returnResource(shardedJedis);  
        }  
        return 0;  
    }  
    
    public boolean set(String key, String value) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.set(key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("set error.", ex);  
            returnBrokenResource(shardedJedis);  
        } finally {  
            returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    public String get(String key, String defaultValue) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.get(key) == null?defaultValue:shardedJedis.get(key);  
        } catch (Exception ex) {  
            logger.error("get error.", ex);  
            returnBrokenResource(shardedJedis);  
        } finally {  
            returnResource(shardedJedis);  
        }  
        return defaultValue;  
    }  
  
    public boolean del(String key) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.del(key);  
            return true;  
        } catch (Exception ex) {  
            logger.error("del error.", ex);  
            returnBrokenResource(shardedJedis);  
        } finally {  
            returnResource(shardedJedis);  
        }  
        return false;  
    }  
    
    private void returnBrokenResource(ShardedJedis shardedJedis) {  
        try {  
            shardedJedisPool.returnBrokenResource(shardedJedis);  
        } catch (Exception e) {  
            logger.error("returnBrokenResource error.", e);  
        }  
    }  
  
    private void returnResource(ShardedJedis shardedJedis) {  
        try {  
            shardedJedisPool.returnResource(shardedJedis);  
        } catch (Exception e) {  
            logger.error("returnResource error.", e);  
        }  
    }  
	
}
