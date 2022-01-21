package com.payday.bank.repository;


import com.payday.bank.domain.DumpDto;
import com.payday.bank.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * 
 * @author anar
 *
 */
public interface OrderRepository extends CrudRepository<Order,Integer> {

	List<Order> findByUserName(String userName);
	Order findByOrderId(Integer orderId);

	/*"select * from (SELECT accountid, initialquantity,symbol,completiondate,'buying' as type FROM ORDERS "
	"where completiondate>=sysdate-60 "
	"union all "
	"select accountid,quantity,symbol,completiondate,'selling' as type from sells "
	"where completiondate>=sysdate-60) bb "
	"order by bb.type,bb.completiondate asc "*/


	@Query(value = "select * from (SELECT user_name as userName, initial_quantity as initialquantity,symbol,completiondate,'buying' as type FROM ORDERS where completiondate>=sysdate-60 union all select user_name as userName,quantity as initialquantity,symbol,completiondate,'selling' as type from sells where completiondate>=sysdate-60) bb  order by bb.type,bb.completiondate asc ",nativeQuery = true)
	 List<DumpDto> report();

}
