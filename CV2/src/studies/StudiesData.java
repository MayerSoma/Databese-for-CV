package studies;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudiesData {
	
	


	private final StringProperty sID;
	private final StringProperty sName;
	private final StringProperty sStart;
	private final StringProperty sEnd;
	private final StringProperty sOutcome;
	
	
	public StudiesData(String sID,String sName, String sStart, String sEnd, String sOutcome) {
		
		this.sID = new SimpleStringProperty(sID);
		this.sName = new SimpleStringProperty(sName);
		this.sStart = new SimpleStringProperty(sStart);
		this.sEnd = new SimpleStringProperty(sEnd);
		this.sOutcome = new SimpleStringProperty(sOutcome);
		
	}
	
	public StringProperty sIDProperty()
	  {
	    return this.sID;
	    
	  }
	public StringProperty sNameProperty()
	  {
	    return this.sName;
	    
	  }
	public StringProperty sStartProperty()
	  {
	    return this.sStart;
	    
	  }
	public StringProperty sEndProperty()
	  {
	    return this.sEnd;
	    
	  }
	public StringProperty sOutcomeProperty()
	  {
	    return this.sOutcome;
	    
	  }
	
	
	
	public String getsID() {
		return (String)this.sID.get();
	}
	public String getsName() {
		return (String)this.sName.get();
	}
	public String getsStart() {
		return (String)this.sStart.get();
	}
	public String getsEnd() {
		return (String)this.sEnd.get();
	}
	public String getsOutcome() {
		return (String)this.sOutcome.get();
	}
	

}
