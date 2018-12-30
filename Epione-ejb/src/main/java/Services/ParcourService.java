package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;

import java.io.Console;
import java.lang.reflect.Type;
import java.security.Principal;

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
public class ParcourService implements ParcourServiceRemote{
	@PersistenceContext(unitName = "Epione-ejb")

	/**
	 * Default constructor.
	 */
	
	EntityManager em;
	
	Parcour p = new Parcour();

	public ParcourService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Parcour> getAllParcoursPatient(int id) {

		TypedQuery<Parcour> query = em.createQuery("select p from Parcour p where p.idPatient=:idd", Parcour.class);
		query.setParameter("idd", id);
		List<Parcour> list = query.getResultList();
		return list;

	}
	@Override
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
	@Override
	public void AjoutParcours(Parcour u) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/CreateJson");
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
		System.out.println(response.readEntity(String.class));
		///////////////Envoi MAIL 
		
		final String username = "raniafalleh25@gmail.com";
		final String password = "farahctt17R";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("raniafalleh25@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse("farah.falleh@esprit.tn"));
			message.setSubject("Testing Subject");
			message.setText("hhh");
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
		
	}
	@Override
	public void deleteProjectById(int id) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/DeleteJson/" + id);
		Response reponse = target.request().post(Entity.entity("ok", MediaType.TEXT_PLAIN));
		System.out.println(reponse.readEntity(String.class));
	}
	@Override
	public void EditParcours(int id, Parcour u) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/EditJson/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
		System.out.println(response.readEntity(String.class));
	}
	@Override
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
	@Override
	public List<Parcour> getAllPatientByMedecin(int idMedecin) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:51403/Parcours/ParcoursByMedecin?idMedecin="+idMedecin);
		Response response = target.request().get();
		String result = response.readEntity(String.class);
		System.out.println(result);
		response.close();

		return null;
	}
	@Override
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
	@Override
	public AspNetUser getPatientById(int id) {

		TypedQuery<AspNetUser> query = em.createQuery("select p from AspNetUser p where p.id=:id AND p.discriminator='Patient'", AspNetUser.class);
		query.setParameter("id", id);
		AspNetUser patient = query.getSingleResult();
		return patient;

	}
	
	@Override
	public List<String> AffichageNomMedecin() {
		List<String> noms=new ArrayList<String>();
		List<AspNetUser> medecins = em.createQuery("Select p from AspNetUser p where p.discriminator='Medecin'", AspNetUser.class).getResultList();
		for (AspNetUser aspNetUser : medecins) {
			noms.add(aspNetUser.getFirstName()+"-"+aspNetUser.getLastName());
		}
		return noms;

	}
	@Override
	public List<Parcour> ListaffichageConsume() {
		Client client = ClientBuilder.newClient();
		WebTarget target =client.target("http://localhost:51403/Parcours/IndexJson");
		Response reponse = target.request().get();
		List<Parcour> result = reponse.readEntity(new GenericType<List<Parcour>>(){});
		System.err.println("result :: " +result);
		return result;
	}
	@Override
	public void ChangerEtat(Parcour p) {
		p.setEtatrdv("Consultation terminée");
		em.merge(p);
		
	}
	@Override
	public void mailingPatinet(String emailPatient, String emaildoctor,String passworddoc,String contenu ,String nomdocteur) {
///////////////Envoi MAIL 
		
	final String username = emaildoctor;
	final String password = passworddoc;
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
		}
	  });
	try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emaildoctor));
		message.setRecipients(Message.RecipientType.TO,
		InternetAddress.parse(emailPatient));
		message.setSubject(contenu+" ---"+nomdocteur);
		message.setText("hhh");
		Transport.send(message);
		System.out.println("Done");
	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
	System.out.println("mail envoyer");

		
	}
	@Override
	public void mailingDocotor(String emailDoctor, String contenu) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public AspNetUser getPatientByIdaa(int id) {
		// TODO Auto-generated method stub
		return  em.find(AspNetUser.class, id);
	}
	
	@Override
	public String getFirstname(String chaine) {
		int num = chaine.indexOf("-");
		String res =  chaine.substring(0, num);
		System.out.println("Firstname:"+res);
		return res;
	}
	@Override
	public String getLastName(String chaine) {
		int num = chaine.indexOf("-");
		String res =  chaine.substring(num+1, chaine.length());
		System.out.println("lastdayName:"+res);
		return res;
	}
	@Override
	public AspNetUser getDoctotByName(String firstname, String lastName) {
		TypedQuery<AspNetUser> query = em.createQuery("select e from AspNetUser e where e.firstName=:email AND e.lastName=:password", AspNetUser.class); 

		query.setParameter("email", firstname); 
		query.setParameter("password", lastName); 
		AspNetUser user = query.getSingleResult();
		System.out.println("user"+user.toString());
		return user;
	}




		}
	
	


