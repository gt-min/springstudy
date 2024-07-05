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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>번호 : ${upload.uploadNo}</div>
  <div>업로더 : ${upload.uploader}</div>
  <div>IP : ${upload.ip}</div>
  <div>날짜 : ${upload.uploadDt}</div>

  <hr>
  
  <div>첨부 목록</div>
  <div>
    <button type="button" id="down-all-btn">모두 다운로드</button>
  </div>
  <c:forEach items="${fileList}" var="file">
    <div>
      <span>${file.originalFilename}(${file.downCount})</span>
      <button type="button" class="down-btn" data-file-no="${file.fileNo}">다운로드</button>  
    </div>
  </c:forEach>
  
  <script>
  
    const downloadOne = (btn)=>{
      if(!confirm('해당 파일을 다운로드 할까요?')){
        alert('취소되었습니다.');
        return;
      }
      location.href = '${contextPath}/download.do?fileNo=' + btn.dataset.fileNo;
    }
  
    $('.down-btn').on('click', evt=>{
      downloadOne(evt.target);
    })
    
    const downloadAll = ()=>{
      if(!confirm('모든 파일을 다운로드 할까요?')){
        alert('취소되었습니다.');
        return;
      }
      location.href = '${contextPath}/downloadAll.do?uploadNo=${upload.uploadNo}';
    }
    
    $('#down-all-btn').on('click', evt=>{
      downloadAll();
    })
    
  </script>

</body>
</html>