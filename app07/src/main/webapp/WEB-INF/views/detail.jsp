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

  <div>번호 : ${upload.uploadNo}</div>
  <div>업로더 : ${upload.uploader}</div>
  <div>IP : ${upload.ip}</div>
  <div>날짜 : ${upload.uploadDt}</div>

  <hr>
  
  <div>첨부 목록</div>
  <c:forEach items="${fileList}" var="file">
    <div>${file.originalFilename}</div>
    <div>${file.uploadPath}/${file.filesystemName}</div>
  </c:forEach>

</body>
</html>