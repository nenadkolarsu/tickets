<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
  <title>Ticketing</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
<!--   resources/static/ -->
 
      <style type="text/css">
    ul>li, a{cursor: pointer;}
    </style>
<!--     	<script -->
<!-- 			  src="http://code.jquery.com/jquery-3.3.1.js" -->
<!-- 			  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" -->
<!-- 			  crossorigin="anonymous"> -->
<!--     	</script> -->
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/">Ticket reservation</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
<!--         <li class="active"><a href="/">Home</a></li> -->
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Main data <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href='/cinemas.html'>Cinemas</a></li>
            <li><a href='/theatres.html'>Theatres</a></li>
            <li><a href='/projections.html'>Projections</a></li>
            <li><a href='/movies.html'>Movies</a></li>
            <li><a href='/reservations.html'>Reservations</a></li>
<!--             <li><a href="#">Seats</a></li> -->
          </ul>
        </li>
        
        <li><a href='/availableprojections.html'>Make reservation</a></li>
        <li><a href="/reservations.html">View reservations</a></li>

        <li><a href="#">Page 3</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>