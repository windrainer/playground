package com.sen.playground.proxy;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

public class JavapTest {
	public static void main(String[] args) throws Exception {
		
		TestBean t = new TestBean();
		t.setId(1);
		t.setName("sen");
		t.setPassword("32145");
		//get class TestBean's method and attributes
		BeanInfo info = Introspector.getBeanInfo(TestBean.class, Object.class);
		PropertyDescriptor[] props = info.getPropertyDescriptors();
		for(int i=0; i<props.length; i++) 
			System.out.println(props[i].getName() + " = " + props[i].getReadMethod().invoke(t, (Object[])null));
		System.out.println("*********************************");
		MethodDescriptor[] methods = info.getMethodDescriptors();
		for(int i=0; i<methods.length; i++) {
			System.out.println(methods[i].getName());
			
		}
		System.out.println("*********************************");
		EventSetDescriptor[] events = info.getEventSetDescriptors();
		for(int i=0; i<events.length; i++) {
			System.out.println(events[i].getListenerType().getName());
			for(int j=0; j<events[i].getListenerMethods().length; j++){
				System.out.println(events[i].getListenerMethods()[j].getName());
			}
		}
		
		
	}
}

class TestBean {
	Integer id;
	String name;
	String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}