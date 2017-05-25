package com.hellokoding.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hellokoding.account.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>  {
	
	public List<Order> findAll();
    public Order getOrderById(Long id);
    
//    @Modifying
//    @Query("UPDATE order_detail o SET o.state = ?1 WHERE o.id = ?2")
//    void setUpdateState(String state, Long id);




}
