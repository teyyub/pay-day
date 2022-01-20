package com.example.notification.repository;


import com.example.notification.domain.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author anar
 *
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {

//	List<Order> findByAccountId(String accountId);
//	Order findByOrderId(Integer orderId);


}
