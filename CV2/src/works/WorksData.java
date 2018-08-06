package works;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorksData {
	
	


	private final StringProperty wID;
	private final StringProperty wName;
	private final StringProperty wStart;
	private final StringProperty wEnd;
	private final StringProperty wStatus;
	private final StringProperty wTasks;
	
	
	public WorksData(String wID,String wName, String wStart, String wEnd, String wStatus, String wTasks) {
		
		this.wID = new SimpleStringProperty(wID);
		this.wName = new SimpleStringProperty(wName);
		this.wStart = new SimpleStringProperty(wStart);
		this.wEnd = new SimpleStringProperty(wEnd);
		this.wStatus = new SimpleStringProperty(wStatus);
		this.wTasks = new SimpleStringProperty(wTasks);
		
	}
	
	public StringProperty wIDProperty()
	  {
	    return this.wID;
	    
	  }
	public StringProperty wNameProperty()
	  {
	    return this.wName;
	    
	  }
	public StringProperty wStartProperty()
	  {
	    return this.wStart;
	    
	  }
	public StringProperty wEndProperty()
	  {
	    return this.wEnd;
	    
	  }
	public StringProperty wOutcomeProperty()
	  {
	    return this.wStatus;
	    
	  }
	
	public StringProperty wTasksProperty()
	  {
	    return this.wTasks;
	    
	  }
	
	
	
	public String getwID() {
		return (String)this.wID.get();
	}
	public String getwName() {
		return (String)this.wName.get();
	}
	public String getwStart() {
		return (String)this.wStart.get();
	}
	public String getwEnd() {
		return (String)this.wEnd.get();
	}
	public String getwStatus() {
		return (String)this.wStatus.get();
	}
	public String getwTasks() {
		return (String)this.wTasks.get();
	}
	

}

