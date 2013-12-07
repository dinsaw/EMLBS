<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="website.title"/></title>
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
</head>
<body>
<div id="wrap">
	<tags:header/>
	<tags:menu></tags:menu>
	<tags:defmain></tags:defmain>
	<tags:sidepanel></tags:sidepanel>
	<tags:footer></tags:footer>

</div>

<!--  <div id="container">
  <div id="title">
      <b style="font-size:150%"><s:text name="website.title"/></b>
  </div>
  <div id="leftbar">
      <b>Main Menu</b><br />
      HTML<br />
      PHP<br />
      PERL...
  </div>
  <div  id="content">
	Technical and Managerial Tutorials
  </div>
  <div id="footer">
  <center><s:text name="developer.line"/></center>
      
 
  </div>
</div>-->
</body>
</html>