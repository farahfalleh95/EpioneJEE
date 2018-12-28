package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Motifs database table.
 * 
 */
@Entity
@Table(name="Motifs")
@NamedQuery(name="Motif.findAll", query="SELECT m FROM Motif m")
public class Motif implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int motifID;

	private String description;

	private int idPatient;

	public Motif() {
	}

	public int getMotifID() {
		return this.motifID;
	}

	public void setMotifID(int motifID) {
		this.motifID = motifID;
	}
/*
	public Object getDescription() {
		return this.description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}
*/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getIdPatient() {
		return this.idPatient;
	}


	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

}