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
    <a href="${contextPath}/contact/write.do">새연락처등록하기</a>
  </div>
  
  <hr>
  
  <div>
    <form action="${contextPath}/contact/removes.do"
          method="get"
          id="form">
      <table border="1">
        <thead>
          <tr>
            <td>
              <label for="all-check">전체선택</label>
              <input type="checkbox" id="all-check">
            </td>
            <td>순번</td>
            <td>이름</td>
            <td>연락처</td>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${contactList}" var="contact" varStatus="vs">
            <tr>
              <td><input type="checkbox" class="each-check" name="contactNo" value="${contact.contactNo}"></td>
              <td>${vs.count}</td>
              <td><a href="${contextPath}/contact/detail.do?contactNo=${contact.contactNo}">${contact.name}</a></td>
              <td>${contact.mobile}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <div>
        <button type="submit">선택삭제</button>
      </div>
    </form>
  </div>
  <script>
  
    const allCheck = document.getElementById('all-check');
    const checkEach = document.getElementsByClassName('each-check');
    
    // 1. 전체 선택을 클릭하면 개별 선택이 함께 바뀐다.
    allCheck.addEventListener('click', evt=>{
      for(let i = 0; i < checkEach.length; i++) {
        checkEach[i].checked = evt.target.checked;
      }
    })
    // 2. 개별 선택을 클릭하면 전체 선택에 영향을 미친다.
    for(let i = 0; i < checkEach.length; i++) {
      checkEach[i].addEventListener('click', evt=>{
        let total = 0;
        for(let j = 0; j < checkEach.length; j++) {
          total += checkEach[j].checked;
        }
        allCheck.checked = (total === checkEach.length);
      })
    }
  
    // 3. 선택 삭제
    document.getElementById('form').addEventListener('submit', evt=>{
      let total = 0;
      for(let j = 0; j < checkEach.length; j++) {
        total += checkEach[j].checked;
      }
      if(total === 0){
        alert('선택된 연락처가 없습니다.');
        evt.preventDefault();
        return;
      }
      if(!confirm('선택된 연락처를 삭제할까요?')) {
        alert('취소되었습니다.');
        evt.preventDefault();
        return;
      }
    })
  
    if('${registerResult}' !== ''){
      alert('${registerResult}');
    }
    if('${removeResult}' !== ''){
      alert('${removeResult}');
    }
    if('${removeListResult}' !== ''){
      alert('${removeListResult}');
    }
    
  </script>

</body>
</html>