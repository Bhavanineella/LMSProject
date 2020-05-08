package com.capgemini.librarymanagementsystemhibernate.dao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;
import com.capgemini.librarymanagementsystemhibernate.exception.LibraryException;

public class AdminUserDAOImplemetation implements AdminUserDAO {

	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean register(UserInfo user) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public UserInfo login(String email, String password) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql="select u from User_Info u where u.email=:email and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserInfo bean = query.getSingleResult();
			return bean;
		}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}
	
	@Override
	public List<BookInfo> searchBookById(int bookId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book_Info b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookId", bookId);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book_Info b where b.bookName=:bookName";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookName", bookName);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book_Info b where b.author=:author";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("author", author);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from Book_Info b";
		TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
		List<BookInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}
    
	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from UserInfo u where u.userId=:userId and u.role=:role and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("userId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UserInfo rs = query.getSingleResult();
			if(rs != null) {
				UserInfo record = manager.find(UserInfo.class,id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;			
			} else {
				throw new LibraryException("User doesnt exist");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}


}
