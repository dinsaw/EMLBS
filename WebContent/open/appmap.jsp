<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="site.link.setup.appmap"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src='<s:url value="/scripts/jquery.js"></s:url>'></script>
<script type="text/javascript" src='<s:url value="/scripts/gmaps.js"></s:url>'></script>
<script type="text/javascript">
    var map;
    $(document).ready(function(){
      map = new GMaps({
        div: '#map',
        lat: <s:property value="centerlat"/>,
        lng: <s:property value="centerlang"/>
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
    });
  </script>
</head>
<body>
<s:div id="popin"><s:div id="map" height="100%"></s:div></s:div>

</body>
</html>