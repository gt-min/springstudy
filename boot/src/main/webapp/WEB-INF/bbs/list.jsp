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
  <div>${total}개</div>
  <table border="1">
    <thead>
      <tr>
        <td>작성자</td>
        <td>내용</td>
        <td>작성일자</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${bbsList}" var="bbs">
        <tr>
          <td>${bbs.userDTO.name}</td>
          <td>${bbs.contents}</td>
          <td>${bbs.createDt}</td>
        </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="3">${paging}</td>
      </tr>
    </tfoot>
  </table>
</div>

<script>

  if('${saveParentMessage}' !== '')
    alert('${saveParentMessage}');

</script>

<%@ include file="../layout/footer.jsp" %>