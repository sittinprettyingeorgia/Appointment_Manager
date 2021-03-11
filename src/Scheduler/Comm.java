package Scheduler;

import javafx.scene.control.Alert;

import java.util.Locale;
import java.util.ResourceBundle;

/**manages properties for language changes*/
public class Comm {
    private static ResourceBundle bundle = ResourceBundle.getBundle("Scheduler/Nat", Locale.getDefault());
    /**gets appropriate response for 'username' in required language*/
    public static String username(){
        return bundle.getString("Username");
    }
    /**gets appropriate response for 'password' in required language*/
    public static String password(){
        return bundle.getString("Password");
    }
    /**gets appropriate response for 'exit' in required language*/
    public static String exit(){
        return bundle.getString("Exit");
    }
    /**gets appropriate response for location of user in required language*/
    public static String location(String location){
        return bundle.getString(location);
    }
    /**gets appropriate response for 'Login' in required language*/
    public static String login(){
        return bundle.getString("Login");
    }
    /**gets confirmation dailog box alert for connection successful in required language*/
    public static void connSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(bundle.getString("Confirmation") + " " + bundle.getString("Alert"));
        String s =bundle.getString("Confirmation") + " " + bundle.getString("Successful");
        alert.setContentText(s);
        alert.show();
    }
    /**gets dialog box for successful login in required language*/
    public static void loginResult(boolean res){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(res){
            alert.setHeaderText(bundle.getString("Confirmation") + " " + bundle.getString("Alert"));
            String s =bundle.getString("Login") + " " + bundle.getString("Successful");
            alert.setContentText(s);
        }
        else {
            alert.setHeaderText(bundle.getString("Error") + " " + bundle.getString("Alert"));
            String s =bundle.getString("You")+" " + bundle.getString("Have") +" " + bundle.getString("Entered")+ " "
                    + bundle.getString("An")+" " + bundle.getString("Invalid")+ " " + bundle.getString("Username")+ " "
                    + bundle.getString("Or")+ " " +bundle.getString("Password");
            alert.setContentText(s);
        }
        alert.show();
    }
}
