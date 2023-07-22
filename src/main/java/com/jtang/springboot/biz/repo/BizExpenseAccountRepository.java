package com.jtang.springboot.biz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtang.springboot.biz.entities.Account;

@Repository
public interface BizExpenseAccountRepository extends JpaRepository<Account, Integer> {
	
}
