package studies;

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

import javax.swing.JOptionPane;

import dbUtil.dbConnection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import admin.AdminController;
import studies.StudiesData;

public class StudiesController implements Initializable {
	public String idcheck;
	@FXML
	public Label IDIDID;
	@FXML
	private Label name;
	@FXML
	private TextField txtid;
	@FXML
	private TextField txtname;
	@FXML
	private TextField txtstart;
	@FXML
	private TextField txtend;
	@FXML
	private TextField txtoutcome;
	
	@FXML
	private TableView<StudiesData> studiestable;
	
	@FXML
	private TableColumn<StudiesData, String> idcol;
	@FXML
	private TableColumn<StudiesData, String> namecol;
	@FXML
	private TableColumn<StudiesData, String> startcol;
	@FXML
	private TableColumn<StudiesData, String> endcol;
	@FXML
	private TableColumn<StudiesData, String> outcomecol;
	
	
	private ObservableList<StudiesData> data;
	  private dbConnection dc;
	  
	  @Override
		public void initialize(URL location, ResourceBundle resources) {
			this.dc = new dbConnection();
			this.name.setText(AdminController.asd);
			this.IDIDID.setText(AdminController.IDID);
			System.out.println(IDIDID);
			idcheck = AdminController.IDID;
			System.out.println(idcheck);
		}
	  
	  
	  @FXML
	  private void loadStudiesData(ActionEvent event) throws SQLException
	  {
		  String sql = "SELECT * FROM studies WHERE sid='"+idcheck+"'";
		  System.out.println(sql);	  

	    try
	    {
	      Connection conn = dbConnection.getConnection();
	      this.data = FXCollections.observableArrayList();
	      
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      while (rs.next()) {
	        this.data.add(new StudiesData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
	      }
	    }
	    catch (SQLException e)
	    {
	      System.err.println("Error " + e);
	    }
		
		this.idcol.setCellValueFactory(new PropertyValueFactory("sID"));
		this.namecol.setCellValueFactory(new PropertyValueFactory<StudiesData, String>("sName"));
		this.startcol.setCellValueFactory(new PropertyValueFactory<StudiesData, String>("sStart"));
		this.endcol.setCellValueFactory(new PropertyValueFactory<StudiesData, String>("sEnd"));
		this.outcomecol.setCellValueFactory(new PropertyValueFactory<StudiesData, String>("sOutcome"));
		
		
		
		this.studiestable.setItems(null);

		this.studiestable.setItems(this.data);
		
	}
	
	  
	  
	  
	  
	  @FXML
		private void addStudies (ActionEvent event)
		{
			String sqlInsert = "INSERT INTO studies(sid,sname,sstart,send,soutcome) VALUES (?,?,?,?,?)";
			
			try
			{
				Connection conn = dbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlInsert);
				stmt.setString(1,this.txtid.getText());
				stmt.setString(2,this.txtname.getText());
				stmt.setString(3,this.txtstart.getText());
				stmt.setString(4,this.txtend.getText());
				stmt.setString(5,this.txtoutcome.getText());
				
				
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
	
		try {
			String value1 = txtid.getText();
			String value2 = txtname.getText();
			String value3 = txtstart.getText();
			String value4 = txtend.getText();
			String value5 = txtoutcome.getText();
			System.out.println("WTF?");
			System.out.println(value4);
		
		
			String sqlUpdate = "UPDATE studies SET sname ='"+value2+"', sstart ='"+value3+"', send ='"+value4+"', soutcome ='"+value5+"' WHERE sname ='"+value2+"' ";
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
		
	    
	  public void rowClicked(MouseEvent evt)
		{
		  
		  	
			StudiesData usrSelected = studiestable.getSelectionModel().getSelectedItem();
			txtid.setText(usrSelected.getsID() + "");
			txtname.setText(usrSelected.getsName() + "");
			txtstart.setText(usrSelected.getsStart() + "");
			txtend.setText(usrSelected.getsEnd() + "");
			txtoutcome.setText(usrSelected.getsOutcome()+ "");
			
			
			  

		}
	  
	  public void save(MouseEvent evt)
	  {
		  
	  }

		@FXML
		private void deleteStudies (ActionEvent e)
		{
			try {
			Connection conn = dbConnection.getConnection();
			String delete = "DELETE FROM studies WHERE sid='"+txtid.getText()+"' ";
			PreparedStatement pst = conn.prepareStatement(delete);
			
			pst.execute();
			conn.close();
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			}
		
		@FXML
		void clearFields(ActionEvent event)
		{
			this.txtid.setText("");
			this.txtname.setText("");
			this.txtstart.setText("");
			this.txtend.setText("");
			this.txtoutcome.setText("");
			
			
		}



}
