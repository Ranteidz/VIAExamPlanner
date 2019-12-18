package planner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataModel;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        DataModel model = new DataModel();
        PrimaryController controller = loader.getController();
        controller.setModel(model);
        primaryStage.setTitle("VIA Exam Planner");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}