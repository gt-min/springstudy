/*********************************************
 * 파일명 : app04.js
 * 설  명 : 책(Book) 관리 JavaScript
 * 
 * 수정일      수정자  Version  Function
 * --------------------------------------------
 * 2024-07-01  민경태  1.0      Init()
 *                              contextPath()
 *                              GetBooks()
 *********************************************/


const books = $('#books');
const bookNo = $('#bookNo');
const title = $('#title');
const author = $('#author');
const initBtn = $('#init-btn');
const registerBtn = $('#register-btn');
const modifyBtn = $('#modify-btn');
const removeBtn = $('#remove-btn');


const Init = ()=>{
  initBtn.on('click', evt=>{
    bookNo.val('').prop('readonly', false);
    title.val('');
    author.val('');
    registerBtn.prop('disabled', false);
    modifyBtn.prop('disabled', true);
    removeBtn.prop('disabled', true);
  })
}

const contextPath = ()=>{
  const url = location.href;   /* http://localhost:9090/app04/api/users */
  const host = location.host;  /* localhost:9090 */
  const beginIndex = url.indexOf(host) + host.length;
  const endIndex = url.indexOf('/', beginIndex + 1);
  return url.substring(beginIndex, endIndex);  // include beginIndex, exclude endIndex
}

const GetBooks = (url, dataType)=>{
  $.ajax({
    type: 'get',
    url: contextPath() + url,
    dataType: dataType
  }).done(resData=>{
    
    books.empty();
    
    if(dataType === 'xml') {      
      $.each(resData.getElementsByTagName('book'), (i, book)=>{
        let str = '<tr>';
        str += '<td>' + book.getElementsByTagName('bookNo')[0].textContent + '</td>';
        str += '<td>' + book.getElementsByTagName('title')[0].textContent + '</td>';
        str += '<td>' + book.getElementsByTagName('author')[0].textContent + '</td>';
        str += '<td><button type="button" class="detail-btn" data-book-no="' + book.getElementsByTagName('bookNo')[0].textContent + '">상세</button></td>';
        str += '</tr>';
        books.append(str);
      });
    } else if(dataType === 'json') {      
      $.each(resData.books, (i, book)=>{
        let str = '<tr>';
        str += '<td>' + book.bookNo + '</td>';
        str += '<td>' + book.title + '</td>';
        str += '<td>' + book.author + '</td>';
        str += '<td><button type="button" class="detail-btn" data-book-no="' + book.bookNo + '">상세</button></td>';
        str += '</tr>';
        books.append(str);
      })
    }
    
  }).fail(jqXHR=>{
    alert(jqXHR.responseText);
  })
}

const GetBookByNo = ()=>{
  $(document).on('click', '.detail-btn', evt=>{
    $.ajax({
      type: 'get',
      url: contextPath() + '/api/books/' + evt.target.dataset.bookNo,
      dataType: 'json'
    }).done(resData=>{
      bookNo.val(resData.book.bookNo).prop('readonly', true);
      title.val(resData.book.title);
      author.val(resData.book.author);
      registerBtn.prop('disabled', true);
      modifyBtn.prop('disabled', false);
      removeBtn.prop('disabled', false);
    }).fail(jqXHR=>{
      alert(jqXHR.responseText);
    })
  })
}

const RegisterBook = ()=>{
  registerBtn.on('click', evt=>{
    $.ajax({
      type: 'post',
      url: contextPath() + '/api/books',
      contentType: 'application/json',
      data: JSON.stringify({
        bookNo: bookNo.val(),
        title: title.val(),
        author: author.val()
      }),
      dataType: 'json'
    }).done(resData=>{
      if(resData.isSuccess){
        alert(resData.inserted + '에 등록되었습니다.');
        GetBooks('/api/books', 'json');
        initBtn.trigger('click');
      } else {
        alert('책 등록이 실패했습니다.');
      }
    }).fail(jqXHR => {
      alert(jqXHR.responseText);
    })
  })
}

const ModifyBook = ()=>{
  modifyBtn.on('click', evt=>{
    $.ajax({
      type: 'put',
      url: contextPath() + '/api/books',
      contentType: 'application/json',
      data: JSON.stringify({
        bookNo: bookNo.val(),
        title: title.val(),
        author: author.val()
      }),
      dataType: 'json'
    }).done(resData=>{
      if(resData.isSuccess){
        alert(resData.updated + '에 수정되었습니다.');
        GetBooks('/api/books', 'json');
      } else {
        alert('책 정보가 수정되지 않았습니다.');
      }
    }).fail(jqXHR=>{
      alert(jqXHR.responseText);
    })
  })
}

const RemoveBook = ()=>{
  removeBtn.on('click', evt=>{
    if(!confirm('삭제할까요?')){
      alert('취소되었습니다.');
      return;
    }
    $.ajax({
      type: 'delete',
      url: contextPath() + '/api/books/' + bookNo.val(),
      dataType: 'json'
    }).done(resData=>{
      if(resData.isSuccess){
        alert(resData.deleted + '에 삭제되었습니다.');
        GetBooks('/api/books.json', 'json');
        initBtn.trigger('click');
      } else {
        alert('책이 삭제되지 않았습니다.');
      }
    }).fail(jqXHR=>{
      alert(jqXHR.responseText);
    })
  })
}


Init();
initBtn.trigger('click');
// GetBooks('/api/books', 'json');
// GetBooks('/api/books.json', 'json');
GetBooks('/api/books.xml', 'xml');
GetBookByNo();
RegisterBook();
ModifyBook();
RemoveBook();