<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:div id="leftbar">
Role : <s:property value="role"/><hr size="1">
Show Issued Requests By Status - <s:property value="showForStatus"/> (<s:property value="issuedRequests.size"/>)<hr size="1">
<s:form id="selectreqstatus" action="rolescreen" theme="bootstrap">
		<s:hidden name="reqStatus" value="All"></s:hidden>
				<s:hidden name="role" value="%{role}"></s:hidden>
		<s:submit cssClass="btn" value="All" cssStyle="width:100%"/>
		</s:form>
<s:iterator value="requestStatusList" var="reqStatus">
		<s:form id="selectreqstatus" action="rolescreen" theme="bootstrap">
				<s:hidden name="role" value="%{role}"></s:hidden>
		<s:hidden name="showForStatus" value='%{#reqStatus}'></s:hidden>
		<s:submit cssClass="btn" value="%{#reqStatus}" cssStyle="width:100%"/>
		</s:form>
</s:iterator>
</s:div>