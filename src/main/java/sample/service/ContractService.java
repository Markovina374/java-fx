package sample.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.CheckBox;
import sample.entity.Contract;
import sample.entity.ContractForVue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContractService {
    private final static String url = "http://localhost:8080/rest/contracts";

    public List<Contract> getAllContracts(){
        List<Contract> contractList;
        try {
            Type itemsListType = new TypeToken<List<Contract>>() {}.getType();
            URL url = new URL(ContractService.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();
            contractList = gson.fromJson(bufferedReader, itemsListType);
            connection.disconnect();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return contractList;

    }
    public List<ContractForVue> getAllContractsForVue(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Contract> contracts = getAllContracts();
        List<ContractForVue> contractsForVue = new ArrayList<>();
        for (Contract contract:contracts) {
            ContractForVue contractForVue = new ContractForVue();
            contractForVue.setId(contract.getId());
            contractForVue.setDate(LocalDate.parse(contract.getDate(),df));
            contractForVue.setDateOfLastUpdate(LocalDate.parse(contract.getDateOfLastUpdate(),df));
            contractForVue.setDescription(contract.getDescription());
            contractForVue.setRelevant(LocalDate.now().minusDays(60).isBefore(contractForVue.getDateOfLastUpdate()));
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(contractForVue.getRelevant());
            contractForVue.setCheckBox(checkBox);
            contractsForVue.add(contractForVue);
        }


        return contractsForVue;

    }


}
