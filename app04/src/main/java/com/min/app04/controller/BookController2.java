package com.min.app04.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.min.app04.dto.BookDTO;
import com.min.app04.service.BookService;

/* @RestController
 *     모든 메소드가 @ResponseBody 를 가진다.  */
// @RestController

public class BookController2 {

  private BookService bookService;

  @Autowired
  public BookController2(BookService bookService) {
    super();
    this.bookService = bookService;
  }
  
  /* MediaType.APPLICATION_JSON_VALUE == "application/json" */
  @GetMapping(value = {"/api/books", "/api/books.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> listJson() {
    return Map.of("books", bookService.getBookList());
  }
  
  /* MediaType.APPLICATION_XML_VALUE == "application/xml" */
  @GetMapping(value = "/api/books.xml", produces = MediaType.APPLICATION_XML_VALUE)
  public Map<String, Object> listXml() {
    return Map.of("book", bookService.getBookList());
  }
  
  @GetMapping(value = {"/api/books/{bookNo}", "/api/books.json/{bookNo}"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> detailJson(@PathVariable(name = "bookNo") int bookNo) {
    return Map.of("book", bookService.getBookByNo(bookNo));
  }
  
  @GetMapping(value = "/api/books.xml/{bookNo}", produces = MediaType.APPLICATION_XML_VALUE)
  public Map<String, Object> detailXml(@PathVariable int bookNo) {  // @PathVariable 의 name 생략 가능
    return Map.of("book", bookService.getBookByNo(bookNo));
  }
  
  @PostMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> insertBook(@RequestBody BookDTO book) {
    return Map.of("isSuccess", bookService.insertBook(book) == 1
                , "inserted", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
  }
  
  @PutMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> updateBook(@RequestBody BookDTO book) {
    return Map.of("isSuccess", bookService.updateBook(book) == 1
                , "updated", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
  }
  
  @DeleteMapping(value = "/api/books/{bookNo}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> deleteBook(@PathVariable int bookNo) {
    return Map.of("isSuccess", bookService.deleteBook(bookNo) == 1
                , "deleted", new SimpleDateFormat("y-MM-dd").format(new Date()));
  }
  
}
