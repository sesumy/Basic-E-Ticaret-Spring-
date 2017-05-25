package com.hellokoding.account.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellokoding.account.model.Order;
import com.hellokoding.account.repository.OrderRepository;



@Service
public class OrderServiceImpl implements OrderService{

		@Autowired
		OrderRepository orderRepository;
	
	
	@Override
	public void addOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.delete(id);
		
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.getOrderById(id);
	}

//	@Override
//	public void setUpdateState(String state, Long userId) {
//		 orderRepository.setUpdateState(state,userId);
//		
//	}

}