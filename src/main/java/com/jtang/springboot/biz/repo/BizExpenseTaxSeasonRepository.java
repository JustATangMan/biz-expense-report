package com.jtang.springboot.biz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtang.springboot.biz.entities.TaxSeason;

import java.util.List;

@Repository
public interface BizExpenseTaxSeasonRepository extends JpaRepository<TaxSeason, Integer> {
	public List<TaxSeason> findByTaxSeasonId(int id);
}
