/*
 * IT19142692
 * Anuththara K.G.S.N
 * */

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
import model.Project_Management;


@Path("/Project_Management")
public class Project_Management_Service {
  	Project_Management Obj = new Project_Management();
  	//Get METHOD
  	@GET
  	@Path("get")
  	@Produces(MediaType.TEXT_PLAIN)
  	public String getIt() {
	 	return "Got it Navodi";
	       
 	}
  	//Get method - project
  	@GET
	@Path("Project_Read")
	@Produces(MediaType.TEXT_HTML)
	public String readProject()
	{
		return Obj.readProject();
	}
 
  	//POST method - project
  	@POST 
	@Path("/add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject( @FormParam("projectID") int projectID,
			                @FormParam("projectName") String projectName,
							@FormParam("projectType") String projectType,
							@FormParam("projectDescription") String projectDescription)
							
	{
		String output = Obj.insertProject(projectID, projectName, projectType, projectDescription);  
		return output; 
	}
  
  	//PUT Method - project
  	@PUT 
	@Path("/UpdateProject") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String project) { 

		// Convert the input string to a JSON object 
		JsonObject Object = new JsonParser().parse(project).getAsJsonObject(); 
		 
		// Read the values from the JSON object
		String projectID = Object.get("projectID").getAsString();  
		String projectName = Object.get("projectName").getAsString();  
		String projectType = Object.get("projectType").getAsString();  
		String projectDescription = Object.get("projectDescription").getAsString();
		 
		String output = Obj.updateProject(projectID, projectName, projectType, projectDescription); 
		 
		return output; 
		 
	}

	//Delete Method - Project
    	@DELETE
    	@Path("/DeleteProject")
    	@Consumes(MediaType.APPLICATION_XML)
    	@Produces(MediaType.TEXT_PLAIN)
    	public String deleteProject(String project) {
    		// Convert the input string to an XML document
    		Document doc = Jsoup.parse(project, "", Parser.xmlParser());
    		// Read the value from the element <projectID>
    		String projectID = doc.select("projectID").text();
    		String output = Obj.deleteProject(projectID);
    		return output;
    	}
}
