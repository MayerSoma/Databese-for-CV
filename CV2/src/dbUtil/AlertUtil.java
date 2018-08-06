package dbUtil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {

	
	public static Alert createAlert(AlertType alertType, String title, String headerText, String contextText)
	{		
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contextText);
		
		return alert;
	}
	
	public static Alert createAlert(String title, String headerText, String contextText)
	{		
		return createAlert(AlertType.ERROR, title, headerText, contextText);
	}
	
	
	
	public static Alert createAlert(String title, String contextText)
	{		
		return createAlert(title, null, contextText);
	}
}
