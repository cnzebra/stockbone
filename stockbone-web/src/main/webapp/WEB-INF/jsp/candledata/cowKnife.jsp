<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cow Knife - Welcome to Stockbone</title>
<script src="${basePath}/resources/js/jquery-1.7.2.js"></script>
<script src="${basePath}/resources/js/highcharts.js"></script>
<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        title: {
            text: 'fishing net',
            x: -20 
        },
        subtitle: {
            text: 'Source: stockbone.com',
            x: -20
        },
        yAxis: {
            title: {
                text: '价格 (元)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '元'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'average',
            data: [
			${average}
            ]
        }, {
            name: 'price',
            data: [${price}]
        }, {
            name: 'ma60',
            data: [${ma60}]
        }]
    });
});
function search(){
	var code = $("#code").val();
	window.location.href = "${basePath}/line/show?code=" + code;
}
</script>
</head>
<body>
编码:<input type="text" id="code" value="${code}"></input>
<input type="button" value="搜索" onclick="search()"></input>
<div id="container" style="min-width:100%;height:400px"></div>
<c:forEach  var="candle" items="${meat}">
${candle.formatTime}<br>
</c:forEach>
</body>
</html>