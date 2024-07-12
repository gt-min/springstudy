<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="BBS" name="title"/>
</jsp:include>

<style>
  .hidden {
    display: none;
  }
</style>

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
      <c:forEach items="${bbsList}" var="bbs" varStatus="vs">
        <c:if test="${bbs.state == 1}">
          <tr>
            <td>${bbs.userDTO.name}</td>
            <td>
              <c:forEach begin="1" end="${bbs.depth}" step="1">&nbsp;&nbsp;</c:forEach>
              <c:if test="${bbs.depth > 0}"><i class="fa-solid fa-arrow-trend-down"></i></c:if>
              ${bbs.contents}
              <button type="button" class="open-reply-btn" data-index="${vs.index}">답글</button>
              <c:if test="${bbs.userDTO.userNo == sessionScope.loginUser.userNo}">
              <button type="button" class="remove-btn" data-bbs-no="${bbs.bbsNo}">삭제</button>
              </c:if>
            </td>
            <td>${bbs.createDt}</td>
          </tr>
          <tr class="reply-form show${vs.index} hidden">
            <td colspan="3">
              <form method="post"
                    action="${contextPath}/bbs/saveChild.do">
                <!-- 원글(부모)의 depth, group_no, group_order -->
                <input type="hidden" name="depth" value="${bbs.depth}">
                <input type="hidden" name="groupNo" value="${bbs.groupNo}">
                <input type="hidden" name="groupOrder" value="${bbs.groupOrder}">
                <div>작성자 : ${sessionScope.loginUser.name}</div>
                <div><textarea name="contents" class="contents" placeholder="답글을 작성하세요"></textarea></div>
                <div><button type="submit">작성완료</button></div>
              </form>
            </td>
          </tr>
        </c:if>
        <c:if test="${bbs.state == -1}">
          <tr>
            <td colspan="3">삭제된 글입니다.</td>
          </tr>
        </c:if>
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

  $('.open-reply-btn').on('click', evt=>{
    if(!signinCheck())
      return;
    const replyForm = $('.reply-form');
    const show = $('.show' + $(evt.target).data('index'));
    if(show.hasClass('hidden')){
      replyForm.addClass('hidden');  // 모든 replyForm 닫고
      show.removeClass('hidden');    // 작성할 replyForm 만 열기
    } else {
      replyForm.addClass('hidden');
    }
  })
  
  const signinCheck = ()=>{
    if('${sessionScope.loginUser}' === ''){
      if(confirm('로그인이 필요한 기능입니다. 로그인 페이지로 이동할까요?')){
        location.href = '${contextPath}/user/signin.page';
      } else {
        alert('취소되었습니다.');
        return false;
      }
    }
    return true;
  }
  
  $('.contents').on('click', evt=>{
    signinCheck();
  })
  
</script>

<%@ include file="../layout/footer.jsp" %>