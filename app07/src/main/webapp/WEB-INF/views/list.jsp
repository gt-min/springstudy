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
  
  <ul>
    <li><a href="${contextPath}/write1.do">MVC업로드</a></li>
    <li><a href="${contextPath}/write2.do">비동기업로드</a></li>    
  </ul>
  
  <hr>
  
  <div>
    <c:forEach items="${uploadList}" var="upload">
      <div class="upload">
        <span class="uploadNo">${upload.uploadNo}</span>
        <span class="uploader"><a href="${contextPath}/detail.do?uploadNo=${upload.uploadNo}">${upload.uploader}</a></span>
        <span class="fileCnt">(${upload.fileCnt})</span>
      </div>
    </c:forEach>
  </div>
  
</body>
</html>