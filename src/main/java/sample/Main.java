package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.ContractForVue;
import sample.service.ContractService;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Test Work");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        List<ContractForVue> contracts = new ContractService().getAllContractsForVue();
        System.out.println(contracts.toString());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
