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
<script type="text/javascript" src='<s:url value="/scripts/jscolor/jscolor.js"></s:url>'></script>
<script type="text/javascript" src='<s:url value="/scripts/jquery.js"></s:url>'></script>
</head>
<body>


			<s:form id="overlayForm" action="addOTA" enctype="multipart/form-data" theme="bootstrap">
				 <s:actionerror theme="bootstrap"/>
					<s:actionmessage theme="bootstrap"/>
					<s:fielderror theme="bootstrap"/>
				<s:textfield name="overlayName" label="Overlay Name" required="true"></s:textfield>
				<s:textarea name="overlayDescription" label="Description"></s:textarea>
				<s:select name="whoState"
					list="{'Admin','User'}"
					label="Who will state overlays for this type" value="Admin"
					required="true" />
				<s:file name="overlayImage" accept="image/PNG"
					label="Image for Overlay" required="true"></s:file>
				<s:textfield name="colorString" cssClass="color" label="Pick a Color" required="true"/>
				<s:submit label="Save" value="Save" align="center" cssClass="btn"/>
				<s:reset label="Reset" align="center" cssClass="btn"/>
				<s:token />
			</s:form>
</body>
</html>