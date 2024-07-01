package com.min.app04.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.min.app04.dto.BookDTO;

@Repository
public class BookDAOImpl implements BookDAO {

  // DB 대신 List 사용 (bookNo 는 index + 1 로 가정)
  
  private List<BookDTO> books;
  
  public BookDAOImpl() {
    books = new ArrayList<>();
    books.add(new BookDTO(1, "소나기", "황순원"));
    books.add(new BookDTO(2, "태백산맥", "조정래"));
    books.add(new BookDTO(3, "홍길동전", "허균"));
    books.add(new BookDTO(4, "수학의정석", "홍성대"));
    books.add(new BookDTO(5, "자바의정석", "남궁성"));    
  }

  @Override
  public List<BookDTO> getBookList() {
    return books;
  }

  @Override
  public BookDTO getBookByNo(int bookNo) {
    return books.get(bookNo - 1);
  }

  @Override
  public int insertBook(BookDTO book) {
    return books.add(book) ? 1 : 0;
  }

  @Override
  public int updateBook(BookDTO book) {
    return books.set(book.getBookNo() - 1, book) != null ? 1 : 0;
  }

  @Override
  public int deleteBook(int bookNo) {
    return books.remove(bookNo - 1) != null ? 1 : 0;
  }

}
