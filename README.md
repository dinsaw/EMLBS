EMLBS - Enabling Mobile Location based Services for Smartcity
===================================
This is server side component of project which receives requests(complaints) from mobile application. Code is capable of catagorizing requests/complaints.
-----------------------------
**Frameworks and Technologies**
- Struts2
- Hibernate 3.5

**For showing google maps mainly following scripts are used**
- gmaps.js from http://hpneo.github.io/gmaps/

*WebContent/WEB-INF/tags*
- contains tags which are used all over in jsp pages

*Webcontent/setup/pages*
- contains jsp for administrator side control

*dev.dinesh.emlbs.action*
- contains ActionServlet for all 

*dev.dinesh.emlbs.dao*
- Data Access Objects to get data from persistance objects 

*dev.dinesh.emlbs.persistence*
- persistence classes to represent tables in DB 

*dev.dinesh.emlbs.interceptor*
- intereptors for authentication

