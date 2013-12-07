<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic App Config</title>
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<s:head />
<sb:head />
<sj:head jqueryui="true" />

</head>
<body>
	<div id="formResult" class="result ui-widget-content ui-corner-all">
		<img id="indicator" src="<s:url value="/images/indicator.gif"/>"
			alt="Loading..." style="display: none" /> Use on/off button to set
		application active/inactive.
	</div>

	<s:iterator value="apps">

		<s:form id="form1" action="appstatuschange" theme="xhtml">
			<s:textfield key="app.label.name" name="appname" value="%{appname}"
				disabled="true" />
			<sj:radio id="radiobuttonset" list="appstatuslist" name="appstatus"
				onChangeTopics="submitForm1" buttonset="true"
				label="Activation Status" value="defaultAppStatus" />
			<br />
			<sj:submit targets="formResult" value="AJAX Submit"
				indicator="indicator" button="true" listenTopics="submitForm1"
				cssStyle="display:none;" />
			<sj:textarea key="app.label.area" value="%{area}" disabled="true"
				cols="25" rows="10" />
			<s:token />
		</s:form>
	</s:iterator>
	<%--   <s:url var="remoteurl" value="/open/getMapPolyAsText"/>
 
 
    <sj:dialog id="mydialog3" href="%{remoteurl}" title="View Map" autoOpen="false" width="500" position="center"/>
   --%>
	<sj:dialog id="mydialog3" title="Map - Boundary of Application"
		autoOpen="false" width="800" height="500" position="center">
		<iframe id="mapframe" src='<s:url value="/open/getMapPolyAsText"/>'
			scrolling="no" frameborder="0" width="100%" height="100%">Your
			browser can't render iframe.</iframe>
	</sj:dialog>
	
	<sj:a openDialog="mydialog3">Map - Boundary of Application</sj:a><br>
	<s:a value="/open/getMapPolyAsText" title="Map" target="_blank">Map</s:a>
	<%-- 			<s:div id="popin"><s:div id="map"></s:div></s:div>
 --%>
</body>
</html>