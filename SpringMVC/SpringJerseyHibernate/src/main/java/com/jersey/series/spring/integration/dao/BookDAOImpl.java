package com.jersey.series.spring.integration.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jersey.series.spring.integration.model.Book;

@Repository("bookDAO")
public class BookDAOImpl implements BookDAO {

	@Override
	public String insertNewBookInfo(Book book) {
		// get book information from formal arguments and inserts into database & return bookId (primary_key)
		return "New Book information saved successfully with Book_ID " + book.getBookId();
	}

	@Override
	public Book getBookInfo(int bookId) {

		// construct dummy book object
		Book book = new Book();
		book.setBookId(bookId);
		book.setBookName("Microbiology Coloring Book");
		book.setAuthor("I. Edward Alcamo");
		book.setCategory("Microbiology");
		return book;
	}

	@Override
	public String updateBookInfo(Book book) {
		return "Book information updated successfully";
	}

	@Override
	public String removeBookInfo(int bookId) {
		return "Book information with Book_ID " + bookId +  " deleted successfully";
	}

	@Override
	public List<Book> getAllBookInfo() {

		List<Book> lstBook = new ArrayList<Book>();

		// construct dummy object for book one
		Book bookOne = new Book();
		bookOne.setBookId(25369);
		bookOne.setBookName("Medical Microbiology and Infection");
		bookOne.setAuthor("Stephen Gillespie");
		bookOne.setCategory("Microbiology");
		lstBook.add(bookOne); // add to the list

		// construct dummy object for book two
		Book bookTwo = new Book();
		bookTwo.setBookId(25370);
		bookTwo.setBookName("Essential Microbiology and Hygiene for Food");
		bookTwo.setAuthor("Sibel Roller");
		bookTwo.setCategory("Microbiology");
		lstBook.add(bookTwo); // add to the list

		return lstBook;
	}
}