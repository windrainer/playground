package com.sen.playground.generic;

import java.sql.Date;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;

import javax.sql.DataSource;
public class Generic {

	public static void main(String[] args) {
		String driverClassName="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/GS";
		String username="gs";
		String password="gs";
		DataSource ds = new DriverManagerDataSource(driverClassName,url,username,password);
		Mango mango = Mango.newInstance(ds);
		System.out.println("Start inserting a new user to db.");
		long startTime = System.nanoTime();
		User user = new User();
		user.setCreateTime(new Date(System.currentTimeMillis()));
		user.setFirstname("John");
		user.setLastname("Smith");
		user.setPassword("12345");
		user.setStatus(true);
		user.setUsername("john");
		user.setGender("Male");
		UserDao userDao = mango.create(UserDao.class);
		int id = userDao.add(user);
		long timeTake = System.nanoTime() - startTime;
		System.out.println(String.format("This operation takes %d. The ID The ID created for new user is %d ", timeTake, id));
		
		System.out.println("Tear down, deleting the record just added");
		startTime = System.nanoTime();
		userDao.deleteUser(id);
		timeTake =  System.nanoTime() - startTime;
		System.out.println(String.format("Tear down process takes %d. User with ID %d was deleted. ", timeTake, id));
		
	}
	
	@DB
	interface UserDao {
		@ReturnGeneratedId
		@SQL("insert into user(firstname, middlename, lastname, username, status, create_time, gender, password)"
				+ "values(:1.firstname, :1.middlename, :1.lastname, :1.username, :1.status, :1.createTime, :1.gender, :1.password)")
		public int add(User user);
		
		
		@SQL("delete from user where userid=:1")
		public int deleteUser(int id);
	}
	

}
