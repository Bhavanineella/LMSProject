package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminUsersDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminServiceImplementation;
import com.capgemini.librarymanagementsystemjdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminUserServiceImplementation;
import com.capgemini.librarymanagementsystemjdbc.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc.service.UsersServiceImplementation;

public class LibraryFactory {
	
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImplementation();
	}
	public static UsersDAO getUsersDAO() {
		return new UsersDAOImplementation();
	}
	public static AdminUserDAO getAdminUserDAO() {
		return new AdminUsersDAOImplementation();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImplementation();
	}
	public static UsersService getUsersService() {
		return new UsersServiceImplementation();
	}
	public static AdminUserService getAdminUserService() {
		return new AdminUserServiceImplementation();
	}
	
}
