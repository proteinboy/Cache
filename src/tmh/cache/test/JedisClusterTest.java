package tmh.cache.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;
import sun.util.logging.resources.logging;
import tmh.cache.model.User;

/**
 * Jedis2.7.0
 */
public class JedisClusterTest {
	private ApplicationContext app; 
	private JedisCluster jedisCluster;
	
	Logger logger = Logger.getLogger(JedisClusterTest.class);
    
    @Before  
    public void init() {  
        app = new ClassPathXmlApplicationContext("applicationContext-redis.xml"); 
        jedisCluster = (JedisCluster) app.getBean("jedisCluster"); 
    }
	
	@Test  
    public void test() { 
		//setһ��key�Ĺ���ʱ�� expire ��λ��
//		jedisCluster.set("tmh", "value");
//		jedisCluster.expire("tmh", 15);
		
		
		//set(String ,String)
//		jedisCluster.set("tmh", "value");
//		String str = jedisCluster.get("tmh");
//		logger.info(str);
		
		//set get ���� ����תjson
//		User user = new User();
//		user.setName("tmh");
//		user.setPassword("123456");
//		setObject("tmh", user);
//		User cacheUser = new User();
//		cacheUser = (User) getObject("tmh",User.class);
//		System.out.println(cacheUser.getName() + cacheUser.getPassword());
		
		
		
		//map
//		 String key = "mostUsedLanguages";
//		 //Adding a value with score to the set
//		 jedisCluster.zadd(key,100,"Java");//ZADD
//		 //We could add more than one value in one calling
//		 Map<String, Double> scoreMembers = new HashMap<String, Double>();
//		 scoreMembers.put("Python", 1d);
//		 scoreMembers.put( "Javascript", 2d);
//		 scoreMembers.put( "php", 3d);
//		 jedisCluster.zadd(key, scoreMembers);
//		 //Ԫ�ص�ֵ
//		 System.out.println("Ԫ�ص�ֵ��" + jedisCluster.zscore(key, "php"));
//		 //�ܹ��ж���Ԫ��
//		 System.out.println("�ܹ��ж���Ԫ�أ�" + jedisCluster.zcard(key));//ZCARD
		 
		 //set����
//		 jedisCluster.sadd("key", "value");
//		 jedisCluster.sadd("key", "value1");
//		 jedisCluster.sadd("key", "value2");
//		 System.out.println("set������������" + jedisCluster.scard("key"));
		 //�ж�ֵ֪����set��
//		 System.out.println(jedisCluster.sismember("key", "value")); 
		 //��ȡset
//		 Set<String> set = jedisCluster.smembers("key");
//		 for (String str : set) {  
//		      System.out.println(str);  
//		 }  
		
		 //set���� �洢����
//		 User user = new User();
//		 user.setName("tmh");
//		 user.setPassword("123456");
//		 jedisCluster.sadd("key", objToJsonStr(user));
//		 user.setName("tmh1");
//		 user.setPassword("1234567");
//		 jedisCluster.sadd("key", objToJsonStr(user));
//		 System.out.println("set������������" + jedisCluster.scard("key"));
//		 //�ж�ֵ֪����set��
//		 System.out.println(jedisCluster.sismember("key", objToJsonStr(user))); 
//		 //��ȡset
//		 Set<String> set = jedisCluster.smembers("key");
//		 for (String str : set) {  
//		      System.out.println(str); 
//		      User cacheUser = (User) jsonStrToObject(str, User.class);
//		      System.out.println(cacheUser.getName() + cacheUser.getPassword());
//		 }  
		 
		 //list����
//		 jedisCluster.lpush("keyList", "valueList");
//		 jedisCluster.lpush("keyList", "valueList1");
//		 jedisCluster.lpush("keyList", "valueList");
//		 List<String> list = jedisCluster.lrange("keyList", 0, 3);
//		 for (String str : list) {  
//		      System.out.println(str); 
//		 }
		 
	}
	
	/**
	 * set����
	 * @param key
	 * @param object
	 */
	public void setObject(String key,Object object){
		JSONObject jsonObject = new JSONObject().fromObject(object);
		jedisCluster.set(key, jsonObject.toString());
	}
	
	
	/**
	 * get����
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> Object getObject(String key,Class<T> clazz){
		String value = jedisCluster.get(key);
		return jsonStrToObject(value, clazz);
	}
	
	/**
	 * strת����json����
	 * @param <T>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public <T> Object jsonStrToObject(String jsonStr,Class<T> clazz){
		JSONObject jsonObject = new JSONObject().fromObject(jsonStr);
		Object object = JSONObject.toBean(jsonObject,clazz);
		return object;
	}
	
	/**
	 * ����ת����jsonStr
	 * @param obj
	 * @return
	 */
	public String objToJsonStr(Object obj){
		JSONObject jsonObject = new JSONObject().fromObject(obj);
		return jsonObject.toString();
	}
	
}
