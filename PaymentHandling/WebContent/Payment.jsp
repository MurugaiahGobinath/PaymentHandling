<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.Payment"%>
<%
//Save---------------------------------
if (request.getParameter("itemCode") != null)
{
Payment itemObj = new Payment();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidItemIDSave") == "")
{
stsMsg = itemObj.insertpayment(request.getParameter("fname"),
request.getParameter("lname"),
request.getParameter("amount"),
request.getParameter("number"),
request.getParameter("method"));
}
else//Update----------------------
{
stsMsg = itemObj.updatePayment(request.getParameter("hidItemIDSave"),
request.getParameter("fname"),
request.getParameter("lname"),
request.getParameter("amount"),
request.getParameter("number"),
request.getParameter("method"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
Payment itemObj = new Payment();
String stsMsg =
itemObj.deletepayment(request.getParameter("hidItemIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
<title>Payment Management</title>
</head>
<body>
<h1>Payment Management System</h1>

<form id="formItem" name="formItem" method="post" action="items.jsp">

 CardHolderFirstname:
<input id="fname" name="fname" type="text"
 class="form-control form-control-sm">
<breadpaymentreadpaymentr> CardHolderLastname:
<input id="lname" name="lname" type="text"
 class="form-control form-control-sm">
<br> Total Amount:
<input id="amount" name="amount" type="text"
 class="form-control form-control-sm">
<br> Mobile Number:
<input id="number" name="number" type="text"
 class="form-control form-control-sm">
<br>Payment Method:
<input id="method" name="method" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divItemsGrid">
<%

Payment itemObj = new Payment();
 out.print(itemObj.readpayment());
%>
</div>
</body>
</html>