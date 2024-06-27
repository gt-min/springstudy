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
<script src="${contextPath}/resources/lib/jquery-3.7.1.min.js"></script>
<script>
  $(function(){
    $('.detail-btn').on('click', evt=>{
      location.href = '${contextPath}/bbs/detail?bbsNo=' + $(evt.target).prev().val();
    })
  })
</script>
</head>
<body>

  <c:forEach items="${bbsList}" var="bbs" varStatus="vs">
    <div>
      <div>${bbs.title}</div>
      <div>${bbs.contents}</div>
      <div>
        <input type="hidden" value="${vs.index + 1}">
        <button type="button" class="detail-btn">상세</button>
      </div>
    </div>
  </c:forEach>

</body>
</html>