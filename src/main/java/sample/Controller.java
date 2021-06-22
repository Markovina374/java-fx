package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        private TableColumn<ContractForVue, Boolean> relevant;

        @FXML
        private Button button;


        /**
         * Called to initialize a controller after its root element has been
         * completely processed.
         *
         * @param location  The location used to resolve relative paths for the root object, or
         *                  <tt>null</tt> if the location is not known.
         * @param resources The resources used to localize the root object, or <tt>null</tt> if
         */
        @Override
        public void initialize(URL location, ResourceBundle resources) {
                ContractService contractService = new ContractService();
                ObservableList<ContractForVue> contracts = FXCollections.observableArrayList(contractService.getAllContractsForVue());
                id.setCellValueFactory(new PropertyValueFactory<ContractForVue,Integer>("id"));
                date.setCellValueFactory(new PropertyValueFactory<ContractForVue,LocalDate>("date"));
                description.setCellValueFactory(new PropertyValueFactory<ContractForVue,String>("description"));
                dateOfLastUpdate.setCellValueFactory(new PropertyValueFactory<ContractForVue,LocalDate>("dateOfLastUpdate"));
                relevant.setCellValueFactory(new PropertyValueFactory<ContractForVue,Boolean>("relevant"));
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                myTable.setItems(contracts);
                        }
                });



        }
}
