package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items")
public class PaymentService
{
Payment Obj = new Payment();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML) //media type
public String readPayment(){

	return Obj.readpayment();

}

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)  //media type
@Produces(MediaType.TEXT_PLAIN)
public String insertPayment(@FormParam("CardHolderFirstname") String CardHolderFirstname,@FormParam("CardHolderLastname") String CardHolderLastname,@FormParam("Amount") String Amount,@FormParam("mobile") String mobile,@FormParam("PaymentMethod") String PaymentMethod) {
	String output = Obj.insertpayment(CardHolderFirstname, CardHolderLastname, Amount, mobile, PaymentMethod);
	return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePayment(String PaymentData)
{
//Convert the input string to a JSON object
 JsonObject Object = new JsonParser().parse(PaymentData).getAsJsonObject();
//Read the values from the JSON object
//ID CardHolderFirstname CardHolderLastname Amount mobile PaymentMethod
 String ID = Object.get("ID").getAsString();
 String CardHolderFirstname = Object.get("CardHolderFirstname").getAsString();
 String CardHolderLastname = Object.get("CardHolderLastname").getAsString();
 String Amount = Object.get("Amount").getAsString();
 String mobile = Object.get("mobile").getAsString();
 String PaymentMethod = Object.get("PaymentMethod").getAsString();
 String output = Obj.updatePayment(ID, CardHolderFirstname, CardHolderLastname, Amount, mobile, PaymentMethod);

 return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletePayment(String PaymentData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String ID = doc.select("ID").text();
 String output = Obj.deletepayment(ID);
return output;
}


}
