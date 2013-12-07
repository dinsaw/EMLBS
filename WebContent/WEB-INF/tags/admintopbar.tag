<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:div id="topbar">
<s:div cssStyle="float:left" id="topbartitle">
EMLBS | <s:a href="controlhome">Control Home</s:a>
</s:div>


<s:div id="topbarright">
<s:div>Logged as : <s:property value="#session.userName"/>&nbsp;|&nbsp;
<s:a value="Logout">Log Out</s:a>

</s:div>
</s:div></s:div>
