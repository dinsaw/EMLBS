<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="main">

<s:form action="Login" theme="bootstrap" cssClass="form-horizontal" label="Administrator Login">
<s:actionerror theme="bootstrap"/>
<s:actionmessage theme="bootstrap"/>
<s:fielderror theme="bootstrap"/>
<s:token></s:token>
<s:textfield name="userName" label="Admin UserName" required="true"/>
<s:password name="password" label="Password" required="true"/>
<s:submit value="Login" cssClass="btn" />
<s:hidden name="LOGINATTEMPT" value="LAGIN"></s:hidden>
</s:form>
</div>
