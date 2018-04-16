<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Print preview</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel='stylesheet' href='jsp/printing/style.css' type='text/css' />

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

<body>
    
    <page size="A4" class="print">
    
        <div class='container-fluid'>
            <div class='row spacer-top-20'>
                <div class='col-xs-12'>
                    <div class='row text-center'>
                        <h4>Review of ${title}</h4>
                    </div>
                </div>
                <div class='col-xs-12 spacer-top-15'>
                    <div class='row'>
                       <div class='col-xs-1'>Date:</div>
                       
						<c:set var="today" value="<%=new Date()%>"/>
						<c:set var="tomorrow" value="<%=new Date(new Date().getTime() + 60*60*24*1000)%>"/>
						                       
                        <div class='col-xs-2'> <fmt:formatDate type="date" value="${today}" pattern="dd.MM.yyyy"/></div>
                    </div>
                </div>
            </div>
            <div class='row spacer-top-10'>
                <div class='col-xs-12'>
                    <table class='tableDocuments'>
                        <thead>
                            <th style="width:5%">No</th>
                            <th style="width:7%">Id</th>
                            <th style="width:10%">Code</th>
                            <th style="width:20%">Name</th>
                            <th style="width:20%">Remark</th>
                            <th style="width:10%">Status</th>
                            <th style="width:25%"'>Timestamp</th>
                        </thead>
                        <tbody>
                                                     
				<!--             pocetak petlje -->   
				<c:set var="rbr" value="1"/>
				<c:forEach items="${states}" var="states">                     
                            <tr>
                                <td>${rbr}.</td>
                                <td>${states.id}</td>
                                <td>${states.code}</td>
                                <td>${states.name}</td>
                                <td>${states.remark}</td>
                                <td>${states.status}</td>
                                <td>${states.timestamp}</td>
                            </tr>
                            
                  <c:set var="rbr" value="${rbr + 1}" />
                                                        
                 </c:forEach>                                                                
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </page>
    <br />
    <a href="#" class="noprint btn btn-lg btn-primary btn-flat" onClick="window.print()">Print </a>
        
    <script>

    </script>

</body>

</html> 