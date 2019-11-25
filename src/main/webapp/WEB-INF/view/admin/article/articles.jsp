<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>

</head>
<body>
	<div style="text-align: center;">
		<div class="form-group form-inline">
			<label for="title">标题:</label><input id="title" type="text"
				name="title" value="${article.title}">&nbsp;
			
			文章状态:
			<select class="form-control form-control-sm" name="status" id="status">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
				<option value="">全部</option>
			
			</select>
			
			&nbsp;
			
			
			<button type="button" class="btn btn-sucess btn-sm" onclick="query()">
				查询</button>
		</div>
		<table class="table table-hover  table-bordered">
			<thead class="thead-light">
				<tr>
					<th>序号</th>
					<th>文章标题</th>
					<th>作者</th>
					<th>是否热门</th>
					<th>所属栏目</th>
					<th>所属分类</th>
					<th>更新时间</th>
					<th>是否删除</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${info.list}" var="a" varStatus="i">
					<tr>
						<td>${(info.pageNum-1)*info.pageSize+i.index+1}</td>
						<td>${a.title}</td>
						<td>${a.user.username}</td>
						<td><c:if test="${a.hot==0}">
								<button type="button" class="btn btn-info"
									onclick="update(this,${a.id})">否</button>
							</c:if> <c:if test="${a.hot==1}">
								<button type="button" class="btn btn-success"
									onclick="update(this,${a.id})">是</button>
							</c:if></td>
						<td>${a.channel.name}</td>
						<td>${a.category.name}</td>
						<td><fmt:formatDate value="${a.updated}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						
						<td>
						<c:if test="${a.deleted==0}">
								<button type="button" class="btn btn-warning"
									onclick="del(this,${a.id})">正常</button>
							</c:if> <c:if test="${a.deleted==1}">
								<button type="button" class="btn btn-danger"
									onclick="del(this,${a.id})">已删除</button>
							</c:if>
						</td>
						
						
						<td><button type="button" class="btn btn-info" onclick="detail(${a.id})">详情</button></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 引入分页信息 -->
		<jsp:include page="/WEB-INF/view/common/pages.jsp" />
		
	</div>
</body>
<script type="text/javascript">

//文章详情
	function detail(id){
		$("#center").load("/admin/article/article?id="+id);
	}


	//删除文章
	function del(obj,id){
		//0正常 1已删除
		var deleted=$(obj).text()=="正常"?"1":"0";
		$.post(
				"/admin/article/update",
				{deleted:deleted,id:id},
				function(flag){
					if(flag){
						alert("操作成功")	
						$(obj).text(deleted==0?"正常":"已删除");
						$(obj).attr("class",deleted=="1"?"btn btn-danger":"btn btn-warning")
					}			
				}
			)
	}


$(function(){
	$("#status").val('${article.status}');
})
	

	function query(){
		$("#center").load("/admin/article/articles?title="+$("[name='title']").val()+"&status="+$("[name='status']").val());
	}

	function goPage(page){
		 var url ="/admin/article/articles?page="+page+"&title="+$("[name='title']").val()+"&status="+$("[name='status']").val();
		 //在中间区域加载分页页面
		 $("#center").load(url);
		 
	 }
	
	

	function update(obj,id){
		//0正常 1停用
		var hot=$(obj).text()=="否"?"1":"0";
		$.post(
				"/admin/article/update",
				{hot:hot,id:id},
				function(flag){
					if(flag){
						alert("操作成功")	
						$(obj).text(hot==1?"是":"否");
						$(obj).attr("class",hot=="1"?"btn btn-success":"btn btn-info")
					}			
				}
			)
	}
	


</script>
</html>