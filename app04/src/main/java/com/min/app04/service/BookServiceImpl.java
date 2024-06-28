package com.min.app04.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

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
  public Map<String, Object> getBookList() {
    // {"books": [{"bookNo": 1, "title": "소나기", "author": "황순원"}, {}, {}, {}, {}]}
    return Map.of("books", bookDAO.getBookList());
  }

  @Override
  public Map<String, Object> getBookByNo(int bookNo) {
    // {"book": {"bookNo": 1, "title": "소나기", "author": "황순원"}}
    return Map.of("book", bookDAO.getBookByNo(bookNo));
  }

  @Override
  public Map<String, Object> insertBook(BookDTO book) {
    // {"isSuccess": true, "inserted": "2024-06-28"}
    return Map.of("isSuccess", bookDAO.insertBook(book) == 1
                , "inserted", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
  }

  @Override
  public Map<String, Object> updateBook(BookDTO book) {
    // {"isSuccess": true, "updated": "2024-06-28"}
    return Map.of("isSuccess", bookDAO.updateBook(book) == 1
                , "updated", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
  }

  @Override
  public Map<String, Object> deleteBook(int bookNo) {
    // {"isSuccess": true, "deleted": "2024-06-28"}
    return Map.of("isSuccess", bookDAO.deleteBook(bookNo) == 1
                , "deleted", new SimpleDateFormat("y-MM-dd").format(new Date()));
  }

}
