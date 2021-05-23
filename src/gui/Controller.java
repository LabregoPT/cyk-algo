package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.RuleType;

public class Controller {

    @FXML
    private TextField txtFieldVariables;

    @FXML
    private Button butContinuarCadena;

    @FXML
    private Button butContinueVariables;

    @FXML
    private ComboBox<?> comboBoxVariable;

    @FXML
    private TextField txtFieldProduction;

    @FXML
    private Button butAddProduction;

    @FXML
    private Button butEvaluar;

    @FXML
    private TextField txtCadena;

    @FXML
    private ComboBox<?> comboBoxTipoRegla;

    @FXML
    void addProduction(ActionEvent event) {

    }

    @FXML
    void continuarCadena(ActionEvent event) {

    }

    @FXML
    void evaluarCadena(ActionEvent event) {

    }

    @FXML
    void getVariables(ActionEvent event) {

        //traer información
        txtFieldVariables.setDisable(true);
        txtFieldVariables.setText("S," + txtFieldVariables.getText());
        String[] variables = txtFieldVariables.getText().split(",");
        butContinueVariables.setDisable(true);

        //asignar los datos recogidos al modelo

        //activar lo siguiente
        comboBoxVariable.setDisable(false);
        txtFieldProduction.setDisable(false);
        comboBoxTipoRegla.setDisable(false);
        butAddProduction.setDisable(false);
        butContinuarCadena.setDisable(false);

        //asignar los valores a los combobox
        comboBoxTipoRegla.getItems().add(RuleType.Simple.toString());

    }
}
