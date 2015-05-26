<%-- 
    Document   : header
    Created on : May 23, 2015, 8:50:58 PM
    Author     : kiskin
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<header style="background-color: #111111;">
	<span style="color: #FFFFFF;">	Вы вошли как <b style="color: #FF0000;"><jsp:doBody/></b></span>
	<span style="float: right; text-align: right; color: #FFFFFF;"><a href="logout"><b style="color: #FF0000;">ВЫЙТИ</b></a></span>
</header>
