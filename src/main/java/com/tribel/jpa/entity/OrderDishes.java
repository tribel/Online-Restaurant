package com.tribel.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDishes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="dishId")
	private Menu dish;
	//private int dishId;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	private Orders order;
	//private int orderId;
	private double price;
	private String status;
	
	
	public OrderDishes() {
	}
	

	public Orders getOrder() {
		return order;
	}

	public Menu getDish() {
		return dish;
	}


	public void setDish(Menu dish) {
		this.dish = dish;
	}


	public void setOrder(Orders order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

/*	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	*/

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "OrderDishes [id=" + id + ", dishId=" + dish + ", orderId="
				 + ", price=" + price + "]";
	}
	
	
}

