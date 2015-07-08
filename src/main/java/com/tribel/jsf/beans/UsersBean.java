package com.tribel.jsf.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.tribel.jpa.entity.Role;
import com.tribel.jpa.entity.Users;
import com.tribel.jpa.service.UsersService;

@Named
@Scope("session")
public class UsersBean {

	private List<Users> users;
	private List<Users> userToORder;
	private ArrayList<String> roleList = new ArrayList<String>();;
	private String userRole;
	private Users userToBanner;
	private String roleToBanner;

	@Inject
	private UsersService usersService;
	private Users user;
	private Date date = new Date();

	private String login;
	private String password;
	private String address;
	private String nameUs;
	private int usRole;
	private String flag;
	private String newStaffRole;
	private int userId;

	public UsersBean() {
		user = new Users();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNewStaffRole() {
		return newStaffRole;
	}

	public void setNewStaffRole(String newStaffRole) {
		this.newStaffRole = newStaffRole;
	}

	public String getRoleToBanner() {
		return roleToBanner;
	}

	public void setRoleToBanner(String roleToBanner) {
		this.roleToBanner = roleToBanner;
	}

	public Users getUserToBanner() {
		return userToBanner;
	}

	public void setUserToBanner(Users userToBanner) {
		this.userToBanner = userToBanner;
	}

	public String getNameUs() {
		return nameUs;
	}

	public void setNameUs(String nameUs) {
		this.nameUs = nameUs;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAddress() {
		address = user.getAddress();
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogin() {
		return login;
	}

	public int getUsRole() {
		return usRole;
	}

	public void setUsRole(int usRole) {
		this.usRole = usRole;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void userToOrderListSet() {
		userToORder = new ArrayList<Users>();
		userToORder.add(user);
	}

	public void refreshList() {
		users = usersService.getStaffList();
	}

	public List<String> refreshRoleName(String query) {
		ArrayList<String> tmp = new ArrayList<String>();
		List<Role> list = usersService.getAllRoles();
		for (Role r : list) {
			tmp.add(r.getName());
		}
		roleList = tmp;
		return tmp;
	}

	public ArrayList<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(ArrayList<String> roleList) {
		this.roleList = roleList;
	}

	public List<Users> getUsers() {
		return users;
	}

	public List<Users> getUserToORder() {
		return userToORder;
	}

	public void setUserToORder(List<Users> userToORder) {
		this.userToORder = userToORder;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		user.setBirthday(new java.sql.Date(date.getTime()));
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String newUser() {
		user = new Users();
		return "newUser";
	}

	public String registrationUser() {
		List<String> tmp = usersService.getLosginRequest();
		if (tmp.contains(user.getLogin())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"This login is already used"));
			return "";
		}
		usersService.registration(user);
		login = user.getLogin();
		password = user.getPassword();
		address = user.getAddress();
		nameUs = user.getName();

		return "customerMenu";

	}
	
	public String createStaff() {
		int  tmpRole= 0;
		if ( newStaffRole.equals("administrator")) {
			tmpRole = 2;
		} else if(newStaffRole.equals("rightsManager")) {
			tmpRole = 3;
		} else if(newStaffRole.equals("cook")) {
			tmpRole = 4;
		} else if(newStaffRole.equals("businessAnalytic")) {
			tmpRole = 5;
		} else if(newStaffRole.equals("deliveryGuy")) {
			tmpRole = 6;
		}
		user.setName("default");
		user.setAddress("default");
		user.setEmail("default");
		user.setTelephone("default");
		usersService.createStaffAccount(user, tmpRole);
		return "rightsManager";
	}

	public void validateLogin() {
		List<String> list = usersService.getLosginRequest();
		if (list.contains(user.getLogin())) {

		} else {
			flag = "This login already exist";
		}
	}

	public String saveUser() {
		int  tmpRole= 1;
		if ( user.getRole().getName().equals("administrator")) {
			tmpRole = 2;
		} else if(user.getRole().getName().equals("rightsManager")) {
			tmpRole = 3;
		} else if(user.getRole().getName().equals("cook")) {
			tmpRole = 4;
		} else if(user.getRole().getName().equals("businessAnalytic")) {
			tmpRole = 5;
		} else if(user.getRole().getName().equals("deliveryGuy")) {
			tmpRole = 6;
		}
		
		usersService.editUser(user);
		usersService.editStaffRights(user.getId(), tmpRole);
		if(tmpRole != 3) {
			return "customerMenu";
		}
		return "rightsManager";
	}

	public String signIn() {
		user = new Users();
		return "logIn";
	}

	public String autorize() {
		try {
		Users tmpUsers = usersService.autorize(user);
		userToBanner = tmpUsers;
		login = user.getLogin();
		password = user.getPassword();
		address = user.getAddress();
		usRole = tmpUsers.getRole().getId();
		nameUs = tmpUsers.getName();
		userId = tmpUsers.getId();

		String tmpRole = "";
		switch (tmpUsers.getRole().getId()) {
		case 1:
			tmpRole = "customerMenu";
			break;
		case 2:
			tmpRole = "menu";
			break;
		case 3:
			tmpRole = "rightsManager";
			break;
		case 4:
			tmpRole = "cookList";
			break;
		case 5:
			tmpRole = "analytic";
			break;
		case 6:
			tmpRole = "deliveryList";
			break;
		}
		return tmpRole;
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Login or password is wrong"));
			return "";
		}
			
		
		
		
		
	}

	public void addAjaxMessage() {
		if (user.getName() == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Your email/user ID or password is incorrect."));
		}
	}

	public String editUserS(String userId) {
		int intID = Integer.valueOf(userId);
		user = usersService.findById(intID);
		return "editUser";
	}
	
	public String editUser(int userId) {
		user = usersService.findById(userId);
		return "editProfile";
	}

	public void setLoginAddress() {
		this.login = user.getAddress();
	}

	public String logOut() {
		user.setLogin(null);
		user.setPassword(null);
		login = null;
		password = null;
		usRole = -1;

		return "logIn";
	}

	public String refreshButton() {
		if (user.getLogin() != null || login != null) {
			return "LogOut";
		} else {
			return "SignIn";
		}
	}

	public void backToLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("logIn.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String banner() {
		String tmpRole = "menu";
		switch (userToBanner.getRole().getId()) {
		case 1:
			tmpRole = "customerMenu";
			break;
		case 2:
			tmpRole = "menu";
			break;
		case 3:
			tmpRole = "rightsManager";
			break;
		case 4:
			tmpRole = "cookList";
			break;
		case 5:
			tmpRole = "analytic";
			break;
		case 6:
			tmpRole = "deliveryList";
			break;
		}
		roleToBanner = tmpRole;
		return tmpRole;
	}
	
}
