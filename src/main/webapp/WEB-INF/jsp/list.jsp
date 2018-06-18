<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>
<body>
	<h1>用户管理</h1>
	<table>
		<thead>
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>年龄</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.age}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<table>
		<thead>
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>年龄</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="users">
				<tr>
					<td><s:property value="id"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="age"/></td>
				</tr>
			</s:iterator>
				
		</tbody>
	</table>
	<h2>显示上下文环境区域信息</h2>
	<p> <s:property value="#request.message"/> </p>
	<h2>显示内容区域的信息</h2>
	<p> <s:property value="myMessage"/> </p>
	<h2>显示ValueStack信息</h2>
	<s:debug></s:debug>
</body>
</html>