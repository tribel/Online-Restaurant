package com.tribel.jsf.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.tribel.jpa.entity.DeliverPending;
import com.tribel.jpa.entity.Orders;
import com.tribel.jpa.service.OrdersService;

@Named
@Scope("session")
public class DeliveryBean {
	private List<DeliverPending> deliverList;

	@Inject
	private OrdersService ordersService;
	private DeliverPending deliver;
	private String statusButton;
	
	public DeliveryBean() {
		deliver = new DeliverPending();
	}

	public List<DeliverPending> getDeliverList() {
		return deliverList;
	}

	public void setDeliverList(List<DeliverPending> deliverList) {
		this.deliverList = deliverList;
	}

	public String getStatusButton() {
		
		return statusButton;
	}

	public void setStatusButton(String statusButton) {
		this.statusButton = statusButton;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public DeliverPending getDeliver() {
		return deliver;
	}

	public void setDeliver(DeliverPending deliver) {
		this.deliver = deliver;
	}
	
	public void getReadyForShipmentList() {
		deliverList = ordersService.getDeliverPendingList();
	}
	
	public void getDeliveringList() {
		deliverList = ordersService.getDeliverPendingListDelivery();
	}
	
	public String refreshButtonStatus(int orderId) {
		Orders order = ordersService.find(orderId);
		String odStatus = order.getStatus();
		if (odStatus.equals("ready for shipment")) {
			statusButton = "Delivering";
		} else if (odStatus.equals("delivering")) {
			statusButton = "Delivered";
		}
		
		return statusButton;
	}
	
	public void setOrderStatus(int orderId) {
		Orders order = ordersService.find(orderId);
		String odStatus = order.getStatus();

		if (odStatus.equals("ready for shipment")) {
			ordersService.setOrderStatus(orderId, "delivering");
			getReadyForShipmentList();
		} else if (odStatus.equals("delivering")) {
			ordersService.setOrderStatus(orderId, "delivered");
			getDeliveringList();
		}
		
	}
	
	public void addAjaxMessage() {
			FacesContext.getCurrentInstance().addMessage(
					null,new FacesMessage("Status was marked as " + statusButton));
		
	}

}
