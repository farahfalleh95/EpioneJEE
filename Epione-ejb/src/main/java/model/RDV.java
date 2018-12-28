package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RDVs database table.
 * 
 */
@Entity
@Table(name="RDVs")
@NamedQuery(name="RDV.findAll", query="SELECT r FROM RDV r")
public class RDV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp date;

	private int idMedecien;

	private int idPatient;

	private String maladi;

	private String nom;

	private String prenom;

	private String ville;

	public RDV() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getIdMedecien() {
		return this.idMedecien;
	}

	public void setIdMedecien(int idMedecien) {
		this.idMedecien = idMedecien;
	}

	public int getIdPatient() {
		return this.idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public String getMaladi() {
		return maladi;
	}

	public void setMaladi(String maladi) {
		this.maladi = maladi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}



}