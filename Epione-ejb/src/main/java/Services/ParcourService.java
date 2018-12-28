package Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import Interfaces.ParcourServiceLocal;
import Interfaces.ParcourServiceRemote;
import model.AspNetUser;
import model.Parcour;

/**
 * Session Bean implementation class ParcourService
 */
@Stateless
@LocalBean
public class ParcourService implements ParcourServiceRemote, ParcourServiceLocal {
	@PersistenceContext(unitName = "Epione-ejb")

	/**
	 * Default constructor.
	 */

	EntityManager em;

	Parcour p = new Parcour();

	public ParcourService() {
		// TODO Auto-generated constructor stub
	}

	public List<Parcour> getAllParcoursPatient(int id) {

		TypedQuery<Parcour> query = em.createQuery("select p from Parcour p where p.idPatient=:idd", Parcour.class);
		query.setParameter("idd", id);
		List<Parcour> list = query.getResultList();
		return list;

	}

	public List<AspNetUser> getAllPatient() {
		// create new jax-rs client
		Client client = ClientBuilder.newClient();
		// the base url of the service
		WebTarget target = client.target("http://localhost:51403/MedecinF/IndexJson");
		// get the response from the url
		Response response = target.request().get();
		// get the result as string
		String result = response.readEntity(String.class);
		// print the result to the standard output
		System.out.println(result);
		response.close();

		return null;
	}

	public void AjoutParcours(Parcour u) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/CreateJson");
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
		System.out.println(response.readEntity(String.class));
	}

	public void deleteProjectById(int id) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/DeleteJson/" + id);
		Response reponse = target.request().post(Entity.entity("ok", MediaType.TEXT_PLAIN));
		System.out.println(reponse.readEntity(String.class));
	}

	public void EditParcours(int id, Parcour u) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/EditJson/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
		System.out.println(response.readEntity(String.class));
	}

	public List<Parcour> AffichageParcour() {
		List<Parcour> parcours = em.createQuery("Select p from Parcour p", Parcour.class).getResultList();
		return parcours;

	}
	
	public AspNetUser getUserByEmailAndPassword(String email, String password) {
		
		TypedQuery<AspNetUser> query = em.createQuery("select e from AspNetUser e where e.email=:email AND e.password=:password", AspNetUser.class); 

		query.setParameter("email", email); 
		query.setParameter("password", password); 

		AspNetUser user = null; 
		try { user = query.getSingleResult(); }
		catch (Exception e) { System.out.println("Erreur : " + e); }

		return user;
		} 
	public List<Parcour> getAllPatientByMedecin(int idMedecin) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/ParcoursByMedecin?idMedecin="+idMedecin);
		Response response = target.request().get();
		String result = response.readEntity(String.class);
		System.out.println(result);
		response.close();

		return null;
	}
	public List<AspNetUser> getAllPatientByMedecinJee(int id) {
		
		TypedQuery<AspNetUser> query = em.createQuery("select e from AspNetUser e where e.id=:idMedecin AND e.discriminator='Medecin'", AspNetUser.class); 

		query.setParameter("idMedecin", id); 

		AspNetUser user = null; 
		List<AspNetUser> listpatient=new ArrayList<AspNetUser>();
		List<Parcour> listparcoursbymedecin=new ArrayList<Parcour>();
		
		try {
		user = query.getSingleResult(); 
		TypedQuery<Parcour> query2 = em.createQuery("select e from Parcour e where e.idMedecin=:id", Parcour.class); 

		query2.setParameter("id", id); 
		 listparcoursbymedecin = query2.getResultList();
		for (Parcour parcour : listparcoursbymedecin) {
			if(!listpatient.contains(getPatientById(parcour.getIdPatient()))){
				listpatient.add(getPatientById(parcour.getIdPatient()));
			}
		
		}
		
		}
		catch (Exception e) { System.out.println("Erreur : " + e); }
		return listpatient;


		}
	public AspNetUser getPatientById(int id) {

		TypedQuery<AspNetUser> query = em.createQuery("select p from AspNetUser p where p.id=:id AND p.discriminator='Patient'", AspNetUser.class);
		query.setParameter("id", id);
		AspNetUser patient = query.getSingleResult();
		return patient;

	}
	public List<String> AffichageNomMedecin() {
		List<String> noms=new ArrayList<String>();
		List<AspNetUser> medecins = em.createQuery("Select p from AspNetUser p where p.discriminator='Medecin'", AspNetUser.class).getResultList();
		for (AspNetUser aspNetUser : medecins) {
			noms.add(aspNetUser.getFirstName()+" "+aspNetUser.getLastName());
		}
		return noms;

	}
	

}
