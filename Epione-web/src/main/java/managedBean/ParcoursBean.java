package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Services.ParcourService;
import model.Parcour;

@ManagedBean(name = "parcoursBean")
@SessionScoped
public class ParcoursBean {
	@EJB
	ParcourService parcourService;

	private String adresse;
	private String date;
	private String etat;
	private String etatrdv;
	private int idMedecin;
	private int idPatient;
	private int idRDV;
	private String justification;
	private String maladie;
	private String nomMedecin;
	private String specialite;
	private List<Parcour> parcours;
	private List<Parcour> parcourspatients;
	private List<String> noms;
	private int idp;
	private Parcour parcoura=new Parcour();
	//////////////
	private Integer parcourIdToBeUpdated;
	public Integer getParcourIdToBeUpdated() {
		return parcourIdToBeUpdated;
	}
	public void setParcourIdToBeUpdated(Integer parcourIdToBeUpdated) {
		this.parcourIdToBeUpdated = parcourIdToBeUpdated;
	}
	///////////////
	public String AddParcours(int id) {
		
		String navigateTo = "null";
		navigateTo = "/pages/AjoutParcours?faces-redirect=true";
		setIdPatient(id);
		return navigateTo;
	}
	public String AddSubmitParcours() {
		Parcour parcours = new Parcour();	
		parcours.setAdresse(adresse);
		parcours.setDate(date);
		parcours.setEtat(etat);
		parcours.setEtatrdv(etatrdv);
		parcours.setIdMedecin(idMedecin);
		parcours.setIdPatient(idPatient);
		parcours.setIdRDV(idRDV);
		parcours.setJustification(justification);
		parcours.setMaladie(maladie);
		parcours.setNomMedecin(nomMedecin);
		parcours.setSpecialite(specialite);
		parcourService.AjoutParcours(parcours);
		String navigateTo = "null";
		navigateTo = "/pages/AffichageParcoursPatient?faces-redirect=true";
		return navigateTo;
	}
 
	public String modifier(Parcour p) {
		setParcoura(p);
		String navigateTo = "null";
		navigateTo = "/pages/EditParcours?faces-redirect=true";
		return navigateTo;
		}

		public String EditParcour(int parcourIdToBeUpdated,Parcour parcour){
			parcourService.EditParcours(parcourIdToBeUpdated,parcour);
			String navigateTo = "null";
			navigateTo = "/pages/AffichageParcoursPatient?faces-redirect=true";
			return navigateTo;
		}
		

	
	public List<Parcour> AffichageParcours() {
		parcours=parcourService.AffichageParcour();
		return parcours;
		
		
	}
	
	public String AffichageParcoursPatient(int id) {
		String navigateTo = "null";
		navigateTo = "/pages/AffichageParcoursPatient?faces-redirect=true";
		setIdp(id);
		setParcourspatients(parcourService.getAllParcoursPatient(id));
		return navigateTo;
		
	}

	public void removeParcour(Parcour p)
	{
		
	parcourService.deleteProjectById(p.getId()); 
	this.parcourspatients.remove(p);
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getEtatrdv() {
		return etatrdv;
	}

	public void setEtatrdv(String etatrdv) {
		this.etatrdv = etatrdv;
	}

	public int getIdMedecin() {
		return idMedecin;
	}

	public void setIdMedecin(int idMedecin) {
		this.idMedecin = idMedecin;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getIdRDV() {
		return idRDV;
	}

	public void setIdRDV(int idRDV) {
		this.idRDV = idRDV;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getMaladie() {
		return maladie;
	}

	public void setMaladie(String maladie) {
		this.maladie = maladie;
	}

	public String getNomMedecin() {
		return nomMedecin;
	}

	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public List<Parcour> getParcours() {
		parcours=parcourService.AffichageParcour();
		return parcours;
	}

	public void setParcours(List<Parcour> parcours) {
		this.parcours = parcours;
	}
	public List<Parcour> getParcourspatients() {
		return parcourspatients ;
	
	}
	public void setParcourspatients(List<Parcour> parcourspatients) {
		this.parcourspatients = parcourspatients;
	}
	public Parcour getParcoura() {
		return parcoura;
	}
	public void setParcoura(Parcour parcoura) {
		this.parcoura = parcoura;
	}
	public List<String> getNoms() {
		
		return parcourService.AffichageNomMedecin();
	}
	public void setNoms(List<String> noms) {
		this.noms = noms;
	}
	public int getIdp() {
		return idp;
	}
	public void setIdp(int idp) {
		this.idp = idp;
	}
	

}
