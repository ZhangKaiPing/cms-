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
<title>用户列表</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="text-align: center;">
		<div class="form-group form-inline">
			<label for="username">用户名:</label><input id="username" type="text"
				class="form-control form-control-sm" name="username" value="${user.username}">&nbsp;
			<button type="button" class="btn btn-success btn-sm"
			onclick="query()">查询
			</button>
		</div>
		<table class="table table-secondary">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>昵称</th>
					<th>性别</th>
					<th>生日</th>
					<th>注册时间</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pg.list}" var="a" varStatus="i">
					<tr>
						<td>${(info.pageNum-1)*info.pageSize+i.index+1}</td>
						<td>${a.username}</td>
						<td>${a.nickname}</td>
						<td>${a.gender==0?"男":"女"}</td>
						<td>
						<fmt:formatDate value="${a.birthday}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
						<fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
						<fmt:formatDate value="${a.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
						<c:if test="${a.locked==0}">
						<button type="button" class="btn btn-success" onclick="update(this,${a.id})">正常</button>
						</c:if>
						<c:if test="${a.locked==1}">
						<button type="button" class="btn btn-danger" onclick="update(this,${a.id})">禁用
						</button>
						</c:if>
						</td>
					</tr>
				
				
				</c:forEach>
			
			
			</tbody>
		</table>
		  <!-- 引入分页信息 -->
		<jsp:include page="/WEB-INF/view/common/pages.jsp"/>
			
	

	</div>
</body>
<script type="text/javascript">
	function query(){
		$("#center").load("/admin/user/users?username="+$("[name='username']").val());
	}
	 function goPage(page){
		 var url ="/admin/user/users?page="+page+"&username="+$("[name='username']").val();
		 //在中间区域加载分页页面
		 $("#center").load(url);
		 
	 }
	
	function update(obj,id){
		//0正常 1停用
		
		var locked=$(obj).text()=="正常"?"1":"0";
		
		$.post("/admin/user/update",
		{id:id,locked:locked},function(flag){
			if(flag){
				alert("操作成功")	
				$(obj).text(locked==1?"禁用":"正常");
				$(obj).attr("class",locked=="0"?"btn btn-success":"btn btn-danger")
			}else{
				alert("操作失败")
			}
		}		
		)
	}
	
</script>
</html>