package com;
import model.Products;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/ProductService") 
public class ProductService {

	Products productObj = new Products(); 
	@GET
	@Path("get") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProduct() 
	 { 
	 return "Hello"; 
	 } 
	@POST
	@Path("/add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(@FormParam("pro_id") String pro_id, 
	 @FormParam("name") String name, 
	 @FormParam("description") String description, 
	 @FormParam("type") String type, 
	@FormParam("location") String location)
	{ 
	 String output = productObj.insertProduct(pro_id, name, description, type, location ); 
	return output; 
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String productData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String pro_id = productObject.get("pro_id").getAsString(); 
	 String name = productObject.get("name").getAsString(); 
	 String description = productObject.get("description").getAsString(); 
	 String type = productObject.get("type").getAsString(); 
	 String location = productObject.get("location").getAsString(); 
	 String output = productObj.updateProduct(pro_id, name, description, type, location); 
	return output; 
	}
	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String productData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String pro_id = doc.select("pro_id").text(); 
	 String output = productObj.deleteProduct(pro_id); 
	return output; 
	}

	
}
