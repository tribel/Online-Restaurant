package com.tribel.jsf.beans;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.tribel.jpa.entity.Menu;
import com.tribel.jpa.entity.OrderDishes;
import com.tribel.jpa.entity.Orders;
import com.tribel.jpa.entity.Role;
import com.tribel.jpa.entity.Users;
import com.tribel.jpa.service.MenuService;
import com.tribel.jpa.service.OrdersService;

@Named("menuBean")
@Scope("session")
public class MenuBean {

	private List<Menu> dishes;
	private ArrayList<ArrayList<Menu>> dishesToScreen;
	private String data = "meat";
	private String tx = "meat";
	private Map<String, String> categories;
	private String activeType = "1";
	private String orderSum;

	private  int iteratorD = 0;
	
	private List<Menu> orderBasket;

	@Inject
	private MenuService menuService;

	@Inject
	private OrdersService ordersService;

	private Menu dish;
	private String category;
	private Menu dishToBasket;

	public MenuBean() {
		dish = new Menu();
		categories = new LinkedHashMap<>();
		orderBasket = new ArrayList<>();
		dishToBasket = new Menu();
	}

	public  int getIteratorD() {
		return iteratorD;
	}

	public void setIteratorD(int iteratorD) {
		this.iteratorD = iteratorD;
	}

	public ArrayList<ArrayList<Menu>> getDishesToScreen() {
		return dishesToScreen;
	}

	public void setDishesToScreen(ArrayList<ArrayList<Menu>> dishesToScreen) {
		this.dishesToScreen = dishesToScreen;
	}

	public List<Menu> getDishes() {
		return dishes;
	}

	public void setDishes(List<Menu> dishes) {
		this.dishes = dishes;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}

	public Menu getDish() {
		return dish;
	}

	public void setDish(Menu dish) {
		this.dish = dish;
	}

	public String getCategory() {
		return category;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public Menu getDishToBasket() {
		return dishToBasket;
	}

	public void setDishToBasket(Menu dishToBasket) {
		this.dishToBasket = dishToBasket;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<String, String> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTx() {
		return tx;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}

	public void getAllMenu() {
		dishes = menuService.getFoodListAll();

	}

	public void getMenuByCategory(String category) {
		dishes = menuService.getFoodListByCategory(category);
		calculateListToScreen();
		if ( (dishes.size() % 2 ) == 0 ) {
			 iteratorD  = 1;
		 } else {
			 iteratorD = 0;
		 }
		 System.out.println("iterator=" + iteratorD);
	}

	public List<Menu> getOrderBasket() {
		return orderBasket;
	}

	public void setOrderBasket(List<Menu> orderBasket) {
		this.orderBasket = orderBasket;
	}

	public void getAllCategories() {

		for (Menu m : dishes) {
			categories.put(m.getCategory(), m.getCategory());
		}
	}

	public void itemChange(ValueChangeEvent event) {
		String key = (String) event.getNewValue();
		tx = categories.get(key);
	}

	public String addDish() {
		dish = new Menu();
		return "newDish";
	}

	public String editDish(String id) {
		int idInt = Integer.valueOf(id);
		dish = menuService.findById(idInt);
		return "newDish";
	}

	public void deleteDish(String id) {
		int idInt = Integer.valueOf(id);
		menuService.deleteDish(idInt);
	}

	public String saveDish() {
		menuService.addDishToMenu(dish);
		return "menu";
	}

	public List<String> completeCategory(String query) {
		ArrayList<String> categoryList = new ArrayList<>();
		for (Map.Entry<String, String> e : categories.entrySet()) {
			categoryList.add(e.getValue());
		}
		return categoryList;
	}

	public void activeComplete() {
		if (menuService.isActive(dish.getId()) == true) {
			activeType = "1";
		} else {
			activeType = "2";
		}
	}

	public void addDishToBasket(Menu dis) {
		orderBasket.add(dis);
	}

	public void deleteDishFromBasket(Menu dis) {
		orderBasket.remove(dis);
	}

	public String getOrderSum() {
		orderSum = String.valueOf(orderBasket.stream()
				.mapToDouble(Menu::getPrice).sum());
		return orderSum;
	}

	public String createOrder(String log, String pass, String UsAddress) {
		Orders order = new Orders();
		// Users tmpuser = new Users(us.getLogin(), us.getPassword());
		Users tmpuser = new Users(log, pass);
		if (UsAddress.equals("") || UsAddress == null) {
		} else {
			tmpuser.setAddress(UsAddress);
		}
		LocalDateTime dateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(dateTime);

		ArrayList<OrderDishes> orderDishesList = new ArrayList<>();
		OrderDishes orderDishes;

		for (Menu m : orderBasket) {
			orderDishes = new OrderDishes();
			orderDishes.setDish(m);
			orderDishes.setPrice(m.getPrice());
			orderDishes.setOrder(order);
			orderDishesList.add(orderDishes);
		}

		order.setDateTime(timestamp);
		order.setStatus(null);
		order.setOrderDishes(orderDishesList);

		ordersService.createOrderAutorizedUser(tmpuser, order);
		orderBasket.clear();

		System.out.println(tmpuser.getAddress());
		return "afterMenu";
	}

	public String createOrderNonAutorize(String userAddress) {
		Orders order = new Orders();
		Users tmpUser = new Users();
		tmpUser.setName("nonAutorize");
		tmpUser.setEmail("nonAutorize@");
		tmpUser.setTelephone("nonAutorize");
		tmpUser.setLogin("nonAutorize");
		tmpUser.setPassword("nonAutorize");

		tmpUser.setAddress(userAddress);

		Role tmpRole = new Role();
		tmpRole.setId(1);
		tmpUser.setRole(tmpRole);

		LocalDateTime dateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(dateTime);

		ArrayList<OrderDishes> orderDishesList = new ArrayList<>();
		OrderDishes orderDishes;

		for (Menu m : orderBasket) {
			orderDishes = new OrderDishes();
			orderDishes.setDish(m);
			orderDishes.setPrice(m.getPrice());
			orderDishes.setOrder(order);
			orderDishesList.add(orderDishes);
		}

		order.setDateTime(timestamp);
		order.setStatus(null);
		order.setOrderDishes(orderDishesList);

		ordersService.createOrderNonAutorizeUser(tmpUser, order);
		orderBasket.clear();

		return "afterMenu";
	}

	public String setDishImage(Menu imgDish) {
		String imgName = "resources/images/" + imgDish.getName() + ".jpg";
		return imgName;
	}

	public void calculateListToScreen() {
		dishesToScreen = new ArrayList<ArrayList<Menu>>();
		int j = 0;
		for (int i = 0; j < dishes.size(); i++) {
			System.out.println("i =" + i + "j =" + j);
			dishesToScreen.add(new ArrayList<Menu>());
			dishesToScreen.get(i).add(dishes.get(j));
			if ( dishes.size() > j +1 ) {
				dishesToScreen.get(i).add(dishes.get(j + 1));
			}	
			j+=2;
		}
		
		 
	}
	
	public void iterateDish() {
		iteratorD += 2;
	}

}
