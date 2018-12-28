package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Notifications database table.
 * 
 */
@Entity
@Table(name="Notifications")
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String contenu;

	private Timestamp date;

	private int idMedecin;

	private int idPatient;

	public Notification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

/*	public Object getContenu() {
		return this.contenu;
	}

	public void setContenu(Object contenu) {
		this.contenu = contenu;
	}*/
	

	public Timestamp getDate() {
		return this.date;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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

}