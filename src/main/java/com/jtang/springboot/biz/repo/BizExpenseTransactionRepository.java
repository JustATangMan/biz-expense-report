package com.jtang.springboot.biz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtang.springboot.biz.entities.Transaction;

import java.util.List;

@Repository
public interface BizExpenseTransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByTaxSeasonId(int id);
}
