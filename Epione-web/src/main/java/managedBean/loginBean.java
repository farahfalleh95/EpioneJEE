package managedBean;


import java.io.Serializable;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Services.ParcourService;
import model.AspNetUser;


@ManagedBean(name="loginBean") 
@SessionScoped
public class loginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email; 
	private String password;  
	private Boolean loggedIn;
	private AspNetUser user;

	@EJB
	ParcourService parcoursService; 

	public String doLogin()
	{
		String navigateTo = "null"; 
		user = parcoursService.getUserByEmailAndPassword(email, password); 

		if (user != null && user.getDiscriminator().equals("Medecin")) {
			navigateTo = "/pages/AffichagePatientsByMedecin?faces-redirect=true";
			loggedIn = true;
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", user);
		}
		else 
		{
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
		}
		return navigateTo; 
	}

	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
 
	public loginBean() {} 
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public AspNetUser getUser() {return user;}
	public void setUser(AspNetUser user) {this.user = user;}
	public Boolean getLoggedIn() {return loggedIn;}
	public void setLoggedIn(Boolean loggedIn) {this.loggedIn = loggedIn;}

}
