package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Admin;

@Path("/Admin")
public class AdminService {
	Admin registerObj = new Admin();

	@GET
	@Path("get")
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it sammani!";

	}

	@GET
	@Path("admin_read")
	@Produces(MediaType.TEXT_HTML)

	public String readAdmin() {
		return registerObj.readAdmin();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdmin(
			@FormParam("UID") String UID,
			@FormParam("Name") String Name,
			@FormParam("Address") String Address, 
			@FormParam("Email") String Email, 
			@FormParam("Age") String Age,
			@FormParam("pno") String pno, 
			@FormParam("user_type") String user_type) {

		String output = registerObj.insertAdmin(UID, Name, Address, Email, Age, pno, user_type);
		return output;
	}

	@PUT
	@Path("/update_ADMIN")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdmin(String AdminData) {

		// Convert the input string to a JSON object
		JsonObject updateObj = new JsonParser().parse(AdminData).getAsJsonObject();

		// Read the values from the JSON object
		String UID = updateObj.get("UID").getAsString();
		String Name = updateObj.get("Name").getAsString();
		String Address = updateObj.get("Address").getAsString();
		String Email = updateObj.get("Email").getAsString();
		String Age = updateObj.get("Age").getAsString();
		String pno = updateObj.get("pno").getAsString();
		String user_type = updateObj.get("user_type").getAsString();

		String output = registerObj.updateAdmin(UID, Name, Address, Email, Age, pno, user_type);
		return output;

	}

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAdmin(String AdminData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(AdminData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String UID = doc.select("UID").text();
		String output = registerObj.deleteAdmin(UID);
		return output;
	}
}
