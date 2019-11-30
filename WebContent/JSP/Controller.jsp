<%@page import="Controllers.MainController"%>
<%
	String str = "";
	try{
		str = request.getParameter("submit");
	}catch(Exception e){
		e.printStackTrace();
	}
	if(str.equalsIgnoreCase("search")){
		String data = request.getParameter("query");
		request.setAttribute("submit", "fromController");
		request.setAttribute("searchString", data);
		request.getRequestDispatcher("/JSP/displayResult.jsp").forward(request, response); 
		//response.sendRedirect(request.getContextPath()+"/JSP/displayResult.jsp?submit=fromController&searchString=" + data);
	}else if(str.equalsIgnoreCase("checkSpell")){
		String data = request.getParameter("data");
		MainController cnt = new MainController();
		String result = cnt.getSpellCheck(data);
	}
%>
