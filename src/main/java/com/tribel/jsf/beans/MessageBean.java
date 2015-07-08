package com.tribel.jsf.beans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class MessageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final String nameRequired = "Dish name field could not be empty!";
	private final String priceRequired = "Dish price could not be empty!";
	private final String kitchenRequired = "Dish kitchenRequired status could not be empty!";
	private final String activeRequired = "Dish active status could not be empty!";
	private final String priceRange = "Dish price must be minimum 1.0";
	private final String userNameRequired = "Name field could not be empty!";
	private final String userEmailRequired = "Email field could not be empty!";
	private final String userAddressRequired = "Address field could not be empty!";
	private final String userLoginRequired = "Login field could not be empty!";
	private final String userPasswordRequired = "Password field could not be empty!";
	private final String userPasswordRange = "Password very short!";
	private final String userTelephoneRange = "Please input correct number!";
	
	public String getUserTelephoneRange() {
		return userTelephoneRange;
	}

	public String getUserEmailRequired() {
		return userEmailRequired;
	}

	public String getUserAddressRequired() {
		return userAddressRequired;
	}

	public String getUserLoginRequired() {
		return userLoginRequired;
	}

	public String getUserPasswordRequired() {
		return userPasswordRequired;
	}

	public String getUserPasswordRange() {
		return userPasswordRange;
	}

	public String getUserNameRequired() {
		return userNameRequired;
	}

	public String getPriceRange() {
		return priceRange;
	}


	public String getKitchenRequired() {
		return kitchenRequired;
	}


	public String getActiveRequired() {
		return activeRequired;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNameRequired() {
		return nameRequired;
	}


	public String getPriceRequired() {
		return priceRequired;
	}

	
	
}
