package com.mysql_mvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mobile {

	@Id // for primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // to auto increment primary key
	int id;

	// @Column(name="mobile_name") used to change column name if you want
	@Column(nullable = false)
	String name;

	@Column(nullable = false, unique = true)
	String price;

	public Mobile() {
	}

	public Mobile(int id, String name, String price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}