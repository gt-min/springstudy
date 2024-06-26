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
</head>
<body>

  <%-- servlet-context.xml's <resources> 태그 동작 확인 --%>
  <div>
    <img src="${contextPath}/resources/images/404.jpg" width="50px">
  </div>

  <hr>

  <%-- MvcController2's @RequestMapping 동작 확인 --%>
  <div>
    <div><a href="${contextPath}/api/user">user</a></div>
    <div><a href="${contextPath}/api/board">board</a></div>
    <div><a href="${contextPath}/api/news">news</a></div>
  </div>
  
  <hr>
  
  <%-- MvcController3's Query String 방식의 요청 파라미터 처리 방식 확인 --%>
  <div>
    <div><a href="${contextPath}/param1">요청1</a></div>
    <div><a href="${contextPath}/param2?page=1">요청2</a></div>
    <div><a href="${contextPath}/param3?page=1&sort=DESC">요청3</a></div>
  </div>
  
  <hr>
  
  <%-- MvcController4's Path Variable 방식의 요청 파라미터 처리 방식 확인 --%>
  <div>
    <div><a href="${contextPath}/app/users/1">요청1</a></div>
    <div><a href="${contextPath}/app/members">요청2</a></div>
    <div><a href="${contextPath}/app/page/1/sort/DESC">요청3</a></div>
  </div>

  <hr>
  
  <div>
    <form action="${contextPath}/param">
      <div>
        <label for=""></label>
        <input type="text" name="" id="">
      </div>
      <div>
        <label for=""></label>
        <input type="text" name="" id="">
      </div>
    </form>
  </div>





</body>
</html>