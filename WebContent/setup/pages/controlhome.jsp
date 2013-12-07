<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="site.link.setup.controlhome"/></title>
<s:head />
<sj:head/>
<sb:head/>
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
</head>
<body>
<tags:admintopbar/>
<div id="wrap">
	<tags:header/>
	<tags:adminmenu/>
	<tags:adminsidepanel/>
	<div id="main">This is <br>control center
	</div>
	<tags:adminwarnsection/>
	<tags:footer/>
</div>
</body>
</html>