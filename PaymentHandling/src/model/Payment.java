package model;

import java.sql.*;
public class Payment
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertpayment(String fname, String lname, String amount, String number, String method)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " INSERT INTO `payment`(`ID`, `CardHolderFirstname`, `CardHolderLastname`, `Amount`, `mobile`, `PaymentMethod`)  values (?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, fname);
 preparedStmt.setString(3, lname);
 preparedStmt.setDouble(4, Double.parseDouble(amount));
 preparedStmt.setString(5, number);
 preparedStmt.setString(6, method);
// execute the statement

 preparedStmt.execute();
 con.close();
 String newItems = readpayment();
 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
public String readpayment()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
//Prepare the html table to be displayed
output = "<table border=\"1\"><tr><th>Item Code</th><th>Item Name</th><th>Item Price</th><th>Item Description</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from payment";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String id = Integer.toString(rs.getInt("ID"));
String fname = rs.getString("CardHolderFirstname");
String lname = rs.getString("CardHolderLastname");
String amount = rs.getString("Amount");
String number = rs.getString("mobile");
String method = rs.getString("PaymentMethod");

// Add into the html table
output += "<tr><td>"+ fname + "</td>";
output += "<td>" + lname + "</td>";
output += "<td>" + amount + "</td>";
output += "<td>" + number + "</td>";
output += "<td>" + method + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";
} 
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updatePayment(String id , String fname, String lname, String amount, String number, String method)

 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 // create a prepared statement
 String query = "UPDATE `payment` SET `CardHolderFirstname`=?,`CardHolderLastname`=?,`Amount`=?,`mobile`=?,`PaymentMethod`=? WHERE `ID`=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, fname);
 preparedStmt.setString(2, lname);
 preparedStmt.setDouble(3, Double.parseDouble(amount));
 preparedStmt.setString(4, number);
 preparedStmt.setString(5, method);
 preparedStmt.setInt(6, Integer.parseInt(id));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newItems = readpayment();
 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
public String deletepayment(String itemID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "DELETE FROM `payment` where `ID`=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(itemID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newItems = readpayment();
 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
} 