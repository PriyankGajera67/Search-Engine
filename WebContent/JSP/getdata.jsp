<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.AutoComplete"%>
<%
	String query = request.getParameter("filter");
	System.out.println(query);
	List<String> wordSuggestonData = AutoComplete.getAutoCompleteData(query);

	Iterator<String> itr = wordSuggestonData.iterator();
	while (itr.hasNext()) {
		String wordData = (String) itr.next();
		out.println(wordData);
	}
%>
