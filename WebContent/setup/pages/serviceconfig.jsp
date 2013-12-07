<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Config</title>

<s:head />
<sb:head />
<sj:head />
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
</head>
<body>
	<ul>
		<li><sj:a openDialog="overlayForm">Add new OverlayType</sj:a>
		<li><sj:a openDialog="handelerForm">Add new RequestHandeler</sj:a>
		<li><sj:a openDialog="statusForm">Add Request Status</sj:a>
	</ul>
	<sj:dialog position="center" id="overlayForm" autoOpen="false"
		modal="true" closeOnEscape="true" title="New OverlayType" width="600"
		resizable="true">
		<iframe id="otframe" src='<s:url value="/setup/addOTDialog"/>'
			scrolling="no" frameborder="0" width="600" height="500"></iframe>
	</sj:dialog>
	<sj:dialog position="center" id="handelerForm" autoOpen="false"
		modal="true" closeOnEscape="true" title="New RequestHandeler" width="600"
		resizable="true">
		<iframe id="rhframe" src='<s:url value="/setup/addRHDialog"/>'
			scrolling="no" frameborder="0" width="600" height="500"></iframe>
	</sj:dialog>
	<sj:dialog position="center" id="statusForm" autoOpen="false"
		modal="true" closeOnEscape="true" title="New Request Status" width="600"
		resizable="true">
		<iframe id="rhframe" src='<s:url value="/setup/addRSDialog"/>'
			scrolling="no" frameborder="0" width="600" height="500"></iframe>
	</sj:dialog>
</body>
</html>