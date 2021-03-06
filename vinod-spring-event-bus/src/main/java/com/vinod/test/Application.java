package com.vinod.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	@Autowired
	private EventBus eventBus;
	@Autowired
	private CustomerReceiver customerReceiver;
	@Autowired
	private CustomerPublisher customerPublisher;
	@Autowired
	private AdminReceiver adminReceiver;

	public void run(String... args) throws Exception {
		eventBus.on($("customer"), customerReceiver);
		customerPublisher.publishCustomerDetails();
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);

	}

}