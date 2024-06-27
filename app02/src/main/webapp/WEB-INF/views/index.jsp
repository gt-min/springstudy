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

  <%-- servlet-context.xml's <resources> 태그 동작 확인 --%>
  <div>
    <img src="${contextPath}/resources/images/404.jpg" width="50px">
  </div>

  <hr>

  <%-- MvcController2's @RequestMapping 동작 확인 --%>
  <div>
    <div><a href="${contextPath}/api/user">user</a></div>
    <div><a href="${contextPath}/api/board">board</a></div>
    <div><a href="${contextPath}/api/news">news</a></div>
  </div>
  
  <hr>
  
  <%-- MvcController3's Query String 방식의 요청 파라미터 처리 방식 확인 --%>
  <div>
    <div><a href="${contextPath}/param1">요청1</a></div>
    <div><a href="${contextPath}/param2?page=1">요청2</a></div>
    <div><a href="${contextPath}/param3?page=1&sort=DESC">요청3</a></div>
  </div>
  
  <hr>
  
  <%-- MvcController4's Path Variable 방식의 요청 파라미터 처리 방식 확인 --%>
  <div>
    <div><a href="${contextPath}/app/users/1">요청1</a></div>
    <div><a href="${contextPath}/app/members">요청2</a></div>
    <div><a href="${contextPath}/app/page/1/sort/DESC">요청3</a></div>
  </div>

  <hr>
  
  <%-- MvcController5's Model 과 forward 를 이용한 데이터 전달 방식 확인 --%>
  <div>
    <div><a href="${contextPath}/news/list">뉴스</a></div>
  </div>
  
  <hr>
  
  <%-- MvcController6's RedirectAttributes 와 redirect 를 이용한 데이터 전달 방식 확인 --%>
  <div>
    <div><a href="${contextPath}/blog/register">블로그등록</a></div>
    <div><a href="${contextPath}/blog/modify?blogNo=1">블로그수정</a></div>
    <div><a href="${contextPath}/blog/remove">블로그삭제</a></div>
  </div>
  
  <hr>
  
  <%-- MvcController7's HttpSession 을 이용한 세션 데이터 저장 방식 --%>
  <c:if test="${empty sessionScope.loginUser}">
    <div>
      <form action="${contextPath}/user/login" method="post">
        <input type="hidden" name="redirectURL" value="/main">
        <div>
          <label for="id">ID</label>
          <input type="text" name="id" id="id">
        </div>
        <div>
          <label for="pw">PW</label>
          <input type="password" name="pw" id="pw">
        </div>
        <div>
          <button type="submit">LOG IN</button>
        </div>
      </form>
    </div>
  </c:if>
  <c:if test="${not empty sessionScope.loginUser}">
    <div><a href="${contextPath}/user/mypage">${sessionScope.loginUser.id}</a> 님 반갑습니다.</div>
    <div><a href="${contextPath}/user/logout">LOG OUT</a></div>
  </c:if>

  <hr>
  
  <%-- MvcController8's @SessionAttributes / @SessionAttribute 를 활용한 세션 데이터 저장 방식 --%>
  <c:if test="${empty sessionScope.memberVO}">
    <div>
      <form action="${contextPath}/member/login" method="post">
        <input type="hidden" name="redirectURL" value="/main">
        <div>
          <label for="id">ID</label>
          <input type="text" name="id" id="id">
        </div>
        <div>
          <label for="pw">PW</label>
          <input type="password" name="pw" id="pw">
        </div>
        <div>
          <button type="submit">LOG IN</button>
        </div>
      </form>
    </div>
  </c:if>
  <c:if test="${not empty sessionScope.memberVO}">
    <div><a href="${contextPath}/member/mypage">${sessionScope.memberVO.id}</a> 님 어서오세요.</div>
    <div><a href="${contextPath}/member/logout">LOG OUT</a></div>
  </c:if>

</body>
</html>