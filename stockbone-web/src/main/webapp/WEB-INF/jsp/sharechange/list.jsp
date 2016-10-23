<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock list - Welcome to Stockbone</title>
<script src="${basePath}/resources/js/jquery-1.7.2.js"></script>
<script src="${basePath}/resources/js/timeUtils.js"></script>
<script type="text/javascript">
function deleteShareChange(code,changeDate){
	if(!window.confirm('确定删除?')){
		return;
	}
	var url = "${basePath}/sharechange/delete?code=" + code + "&changeDate=" + changeDate;
	$.ajax({
	    type: 'GET',
	    url: url,
	    dataType: "text",
	    success: function(result){
	    	alert("删除条数:" + result);
	    	window.location.reload();
	    }
	});
}

function mergeShareChange(code){
	var url = "${basePath}/sharechange/merge?code=" + code;
	$.ajax({
	    type: 'GET',
	    url: url,
	    dataType: "text",
	    success: function(result){
	    	alert("merge条数:" + result);
	    	window.location.reload();
	    }
	});
}
function completeShareChange(code){
	var url = "${basePath}/sharechange/complete?code=" + code;
	$.ajax({
	    type: 'GET',
	    url: url,
	    dataType: "text",
	    success: function(result){
	    	alert("complete条数:" + result);
	    	window.location.reload();
	    }
	});
}
</script>
</head>
<body>
	<br><br><br><br>
	<center>
		<span>Share change list</span>
		<table width="900px" border="1">
			<tr>
				<td colspan="7">
					<a href="${basePath}/sharechange/addAndEditPage">添加</a>
					<a href="javascript:mergeShareChange(${shareChangeStockCode })">合并</a>
					<a href="javascript:completeShareChange(${shareChangeStockCode })">补全</a>
				</td>
			</tr>
			<tr>
				<td width="14%">code</td>
				<td width="14%">change date</td>
				<td width="14%">AShares</td>
				<td width="14%">BShares</td>
				<td width="14%">TotalShares</td>
				<td width="14%">changeReason</td>
				<td width="14%">操作</td>
			</tr>
			<c:forEach items="${page.content}" var="sharechange">
			<tr>
				<td>${sharechange.code}</td>
				<td>
					<c:if test="${sharechange.changeDate != 0}">
						<script type="text/javascript">document.write(dateFormat(new Date(${sharechange.changeDate})));</script>
					</c:if>
				</td>
				<td>${sharechange.AShares}</td>
				<td>${sharechange.AShares}</td>
				<td>${sharechange.totalShares}</td>
				<td>${sharechange.changeReason}</td>
				<td>
					<a href="javascript:deleteShareChange(${sharechange.code},${sharechange.changeDate});void(0)">delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>