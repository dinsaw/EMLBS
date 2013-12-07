<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="main">
	<s:form action="dochangepass" theme="bootstrap" cssClass="form-horizontal" label="Change Account Password">
	<s:actionerror theme="bootstrap"/>
	<s:actionmessage theme="bootstrap"/>
	<s:fielderror theme="bootstrap"/>
	<s:password name="currPass" key="website.form.currentpass" required="true"/>
	<s:password name="newPass" key="website.form.newpass" required="true" tooltip="Atleast 8 characters long."/>
	<s:password name="newPassAgain" key="website.form.newpassagain" required="true"/>
	<s:submit value="Change Password" align="center" cssClass="btn"/>
	<s:token/>
	</s:form>
</div>