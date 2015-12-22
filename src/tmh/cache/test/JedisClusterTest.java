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
		//set一个key的过期时间 expire 单位秒
//		jedisCluster.set("tmh", "value");
//		jedisCluster.expire("tmh", 15);
		
		
		//set(String ,String)
//		jedisCluster.set("tmh", "value");
//		String str = jedisCluster.get("tmh");
//		logger.info(str);
		
		//set get 对象 对象转json
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
//		 //元素的值
//		 System.out.println("元素的值：" + jedisCluster.zscore(key, "php"));
//		 //总共有多少元素
//		 System.out.println("总共有多少元素：" + jedisCluster.zcard(key));//ZCARD
		 
		 //set集合
//		 jedisCluster.sadd("key", "value");
//		 jedisCluster.sadd("key", "value1");
//		 jedisCluster.sadd("key", "value2");
//		 System.out.println("set集合总数量：" + jedisCluster.scard("key"));
		 //判断值知否在set中
//		 System.out.println(jedisCluster.sismember("key", "value")); 
		 //获取set
//		 Set<String> set = jedisCluster.smembers("key");
//		 for (String str : set) {  
//		      System.out.println(str);  
//		 }  
		
		 //set集合 存储对象
//		 User user = new User();
//		 user.setName("tmh");
//		 user.setPassword("123456");
//		 jedisCluster.sadd("key", objToJsonStr(user));
//		 user.setName("tmh1");
//		 user.setPassword("1234567");
//		 jedisCluster.sadd("key", objToJsonStr(user));
//		 System.out.println("set集合总数量：" + jedisCluster.scard("key"));
//		 //判断值知否在set中
//		 System.out.println(jedisCluster.sismember("key", objToJsonStr(user))); 
//		 //获取set
//		 Set<String> set = jedisCluster.smembers("key");
//		 for (String str : set) {  
//		      System.out.println(str); 
//		      User cacheUser = (User) jsonStrToObject(str, User.class);
//		      System.out.println(cacheUser.getName() + cacheUser.getPassword());
//		 }  
		 
		 //list集合
//		 jedisCluster.lpush("keyList", "valueList");
//		 jedisCluster.lpush("keyList", "valueList1");
//		 jedisCluster.lpush("keyList", "valueList");
//		 List<String> list = jedisCluster.lrange("keyList", 0, 3);
//		 for (String str : list) {  
//		      System.out.println(str); 
//		 }
		 
	}
	
	/**
	 * set对象
	 * @param key
	 * @param object
	 */
	public void setObject(String key,Object object){
		JSONObject jsonObject = new JSONObject().fromObject(object);
		jedisCluster.set(key, jsonObject.toString());
	}
	
	
	/**
	 * get对象
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> Object getObject(String key,Class<T> clazz){
		String value = jedisCluster.get(key);
		return jsonStrToObject(value, clazz);
	}
	
	/**
	 * str转换成json对象
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
	 * 对象转换成jsonStr
	 * @param obj
	 * @return
	 */
	public String objToJsonStr(Object obj){
		JSONObject jsonObject = new JSONObject().fromObject(obj);
		return jsonObject.toString();
	}
	
}
