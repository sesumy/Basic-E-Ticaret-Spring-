package com.hellokoding.account.service;

import java.util.List;

import com.hellokoding.account.model.Order;

public interface OrderService {

	void addOrder(Order order);
	void deleteOrder(Long id);
	List<Order> findAll();
    Order getOrderById(Long id);
//    void setUpdateState(String state, Long userId);
}
