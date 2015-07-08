package com.tribel.jpa.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double price;
	private String name;
	private String category;
	private String active;
	private String kitchenMade;
	
	@OneToMany(cascade= CascadeType.PERSIST,mappedBy="dish")
	private Collection<OrderDishes> orderDishes;
	
	public Menu() {
	}

	public Menu(double price, String name, String category, String active,
			String kitchenMade) {
		this.price = price;
		this.name = name;
		this.category = category;
		this.active = active;
		this.kitchenMade = kitchenMade;
	}

	public int getId() {
		return id;
	}

	public Collection<OrderDishes> getOrderDishes() {
		return orderDishes;
	}

	public void setOrderDishes(Collection<OrderDishes> orderDishes) {
		this.orderDishes = orderDishes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getKitchenMade() {
		return kitchenMade;
	}

	public void setKitchenMade(String kitchenMade) {
		this.kitchenMade = kitchenMade;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", price=" + price + ", name=" + name
				+ ", category=" + category + ", active=" + active
				+ ", kitchenMade=" + kitchenMade + "]";
	}
	
	
}