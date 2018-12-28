package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Soins database table.
 * 
 */
@Entity
@Table(name="Soins")
@NamedQuery(name="Soin.findAll", query="SELECT s FROM Soin s")
public class Soin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int idMed;

	private String soin;

	public Soin() {
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
/*
	public Object getSoin() {
		return this.soin;
	}

	public void setSoin(Object soin) {
		this.soin = soin;
	}*/

	public String getSoin() {
		return soin;
	}

	public void setSoin(String soin) {
		this.soin = soin;
	}
	

}