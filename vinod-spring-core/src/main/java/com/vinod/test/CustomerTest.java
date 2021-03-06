package com.vinod.test;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerTest {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MySpringConifg.class);
		context.refresh();
		CustomerDetails cd = context.getBean(CustomerDetails.class);
		System.out.println(cd.getCustomerDetails().getCustomer());
	}

}
