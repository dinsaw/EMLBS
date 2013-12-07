<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<s:head />
<sb:head />
</head>
<body>
			<s:form id="requestStatusForm" action="addRS" theme="bootstrap">
				<s:actionerror theme="bootstrap"/>
				<s:actionmessage theme="bootstrap"/>
				<s:fielderror theme="bootstrap"/>
				<s:textfield name="requestStatusName" label="Status Name" required="true"></s:textfield>
				<s:textarea name="requestStatusDescription" label="Status Description"></s:textarea>
				<s:submit label="Save" value="Save" align="center" cssClass="btn"/>
				<s:reset label="Reset" align="center" cssClass="btn"/>
				<s:token />
			</s:form>
</body>
</html>