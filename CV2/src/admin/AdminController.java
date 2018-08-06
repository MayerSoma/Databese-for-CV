package admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import studies.StudiesController;
import works.WorksController;
import dbUtil.AlertUtil;
import dbUtil.dbConnection;




public class AdminController implements Initializable{
		
	

	boolean isNew;
	public static String asd;
	public static String IDID;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField birthplace;
	@FXML
	private DatePicker birthtime;
	@FXML
	private TextField nationality;
	@FXML
	private TextField email;
	@FXML
	private TextField phonenumber;
	@FXML
	private TextField createdtime;
	
	@FXML
	private TableView<UserData> usertable;
	
	@FXML
	private TableColumn<UserData, String> idcol;
	@FXML
	private TableColumn<UserData, String> namecol;
	@FXML
	private TableColumn<UserData, String> birthplacecol;
	@FXML
	private TableColumn<UserData, String> birthtimecol;
	@FXML
	private TableColumn<UserData, String> nationalitycol;
	@FXML
	private TableColumn<UserData, String> emailcol;
	@FXML
	private TableColumn<UserData, String> phonenumbercol;
	@FXML
	private TableColumn<UserData, String> createdtimecol;
	
	@FXML
	  private Button loadbutton;
	  private ObservableList<UserData> data;
	  private dbConnection dc;
	  
	  @Override
		public void initialize(URL location, ResourceBundle resources) {
			this.dc = new dbConnection();
			
		}
	  
	  private String sql = "SELECT * FROM users";
	  
	  @FXML
	  private void loadUserData(ActionEvent event) throws SQLException
	  {
	    try
	    {
	      Connection conn = dbConnection.getConnection();
	      this.data = FXCollections.observableArrayList();
	      
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      while (rs.next()) {
	        this.data.add(new UserData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
	      }
	    }
	    catch (SQLException e)
	    {
	      System.err.println("Error " + e);
	    }
		
		this.idcol.setCellValueFactory(new PropertyValueFactory("ID"));
		this.namecol.setCellValueFactory(new PropertyValueFactory<UserData, String>("name"));
		this.birthplacecol.setCellValueFactory(new PropertyValueFactory<UserData, String>("birthplace"));
		this.birthtimecol.setCellValueFactory(new PropertyValueFactory<UserData, String>("birthtime"));
		this.nationalitycol.setCellValueFactory(new PropertyValueFactory<UserData, String>("nationality"));
		this.emailcol.setCellValueFactory(new PropertyValueFactory<UserData, String>("email"));
		this.phonenumbercol.setCellValueFactory(new PropertyValueFactory<UserData, String>("phonenumber"));
		this.createdtimecol.setCellValueFactory(new PropertyValueFactory<UserData, String>("createdtime"));
		
		
		this.usertable.setItems(null);

		this.usertable.setItems(this.data);
		
	}
	
	
	@FXML
	private void addUser (ActionEvent event)
	{
		String sqlInsert = "INSERT INTO users(id,name,birthPlace,birthTime,nationality,email,phoneNumber,createdTime) VALUES (?,?,?,?,?,?,?,?)";
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String datee = df.format(today);
		try
		{
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1,this.id.getText());
			stmt.setString(2,this.name.getText());
			stmt.setString(3,this.birthplace.getText());
			stmt.setString(4,this.birthtime.getEditor().getText());
			stmt.setString(5,this.nationality.getText());
			stmt.setString(6,this.email.getText());
			stmt.setString(7,this.phonenumber.getText());
			stmt.setString(8,datee);
			
			stmt.execute();
			conn.close();
		} 
		catch (SQLException e)
		{
			System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		}
	}
	
	
	public void save(ActionEvent evt)

	  {
		UserData usrSelected = usertable.getSelectionModel().getSelectedItem();
		if(usrSelected == null)
		{
			Alert alert = AlertUtil.createAlert("Error", "Chosing Error", "Chose an object!");
			alert.show();
		}
		else {
		
		try {
			String value1 = id.getText();
			String value2 = name.getText();
			String value3 = birthplace.getText();
			String value4 = birthtime.getEditor().getText();
			String value5 = nationality.getText();
			String value6 = email.getText();
			String value7 = phonenumber.getText();
			String value8 = createdtime.getText();
			
		
		
			String sqlUpdate = "UPDATE users SET name ='"+value2+"', birthplace ='"+value3+"', birthtime ='"+value4+"', nationality ='"+value5+"', email ='"+value6+"', phonenumber ='"+value7+"', createdtime ='"+value8+"' WHERE id ='"+value1+"' ";
			Connection conn = dbConnection.getConnection();
			PreparedStatement pst = conn.prepareStatement(sqlUpdate);
			
			pst.execute();
			conn.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		}
	
	
	public void rowClicked(MouseEvent evt)
	{
		UserData usrSelected = usertable.getSelectionModel().getSelectedItem();
		id.setText(usrSelected.getID() + "");
		name.setText(usrSelected.getName() + "");
		birthplace.setText(usrSelected.getBirthplace() + "");
		birthtime.setPromptText(usrSelected.getBirthtime() + "");
		nationality.setText(usrSelected.getNationality()+ "");
		email.setText(usrSelected.getEmail() + "");
		phonenumber.setText(usrSelected.getPhonenumber() + "");
		createdtime.setText(usrSelected.getCreatedtime() + "");

		asd = setTextt(usrSelected.getName() + "");
		System.out.println(asd);
		IDID = setTexttt(usrSelected.getID() + "");
		System.out.println(IDID);
		
		
	}
	


	private String setTexttt(String string) {
		// TODO Auto-generated method stub
		return string;
	}
	
	public String getIDID() {
		return IDID;
	}


	public String getAsd() {
		return asd;
	}
	
	private String setTextt(String string) {
		// TODO Auto-generated method stub
		return string;
	}


	@FXML
	private void deleteUser (ActionEvent e)
	{
		
		UserData usrSelected = usertable.getSelectionModel().getSelectedItem();
		if(usrSelected == null)
		{
			Alert alert = AlertUtil.createAlert("Error", "Chosing Error", "Chose an object!");
			alert.show();
		}
		else {
		try {
		Connection conn = dbConnection.getConnection();
		String delete = "DELETE FROM users WHERE id='"+id.getText()+"' ";
		PreparedStatement pst = conn.prepareStatement(delete);
		
		pst.execute();
		conn.close();
		
			}
		catch(Exception ex)
			{
			ex.printStackTrace();
			}
		}
	}
	

	
	@FXML
	void clearFields(ActionEvent event)
	{
		this.id.setText("");
		this.name.setText("");
		this.birthplace.setText("");
		this.birthtime.setValue(null);
		this.nationality.setText("");
		this.email.setText("");
		this.phonenumber.setText("");
		
	}

	public void studiesClick()
	  {
		
		UserData usrSelected = usertable.getSelectionModel().getSelectedItem();
		if(usrSelected == null)
		{
			Alert alert = AlertUtil.createAlert("Error", "Chosing Error", "Chose an object!");
			alert.show();
		}
		else {
	    try
	    {
	      Stage studiesStage = new Stage();
	      FXMLLoader loader = new FXMLLoader();
	      Pane root = (Pane)loader.load(getClass().getResource("/studies/studiesFXML.fxml").openStream());
	      StudiesController studiesController = (StudiesController)loader.getController();
	      
	      Scene scene = new Scene(root);
	      studiesStage.setScene(scene);
	      studiesStage.setTitle("Studies");
	      studiesStage.setResizable(false);
	      studiesStage.show();
	      
	      
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
		}
	  }
	
	@FXML
	public void worksClick()
	  {
		UserData usrSelected = usertable.getSelectionModel().getSelectedItem();
		if(usrSelected == null)
		{
			Alert alert = AlertUtil.createAlert("Error", "Chosing Error", "Chose an object!");
			alert.show();
		}
		else {
		
	    try
	    {
	    	System.out.println("WTF?");
	      Stage worksStage = new Stage();
	      FXMLLoader loader = new FXMLLoader();
	      Pane root = (Pane)loader.load(getClass().getResource("/works/worksFXML.fxml").openStream());
	      WorksController worksController = (WorksController)loader.getController();
	      
	      Scene scene = new Scene(root);
	      worksStage.setScene(scene);
	      worksStage.setTitle("Works");
	      worksStage.setResizable(false);
	      worksStage.show();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
		}
	  }

	
}


	
	
	
	
	

