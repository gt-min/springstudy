<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="BBS" name="title"/>
</jsp:include>

<h1 class="title">BBS List</h1>

<div>
  <a href="${contextPath}/bbs/write.page">작성하러가기</a>
</div>

<div>
  <form>검색폼</form>
</div>

<div>
  목록
</div>

<%@ include file="../layout/footer.jsp" %>