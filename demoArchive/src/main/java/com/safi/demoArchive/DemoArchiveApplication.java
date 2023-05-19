package com.safi.demoArchive;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.safi.demoArchive.entities.Attachment;
import com.safi.demoArchive.entities.Department;
import com.safi.demoArchive.entities.Order;
import com.safi.demoArchive.repository.OrderRepository;

@SpringBootApplication
public class DemoArchiveApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoArchiveApplication.class, args);
		
		//OrderRepository orderRepository = context.getBean(OrderRepository.class);		
		//orderRepository.save(creaOrder());
	}
	
	private static Order creaOrder() {
		Order order = new Order();
		
		Timestamp time =new Timestamp(new Date().getTime());
		
	 
		
		order.setDate(time);
		order.setReplyDate(null);
		order.setType("sent");
		order.setTo("y.co");
		order.setFrom(null);
		order.setInformedDept("Marketing");
		order.setRegNum(500);
		order.setSummary("a new demo order");
		
		Department department1 = new Department();
		department1.setDepartmentN("Marketing");
		
		Department department2 = new Department();
		department2.setDepartmentN("It");
		
		Department department3 = new Department();
		department3.setDepartmentN("Engineering");
		
		List<Department> departments=new ArrayList<>();
		departments.add(department1);
		departments.add(department2);
		departments.add(department3);
		
		Attachment attachment1=new Attachment();
		attachment1.setPath("dfsdf/sdfs/sdf");
		//attachment1.setDepartment(department1);
		//attachment1.setOrder(order);
		
		Attachment attachment2=new Attachment();
		attachment2.setPath("sdlkfn/asld-asd/saf");
		//attachment2.setDepartment(department2);
		//attachment2.setOrder(order);
		
		List<Attachment>attachments =new ArrayList<>();
		attachments.add(attachment1);
		attachments.add(attachment2);
		
		order.setDepartment(departments);
		order.setAttachment(attachments);
		
		/*
		 * order.getAttachment().add(attachment1);
		 * order.getAttachment().add(attachment2);
		 */
		 

		return order;
	}

}
