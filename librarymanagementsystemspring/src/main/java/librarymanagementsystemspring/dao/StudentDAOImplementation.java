package librarymanagementsystemspring.dao;

import java.util.Calendar;
import java.util.Date;
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
import librarymanagementsystemspring.exception.LibraryException;

@Repository
public class StudentDAOImplementation implements StudentDAO{

	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@PersistenceUnit
	private EntityManagerFactory factory;


	@Override
	public List<BooksBorrowed> borrowedBook(int userId) {
		try{
			manager = factory.createEntityManager();
			String jpql = "select b from BooksBorrowed b where b.userId=:userId";
			TypedQuery<BooksBorrowed> query = manager.createQuery(jpql,BooksBorrowed.class);
			query.setParameter("uId", userId);
			List<BooksBorrowed> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean request(int userId, int bookId) {
		int count=0;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bookId);
			List rs = query.getResultList();
			if(rs != null) {
				String jpql1 = "select b from BookIssueDetails b where b.userId=:userId and b.bookId=:bookId";
				TypedQuery<BooksBorrowed> query1 = (TypedQuery<BooksBorrowed>) manager.createQuery(jpql1,BooksBorrowed.class);
				query1.setParameter("uId", userId);
				query1.setParameter("bId", bookId);
				List rs1 = query1.getResultList();
				if( rs1.isEmpty() || rs1==null ) {
					String jpql2 = "select b from BookIssueDetails b where b.userId=:userId";
					TypedQuery<BookIssueDetails> query2 = (TypedQuery<BookIssueDetails>) manager.createQuery(jpql2,BookIssueDetails.class);
					query2.setParameter("uId", userId);
					List<BookIssueDetails> rs2 = query2.getResultList();
					for(BookIssueDetails p : rs2) {
						noOfBooks = count++;
					}
					if(noOfBooks<3) {
						Query bookName = manager.createQuery("select b.bookName from BookInfo b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from UserInfo u where u.userId=:user_Id");
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

					}else {
						throw new LibraryException("You have crossed the book limit");
					}
				}else {
					throw new LibraryException("You have already borrowed the requested book");
				}
			}else {
				throw new LibraryException("The book with requested id is not present");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		} 
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		try{
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bookId);
			BookInfo rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select b from BookIssueDetails b where b.bookId=:bookId and b.userId=:userId ";
				TypedQuery<BookIssueDetails> query1 = manager.createQuery(jpql1,BookIssueDetails.class);
				query1.setParameter("bId", bookId);
				query1.setParameter("uId", userId);
				BookIssueDetails rs1 = query1.getSingleResult();
				if(rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000*60*60*24));
					if(daysBetween>7.0) {
						float fine = daysBetween*5;
						System.out.println("The user has to pay the fine of the respective book of Rs:"+fine);
						if(status=="yes") {
							transaction.begin();
							manager.remove(rs1);
							transaction.commit();


							transaction.begin();
							String jpql3 = "select b from BooksBorrowed b  where b.bookId=:bookId and b.userId=:userId";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bId", bookId);
							query3.setParameter("uId", userId);
							BooksBorrowed bbb = (BooksBorrowed) query3.getSingleResult();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from RequestInfo r where r.bookId=:bookId and r.userId=:userId";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bId", bookId);
							query4.setParameter("uId", userId);
							RequestInfo rdb = (RequestInfo) query4.getSingleResult();
							manager.remove(rdb);
							transaction.commit();
							return true;
						}else {
							throw new LibraryException("The User has to pay fine for delaying book return");
						}
					}else {
						transaction.begin();
						manager.remove(rs1);
						transaction.commit();


						transaction.begin();
						String jpql3 = "select b from BooksBorrowed b  where b.bookId=:bookId and b.userId=:userId";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bId", bookId);
						query3.setParameter("uId", userId);
						BooksBorrowed bbb = (BooksBorrowed) query3.getSingleResult();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from RequestInfo r where r.bookId=:bookId and r.userId=:userId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bId", bookId);
						query4.setParameter("uId", userId);
						RequestInfo rdb = (RequestInfo) query4.getSingleResult();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				}else {
					throw new LibraryException("This respective user hasn't borrowed any book");
				}
			}else {
				throw new LibraryException("book doesnt exist");
			}

		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
