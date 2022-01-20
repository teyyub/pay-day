package com.payday.bank.repository;


import com.payday.bank.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Integer> {
	public Account findByUserNameAndPassword(String userId, String passwd);
	public Account findByUserName(String userId);

}
