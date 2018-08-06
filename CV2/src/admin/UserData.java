package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {
	
	



	private final StringProperty ID;
	private final StringProperty name;
	private final StringProperty birthplace;
	private final StringProperty birthtime;
	private final StringProperty nationality;
	private final StringProperty email;
	private final StringProperty phonenumber;
	private final StringProperty createdtime;
	
	
	
	public UserData(String id, String name, String birthplace, String birthtime,
			String nationality, String email, String phonenumber, String createdtime) {
		
		this.ID = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.birthplace = new SimpleStringProperty(birthplace);
		this.birthtime = new SimpleStringProperty(birthtime);
		this.nationality = new SimpleStringProperty(nationality);
		this.email = new SimpleStringProperty(email);
		this.phonenumber = new SimpleStringProperty(phonenumber);
		this.createdtime = new SimpleStringProperty(createdtime);

	}
	
	public StringProperty idProperty()
	  {
	    return this.ID;
	    
	  }
	public StringProperty nameProperty()
	  {
	    return this.name;
	    
	  }
	public StringProperty birthplaceProperty()
	  {
	    return this.birthplace;
	    
	  }
	public StringProperty birthtimeProperty()
	  {
	    return this.birthtime;
	    
	  }
	public StringProperty nationalityProperty()
	  {
	    return this.nationality;
	    
	  }
	public StringProperty emailProperty()
	  {
	    return this.email;
	    
	  }
	public StringProperty phonenumberProperty()
	  {
	    return this.phonenumber;
	    
	  }
	public StringProperty createdtimeProperty()
	  {
	    return this.createdtime;
	    
	  }
	
	
	public String getID() {
		return (String)this.ID.get();
	}

	public String getName() {
		return (String)this.name.get();
		}

	public String getBirthplace() {
		return (String)this.birthplace.get();
		}

	public String getBirthtime() {
		return (String)this.birthtime.get();
		}

	public String getNationality() {
		return (String)this.nationality.get();
		}

	public String getEmail() {
		return (String)this.email.get();
		}

	public String getPhonenumber() {
		return (String)this.phonenumber.get();
		}

	public String getCreatedtime() {
		return (String)this.createdtime.get();
		}
	

	
	

	

}
