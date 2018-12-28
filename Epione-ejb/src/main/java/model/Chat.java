package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Chats database table.
 * 
 */
@Entity
@Table(name="Chats")
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String contenu;

	private Timestamp date;

	private boolean etat;

	private int idMed;

	private int idPat;

	public Chat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public Object getContenu() {
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

	public boolean getEtat() {
		return this.etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public int getIdMed() {
		return this.idMed;
	}

	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}

	public int getIdPat() {
		return this.idPat;
	}

	public void setIdPat(int idPat) {
		this.idPat = idPat;
	}

}