<%@page import="Controllers.CreateTST"%>
<%@include file="init.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Engine</title>
<%
	CreateTST.createTrie();
%>

</head>
<body class="home">
	<div>
		<div class="home_wrapper">
			<div class="animate form home_form">
				<section class="home_content" style="width: 500px">
					<form name="searchForm"
						action="${pageContext.request.contextPath}/JSP/Controller.jsp"
						id="homeForm" method="post">
						<h1>Search Engine</h1>
						<div class="search-section">
							<input type="text" class="form-control" name="queryText"
								id="queryText" placeholder="Search..." autocomplete='off' />
						</div>
						<div>
							<button id="searchButton" type="submit"
								class="btn btn-primary submit" name="submit" value="search">Search</button>
							<button id="spellCheck" type="button"
								class="btn btn-success submit" name="submit" value="Spell Check">Spelling 
								Check</button>
						</div>
						<div class="separator">
							<br />
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
	<script type="text/javascript">
		$("#queryText").autocomplete("getdata.jsp", {
			extraParams : {
				filter : document.getElementById("queryText").value
			}
		});
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/index.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-dialog.min.js"></script>
</body>
</html>