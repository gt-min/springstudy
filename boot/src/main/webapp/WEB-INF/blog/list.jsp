<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="BLOG" name="title"/>
</jsp:include>

<h1 class="title">BLOG List</h1>

<div>
  <a href="${contextPath}/blog/write.page">작성하러가기</a>
</div>

<script>

  if('${saveBlogMessage}' !== '')
    alert('${saveBlogMessage}');

</script>

<%@ include file="../layout/footer.jsp" %>