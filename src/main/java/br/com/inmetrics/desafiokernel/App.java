package br.com.inmetrics.desafiokernel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = "br.com.inmetrics.desafiokernel.controllers, br.com.inmetrics.desafiokernel.services, br.com.inmetrics.desafiokernel.aws, br.com.inmetrics.desafiokernel")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
