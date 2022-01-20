package com.payday.bank.repository;


import java.util.List;

import com.payday.bank.domain.Order;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * @author anar
 *
 */
public interface OrderRepository extends CrudRepository<Order,Integer> {

	List<Order> findByAccountId(String accountId);
	Order findByOrderId(Integer orderId);

	"select * from (SELECT accountid, initial_quantity,symbol,completiondate,'buying' as type FROM ORDERS "
	"where completiondate>=sysdate-60 "
	"union all "
	"select accountid,quantity,symbol,completiondate,'selling' as type from sells "
	"where completiondate>=sysdate-60) bb "
	"order by bb.type,bb.completiondate asc "

}
