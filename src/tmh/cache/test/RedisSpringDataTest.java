package tmh.cache.test;

import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import redis.clients.jedis.ShardedJedisPool;
import tmh.cache.model.User;

/**
 * 
 *   jedis-2.3.0
 */
public class RedisSpringDataTest {
	private ApplicationContext app; 
	private RedisTemplate redisTemplate;
	
	Logger logger = Logger.getLogger(MemcachedSpringTest.class);
    
    @Before  
    public void init() {  
        app = new ClassPathXmlApplicationContext("applicationContext-spring-data-redis.xml"); 
        redisTemplate = (RedisTemplate) app.getBean("redisTemplate");
    }
    
    @Test  
    public void test() { 

        User user = new User();
        user.setName("tmh");
        user.setPassword("123456");
        
        ValueOperations<String, User> valueops = redisTemplate.opsForValue();
        valueops.set(user.getName(), user);
        
        User cachedUser = valueops.get(user.getName());
        logger.info(cachedUser.getName() + cachedUser.getPassword());
	}
}
