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

  <c:forEach items="${blogList}" var="blog" varStatus="vs">
    <ul class="blog">
      <li><a href="${contextPath}/blog/detail?blogNo=${vs.index + 1}">${blog.title}</a></li>
      <li>${blog.contents}</li>
    </ul>
  </c:forEach>

</body>
</html>