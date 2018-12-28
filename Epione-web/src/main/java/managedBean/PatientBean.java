package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Services.ParcourService;
import model.AspNetUser;


@ManagedBean(name="patientBean") 
@SessionScoped
public class PatientBean {
	
	private List<AspNetUser> patients;
	private String FirstName;
	private String LastName;
	private String Email;
	private String PhoneNumber;
	private String UserName;
	@EJB
	ParcourService parcoursService; 
	
	public List<AspNetUser> getPatientsByDoctor(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		AspNetUser user=(AspNetUser) context.getExternalContext().getSessionMap().get("user");
		System.out.println(user.getId());
		return parcoursService.getAllPatientByMedecinJee(user.getId());
	}

	public List<AspNetUser> getPatients() 
	{
		return getPatientsByDoctor();
	}

	public void setPatients(List<AspNetUser> patients) {
		
		this.patients =  getPatientsByDoctor();
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	
	
	
}
