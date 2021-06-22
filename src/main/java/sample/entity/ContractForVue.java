package sample.entity;

import javafx.scene.control.CheckBox;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContractForVue {
    private int id;
    private String description;
    private LocalDate date;
    private LocalDate dateOfLastUpdate;
    private Boolean relevant;
    private CheckBox checkBox;
}
