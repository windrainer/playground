package com.sen.playground.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Reflect {
	public static void main(String[] args)  {
		
		try {
			System.out.println("Input class path");
			Scanner scanner = new Scanner(System.in);
			String classInfo = scanner.nextLine();
			Class clazz = Class.forName(classInfo);
			Method[] methods = clazz.getDeclaredMethods();
			System.out.println("Methods in Class are: ");
			for(Method method : methods) {
				System.out.println(method.getName());
			}
			
			System.out.println("Fileds in Class are: ");
			Field[] fields=clazz.getDeclaredFields();//利用得到的Class对象的自审，返回属性对象集合  
	        for(Field field : fields){ //遍历该类属性的集合  
	           System.out.println(field.getName());//打印属性信息  
	        }  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Student {
	private Integer id;
	private String name;
	
	Student() {}
	Student(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

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
	
}