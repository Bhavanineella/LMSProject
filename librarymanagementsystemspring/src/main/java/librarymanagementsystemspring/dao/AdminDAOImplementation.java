package librarymanagementsystemspring.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.BookIssueDetails;
import librarymanagementsystemspring.dto.BooksBorrowed;
import librarymanagementsystemspring.dto.RequestInfo;
import librarymanagementsystemspring.dto.UserInfo;
import librarymanagementsystemspring.exception.LibraryException;


@Repository
public class AdminDAOImplementation implements AdminDAO{
	
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public boolean addBook(BookInfo book) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
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
			return false;
		} 
	}

	@Override
	public boolean updateBook(BookInfo book) {
		try{
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class, book.getBookId());
			record.setBookName(book.getBookName());
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override 
	public boolean issueBook(int bookId, int userId) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bookId);
			BookInfo rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select r from RequestInfo r where r.userId=:userId and r.bookId=:bookId";
				TypedQuery<RequestInfo> query1 = manager.createQuery(jpql1,RequestInfo.class);
				query1.setParameter("uId", userId);
				query1.setParameter("bId", bookId);
				List<RequestInfo> rs1 = query1.getResultList();
				if(!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueDetails issueBook = new BookIssueDetails();
					issueBook.setId(userId);
					issueBook.setBookId(bookId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if(!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from BookInfo b where b.bookId=:bookId");
						bookName.setParameter("bId", bookId);
						List book = bookName.getResultList();
						BooksBorrowed borrowedBooks = new BooksBorrowed();
						borrowedBooks.setUserId(userId);
						borrowedBooks.setBookId(bookId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					}else {
						throw new LibraryException("Book Not issued");
					}
				}else {
					throw new LibraryException("The respective user have not placed any request");
				}
			}else {
				throw new LibraryException("There is no book exist with bookId"+bookId);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}



	@Override
	public List<Integer> bookHistoryDetails(int userId) {
		int count=0;
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueDetails b";
		TypedQuery<BookIssueDetails> query = manager.createQuery(jpql,BookIssueDetails.class);
		List<BookIssueDetails> recordList = query.getResultList();
		for(BookIssueDetails p : recordList) {
			noOfBooks = count++;
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(noOfBooks);
		factory.close();
		return list;
	}
	
	@Override
	public List<RequestInfo> showRequests() {
		manager = factory.createEntityManager();
		String jpql = "select r from RequestInfo r";
		TypedQuery<RequestInfo> query = manager.createQuery(jpql,RequestInfo.class);
		List<RequestInfo> recordList = query.getResultList();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookIssueDetails> showIssuedBooks() {
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueDetails b";
		TypedQuery<BookIssueDetails> query = manager.createQuery(jpql,BookIssueDetails.class);
		List<BookIssueDetails> recordList = query.getResultList();
		factory.close();
		return recordList;
	}

	@Override
	public List<UserInfo> showUsers() {
		manager = factory.createEntityManager();
		String jpql = "select u from UserInfo u";
		TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
		List<UserInfo> recordList = query.getResultList();
		factory.close();
		return recordList;
	}


}
