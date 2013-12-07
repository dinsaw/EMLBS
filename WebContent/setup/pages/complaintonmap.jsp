<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="site.link.setup.complaintonmap" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<sj:head debug="true" />
<s:head />
<sb:head />
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src='<s:url value="/scripts/gmaps.js"></s:url>'></script>
<script type="text/javascript">
var inilatlng;
var prevatlng;
var currlatlng;
var noofpt = 0;
var pts = new Array();
var polygon;
var complete=0;
    var map;
    $(document).ready(function(){
      map = new GMaps({
        div: '#configmap',
        lat: <s:property value="centerlat"/>,
        lng: <s:property value="centerlang"/>,
        
      });

      var path = <s:property value="path"/>;

      polygon = map.drawPolygon({
			  paths: path,
			  strokeColor: '#BBD8E9',
			  strokeOpacity: 1,
			  strokeWeight: 3,
			  fillColor: '#BBD8E9',
			  fillOpacity: 0.6
			});
      map.addMarker({
    	  lat: <s:property value="centerlat"/>,
    	  lng: <s:property value="centerlang"/>,
    	  title: '<s:property value="%{issuedRequest.requestId}"/>:<s:property value="%{issuedRequest.description}"/>'
      });
    });
</script>

</head>
<body bgcolor="gray">
	
	<tags:admintopbar/>
    	  <s:div cssStyle="top:30px;position:relative">
		<s:div id="configpopin">
			<s:div id="configmap"></s:div>
		</s:div>
		<s:form id="selectreqhandler" action="rolescreen" theme="bootstrap" method="get">
		<s:hidden name="role" value='%{role}'></s:hidden>
		<s:submit cssClass="btn" value="<<   Back To Role Screen" cssStyle="width:50%"/>
		</s:form>
		<s:form id="fullscreen" action="complaintMapFullScreen" theme="bootstrap" method="get">
		
		<s:submit cssClass="btn" value="Fullscreen" cssStyle="width:50%"/>
		</s:form>
		
		<s:div id="overlayForm" cssStyle="height:100%;width:25%;float:right;">
			<s:div id="onlyImage">
		<img
				src="requestImage?imageId=<s:property value="issuedRequest.requestId"/>"
				alt="Alternate Text">
		</s:div>
			<s:form id="form%{issuedRequest.requestId}" action="statuschange" theme="xhtml">
			<s:textfield key="sitn.id" name="reqId" value="%{issuedRequest.requestId}"
				disabled="true" />
			<s:hidden name="requestId" value="%{issuedRequest.requestId}"></s:hidden>
			<sj:textarea key="sitn.desc" value="%{issuedRequest.description}" disabled="true"
				cols="40" rows="5" />
				<s:if test="%{issuedRequest.requestStatus!=null}">
				<sj:radio id="radiobuttonset%{issuedRequest.requestId}" list="requestStatusList" name="requestStatus"
				onChangeTopics="submitForm%{issuedRequest.requestId}" buttonset="true"
				label="Situation Status" value="%{issuedRequest.requestStatus.statusName}" />
				</s:if>
				<s:else>
				<sj:radio id="radiobuttonset%{issuedRequest.requestId}" list="requestStatusList" name="requestStatus"
				onChangeTopics="submitForm%{issuedRequest.requestId}" buttonset="true"
				label="Situation Status" value="{'Issued'}" />
				</s:else>
				<div id="formResult<s:property value="issuedRequest.requestId"/>" class="result ui-widget-content ui-corner-all" style="clear:right">
		<img id="indicator" src="<s:url value="/images/indicator.gif"/>"
			alt="Loading..." style="display: none" /> You can change status if required.
	</div>
			<br />
			<%-- <s:property value="%{issuedRequest.requestStatus!=null}" default="default"/> --%>
			<sj:submit targets="formResult%{issuedRequest.requestId}" value="AJAX Submit"
				indicator="indicator" button="true" listenTopics="submitForm%{issuedRequest.requestId}"
				cssStyle="display:none;" />
			<s:token />
		</s:form>
			<%-- <s:div cssStyle="float:left"><b>Situation Description</b> : <s:property value="issuedRequest.description"/></s:div>
			<s:div cssStyle="float:left;clear:left"><b>Situation Id</b> : <s:property value="issuedRequest.requestId" /></s:div> --%>
	
			</s:div>
			
		</s:div>
</body>
</html>

