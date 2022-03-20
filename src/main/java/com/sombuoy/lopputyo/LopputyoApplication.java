package com.sombuoy.lopputyo;

import com.sombuoy.lopputyo.controllers.AppRestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LopputyoApplication 
{

	public static void main(String[] args)  
	{
		SpringApplication.run(LopputyoApplication.class, args);
		System.out.println("Hohoi");

		AppRestController a = new AppRestController();					//Luodaan uusi olio datan lukemista tiedostosta varten. 
		a.readDataFromFile();
	}

}
