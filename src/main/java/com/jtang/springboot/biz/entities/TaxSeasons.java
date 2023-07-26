package com.jtang.springboot.biz.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tax_seasons")
public class TaxSeasons { //create another table to store the "identifier" (tax season)
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tax_season_id")
	private int taxSeasonId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="year")
	private int year;
	
	@Column(name="description")
	private String description;

	public int getTaxSeasonId() {
		return taxSeasonId;
	}

	public void setTaxSeasonId(int taxSeasonId) {
		this.taxSeasonId = taxSeasonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TaxSeason | Id: " + taxSeasonId + ", Name: " + name + ", Year: " + year + ", Description: " + description;
	}
}
