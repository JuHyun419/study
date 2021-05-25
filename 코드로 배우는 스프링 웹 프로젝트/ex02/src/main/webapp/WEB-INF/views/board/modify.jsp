<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>
<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Read</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Read Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">
      
      <form role="form" action="/board/modify" method="post">
      		
      	  <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
      	  <input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
      	  <input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
		  <input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
      
          <div class="form-group">
            <label>Bno</label> <input class="form-control" name='bno' 
            value='<c:out value="${board.bno }"/>' readonly="readonly">
          </div>

          <div class="form-group">
            <label>Title</label>
            <input class="form-control" name='title' 
            value='<c:out value="${board.title }"/>'></input>
          </div>

          <div class="form-group">
            <label>Textarea</label>
            <textarea class="form-control" name='content'><c:out value="${board.content }" /></textarea >
          </div>
          
          <div class="form-group">
            <label>Writer</label>
            <input class="form-control" name='writer' 
            value='<c:out value="${board.writer }"/>' readonly="readonly"></input>
          </div>
          
          <div class="form-group">
          	<label>Update Date</label>
          	<input class="form-control" name='updateDate'
          	value='<fmt:formatDate pattern = "yyyy/MM/dd" value="${board.updateDate }" />'
          	readonly="readonly">
          </div>
          
          <button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
          <button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
          <button type="submit" data-oper='list' class="btn btn-info">List</button>
      </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
	$(document).ready(function(){
		
		// 24라인에서 <form role="form" action="/board/modify" method="post"> form 속성 받아옴.
		var formObj = $("form");	
		
		$('button').on("click", function(e){
			e.preventDefault();
			
			// 버튼 클릭했을때, 54~56 라인에 3개의 data-oper 값 중 하나 저장.
			var operation = $(this).data("oper");
			
			console.log("operation: " + operation);
			
			if(operation === 'remove'){
				
				// 위에서 받아온 formObj 속성에서 action 값을 /board/remove로 설정함.
				// 즉, BoardController 에서 해당 값의 컨트롤러가 실행됨.
				formObj.attr("action", "/board/remove");
			} else if(operation === 'list'){
				
				//self.location = "/board/list";
				//return;
				
				formObj.attr("action", "/board/list").attr("method", "get");
				
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty();
				
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			}
			formObj.submit();
		});
	});
</script>

<%@include file="../includes/footer.jsp"%>
