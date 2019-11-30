<%@page import="java.util.HashMap"%>
<%@page import="com.sun.javafx.collections.MappingChange.Map"%>
<%@page import="Controllers.MainController"%>
<%@include file="init.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title></title>
</head>
<%
	HashMap<String, Integer> searchData = null;
	HashMap<String, Integer> sortedData = null;
	try {
		String submit = request.getAttribute("submit").toString();
		String data = "";
		MainController cnt = new MainController();
		if (submit.equalsIgnoreCase("fromController")) {
			data = request.getAttribute("searchString").toString();
			searchData = cnt.countWords(data.trim());
			sortedData = cnt.sortedResult(searchData);
		} else {
			response.sendError(500);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<body>
	<div class="container" style="margin-top: 20px;">

		<div class="card" style="width: 100%;">
			<div class="card-body">
				<div class="row">
					<div class="col-md-12">
						<table id="searchResult" border="1" align="center" class="table">
							<thead>
								<tr>
									<th>Id</th>
									<th style="width: 50%">Page</th>
									<th>Word Occurance</th>
								</tr>
							</thead>
							<tbody>
								<%
									Object keys[] = sortedData.keySet().toArray();
									for (int i = 1; i <= 100; i++) {
								%>
								<tr>
									<td><%=i%></td>
									<td><a
										href="<%=request.getContextPath() + "/JSP/htmls/" + keys[i - 1]%>"><%=keys[i - 1]%></a></td>
									<td><%=sortedData.get(keys[i - 1].toString())%></td>
								</tr>
								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#searchResult').DataTable();
		});
	</script>
</body>
</html>