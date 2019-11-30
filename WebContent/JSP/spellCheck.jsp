<%@page import="Controllers.MainController"%>
<%@include file="init.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spelling Check</title>
</head>
<body>
	<%
		String wordData = "";
		String results[] = {};
		try {
			wordData = request.getParameter("word");
			MainController mainController = new MainController();
			results = mainController.getSpellCheck(wordData).split(" , ");
			System.out.println("aaa: "+results);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	%>
	<div class="container">
  <!-- Content here -->
   
  <div class="row" style="padding-top:25px;text-align:center;">
    <div class="col-md-12">
    
    <div class="alert alert-success" role="alert">
  <h4 class="alert-heading">Spelling Check : <b><%=wordData%></b></h4>
  <hr>
  <h4 class="mb-0">Suggestions:</h4>
  <br>
  <%
		for (String string : results){
	%>
	<h2><span class="badge badge-info"><%=string %></span></h2>
	  
	
	<%
		}
	%>
</div>
    
	
    </div>
    
  </div>
</div>
</body>
</html>