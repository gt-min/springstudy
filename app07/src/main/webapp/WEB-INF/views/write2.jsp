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

  <div>
    <div>
      <label for="uploader-inp">작성자</label>
      <input type="text" id="uploader-inp">
    </div>
    <div>
      <input type="file" id="files-inp" multiple="multiple">
    </div>
    <div>
      <button type="button" id="upload-btn">업로드하기</button>
    </div>
  </div>
  
  <script>
  
    const asyncUpload = ()=>{
      
      let formData = new FormData();
      
      const uploaderInp = document.getElementById('uploader-inp');
      formData.append('uploader', uploaderInp.value);
      
      const filesInp = document.getElementById('files-inp');
      for(const file of filesInp.files) {
        formData.append('files', file);        
      }
            
      $.ajax({
        type: 'post',
        url: '${contextPath}/register2.do',
        contentType: false,  /* Content-Type 헤더 값 생성 방지 */
        data: formData,      /* 서버로 보내는 데이터 */
        processData: false,  /* 객체를 보내는 경우 해당 객체를 {property: value} 형식의 문자열로 자동으로 변환해서 보내는데 이를 방지해야 한다. */
        dataType: 'json'
      }).done(resData=>{
        if(resData.isSuccess){
          alert('등록되었습니다.');
          location.href = '${contextPath}/list.do';
        } else {
          alert('등록이 실패했습니다.');
        }
      }).fail(jqXHR=>{
        alert(jqXHR.responseText);
      })
      
    }
  
    document.getElementById('upload-btn').addEventListener('click', evt=>{
      asyncUpload();
    })
  
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
    
    document.getElementById('files-inp').addEventListener('change', evt=>{
      checkFile(evt.target);
    })
  
  </script>

</body>
</html>