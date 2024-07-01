package com.min.app04.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.min.app04.dto.BookDTO;
import com.min.app04.service.BookService;

@Controller
public class BookController3 {

  private BookService bookService;

  @Autowired
  public BookController3(BookService bookService) {
    super();
    this.bookService = bookService;
  }
  
  /* ResponseEntity 클래스
   *     @ResponseBody 를 내장하고 있는 클래스  */
  @GetMapping(value = {"/api/books", "/api/books.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> listJson() {
    return new ResponseEntity<>(Map.of("books", bookService.getBookList()), HttpStatus.OK);
  }
  
  @GetMapping(value = "/api/books.xml", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Map<String, Object>> listXml() {
    return ResponseEntity.ok(Map.of("book", bookService.getBookList()));
  }
  
  @GetMapping(value = {"/api/books/{bookNo}", "/api/books.json/{bookNo}"})
  public ResponseEntity<Map<String, Object>> detailJson(@PathVariable(name = "bookNo") int bookNo) {
    
    // 응답 헤더에 produces = MediaType.APPLICATION_JSON_VALUE 정보 넣기
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    
    return new ResponseEntity<>(Map.of("book", bookService.getBookByNo(bookNo)), headers, HttpStatus.OK);
    
  }
  
  @GetMapping(value = "/api/books.xml/{bookNo}")
  public ResponseEntity<Map<String, Object>> detailXml(@PathVariable int bookNo) {
    
    /* HttpHeaders.CONTENT_TYPE == "Content-Type" */
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
    
    return new ResponseEntity<>(Map.of("book", bookService.getBookByNo(bookNo)), headers, HttpStatus.OK);
    
  }
  
  @PostMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> insertBook(@RequestBody BookDTO book) {
    return ResponseEntity.ok(Map.of("isSuccess", bookService.insertBook(book) == 1
                                  , "inserted", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now())));
  }
  
  @PutMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> updateBook(@RequestBody BookDTO book) {
    return ResponseEntity.ok(Map.of("isSuccess", bookService.updateBook(book) == 1
                                  , "updated", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())));
  }
  
  @DeleteMapping(value = "/api/books/{bookNo}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable int bookNo) {
    return ResponseEntity.ok(Map.of("isSuccess", bookService.deleteBook(bookNo) == 1
                                  , "deleted", new SimpleDateFormat("y-MM-dd").format(new Date())));
  }
  
}
