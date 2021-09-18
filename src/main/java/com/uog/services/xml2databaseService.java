package com.uog.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin
@RequestMapping("/xml2database")
public class xml2databaseService {

//	    public String xml2database(@RequestParam("file") MultipartFile file) throws JsonProcessingException {
	@RequestMapping(method = RequestMethod.POST)
    public String xml2database(@RequestBody String data) throws JsonProcessingException {	    	
		JSONObject jsonObj = new JSONObject(data);
		JSONObject objOutput = new JSONObject();
		
    	try{
    		Connection con = DriverManager.getConnection("jdbc:mysql://" + jsonObj.getString("server") + "/mysql?user=" + jsonObj.getString("username") + "&password=" + jsonObj.getString("password") + "");
    		Statement stmt = con.createStatement();
    		
    		String sql = "CREATE DATABASE "+jsonObj.getString("databasename");
    		stmt.executeUpdate(sql);
    		
    		objOutput.put("spring.datasource.url", "jdbc:mysql://" + jsonObj.getString("server") + ":" + jsonObj.getString("port") + "/" + jsonObj.getString("databasename"));
    		objOutput.put("spring.datasource.username", jsonObj.getString("username"));
    		objOutput.put("spring.datasource.password", jsonObj.getString("password"));
    		objOutput.put("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
    		objOutput.put("spring.jpa.hibernate.ddl-auto", "update");
    		objOutput.put("spring.jpa.show-sql", "true");
    		objOutput.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
    		
    	}catch(Exception e) {
    		System.out.println(e);
    	}

    	return objOutput.toString();
	 }
}
