package Scheduler;

import Scheduler.data.Appointments;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


/*
Server name: wgudb.ucertify.com
Port: 3306
Database name: WJ08NHo
Username: U08NHo
Password: 53689337383
 */
/*database interfaces are:
driver
connection
statement:executeUpdate():createStatement()
resultset
 */

/*createStatement method must be called from connetion reference*/
public class mainController implements Initializable {

    @FXML private Label location;
    @FXML private TextField user;
    @FXML private TextField pass;
    @FXML private Label userLabel;
    @FXML private Label passLabel;
    @FXML private Button exit;
    @FXML private Button login;
    /**initialize main controller*/
    @Override
    public void initialize(URL url, ResourceBundle rb){
        /**get local Zone for user and set language*/
        ZoneId myZone = ZoneId.systemDefault();
        location.setText(Comm.location(myZone.getId()));
        userLabel.setText(Comm.username());
        passLabel.setText(Comm.password());
        exit.setText(Comm.exit());
        login.setText(Comm.login());
    }
    /**initiates user login from main*/
    public void login(ActionEvent event) throws IOException {
        try {
            if (Main.login(user.getText(), pass.getText())) {
                Appointments app = Main.upcomingAppointment(Main.getUser().getCreateDate());
                DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter time= DateTimeFormatter.ofPattern("HH:mm");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(app!=null){
                    alert.setHeaderText("Appointment Alert!");
                    String s ="You have an upcoming appointment. Appointment ID:"+app.getId()+" Date:"+app.getStart().format(date)+
                            " Time:"+app.getStart().format(time);
                    alert.setContentText(s);
                    //appointment ID, date, and time
                }
                else {
                    alert.setHeaderText("Appointment Alert!");
                    String s ="You do not have any upcoming appointments.";
                    alert.setContentText(s);
                }
                alert.show();
                Parent landingView = FXMLLoader.load(getClass().getResource("landing/landing.fxml"));
                Scene landingScene = new Scene(landingView);

                Stage landingWin = (Stage) ((Node) event.getSource()).getScene().getWindow();
                landingWin.setScene(landingScene);
                landingWin.show();
            }
        }catch(Exception e){
            //ignore
        }
    }
    /**redirects to login form*/
    public void exit(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }



}
