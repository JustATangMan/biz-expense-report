package com.jtang.springboot.biz.repo;

import com.jtang.springboot.biz.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtang.springboot.biz.entities.Business;

import java.util.List;

@Repository
public interface BizExpenseBusinessRepository extends JpaRepository<Business, Integer> {
    List<Business> findByTaxSeasonId(int id);

}
