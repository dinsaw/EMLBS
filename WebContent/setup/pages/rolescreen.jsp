<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="site.link.setup.rolescreen" /></title>
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<s:head />
<sj:head />
<sb:head />
</head>
<body>
	<tags:admintopbar />
	<tags:requestByStatus />
	<s:div id="screenmain">
		<s:iterator value="issuedRequests" var="myrequest">
		<div id="situation<s:property value="#myrequest.requestId"/>" class="situationBox" style="clear:both;padding-top: 20px;">
			<s:div id="firstHalf"><img
				src="requestImage?imageId=<s:property value="#myrequest.requestId"/>"
				alt="Alternate Text"></s:div>
			<s:div id="secondHalf">
			
		<s:form id="form%{#myrequest.requestId}" action="statuschange" theme="xhtml">
			<s:textfield key="sitn.id" name="reqId" value="%{#myrequest.requestId}"
				disabled="true" />
			<s:hidden name="requestId" value="%{#myrequest.requestId}"></s:hidden>
			<sj:textarea key="sitn.desc" value="%{#myrequest.description}" disabled="true"
				cols="40" rows="5" />
				<s:if test="%{#myrequest.requestStatus!=null}">
				<sj:radio id="radiobuttonset%{#myrequest.requestId}" list="requestStatusList" name="requestStatus"
				onChangeTopics="submitForm%{#myrequest.requestId}" buttonset="true"
				label="Situation Status" value="%{#myrequest.requestStatus.statusName}" />
				</s:if>
				<s:else>
				<sj:radio id="radiobuttonset%{#myrequest.requestId}" list="requestStatusList" name="requestStatus"
				onChangeTopics="submitForm%{#myrequest.requestId}" buttonset="true"
				label="Situation Status" value="{'Issued'}" />
				</s:else>
				<div id="formResult<s:property value="#myrequest.requestId"/>" class="result ui-widget-content ui-corner-all">
		<img id="indicator" src="<s:url value="/images/indicator.gif"/>"
			alt="Loading..." style="display: none" /> You can change status if required.
	</div>
			<br />
			<%-- <s:property value="%{#myrequest.requestStatus!=null}" default="default"/> --%>
			<sj:submit targets="formResult%{#myrequest.requestId}" value="AJAX Submit"
				indicator="indicator" button="true" listenTopics="submitForm%{#myrequest.requestId}"
				cssStyle="display:none;" />
			<s:token />
		</s:form>
		<s:form action="showOnMap">
		<s:hidden name="role" value="%{role}"/>
		<s:hidden name="id" value="%{#myrequest.requestId}" />
		<s:submit value="Show On Map"></s:submit>
		</s:form>
			<%-- <s:div cssStyle="float:left"><b>Situation Description</b> : <s:property value="#myrequest.description"/></s:div>
			<s:div cssStyle="float:left;clear:left"><b>Situation Id</b> : <s:property value="#myrequest.requestId" /></s:div> --%>
			</s:div>
			<hr style="clear:both;">
			</div>
		</s:iterator>
	</s:div>
</body>
</html>