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

  <div>
    <form action="${contextPath}/register.do"
          method="post"
          enctype="multipart/form-data">
      <div>
        <label for="uploader">작성자</label>
        <input type="text" name="uploader" id="uploader">
      </div>
      <div>
        <input type="file" name="files" id="files" multiple="multiple">
      </div>
      <div>
        <button type="submit">업로드하기</button>
      </div>
    </form>
  </div>
  
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
  
  <script>
  
    const isOversize = (file)=>{
      const limit = 1024 * 1024 * 10;  // 10MB(1024Byte === 1KB, 1024KB === 1MB)
      return file.size > limit;
    }
    
    const checkFile = (inp)=>{
      if(inp.files.length === 0) {
        alert('첨부된 파일이 없습니다.');
        return;       
      }
      let total = 0;
      const totalLimit = 1024 * 1024 * 100;  // 100MB(1024Byte === 1KB, 1024KB === 1MB)
      for(const file of inp.files) {
        if(isOversize(file)){
          alert('단일 첨부 파일의 최대 크기는 10MB입니다.');
          inp.value = '';
          return;
        }
        total += file.size;
      }
      if(total > totalLimit) {
        alert('전체 100MB 초과 용량은 업로드할 수 없습니다.');
        inp.value = '';
        return;
      }
    }
    
    document.getElementById('files').addEventListener('change', evt=>{
      checkFile(evt.target);
    })
  
  </script>

</body>
</html>