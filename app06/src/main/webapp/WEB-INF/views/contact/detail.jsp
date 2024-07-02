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
    <form action="${contextPath}/contact/modify.do"
          method="post">
      <div>
        <label for="contactNo">번호</label>
        <input type="text" id="contactNo" name="contactNo" value="${contact.contactNo}" readonly>
      </div>
      <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name" value="${contact.name}">
      </div>
      <div>
        <label for="email">이메일</label>
        <input type="text" id="email" name="email" value="${contact.email}">
      </div>
      <div>
        <label for="mobile">모바일</label>
        <input type="text" id="mobile" name="mobile" value="${contact.mobile}">
      </div>
      <div>
        <button type="submit">연락처수정하기</button>
        <button type="button" id="remove-btn">연락처삭제하기</button>
        <button type="button" id="list-btn">연락처목록보기</button>
      </div>
    </form>
  </div>
  
  <script>
  
    document.getElementById('remove-btn').addEventListener('click', evt=>{
      if(confirm('연락처를 삭제할까요?')){
        location.href = '${contextPath}/contact/remove.do?contactNo=' + document.getElementById('contactNo').value;
      } else {
        alert('취소되었습니다.');
      }
    })
    
    document.getElementById('list-btn').addEventListener('click', evt=>{
      location.href = '${contextPath}/contact/list.do';
    })
  
    if('${modifyResult}' !== ''){
      alert('${modifyResult}');
    }
    if('${removeResult}' !== ''){
      alert('${removeResult}');
    }
  
    if('${removeResult}' !== ''){
      alert('${removeResult}');
    }
    
  </script>

</body>
</html>