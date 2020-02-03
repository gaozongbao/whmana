package com.cmdi.action;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class SpringInitListener implements ApplicationListener<ContextRefreshedEvent> {
	//@Autowired
	//private TestDao testDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if(event.getApplicationContext().getParent() == null) {
			System.out.println("start init application");
		}
	}
	
}
