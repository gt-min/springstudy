package com.min.app04.service;

import java.util.Map;

import com.min.app04.dto.BookDTO;

public interface BookService {
  Map<String, Object> getBookList();
  Map<String, Object> getBookByNo(int bookNo);
  Map<String, Object> insertBook(BookDTO book);
  Map<String, Object> updateBook(BookDTO book);
  Map<String, Object> deleteBook(int bookNo);
}
