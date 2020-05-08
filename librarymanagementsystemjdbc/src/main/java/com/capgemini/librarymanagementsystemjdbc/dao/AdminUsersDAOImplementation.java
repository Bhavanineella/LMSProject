package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class AdminUsersDAOImplementation implements AdminUserDAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean register(UsersInfo user) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.registerQuery);) {

			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setLong(6, user.getMobile());
			statement.setString(7, user.getRole());
			int count = statement.executeUpdate();
			if ((user.getEmail().isEmpty()) && (count == 0)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UsersInfo login(String email, String password) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.loginQuery);) {

			statement.setString(1, email);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				UsersInfo bean = new UsersInfo();
				bean.setUserId(rs.getInt("UserId"));
				bean.setFirstName(rs.getString("FirstName"));
				bean.setLastName(rs.getString("LastName"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setMobile(rs.getLong("MobileNo"));
				bean.setRole(rs.getString("Role"));
				return bean;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
   
	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.titleQuery);) {

			statement.setString(1, bookName);
			rs = statement.executeQuery();
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setBookId(rs.getInt("BookId"));
				bean.setBookName(rs.getString("BookName"));
				bean.setAuthor(rs.getString("Author"));
				bean.setCategory(rs.getString("Category"));
				bean.setPublisher(rs.getString("Publisher"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.authorQuery);) {

			statement.setString(1, author);
			rs = statement.executeQuery();
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setBookId(rs.getInt("BookId"));
				bean.setBookName(rs.getString("BookName"));
				bean.setAuthor(rs.getString("Author"));
				bean.setCategory(rs.getString("Category"));
				bean.setPublisher(rs.getString("Publisher"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<BookInfo> getBooksInfo() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();) {
			rs = stmt.executeQuery(QueryMapping.getAllBooksQuery);
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setBookId(rs.getInt("BookId"));
				bean.setBookName(rs.getString("BookName"));
				bean.setAuthor(rs.getString("Author"));
				bean.setCategory(rs.getString("Category"));
				bean.setPublisher(rs.getString("Publisher"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
   
	@Override
	public ArrayList<BookInfo> searchBookById(int bookId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.searchIdQuery);) {
			statement.setInt(1, bookId);
			rs = statement.executeQuery();
			ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
			while (rs.next()) {
				BookInfo bean = new BookInfo();
				bean.setBookId(rs.getInt("BookId"));
				bean.setBookName(rs.getString("BookName"));
				bean.setAuthor(rs.getString("Author"));
				bean.setCategory(rs.getString("Category"));
				bean.setPublisher(rs.getString("Publisher"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.updatePasswordQuery1);) {
			statement.setString(1, email);
			statement.setString(2, role);
			rs = statement.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt = connection.prepareStatement(QueryMapping.updatePasswordQuery2);) {
					pstmt.setString(1, newPassword);
					pstmt.setString(2, email);
					pstmt.setString(3, password);
					int count = pstmt.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				throw new LibraryException("user doesn't exist");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}


}
