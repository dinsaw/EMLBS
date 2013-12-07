<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="site.link.setup.overlayconfig.point"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<s:head/>
<sb:head/>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src='<s:url value="/scripts/jquery.js"></s:url>'></script>
<script type="text/javascript" src='<s:url value="/scripts/gmaps.js"></s:url>'></script>
<script type="text/javascript">
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
			  fillOpacity: 0.6,
			  click: function(e){
				  	map.removeMarkers();
		        	currlatlng=e.latLng;	
		        	map.addMarker({
		        		lat: currlatlng.lat(),
		          	  	lng: currlatlng.lng(),
		          	  	title: 'Save this point by using Form'
		        		});
		        	$('#lat').val(currlatlng.lat());
		        	$('#lang').val(currlatlng.lng());
		        	}
			});
      
    });
  </script>
</head>
<body bgcolor="gray">
<tags:admintopbar/>
<s:div cssStyle="top:30px;position:relative">
<s:div id="configpopin"><s:div id="configmap"></s:div></s:div>
<s:div id="overlayForm" height="100%">
<s:form id="overlayform" theme="bootstrap" label="Add Overlay" action="addoverlay">
<s:actionerror/>
<s:actionmessage/>
<s:textfield id="lat" name="lat" key="overlayform.lat"></s:textfield>
<s:textfield id="lang" name="lang" key="overlayform.lang"></s:textfield>
<s:select list="overlayTypeList" label="Overlay Type" name="overlayType"></s:select>
<s:submit key="overlayform.submit" cssClass="btn"/>
<s:token/>
</s:form>
</s:div>
</s:div>
</body>
</html>

