package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.Calendar;
import java.util.Date;
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
import com.capgemini.librarymanagementsystemhibernate.exception.LibraryException;

public class UserDAOImplementation implements UserDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean request(int userId, int bookId) {
		int count = 0;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book_Info b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("bookId", bookId);
			List rs = query.getResultList();
			if (rs != null) {
				String jpql1 = "select b from Borrowed_Books_Info b where b.userId=:userId and b.bookId=:bookId";
				TypedQuery<BorrowedBooksInfo> query1 = (TypedQuery<BorrowedBooksInfo>) manager.createQuery(jpql1,
						BorrowedBooksInfo.class);
				query1.setParameter("userId", userId);
				query1.setParameter("bookId", bookId);
				List rs1 = query1.getResultList();
				if (rs1.isEmpty() || rs1 == null) {
					String jpql2 = "select b from Book_Issue_Info b where b.userId=:userId";
					TypedQuery<BookIssueInfo> query2 = (TypedQuery<BookIssueInfo>) manager.createQuery(jpql2,
							BookIssueInfo.class);
					query2.setParameter("userId", userId);
					List<BookIssueInfo> rs2 = query2.getResultList();
					for (BookIssueInfo p : rs2) {
						noOfBooks = count++;
					}
					if (noOfBooks < 3) {
						Query bookName = manager
								.createQuery("select b.bookName from Book_Info b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from User_Info u where u.userId=:user_Id");
						email.setParameter("user_Id", userId);
						List userEmail = email.getResultList();
						transaction.begin();
						RequestInfo request = new RequestInfo();
						request.setUserId(userId);
						request.setBookId(bookId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;
					} else {
						throw new LibraryException("You have crossed the book limit");
					}
				} else {
					throw new LibraryException("You have already borrowed the requested book");
				}
			} else {
				throw new LibraryException("The book with requested id is not present");
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
	public List<BorrowedBooksInfo> borrowedBook(int userId) {
		try {
			manager = factory.createEntityManager();
			String jpql = "select b from Borrowed_Books_Info b where b.userId=:userId";
			TypedQuery<BorrowedBooksInfo> query = manager.createQuery(jpql, BorrowedBooksInfo.class);
			query.setParameter("userId", userId);
			List<BorrowedBooksInfo> recordList = query.getResultList();
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
	public boolean returnBook(int bookId, int userId, String status) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book_Info b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql, BookInfo.class);
			query.setParameter("bookId", bookId);
			BookInfo rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select b from Book_Issue_Info b where b.bookId=:bookId and b.userId=:userId ";
				TypedQuery<BookIssueInfo> query1 = manager.createQuery(jpql1, BookIssueInfo.class);
				query1.setParameter("bookId", bookId);
				query1.setParameter("userId", userId);
				BookIssueInfo rs1 = query1.getSingleResult();
				if (rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000 * 60 * 60 * 24));
					if (daysBetween > 7.0) {
						// transaction.begin();
						float fine = daysBetween * 5;
						System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
						if (status == "yes") {
							transaction.begin();
							/*
							 * String jpql2 =
							 * "select b from BookIssueInfo b where b.bookId=:bookId and b.userId=:userId";
							 * Query query2 = manager.createQuery(jpql2); query2.setParameter("bookId",
							 * bookId); query2.setParameter("userId", userId); BookIssueInfo bib =
							 * (BookIssueInfo) query2.getSingleResult();
							 */
							// int bib_Id = rs1.getId();
							manager.remove(rs1);
							transaction.commit();

							transaction.begin();
							String jpql3 = "select b from Borrowed_Books_Info b  where b.bookId=:bookId and b.userId=:userId";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bookId", bookId);
							query3.setParameter("userId", userId);
							BorrowedBooksInfo bbb = (BorrowedBooksInfo) query3.getSingleResult();
							// int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from Request_Info r where r.bookId=:bookId and r.userId=:userId";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bookId", bookId);
							query4.setParameter("userId", userId);
							RequestInfo rdb = (RequestInfo) query4.getSingleResult();
							// int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						} else {
							throw new LibraryException("The User has to pay fine for delaying book return");
						}
					} else {
						transaction.begin();
						/*
						 * String jpql2 =
						 * "select b from BookIssueInfo b where b.bookId=:bookId and b.userId=:userId";
						 * Query query2 = manager.createQuery(jpql2); query2.setParameter("bookId",
						 * bookId); query2.setParameter("userId", userId); BookIssueInfo bib =
						 * (BookIssueInfo) query2.getSingleResult();
						 */
						// int bib_Id = rs1.getId();
						manager.remove(rs1);
						transaction.commit();

						transaction.begin();
						String jpql3 = "select b from Borrowed_Books_Info b  where b.bookId=:bookId and b.userId=:userId";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bookId", bookId);
						query3.setParameter("userId", userId);
						BorrowedBooksInfo bbb = (BorrowedBooksInfo) query3.getSingleResult();
						// int bbb_Id = bbb.getId();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from Request_Info r where r.bookId=:bookId and r.userId=:userId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bookId", bookId);
						query4.setParameter("userId", userId);
						RequestInfo rdb = (RequestInfo) query4.getSingleResult();
						// int rdb_Id = rdb.getId();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				} else {
					throw new LibraryException("No book has been borrowed by the book");
				}
			} else {
				throw new LibraryException("book doesnt exist");
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
