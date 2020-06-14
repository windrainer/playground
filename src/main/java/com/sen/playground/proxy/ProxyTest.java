package com.sen.playground.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

interface IHelloWorld {
	public void sayHello();
}

class HelloWorld implements IHelloWorld {
	public void sayHello() {
		System.out.println("Hello World!");
	}
}

class HelloWorldHandler implements InvocationHandler {
	private Object obj;
	
	public HelloWorldHandler( Object obj) {
		super();
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		Object result = null;
		doBefore();
		result = method.invoke(obj, args);
		doAfter();
		return result;
	}
	
	private void doBefore() {
		System.out.println("Do Before!");
	}
	
	private void doAfter() {
		System.out.println("Do after");
	}
	
}
public class ProxyTest {
	public static void main (String[] args) {
		IHelloWorld helloWorldObj = new HelloWorld();
		
		InvocationHandler handler = new HelloWorldHandler(helloWorldObj);
		
		IHelloWorld proxyObj = (IHelloWorld)Proxy.newProxyInstance(
				helloWorldObj.getClass().getClassLoader(),
				helloWorldObj.getClass().getInterfaces(),
				handler);
		
		proxyObj.sayHello();
		
	}
}
