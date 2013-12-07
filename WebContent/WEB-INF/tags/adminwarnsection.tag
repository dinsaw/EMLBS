<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:div id="warning">
	<ul>
		<s:iterator value="#session.warnings">
		<li><s:property/></li>
		</s:iterator>
	</ul>
</s:div>
