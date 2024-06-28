package com.min.app04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.app04.dao.BookDAO;
import com.min.app04.dto.BookDTO;

@Service
public class BookServiceImpl implements BookService {

  private BookDAO bookDAO;
  
  @Autowired  
  public BookServiceImpl(BookDAO bookDAO) {
    super();
    this.bookDAO = bookDAO;
  }

  @Override
  public List<BookDTO> getBookList() {
    return bookDAO.getBookList();
  }

  @Override
  public BookDTO getBookByNo(int bookNo) {
    return bookDAO.getBookByNo(bookNo);
  }

  @Override
  public int insertBook(BookDTO book) {
    return bookDAO.insertBook(book);
  }

  @Override
  public int updateBook(BookDTO book) {
    return bookDAO.updateBook(book);
  }

  @Override
  public int deleteBook(int bookNo) {
    return bookDAO.deleteBook(bookNo);
  }

}
