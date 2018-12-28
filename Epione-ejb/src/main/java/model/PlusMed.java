package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PlusMeds database table.
 * 
 */
@Entity
@Table(name="PlusMeds")
@NamedQuery(name="PlusMed.findAll", query="SELECT p FROM PlusMed p")
public class PlusMed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IDMed;

	@Column(name="Hopital")
	private String hopital;

	private String image;

	private String specialieProfondu;

	public PlusMed() {
	}

	public int getIDMed() {
		return this.IDMed;
	}

	public void setIDMed(int IDMed) {
		this.IDMed = IDMed;
	}

	public String getHopital() {
		return hopital;
	}

	public void setHopital(String hopital) {
		this.hopital = hopital;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSpecialieProfondu() {
		return specialieProfondu;
	}

	public void setSpecialieProfondu(String specialieProfondu) {
		this.specialieProfondu = specialieProfondu;
	}

	

}