<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
		<link rel="stylesheet" href="/timelord/resources/css/bootstrap.css">
		<style>
		      body {
		        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
		      }
		</style>
		<link href="/timelord/resources/css/bootstrap-responsive.css" rel="stylesheet">
        <title>Welcome to Timelord</title>
    </head>

    <body>
    	
    	<%@ include file="../common/header.jspf" %>
		
		<!-- Content -->
		<div class="container">
			<div class="row">
				<div class="span4">
					<!-- Admin stuff -->
				</div>
				<div class="span8">
					<h1>Users</h1>
					<table class="table table-bordered table-stripped">
					<thead><tr><td>Username</td><td>email</td><!--<td>Since</td> --></tr></thead>
					<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td> <c:out value="${user.login}" default="default"></c:out></td>
							<td> <c:out value="${user.email}" default="default"></c:out></td>
<%-- 							<td> <c:out value="${user.registrationDate}" default="default"></c:out></td> --%>
						</tr>
					</c:forEach>
					</tbody>
					</table>
				</div>
			</div>
		</div>
		
    </body>
</html>
