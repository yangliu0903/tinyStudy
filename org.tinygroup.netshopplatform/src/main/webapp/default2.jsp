<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%   
  request.setAttribute("logName","admin");  
  session.setAttribute("logName","xuanxuan");
%>  
default2.jsp:<%=request.getParameter("logName1")%></br>
request:${requestScope.logName}</br>
session:${sessionScope.logName}</br>