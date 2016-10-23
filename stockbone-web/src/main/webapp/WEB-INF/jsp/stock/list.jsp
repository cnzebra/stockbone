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
function deleteStock(code){
	if(!window.confirm('确定删除?')){
		return;
	}
	var url = "${basePath}/stock/delete?code=" + code;
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
</script>
</head>
<body>
	<br><br><br><br>
	<center>
		<span>Stock list</span>
		<table width="1000px" border="1">
			<tr>
				<td colspan="6"><a href="${basePath}/stock/addAndEditPage">Add</a></td>
			</tr>
			<tr>
				<td width="10%">code</td>
				<td width="15%">name</td>
				<td width="10%">exchange</td>
				<td width="25%">goPublicTime</td>
				<td width="10%">update</td>
				<td width="30%">operation</td>
			</tr>
			<c:forEach items="${page.content}" var="stock">
			<tr>
				<td>${stock.code}</td>
				<td>${stock.name}</td>
				<td>${stock.exchange}</td>
				<td>
					<c:if test="${stock.goPublicTime != 0}">
						<script type="text/javascript">document.write(dateFormat(new Date(${stock.goPublicTime})));</script>
					</c:if>
				</td>
				<td>
					<form action="${basePath}/candledata/uploadCandleData?code=${stock.code}" method="post" enctype="multipart/form-data">
						<input type="file" name="candleDataFile">
						<input type="submit" value="update">
					</form>
				</td>
				<td>
					<a href="${basePath}/candledata/cowKnife?code=${stock.code}">knife</a>
					<a href="${basePath}/sharechange/list?code=${stock.code}">ShareChanges</a>
					<a href="">edit</a>
					<a href="javascript:deleteStock(${stock.code});void(0)">delete</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>