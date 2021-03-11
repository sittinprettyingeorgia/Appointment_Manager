package Scheduler.landing;

import Scheduler.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML private Button exit;
    @FXML private TextArea report;
    /**initialize form*/
    @Override
    public void initialize(URL url, ResourceBundle rb){
        report.setWrapText(true);
        ArrayList<String> reportList = Main.generateReports();
        for(String s: reportList){
            report.appendText("\n"+s);
        }
    }
    /**redirects to landing form*/
    public void Exit(ActionEvent event) throws IOException {
        Parent landingView = FXMLLoader.load(getClass().getResource("landing.fxml"));
        Scene landingScene = new Scene(landingView);

        Stage landingWin = (Stage) ((Node) event.getSource()).getScene().getWindow();
        landingWin.setScene(landingScene);
        landingWin.show();
    }
}
