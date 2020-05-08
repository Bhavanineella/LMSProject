package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAOImplementation;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAOImplemetation;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminServiceImplementation;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserServiceImplementation;
import com.capgemini.librarymanagementsystemhibernate.service.UsersService;
import com.capgemini.librarymanagementsystemhibernate.service.UsersServiceImplementation;

public class LibraryFactory {
	
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImplementation();
	}
	public static UserDAO getUserDAO() {
		return new UserDAOImplementation();
	}
	public static AdminUserDAO getAdminUserDAO() {
		return new AdminUserDAOImplemetation();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImplementation();
	}
	public static UsersService getUserService() {
		return new UsersServiceImplementation();
	}
	public static AdminUserService getAdminUserService() {
		return new AdminUserServiceImplementation();
	}
	
}
