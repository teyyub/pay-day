package com.payday.bank.repository;


import java.util.List;

import com.payday.bank.domain.Order;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * @author David Ferreira Pinto
 *
 */
public interface OrderRepository extends CrudRepository<Order,Integer> {

	List<Order> findByAccountId(String accountId);
}
