/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    /**
     * Retrieves representation of an instance of rest.service.EmployeeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EmployeeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public String getAllEmployees(){
        List<EmployeeDTO> listOfEmpDTO = new ArrayList<>();
        facade.getAllEmployees().forEach((employee)-> {listOfEmpDTO.add(new EmployeeDTO(employee));});
        return new Gson().toJson(listOfEmpDTO);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getEmployeeById(@PathParam("id") int id){
        return new Gson().toJson(new EmployeeDTO(facade.getEmployeeeById(id)));
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/highestpaid")
    public String getEmployeesWithHighestSalary(){
        List<EmployeeDTO> listOfEmpDTO = new ArrayList<>();
        facade.getEmployeesWithHighestSalary(3).forEach((employee)-> {listOfEmpDTO.add(new EmployeeDTO(employee));});
        return new Gson().toJson(listOfEmpDTO);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public String getEmployeesByName(@PathParam("name") String name){
        List<EmployeeDTO> listOfEmpDTO = new ArrayList<>();
        facade.getEmployeesByName(name).forEach((employee)-> {listOfEmpDTO.add(new EmployeeDTO(employee));});
        return new Gson().toJson(listOfEmpDTO);
    }
}
