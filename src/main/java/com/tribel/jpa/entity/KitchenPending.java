package com.tribel.jpa.entity;

import java.sql.Timestamp;

public class KitchenPending {
	private String name;
	private java.sql.Timestamp time;
	private int orderDishId;

	public KitchenPending() {
	}

	public KitchenPending(String name, Timestamp time, int orderDishId) {
		this.name = name;
		this.time = time;
		this.orderDishId = orderDishId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Timestamp getTime() {
		return time;
	}

	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}

	public int getOrderDishId() {
		return orderDishId;
	}

	public void setOrderDishId(int orderDishId) {
		this.orderDishId = orderDishId;
	}

	@Override
	public String toString() {
		return "KitchenPending [name=" + name + ", time=" + time
				+ ", orderDishId=" + orderDishId + "]";
	}

}
