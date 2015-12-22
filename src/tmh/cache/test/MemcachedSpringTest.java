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
 * Memcached������
 * a.��Ŀ��ʹ����Ҫ���η�װ������������ϸ�쳣����
 * b.memcached֧�ּ�Ⱥ���𣬵�һ����Ⱥ崻��󣬻����½����沿����һ����Ⱥ
 * c.֧�ּ���
 * ֧���첽set delete
 * 
 * ������Ҫ��װҵ��
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
        	
            // ����  
            memcachedClient.set("key", 3000, "value");  
            memcachedClient.set("key1", 3000, "value1");
            memcachedClient.set("key2", 3000, "value2");
        	//֧���첽set
//        	memcachedClient.setWithNoReply("asyncSet", 3000, "asyncSet");
            
            //�����з������
//            User user = new User();
//            user.setName("tmh");
//            user.setPassword("123456");
//            memcachedClient.set("user", 3000, user);
  
            // �滻  
//            memcachedClient.replace("key", 3000, "replace"); 
            
            //��ȡֵ
            String value = memcachedClient.get("key",3000);
            logger.info(value);
            
            //��ȡ����
//            User userCached = memcachedClient.get("user",3000);
//            logger.info(userCached.getName() + ":" + userCached.getPassword() );
  
            // �Ƴ�  
//            memcachedClient.delete("key"); 
            //�첽ɾ��
//            memcachedClient.deleteWithNoReply("key");
        	
            //֧������ͳ��
//            Counter counter = memcachedClient.getCounter("count", 0);
            //Ĭ������1
//            counter.incrementAndGet();
            //���ӷ���(�����͸���������)
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
