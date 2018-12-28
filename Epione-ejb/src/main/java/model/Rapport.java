package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Rapports database table.
 * 
 */
@Entity
@Table(name="Rapports")
@NamedQuery(name="Rapport.findAll", query="SELECT r FROM Rapport r")
public class Rapport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private int idMed;

	private int idPet;

	private String picture;

	public Rapport() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMed() {
		return this.idMed;
	}

	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}

	public int getIdPet() {
		return this.idPet;
	}

	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}



}