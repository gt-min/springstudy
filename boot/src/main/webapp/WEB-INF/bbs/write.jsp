<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="BBS write" name="title"/>
</jsp:include>

<h1 class="title">BBS write</h1>

<form id="bbs-write-form"
      method="post"
      action="${contextPath}/bbs/saveParent.do">

  <div>
    <textarea rows="5" cols="30" name="contents" id="contents" placeholder="내용 작성"></textarea>
  </div>
  
  <div>
    <button type="submit">원글작성완료</button>
    <button type="button" onclick="history.back()">취소하기</button>
  </div>
      
</form>

<script>



</script>

<%@ include file="../layout/footer.jsp" %>