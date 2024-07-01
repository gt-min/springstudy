<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
  
  <div>
    <h1>책 관리하기</h1>
    <div>
      <label for="bookNo">책번호</label>
      <input type="text" id="bookNo">
    </div>
    <div>
      <label for="title">책제목</label>
      <input type="text" id="title">
    </div>
    <div>
      <label for="author">책저자</label>
      <input type="text" id="author">
    </div>
    <div>
      <button id="init-btn">초기화</button>
      <button id="register-btn">등록</button>
      <button id="modify-btn">수정</button>
      <button id="remove-btn">삭제</button>
    </div>
  </div>
  
  <hr>
  
  <div>
    <table border="1">
      <thead>
        <tr>
          <td>책번호</td>
          <td>제목</td>
          <td>저자</td>
          <td></td>
        </tr>
      </thead>
      <tbody id="books">
      </tbody>
    </table>
  </div>
  
<script src="${contextPath}/resources/js/app04.js"></script>
  
</body>
</html>