package librarymanagementsystemspring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.UserInfo;
import librarymanagementsystemspring.exception.LibraryException;

@Repository
public class UserDAOImplementation implements UserDAO{

	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public boolean register(UserInfo user) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override 
	public UserInfo login(String email, String password) {
		try {
			manager = factory.createEntityManager();
			String jpql="select u from UserInfo u where u.email=:email and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserInfo bean = query.getSingleResult();
			return bean;
		}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}
	}


	@Override
	public List<BookInfo> searchBookById(int bookId) {
		try{
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bId", bookId);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		try{
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookName=:bookName";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookName", bookName);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		try{
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.author=:author";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("author", author);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		manager = factory.createEntityManager();
		String jpql = "select b from BookInfo b";
		TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
		List<BookInfo> recordList = query.getResultList();
		factory.close();
		return recordList;
	}


	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		try{
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from UserInfo u where u.userId=:userId and u.role=:role and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UserInfo rs = query.getSingleResult();
			if(rs != null) {
				UserInfo record = manager.find(UserInfo.class,id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;			
			}else {
				throw new LibraryException("User doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		} 
	}


}
