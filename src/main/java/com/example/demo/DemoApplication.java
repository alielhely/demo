package com.example.demo;

import com.example.demo.store.BankDataStore;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    static {
        BankDataStore.loadData();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ResourceConfig jerseyConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(Resource.class);
        return resourceConfig;
    }

}
