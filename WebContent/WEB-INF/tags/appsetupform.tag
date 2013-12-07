<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div id="main" style="width:90%">This application setup.
	<s:form action="appaction" theme="bootstrap" cssClass="form-horizontal" label="Basic Application Setup">
		<s:actionerror theme="bootstrap"/>
		<s:actionmessage theme="bootstrap"/>
		<s:fielderror theme="bootstrap"/>
		<s:textfield name="appName" key="website.form.label.appname" required="true"/>
		<s:textarea id="polyPoints" name="polyPoints" key="website.form.label.selectmaparea" cols="50" rows="3" required="true"></s:textarea>
		<s:submit cssClass="btn"/>
	</s:form>
	<s:div id="popin"><s:div id="map"></s:div></s:div>
</div>