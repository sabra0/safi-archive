package com.safi.demoArchive.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safi.demoArchive.entities.Department;
import com.safi.demoArchive.entities.Order;
import com.safi.demoArchive.exception.ResourceNotFoundException;
import com.safi.demoArchive.repository.DepartmentRepository;
import com.safi.demoArchive.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	//get all orders
	@GetMapping
	public List<Order> getAllOrders(){
		return this.orderRepository.findAll();
	}
	
	//get order by id
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable (value = "id")long orderId) {
		
		return this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
	}

	// create order
	@PostMapping
	public Order createOrder (@RequestBody Order order) {
		
		List<Department>departments = new ArrayList<>();
		departments=order.getDepartment();
		this.departmentRepository.saveAll(departments);
		return this.orderRepository.save(order);
	}
	
	// update order
	@PutMapping("/{id}")
	public Order updateOrder(@RequestBody Order order,@PathVariable ("id") long orderId) {
		
		Order existingOrder = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
		existingOrder.setRegNum(order.getRegNum());
		existingOrder.setType(order.getType());
		existingOrder.setFrom(order.getFrom());
		existingOrder.setTo(order.getTo());
		existingOrder.setDate(order.getDate());
		existingOrder.setReplyDate(order.getReplyDate());
		existingOrder.setInformedDept(order.getInformedDept());
		
		existingOrder.setAttachment(order.getAttachment());
		existingOrder.setDepartment(order.getDepartment());
		existingOrder.setProcedures(order.getProcedures());
				
		return this.orderRepository.save(existingOrder);
	}
	
	//delete order by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("id") long orderId){
		
		Order existingOrder = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id :" + orderId));
	 this.orderRepository.delete(existingOrder);
	 return ResponseEntity.ok().build();
	}
	
	
	
}
