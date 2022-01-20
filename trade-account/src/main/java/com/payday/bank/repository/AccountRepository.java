package com.payday.bank.repository;


import com.payday.bank.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Integer> {
	  Account findByUserNameAndPassword(String userId, String passwd);
	  Account findByUserName(String userId);

}
