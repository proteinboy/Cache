package tmh.cache.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import tmh.cache.service.UserService;

@Component
public class EhcacheTest{
	
	private ApplicationContext app; 
	private UserService userService;
	
	@Before  
    public void init() {  
        app = new ClassPathXmlApplicationContext("Spring-cache-anno.xml"); 
        userService = (UserService) app.getBean("userService"); 
    }
	
	@Test  
    public void test() { 
		
        // ��һ�β�ѯ��Ӧ�������ݿ�
        System.out.print("first query...");
        userService.getPwd("tmh");
        // �ڶ��β�ѯ��Ӧ�ò������ݿ⣬ֱ�ӷ��ػ����ֵ
        System.out.print("second query...");
        userService.getPwd("tmh");

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
