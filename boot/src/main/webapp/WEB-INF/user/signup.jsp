<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="Signup" name="title"/>
</jsp:include>

<h1 class="title">Sign Up</h1>

<form id="signup-form"
      method="post"
      action="${contextPath}/user/signup.do">

  <div>
    <label for="email">아이디</label>
    <input type="text" name="email" id="email" placeholder="example@example.com">
    <%-- 이메일 인증 구현할 것 : 인증코드 6자리를 이메일로 보내고 입력 받아서 검증할 것 --%>
    <button type="button" id="get-code-btn">인증코드받기</button>
  </div>
  
  <div>
    <label for="pw">비밀번호</label>
    <input type="password" name="pw" id="pw">
    <%-- 입력 완료 후 비밀번호 정규식 체크할 것 --%>
    <div id=""></div>
  </div>
  <div>
    <label for="pw2">비밀번호 확인</label>
    <input type="password" id="pw2">
    <%-- 입력 완료 후 비밀번호 맞는지 체크할 것 --%>
    <div id=""></div>
  </div>
  
  <div>
    <label for="name">이름</label>
    <input type="text" name="name" id="name">
  </div>
  
  <div>
    <input type="radio" name="gender" value="none" id="none" checked>
    <label for="none">선택안함</label>
    <input type="radio" name="gender" value="man" id="man">
    <label for="man">남자</label>
    <input type="radio" name="gender" value="woman" id="woman">
    <label for="woman">여자</label>
  </div>
  
  <div>
    <label for="mobile">휴대전화</label>
    <input type="text" name="mobile" id="mobile">
    <%-- 입력 완료 후 비밀번호 정규식 체크할 것 --%>
    <div id=""></div>
  </div>
  
  <div>
    <button type="submit">가입하기</button>
    <button type="button" onclick="history.back()">취소하기</button>
  </div>
      
</form>

<script>

  var emailCheck = false;
  
  const fnEmailCheck = ()=>{
    
    const email = document.getElementById('email');
    
    $.ajax({
      type: 'get',
      url: '${contextPath}/user/sendCode.do',
      data: 'email=' + email.value,
      dataType: 'json'
    }).done(resData=>{
      console.log(resData);
    }).fail(jqXHR=>{
      console.log(jqXHR);
    })
    
  }

  document.getElementById('get-code-btn').addEventListener('click', evt=>{
    fnEmailCheck();
  })


  /* 이메일인증 / 비밀번호 / 휴대전화 모두 통과해야 서브밋 가능하게 구현 */
  
  
  if('${signupMessage}' !== ''){
    alert('${signupMessage}');
  }

</script>

<%@ include file="../layout/footer.jsp" %>