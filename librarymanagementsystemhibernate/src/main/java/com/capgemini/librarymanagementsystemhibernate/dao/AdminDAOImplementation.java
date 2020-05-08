package com.capgemini.librarymanagementsystemhibernate.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;
import com.capgemini.librarymanagementsystemhibernate.exception.LibraryException;

public class AdminDAOImplementation implements AdminDAO {

    private	EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean addBook(BookInfo book) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
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
	public boolean removeBook(int bookId) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class,bookId);
			manager.remove(record);
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
	public boolean updateBook(BookInfo book) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class, book.getBookId());
			record.setBookName(book.getBookName());
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
	public boolean issueBook(int bookId, int userId) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book_Info b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookId", bookId);
			BookInfo rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select r from Request_Info r where r.userId=:userId and r.bookId=:bookId";
				TypedQuery<RequestInfo> query1 = manager.createQuery(jpql1,RequestInfo.class);
				query1.setParameter("userId", userId);
				query1.setParameter("bookId", bookId);
				List<RequestInfo> rs1 = query1.getResultList();
				if (!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueInfo issueBook = new BookIssueInfo();
					issueBook.setUserId(userId);
					issueBook.setBookId(bookId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if (!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from Book_Info b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						BorrowedBooksInfo borrowedBooks = new BorrowedBooksInfo();
						borrowedBooks.setUserId(userId);
						borrowedBooks.setBookId(bookId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					} else {
						throw new LibraryException("Book Not issued");
					}
				} else {
					throw new LibraryException("The respective user have not placed any request");
				}
			} else {
				throw new LibraryException("There is no book exist with bookId"+bookId);
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
	
	@Override
	public List<Integer> bookHistoryDetails(int userId) {
		int count=0;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from Book_Issue_Info b";
		TypedQuery<BookIssueInfo> query = manager.createQuery(jpql,BookIssueInfo.class);
		List<BookIssueInfo> recordList = query.getResultList();
		for (BookIssueInfo p : recordList) {
			noOfBooks = count++;
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(noOfBooks);
		manager.close();
		factory.close();
		return list;
	}

	@Override
	public List<RequestInfo> showRequests() {
		manager = factory.createEntityManager();
		String jpql = "select r from Request_Info r";
		TypedQuery<RequestInfo> query = manager.createQuery(jpql,RequestInfo.class);
		List<RequestInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookIssueInfo> showIssuedBooks() {
		manager = factory.createEntityManager();
		String jpql = "select b from Book_Issue_Info b";
		TypedQuery<BookIssueInfo> query = manager.createQuery(jpql,BookIssueInfo.class);
		List<BookIssueInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<UserInfo> showUsers() {
		manager = factory.createEntityManager();
		String jpql = "select u from User_Info u";
		TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
		List<UserInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}
}
