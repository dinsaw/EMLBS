<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<s:head/>
</head>
<body bgcolor="orange">
<center>
<s:if test="%{appstatus}">
Application is set <span style="color:blue">On</span>.</s:if>
<s:else>
Application is set <span style="color:red">Off</span>.
</s:else>
</center>
</body>
</html>