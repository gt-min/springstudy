<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="Blog write" name="title"/>
</jsp:include>

<h1 class="title">Blog write</h1>

<form id="blog-write-form"
      method="post"
      action="${contextPath}/blog/saveBlog.do">

  <div>
    <label for="title">제목</label>
    <input type="text" name="title" id="title">
  </div>

  <div>
    <textarea name="contents" id="contents" placeholder="내용 작성"></textarea>
  </div>
  
  <div>
    <button type="submit">작성완료</button>
    <button type="button" onclick="history.back()">취소하기</button>
  </div>
      
</form>

<script>

$('#contents').summernote({
  toolbar: [
    ['style', ['bold', 'italic', 'underline', 'clear']],
    ['font', ['strikethrough', 'superscript', 'subscript']],
    ['fontsize', ['fontsize']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['height', ['height']],
    ['insert', ['link', 'picture', 'video']]
  ],
  width: 1024,
  height: 500,
  lang: 'ko-KR',
  callbacks: {
    onImageUpload: function(files) {  // files : 추가한 이미지
      // files 를 업로드하는 ajax 처리
      for(let i = 0; i < files.length; i++){
        $.ajax({
          
        })
      }
      // 이따 위치 조정
      $('#summernote').summernote('insertImage', url, filename);      
    }
  }
});

</script>

<%@ include file="../layout/footer.jsp" %>