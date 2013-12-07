<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="site.link.setup.appsetup"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
<sb:head/>
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src='<s:url value="/scripts/jquery.js"></s:url>'></script>
<script type="text/javascript" src='<s:url value="/scripts/gmaps.js"></s:url>'></script>
<script type="text/javascript">
/**
 * Mouse Events on Maps
 */
 var inilatlng;
 var prevatlng;
 var currlatlng;
 var noofpt = 0;
 var pts = new Array();
 var polygon;
 var complete=0;
$(document).ready(function(){
 map = new GMaps({
    el: '#map',
    lat: 24.04646399966658,
    lng: 71.015625,
    zoom: 3,
    zoomControl : true,
    zoomControlOpt: {
        style : 'SMALL',
        position: 'TOP_LEFT'
    },
    panControl : false,
    click: function(e){
      currlatlng=e.latLng;
      if(inilatlng==null && complete==0){
    	  map.addMarker({
        	  lat: currlatlng.lat(),
        	  lng: currlatlng.lng(),
        	  title: 'Initial: First Point',
        	  click: function(e) {
					if (noofpt<3) {
						alert('Minimum number of points to form a polygon is 3 .');
					} else {
						/* var polygon = map.drawpolygon({
							  paths: pts, // pre-defined polygon shape
							  strokeColor: '#BBD8E9',
							  strokeOpacity: 1,
							  strokeWeight: 5,
							  fillColor: '#BBD8E9',
							  fillOpacity: 0.6
							}); */
							var patharr=new Array(pts[0],currlatlng);
						 map.drawPolyline({
				    		  path: patharr,
				    		  strokeColor: '#131540',
				    		  strokeOpacity: 0.6,
				    		  strokeWeight: 6
				    		});
						 $('#polyPoints').val(pts);
						 complete=1;
							
					}
        	  }
        	});
    	  inilatlng=currlatlng;
    	  pts[noofpt]=currlatlng;
    	  noofpt++;
      } else {
    	  if(complete==0){
    		  map.addMarker({
            	  lat: currlatlng.lat(),
            	  lng: currlatlng.lng(),
            	  title: 'Point NO : '+noofpt,
            	  click: function(e) {
            		  alert('Please do not click on existing points Except First One.');
            	  }
            	});
        	  pts[noofpt]=currlatlng;
              var lnpt=noofpt-1;
        	  
        	  var patharr=new Array(pts[noofpt],pts[lnpt]);
        	  noofpt++;
        	  
        	  map.drawPolyline({
        		  path: patharr,
        		  strokeColor: '#131540',
        		  strokeOpacity: 0.6,
        		  strokeWeight: 6
        		}); 
    	  } else {
    		  alert('If you want to modify this polygon press CLear Points');
    	  }
    	  
	}
      
    },
    dragend: function(e){
      
    }
  });
 map.addControl({
	  position: 'top_right',
	  content: 'ClearPoint(s)',
	  style: {
	    margin: '5px',
	    padding: '1px 6px',
	    border: 'solid 1px #717B87',
	    background: '#fff'
	  },
	  events: {
	    click: function(){
	    	map.removeMarkers();
	    	map.removePolylines();
	    	complete=0;
	    	inilatlng=null;
	    	noofpt = 0;
	    	pts = new Array();
	    	$('#polyPoints').val(pts);
	      console.log(this);
	    }
	  }
	});
});

</script>

</head>
<body>
<div id="wrap">
	<tags:header/>
	<tags:adminmenu/>
	<tags:appsetupform/>
	<tags:adminwarnsection/>
	<tags:footer/>
</div>

</body>
</html>