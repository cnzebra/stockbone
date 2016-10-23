<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock list - Welcome to Stockbone</title>
<script src="${basePath}/resources/js/jquery-1.7.2.js"></script>
<script src="${basePath}/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function submitForm(formId){
	var date = new Date($("#datePicker").val());
	var localOffset = date.getTimezoneOffset() * 60 * 1000;
	$("#goPublicTime").val(date.getTime() + localOffset);
	var param = $("#" + formId).serialize();
    $.post("${basePath}/stock/add.json", param, function(result){
    	alert("添加条数:" + result); 
    }, "text");
}
</script>
</head>
<body>
	<br><br><br><br>
	<center>
		<form id="addStockForm" action="${basePath}/stock/add" method="post">
		<table border="1" width="400px">
			<tr>
				<td align="center" colspan="2">Add stock</td>
			</tr>
			<tr>
				<td align="right">code:</td>
				<td align="left"><input type="text" id="code" name="code" /></td>
			</tr>
			<tr>
				<td align="right">name:</td>
				<td align="left"><input type="text" id="name" name="name" /></td>
			</tr>
			<tr>
				<td align="right">exchange:</td>
				<td align="left">
					<select name="exchange" id="exchange">
						<option value="SH">SH</option>
						<option value="SZ">SZ</option>
						<option value="HK">HK</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">goPublicTime:</td>
				<td align="left">
				<input type="text" id="datePicker" onClick="WdatePicker()" />
				<input type="hidden" id="goPublicTime" name="goPublicTime" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" onclick="submitForm('addStockForm')" value="添加" /></td>
			</tr>
		</table>
		</form>
	</center>
</body>
</html>