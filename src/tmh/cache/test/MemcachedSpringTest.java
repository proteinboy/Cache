package tmh.cache.test;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tmh.cache.model.User;

/**
 * Memcached测试类
 * a.项目中使用需要二次封装方法，加入详细异常处理
 * b.memcached支持集群部署，当一个集群宕机后，会重新将缓存部署到下一个集群
 * c.支持计数
 * 支持异步set delete
 * 
 * 按照需要封装业务
 */
public class MemcachedSpringTest {
	
	private ApplicationContext app;  
    private MemcachedClient memcachedClient; 
    
    Logger logger = Logger.getLogger(MemcachedSpringTest.class);
    
    @Before  
    public void init() {  
        app = new ClassPathXmlApplicationContext("applicationContext-memcached.xml"); 
        memcachedClient = (MemcachedClient) app.getBean("memcachedClient"); 
    } 
    
    @Test  
    public void test() {  
        try {  
        	
            // 设置  
            memcachedClient.set("key", 3000, "value");  
            memcachedClient.set("key1", 3000, "value1");
            memcachedClient.set("key2", 3000, "value2");
        	//支持异步set
//        	memcachedClient.setWithNoReply("asyncSet", 3000, "asyncSet");
            
            //缓存中放入对象
//            User user = new User();
//            user.setName("tmh");
//            user.setPassword("123456");
//            memcachedClient.set("user", 3000, user);
  
            // 替换  
//            memcachedClient.replace("key", 3000, "replace"); 
            
            //获取值
            String value = memcachedClient.get("key",3000);
            logger.info(value);
            
            //获取对象
//            User userCached = memcachedClient.get("user",3000);
//            logger.info(userCached.getName() + ":" + userCached.getPassword() );
  
            // 移除  
//            memcachedClient.delete("key"); 
            //异步删除
//            memcachedClient.deleteWithNoReply("key");
        	
            //支持数据统计
//            Counter counter = memcachedClient.getCounter("count", 0);
            //默认增加1
//            counter.incrementAndGet();
            //增加幅度(正数和负数都可以)
//            counter.addAndGet(-1);
            
        } catch (TimeoutException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } catch (MemcachedException e) {  
            e.printStackTrace();  
        }  
    } 
}
