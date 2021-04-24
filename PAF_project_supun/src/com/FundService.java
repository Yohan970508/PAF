package com;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Fund;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundService {
	
	Fund fundObj = new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	{
	return fundObj.readFunds();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("ProName") String ProName,
	@FormParam("Amount") String Amount,
	@FormParam("Descrip") String Descrip)
	{
	String output = fundObj.insertFund(ProName, Amount, Descrip);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String FundData)
	{
	//Convert the input string to a JSON object
	JsonObject fundObject = new JsonParser().parse(FundData).getAsJsonObject();
	//Read the values from the JSON object
	String FundID = fundObject.get("FundID").getAsString();
	String ProName = fundObject.get("ProName").getAsString();
	String Amount = fundObject.get("Amount").getAsString();
	String Descrip = fundObject.get("Descrip").getAsString();
	String output = fundObj.updateFund(FundID, ProName, Amount, Descrip);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFundd(String FundData)
	{
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(FundData, "", Parser.xmlParser());
	
	//Read the value from the element <FundID>
	String FundID = doc.select("FundID").text();
	String output = fundObj.deleteFund(FundID);
	return output;
	}
	
}
