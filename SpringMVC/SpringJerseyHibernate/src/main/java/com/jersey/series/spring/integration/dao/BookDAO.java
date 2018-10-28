package com.jersey.series.spring.integration.dao;

import java.util.List;

import com.jersey.series.spring.integration.model.Book;

public interface BookDAO {

	public String insertNewBookInfo(Book book);

	public Book getBookInfo(int bookId);

	public String updateBookInfo(Book book);

	public String removeBookInfo(int bookId);

	public List<Book> getAllBookInfo();
}