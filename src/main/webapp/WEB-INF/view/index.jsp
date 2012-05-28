<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    	
    	<%@ include file="./common/header.jspf" %>
		
		<!-- Content -->
		<div class="container">
			<div class="row">
				<div class="span4">
					<sec:authorize ifNotGranted="ROLE_USER">
					<!-- Registration form -->
					<%@include file="./user/create.jspf" %>
					</sec:authorize>
				</div>
				<div class="span8">
					<h1>Welcome to Timelord</h1>
					<p>Timelord is a schedule management application. It helps you manage your TODOs, appointments and much more !
					 Right now, it does not do a lot of things but well, it is some kind of a toy project.</p>
				</div>
			</div>
		</div>
		
    </body>
</html>
