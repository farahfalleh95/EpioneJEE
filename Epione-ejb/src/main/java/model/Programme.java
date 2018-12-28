package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Programmes database table.
 * 
 */
@Entity
@Table(name="Programmes")
@NamedQuery(name="Programme.findAll", query="SELECT p FROM Programme p")
public class Programme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="DateDebutR")
	private String dateDebutR;

	@Column(name="DateFinR")
	private String dateFinR;

	private int idMed;

	private int idRdv;

	private String maladi;

	private String ville;

	public Programme() {
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

	public int getIdRdv() {
		return this.idRdv;
	}

	public void setIdRdv(int idRdv) {
		this.idRdv = idRdv;
	}

	public String getDateDebutR() {
		return dateDebutR;
	}

	public void setDateDebutR(String dateDebutR) {
		this.dateDebutR = dateDebutR;
	}

	public String getDateFinR() {
		return dateFinR;
	}

	public void setDateFinR(String dateFinR) {
		this.dateFinR = dateFinR;
	}

	public String getMaladi() {
		return maladi;
	}

	public void setMaladi(String maladi) {
		this.maladi = maladi;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}



}