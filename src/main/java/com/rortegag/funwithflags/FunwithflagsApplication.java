package com.rortegag.funwithflags;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rortegag.funwithflags.service.storage.StorageService;

@SpringBootApplication
public class FunwithflagsApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(FunwithflagsApplication.class, args);
	}

	/**
	 * This bean is started when the application is launched. It allows us to initialize the storage
	 * secondary of the project
	 * 
	 * @param storageService Secondary project storage
	 * @return
	*/
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
    
}
