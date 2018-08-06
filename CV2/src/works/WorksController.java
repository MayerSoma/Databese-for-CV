package works;
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

public class WorksController implements Initializable {
	
	
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
	private TextField txtstatus;
	@FXML
	private TextField txttask;
	
	@FXML
	private TableView<WorksData> workstable;
	
	@FXML
	private TableColumn<WorksData, String> idcol;
	@FXML
	private TableColumn<WorksData, String> namecol;
	@FXML
	private TableColumn<WorksData, String> startcol;
	@FXML
	private TableColumn<WorksData, String> endcol;
	@FXML
	private TableColumn<WorksData, String> statuscol;
	@FXML
	private TableColumn<WorksData, String> taskcol;
	
	
	private ObservableList<WorksData> data;
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
	  private void loadWorksData(ActionEvent event) throws SQLException
	  {
		  String sql = "SELECT * FROM works WHERE wid='"+idcheck+"'";
		  System.out.println(sql);	  

	    try
	    {
	      Connection conn = dbConnection.getConnection();
	      this.data = FXCollections.observableArrayList();
	      
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      while (rs.next()) {
	        this.data.add(new WorksData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
	      }
	    }
	    catch (SQLException e)
	    {
	      System.err.println("Error " + e);
	    }
		
		this.idcol.setCellValueFactory(new PropertyValueFactory("wID"));
		this.namecol.setCellValueFactory(new PropertyValueFactory<WorksData, String>("wName"));
		this.startcol.setCellValueFactory(new PropertyValueFactory<WorksData, String>("wStart"));
		this.endcol.setCellValueFactory(new PropertyValueFactory<WorksData, String>("wEnd"));
		this.statuscol.setCellValueFactory(new PropertyValueFactory<WorksData, String>("wStatus"));
		this.taskcol.setCellValueFactory(new PropertyValueFactory<WorksData, String>("wTasks"));
		
		
		
		this.workstable.setItems(null);

		this.workstable.setItems(this.data);
		
	}
	  
	  public void save(ActionEvent evt)
	
		  {
		
			try {
				String value1 = txtid.getText();
				String value2 = txtname.getText();
				String value3 = txtstart.getText();
				String value4 = txtend.getText();
				String value5 = txtstatus.getText();
				String value6 = txttask.getText();
				
			
			
				String sqlUpdate = "UPDATE works SET wname ='"+value2+"', wstart ='"+value3+"', wend ='"+value4+"', wstatus ='"+value5+"', wtasks ='"+value6+"' WHERE wname ='"+value2+"' ";
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
	
	  
	  
	  
	  
	  @FXML
		private void addWorks (ActionEvent event)
		{
			String sqlInsert = "INSERT INTO works(wid,wname,wstart,wend,wstatus,wtasks) VALUES (?,?,?,?,?,?)";
			
			try
			{
				Connection conn = dbConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlInsert);
				stmt.setString(1,this.txtid.getText());
				stmt.setString(2,this.txtname.getText());
				stmt.setString(3,this.txtstart.getText());
				stmt.setString(4,this.txtend.getText());
				stmt.setString(5,this.txtstatus.getText());
				stmt.setString(6,this.txttask.getText());
				
				
				stmt.execute();
				conn.close();
			} 
			catch (SQLException e)
			{
				System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			}
		}
		
	    
	  public void rowClicked(MouseEvent evt)
		{
		  
		  	
			WorksData usrSelected = workstable.getSelectionModel().getSelectedItem();
			txtid.setText(usrSelected.getwID() + "");
			txtname.setText(usrSelected.getwName() + "");
			txtstart.setText(usrSelected.getwStart() + "");
			txtend.setText(usrSelected.getwEnd() + "");
			txtstatus.setText(usrSelected.getwStatus()+ "");
			txttask.setText(usrSelected.getwTasks()+ "");
			
			
			  

		}

		@FXML
		private void deleteWorks (ActionEvent e)
		{
			try {
			Connection conn = dbConnection.getConnection();
			String delete = "DELETE FROM works WHERE wname='"+txtname.getText()+"' ";
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
			this.txtstatus.setText("");
			this.txttask.setText("");
			
			
		}



}
