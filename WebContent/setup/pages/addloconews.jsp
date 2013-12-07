<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="site.link.setup.loconews" /></title>
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
			  fillOpacity: 0.6,
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
<body bgcolor="gray">
	<tags:admintopbar />
	<s:div cssStyle="top:30px;position:relative">
		<s:div id="configpopin">
			<s:div id="configmap"></s:div>
		</s:div>
		<s:div id="overlayForm" height="100%">
			<s:form id="loconews" theme="bootstrap" label="Add LOCO news"
				action="addloconews">
				<s:actionerror />
				<s:actionmessage />
				<s:textarea name="newsTitle" key="loconewsForm.title" rows="1"
					required="true" />
				<s:textarea name="news" key="loconewsForm.news" rows="3" cols="25"
					required="true" />
				<s:radio list="whowillseeList" value="defaultwhowillsee"
					key="loconewsForm.whowillsee" name="whowillsee" required="true" />
				<sj:datepicker id="endDate" name="endDate"
					key="loconewsForm.endDate" required="true" minDate="+0"
					 value="today" showAnim="slideDown" />
				<s:textarea id="polyPoints" name="area" key="loconewsForm.poly"
					rows="3" cols="25" required="true" />
				<s:submit key="overlayform.submit" cssClass="btn" />
				<s:token />
			</s:form>
		</s:div>
	</s:div>

</body>
</html>

