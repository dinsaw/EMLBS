<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="site.link.setup.details"/></title>
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<sj:head debug="true"/>
</head>
<body>
<tags:admintopbar/>
<div id="wrap">
	<tags:header/>
	<tags:adminmenu/>
	<tags:adminsidepanel/>
	<div id="main">
		<sj:tabbedpanel id="tjpannel" heightStyle="content" >
			<sj:tab id="t1" label="Basic Config" href="appdataaction"/>
			<sj:tab id="t2" label="Service Config" href="serviceconfig"/>
			<sj:tab id="t3" target="tt3" label="Overlay Config"/>
			<sj:div id="tt3">
				<ul>
				<s:url value="overlayConfigScreen" var="ocsvar"></s:url>
				<li><s:a name="Point Overlay Config Screen" value="%{ocsvar}" target="_blank">Point Overlay Config Screen</s:a></li>
				<s:url value="locoNewsConfigScreen" var="locourl"></s:url>
				<li><s:a name="Loco News Config Screen" value="%{locourl}" target="_blank">LocoNews Config Screen</s:a></li>
				<%-- <s:url value="overlayConfigScreen" var="ocsvar"></s:url>
				<li><s:a name="Polygon Overlay Config Screen" value="%{ocsvar}" target="_blank">Polygon Overlay Config Screen</s:a></li>
				 --%></ul>		
			</sj:div>
		</sj:tabbedpanel>
		
	</div>
	<tags:adminwarnsection/>
	<tags:footer/>
</div>
</body>
</html>