package com.tribel.jpa.dao;

import java.util.List;

import com.tribel.jpa.entity.ClientReport;
import com.tribel.jpa.entity.DeliverPending;
import com.tribel.jpa.entity.Orders;

public interface OrdersDao {
	public Orders find(int id);
	
	public void save(Orders order);
	public void setOrderStatus(int orderId, String status);
	
	public List<ClientReport> getClientReport(java.sql.Timestamp start, java.sql.Timestamp end);
	public ClientReport getClientReportByCategory(java.sql.Timestamp start, java.sql.Timestamp end, 
													String category);
	
	public Long getClientReportByDishes(String category);
	
	public List<DeliverPending> getDeliverPendingList();
	public List<DeliverPending> getDeliverPendingListDelivery();
}
