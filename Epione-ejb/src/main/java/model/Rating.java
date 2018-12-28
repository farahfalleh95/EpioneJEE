package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Ratings database table.
 * 
 */
@Entity
@Table(name="Ratings")
@NamedQuery(name="Rating.findAll", query="SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPat;

	private int idMed;

	private int nbrEtoile;

	public Rating() {
	}

	public int getIdPat() {
		return this.idPat;
	}

	public void setIdPat(int idPat) {
		this.idPat = idPat;
	}

	public int getIdMed() {
		return this.idMed;
	}

	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}

	public int getNbrEtoile() {
		return this.nbrEtoile;
	}

	public void setNbrEtoile(int nbrEtoile) {
		this.nbrEtoile = nbrEtoile;
	}

}