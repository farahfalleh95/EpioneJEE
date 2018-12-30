package model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the Parcours database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="Parcours")
@NamedQuery(name="Parcour.findAll", query="SELECT p FROM Parcour p")
public class Parcour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Adresse")
	private String adresse;

	@Override
	public String toString() {
		return "Parcour [id=" + id + ", adresse=" + adresse + ", date=" + date + ", etat=" + etat + ", etatrdv="
				+ etatrdv + ", idMedecin=" + idMedecin + ", idPatient=" + idPatient + ", idRDV=" + idRDV
				+ ", justification=" + justification + ", maladie=" + maladie + ", nomMedecin=" + nomMedecin
				+ ", specialite=" + specialite + "]";
	}

	private String date;

	@Column(name="Etat")
	private String etat;

	@Column(name="Etatrdv")
	private String etatrdv;

	private int idMedecin;

	private int idPatient;

	private int idRDV;

	@Column(name="Justification")
	private String justification;

	@Column(name="Maladie")
	private String maladie;

	@Column(name="NomMedecin")
	private String nomMedecin;

	@Column(name="Specialite")
	private String specialite;

	public Parcour() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date2) {
		this.date = date2;
	}




	public int getIdMedecin() {
		return this.idMedecin;
	}

	public void setIdMedecin(int idMedecin) {
		this.idMedecin = idMedecin;
	}

	public int getIdPatient() {
		return this.idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getIdRDV() {
		return this.idRDV;
	}

	public void setIdRDV(int idRDV) {
		this.idRDV = idRDV;
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

	public Parcour(int id,String adresse, String date, String etat, int idMedecin, String etatrdv, int idPatient,
			int idRDV, String justification, String maladie, String nomMedecin, String specialite) {
		super();
		this.id=id;
		this.adresse = adresse;
		this.date = date;
		this.etat = etat;
		this.etatrdv = etatrdv;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		this.idRDV = idRDV;
		this.justification = justification;
		this.maladie = maladie;
		this.nomMedecin = nomMedecin;
		this.specialite = specialite;
	}




}