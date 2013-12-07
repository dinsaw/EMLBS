<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="nav">
		<ul>
			<s:url value="/testing/index" var="testindexurl"/>
			<s:url value="/index" var="indexurl"/>
			
			<li><s:a href="%{indexurl}" >Home</s:a>&nbsp;|</li>
			<li><a href="<s:url value="index"/>">Board</a>&nbsp;|</li>
			<li><a href="complaints.jsp">Downloads</a>&nbsp;|</li>

			<li><a href="contact.jsp">Help</a>&nbsp;|</li>
			<li><a href="op.jsp">About</a>&nbsp;|</li>
			<li><s:a href="%{testindexurl}"><s:text name="site.link.testing"/></s:a>&nbsp;</li>
		</ul>
	</div>
	
