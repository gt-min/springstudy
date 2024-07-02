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
    <form method="post"
          action="${contextPath}/contact/register.do">
      <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name">
      </div>
      <div>
        <label for="email">이메일</label>
        <input type="text" id="email" name="email">
      </div>
      <div>
        <label for="mobile">모바일</label>
        <input type="text" id="mobile" name="mobile">
      </div>
      <div>
        <button type="submit">등록하기</button>
        <button type="reset">입력초기화하기</button>
        <button type="button" id="list-btn">연락처목록보기</button>
      </div>
    </form>
  </div>
  
  <script>
  
    document.getElementById('list-btn').addEventListener('click', evt=>{
      location.href = '${contextPath}/contact/list.do';
    })
    
    if('${registerResult}' !== ''){
      alert('${registerResult}');
    }
    
  </script>

</body>
</html>