package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	File file;
	FileInputStream input;
	Properties properties;
	
	
	public static void main(String[] args) {
for(int i=2;i<=100;i++) {
		
			
			for(int j=2;j<=i/2;j++) {
				
				if(i==j) {
					continue;
				}
				else {
					if(i/j==0) {
						System.out.println(i);
					}
					
				}
				
			}
			
		}
	}
	
	public ReadConfig() {
		
		try {
		    file=new File("./Configuration/config.properties");
			input = new FileInputStream(file);
		    properties=new Properties();
			properties.load(input);
		} catch (IOException e1) {
			// 
			e1.printStackTrace();
		}	
	}
	
	public  String readBrowser() {
		return properties.getProperty("browser");
	}
	
	public String readUrl() {
		return properties.getProperty("url");
	}
	

}
