package fr.iutinfo.skeleton.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Path("/formation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormationResource {

	
	private static FormationDao dao = BDDFactory.getDbi().open(FormationDao.class);
	final static Logger logger = LoggerFactory.getLogger(SecureResource.class);
	Gson gson = new Gson();


	@GET
	public Response getFormetablByDiplome(@QueryParam("lib") String lib) {
		List<ListeFormation> res = dao.getFormetablByDiplome(lib);
		return Response.status(200).entity(gson.toJson(res)).build();
	}
	
	@PUT
	public Response addFormEtabl(@QueryParam("fno")int fno,@QueryParam("eno") int eno){
		dao.addFormEtabl(fno,eno);
		return Response.status(200).entity("OK").build();
	}
	

	@GET
	@Path("/diplome")
	public Response getDiplomes() {
		List<Diplome> reponse = dao.getDiplomes();
		return Response.status(200).entity(gson.toJson(reponse)).build();
	}
	
	@GET
	@Path("/domaine")
	public Response getDomaineByDiplome(@QueryParam("lib") String lib) {
		List<Formation> res=dao.getDomaineByDiplome(lib);
		return Response.status(200).entity(gson.toJson(res)).build();
	}
	
	@PUT
	@Path("/domaine")
	public Response addDomaineByDiplome(@QueryParam("lib") String lib, @QueryParam("domaine")String domaine) {
		dao.addDomaineByDiplome(lib,domaine);
		return Response.status(200).entity("Ok").build();
	}
	/*
	 * @DELETE public Voeu removeVoeuTo(String login,Voeu voeu) { return
	 * dao.removeVoeuTo(login,voeu); }
	 */
}
