package id.jagokoding;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo2 {
	
	@Qualifier("myBean")
	@Bean
	public BeanA bean1() {
		return new BeanA("bean1");
	}
	
	@Bean
	public BeanA bean2() {
		return new BeanA("bean2");
	}
	
	@Bean
	public BeanB beanB(@Qualifier("myBean") BeanA bean) {
		return new BeanB(bean);
	}
	
    public static void main(String[] strings) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Demo2.class);
        BeanB beanB = context.getBean(BeanB.class);
    }
    
    class BeanA {
    	
    	private String hello;
    	
    	public BeanA(String hello) {
    		this.hello = hello;
    	}
    	
    	@PostConstruct
    	public void init() {
    		System.out.println("method init beanA");
    	}
    	
    	public void sayHello() {
    		System.out.println("Hello, " + hello);
    	}
    }
    
    class BeanB {
    	
    	public BeanB(BeanA bean) {
    		System.out.println("Konstruktor beanB");
    		bean.sayHello();
    	}
    	
    	@PostConstruct
    	public void init() {
    		System.out.println("method init beanB");
    	}
    }
}