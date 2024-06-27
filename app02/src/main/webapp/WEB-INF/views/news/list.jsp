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
</head>
<body>

  <c:forEach items="${newsList}" var="news" varStatus="vs">
    <div class="news" data-news-no="${vs.index + 1}">
      <div>${news.title}</div>
      <div>조회수 ${news.hit}</div>
    </div>
  </c:forEach>
  <script>
    const newsList = document.getElementsByClassName('news');
    for(const news of newsList) {
      news.addEventListener('click', evt=>{
        const newsNo = evt.currentTarget.dataset.newsNo;
        location.href = '${contextPath}/news/detail?newsNo=' + newsNo;
      })
    }
  </script>
  <script>
  /*
    $('.news').on('click', evt=>{
      const newsNo = $(evt.currentTarget).data('newsNo');
      location.href = '${contextPath}/news/detail?newsNo=' + newsNo;
    })
  */
  </script>

</body>
</html>