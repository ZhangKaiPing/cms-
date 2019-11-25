<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详情</title>

</head>
<body>
	<div style="text-align: center" class="container">
		<dl>
			<dt>
			<h2>${article.title}</h2>
			</dt>
			
			<hr>
			<dd>
			<button type="button" class="btn btn-info" onclick="back()">返回列表</button>
			</dd>
			<dd><fmt:formatDate value="${aritcle.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dd>${article.content}</dd>
		
		</dl>
	
	
	</div>
</body>
<script type="text/javascript">
	function update(id,status){
		$.post(
		"/admin/article/update",{id:id,status:status},function(flag){
			if(flag){
				alert("操作成功");
				$("#center").load("/admin/article/articles")
			}
		}	
		)
	}

	
	function back(){
		$("#center").load("/my/selectByUser")
	}
	
	function query(){
		location.href="/my/";
	}

	

</script>
</html>