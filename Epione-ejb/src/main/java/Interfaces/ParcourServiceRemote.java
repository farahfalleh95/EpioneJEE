package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import model.AspNetUser;
import model.Parcour;

@Remote
public interface ParcourServiceRemote {
    public List<Parcour> getAllParcoursPatient(int id);
    public List<AspNetUser> getAllPatient();
	public void AjoutParcours(Parcour u);
	public void deleteProjectById(int id);
	public void EditParcours(int id,Parcour u);
	public List<Parcour> AffichageParcour();
	public List<Parcour> getAllPatientByMedecin(int idMedecin);
	public List<AspNetUser> getAllPatientByMedecinJee(int id);
	public AspNetUser getPatientById(int id);
	public List<String> AffichageNomMedecin();
	public List<Parcour> ListaffichageConsume();
	public void ChangerEtat(Parcour p);
	public void mailingPatinet (String emailPatient ,String emaildoctor,String password, String contenu,String nomdocteur,String text);
	public void mailingDocotor (String emailDoctor , String contenu);
	public AspNetUser getPatientByIdaa(int id);
	public AspNetUser getDoctotByName (String firstname,String lastName);
	public String getFirstname(String chaine);
	public String getLastName(String chaine);
	public List<AspNetUser> ListaffichagePatientConsume();
}
