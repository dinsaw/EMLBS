<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="site.link.setup.complaintonmapfullscreen" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value="/css/main.css"/>">
<sj:head debug="true" />
<s:head />
<sb:head />
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src='<s:url value="/scripts/gmaps.js"></s:url>'></script>
<script type="text/javascript">
var map;

function loadResults (data) {
  var items, markers_data = [];
  var dir = '';
  var devider = '';
  var action = data.action;
  if (data.groups.length > 0) {
    items = data.groups[0].items;

    for (var i = 0; i < items.length; i++) {
      var item = items[i];

      if (item.location.lat != undefined && item.location.lng != undefined) {
        var icon;
        /* if (item.categories.length > 0) {
          
        } */
        console.log('dirname='+dir);
         icon = dir+devider+action+item.icon; 
        /* icon = "http://localhost:8080/EMLBS/images/road.png"; */
        console.log('icon='+icon);
        markers_data.push({
          lat : item.location.lat,
          lng : item.location.lng,
          title : item.name,
          icon : {
            url : icon
          },
          infoWindow: {
        	  content: '<p>HTML Content</p>'
        	}
        });
        console.log('_______icon='+icon);
      }
    }
  }

  map.addMarkers(markers_data);
}

function printResults(data) {
  $('#foursquare-results').text(JSON.stringify(data));
}

$(document).on('click', '.pan-to-marker', function(e) {
  e.preventDefault();

  var position, lat, lng, $index;

  $index = $(this).data('marker-index');

  position = map.markers[$index].getPosition();

  lat = position.lat();
  lng = position.lng();

  map.setCenter(lat, lng);
});

$(document).ready(function(){
  map = new GMaps({
    div: '#configmap',
    lat: -12.043333,
    lng: -77.028333
  });

/*   map.on('marker_added', function (marker) {
    var index = map.markers.indexOf(marker);
    $('#results').append('<li><a href="#" class="pan-to-marker" data-marker-index="' + index + '">' + marker.title + '</a></li>');

    if (index == map.markers.length - 1) {
      map.fitZoom();
    }
  }); */

  var xhr = $.getJSON('<s:url value="/setup/getcomplaintsJSON"></s:url>');

  xhr.done(printResults);
  xhr.done(loadResults);
});
</script>
</head>
<body>
<s:div id="configpopin">
			<s:div id="configmap"></s:div>
		</s:div>
</body>
</html>