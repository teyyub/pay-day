package com.payday.bank.repository;


import com.payday.bank.domain.Order;
import com.payday.bank.domain.Sell;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author David Ferreira Pinto
 *
 */
public interface SellRepository extends CrudRepository<Sell,Integer> {

//	List<Order> findByAccountId(String accountId);
//	Order findByOrderId(Integer orderId);
}
