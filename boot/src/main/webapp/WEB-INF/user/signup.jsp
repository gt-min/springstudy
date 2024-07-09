<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="Signup" name="title"/>
</jsp:include>

<h1 class="title">Sign Up</h1>

<form method="post"
      action="${contextPath}/user/signup.do">

  <div>
    <label for="email">아이디</label>
    <input type="text" name="email" id="email" placeholder="example@example.com">
    
  </div>
      
</form>


<%@ include file="../layout/footer.jsp" %>