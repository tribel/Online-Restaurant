package com.tribel.jpa.service;

import java.sql.Timestamp;
import java.util.List;

import com.tribel.jpa.entity.ClientReport;
import com.tribel.jpa.entity.DeliverPending;
import com.tribel.jpa.entity.Orders;
import com.tribel.jpa.entity.Users;

public interface OrdersService {
	public Orders find(int id);
	
	public void createOrderNonAutorizeUser(Users user, Orders order);
	public void createOrderAutorizedUser(Users user, Orders order);
	public void setOrderStatus(int orderId, String status);
	
	public List<ClientReport> getClientReport(java.sql.Timestamp start, java.sql.Timestamp end);
	public ClientReport getClientReportByCategory(Timestamp start,
			Timestamp end, String category);
	
	public Long getClientReportByDishes(String cat);
	
	public List<DeliverPending> getDeliverPendingList();
	public List<DeliverPending> getDeliverPendingListDelivery();
}