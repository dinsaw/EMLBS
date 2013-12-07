<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<sj:head/>
		
		
		<sj:menu id="sidemenu" cssStyle="float:left;padding:10px;margin-top:10px;margin-bottom:10px;">
			<sj:menuItem title="Control Home" href="controlhome"/>
			<sj:menuItem title="Application" href="appsetup"/>
			<sj:menuItem title="Roles" href="roles"/>
			<sj:menuItem title="Account">
				<sj:menu>
					<sj:menuItem title="Log Out" href="Logout"/>
					<sj:menuItem title="Change Password" href="changepass"/>
				</sj:menu>
			</sj:menuItem>
		</sj:menu>
	
	
