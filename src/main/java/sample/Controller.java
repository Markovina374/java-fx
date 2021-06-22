package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.entity.ContractForVue;
import sample.service.ContractService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final ContractService contractService = new ContractService();

    @FXML
    private TableView<ContractForVue> myTable;

    @FXML
    private TableColumn<ContractForVue, Integer> id;

    @FXML
    private TableColumn<ContractForVue, LocalDate> date;

    @FXML
    private TableColumn<ContractForVue, String> description;

    @FXML
    private TableColumn<ContractForVue, LocalDate> dateOfLastUpdate;

    @FXML
    private TableColumn<ContractForVue, CheckBox> relevant;

    @FXML
    private Button button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateOfLastUpdate.setCellValueFactory(new PropertyValueFactory<>("dateOfLastUpdate"));
        relevant.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            ObservableList<ContractForVue> contracts = FXCollections.observableArrayList(contractService.getAllContractsForVue());
            myTable.setItems(contracts);
        });


    }
}
