package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class UsersDAOImplementation implements UsersDAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	@Override
	public boolean request(int userId, int bookId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.requestQuery1);) {
			statement.setInt(1, userId);
			statement.setInt(2, bookId);
			statement.setInt(3, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int isBookExists = rs.getInt("userId");
				if (isBookExists == 0) {
					try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapping.requestQuery2);) {
						pstmt1.setInt(1, userId);
						rs = pstmt1.executeQuery();
						if (rs.next()) {
							int noOfBooksBorrowed = rs.getInt("userId");
							if (noOfBooksBorrowed < 3) {
								try (PreparedStatement pstmt2 = connection
										.prepareStatement(QueryMapping.requestQuery3);) {
									pstmt2.setInt(1, userId);
									pstmt2.setInt(2, userId);
									pstmt2.setInt(3, bookId);
									pstmt2.setInt(4, bookId);
									pstmt2.setInt(5, userId);
									int count = pstmt2.executeUpdate();
									if (count != 0) {
										return true;
									} else {
										return false;
									}
								}
							} else {
								throw new LibraryException("no Of books limit has crossed");
							}
						} else {
							throw new LibraryException("no of books limit has crossed");
						}
					}
				} else {
					throw new LibraryException("You have already borrowed the requested book");
				}
			} else {
				throw new LibraryException("You have already borrowed the requested book");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int userId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.borrowQuery);) {
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			ArrayList<BorrowedBooks> beans = new ArrayList<BorrowedBooks>();
			while (rs.next()) {
				BorrowedBooks listOfbooksBorrowed = new BorrowedBooks();
				listOfbooksBorrowed.setUserId(rs.getInt("userId"));
				listOfbooksBorrowed.setBookId(rs.getInt("bookId"));
				listOfbooksBorrowed.setEmail(rs.getString("email"));
				beans.add(listOfbooksBorrowed);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.returnBookQuery1);) {
			statement.setInt(1, bookId);
			statement.setInt(2, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Date issueDate = rs.getDate("issueDate");
				Calendar cal = Calendar.getInstance();
				Date returnDate = cal.getTime();
				long difference = issueDate.getTime() - returnDate.getTime();
				float daysBetween = (difference / (1000 * 60 * 60 * 24));
				if (daysBetween > 7) {
					float fine = daysBetween * 5;
					System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
					if (status == "yes") {
						try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapping.returnBookQuery2);) {
							pstmt1.setInt(1, bookId);
							pstmt1.setInt(2, userId);
							int count = pstmt1.executeUpdate();
							if (count != 0) {
								try (PreparedStatement pstmt2 = connection
										.prepareStatement(QueryMapping.returnBookQuery3);) {
									pstmt2.setInt(1, bookId);
									pstmt2.setInt(2, userId);
									int isReturned = pstmt2.executeUpdate();
									if (isReturned != 0) {
										try (PreparedStatement pstmt3 = connection
												.prepareStatement(QueryMapping.returnBookQuery4);) {
											pstmt3.setInt(1, bookId);
											pstmt3.setInt(2, userId);
											int isRequestDeleted = pstmt3.executeUpdate();
											if (isRequestDeleted != 0) {
												return true;
											} else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							} else {
								return false;
							}
						}
					} else {
						throw new LibraryException("The user has to pay fine for delaying book return");
					}
				} else {
					try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapping.returnBookQuery2);) {
						pstmt1.setInt(1, bookId);
						pstmt1.setInt(2, userId);
						int count = pstmt1.executeUpdate();
						if (count != 0) {
							try (PreparedStatement pstmt2 = connection
									.prepareStatement(QueryMapping.returnBookQuery3);) {
								pstmt2.setInt(1, bookId);
								pstmt2.setInt(2, userId);
								int isReturned = pstmt2.executeUpdate();
								if (isReturned != 0) {
									try (PreparedStatement pstmt3 = connection
											.prepareStatement(QueryMapping.returnBookQuery4);) {
										pstmt3.setInt(1, bookId);
										pstmt3.setInt(2, userId);
										int isRequestDeleted = pstmt3.executeUpdate();
										if (isRequestDeleted != 0) {
											return true;
										} else {
											return false;
										}
									}
								} else {
									return false;
								}
							}
						} else {
							return false;
						}
					}
				}
			} else {
				throw new LibraryException("This respective user hasn't borrowed any book");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}


}
