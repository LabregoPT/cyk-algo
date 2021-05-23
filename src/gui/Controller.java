package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.CFG;
import model.CYK;
import model.Rule;
import model.Variable;

import java.util.ArrayList;

public class Controller {

    CFG g = new CFG("G");

    @FXML
    private TextField txtFieldVariables;

    @FXML
    private Button butContinuarCadena;

    @FXML
    private Button butContinueVariables;

    @FXML
    private ChoiceBox<String> choiceBoxVariable;

    @FXML
    private TextField txtFieldProduction;

    @FXML
    private Button butAddProduction;

    @FXML
    private Button butEvaluar;

    @FXML
    private TextField txtCadena;

    @FXML
    private ChoiceBox<String> choiceBoxTipoRegla;

    @FXML
    private Label resultLabel;

    @FXML
    void addProduction(ActionEvent event) {
        String textProduction = txtFieldProduction.getText();
        txtFieldProduction.setText("");
        String initialVariable = choiceBoxVariable.getValue();
        Variable initialVariable_;
        try {
            initialVariable_ = g.getVariableByName(initialVariable);
            Rule newRule;
            switch (choiceBoxTipoRegla.getValue()) {
                case ("Simple"):
                    newRule = new Rule(textProduction);
                    initialVariable_.addRule(newRule);
                    g.getVariablesOfSimpleProductionRules().add(initialVariable_);
                    break;
                case ("Binaria"):
                    newRule = new Rule(g.getVariableByName(textProduction.substring(0,1)),g.getVariableByName(textProduction.substring(1,2)));
                    initialVariable_.addRule(newRule);
                    g.getVariablesOfBinaryProductionRules().add(initialVariable_);
                    break;
            }
        } catch (NullPointerException e) {
//            throw new NullPointerException("Variable not found in G", e);
        }

    }

    @FXML
    void continuarCadena(ActionEvent event) {
        txtCadena.setDisable(false);
        butEvaluar.setDisable(false);
        choiceBoxVariable.setDisable(true);
        choiceBoxTipoRegla.setDisable(true);
        txtFieldProduction.setDisable(true);
        butContinuarCadena.setDisable(true);
        butAddProduction.setDisable(true);

        //debugging section
        System.out.println("G:");
        System.out.println("Simples:");
        for (Variable variable:g.getVariablesOfSimpleProductionRules()
             ) {
            System.out.println(variable.getName());
        }
        for (Variable variable:g.getVariablesOfBinaryProductionRules()
        ) {
            System.out.println(variable.getName());
        }
    }

    public static void main(String[] args) {
        //Debugging area for the CYK algorithm.
        CFG testG = new CFG("Test G");
        Variable s = new Variable("S",true);
        Variable a = new Variable("A",false);
        Variable b = new Variable("B",false);
        Variable c = new Variable("C",false);
        s.addRule(new Rule(b,a));
        s.addRule(new Rule(a,c));
        a.addRule(new Rule(c,c));
        a.addRule(new Rule("b"));
        b.addRule(new Rule(a,b));
        b.addRule(new Rule("a"));
        c.addRule(new Rule(b,a));
        c.addRule(new Rule("a"));

        testG.getVariablesOfSimpleProductionRules().add(a);
        testG.getVariablesOfSimpleProductionRules().add(b);
        testG.getVariablesOfSimpleProductionRules().add(c);

        testG.getVariablesOfBinaryProductionRules().add(s);
        testG.getVariablesOfBinaryProductionRules().add(a);
        testG.getVariablesOfBinaryProductionRules().add(b);
        testG.getVariablesOfBinaryProductionRules().add(c);

//        System.out.println(testG.getVariablesOfSimpleProductionRules().size());
        System.out.println(CYK.belongsToCFG(testG,"bbab"));
    }

    @FXML
    void evaluarCadena(ActionEvent event) {
        resultLabel.setVisible(true);
        boolean canBeProduced = CYK.belongsToCFG(g, txtCadena.getText());
        if (canBeProduced) {
            resultLabel.setText("G produce la cadena w.");
        } else {
            resultLabel.setText("G no produce la cadena w.");
        }
    }

    @FXML
    void getVariables(ActionEvent event) {
        //traer información
        txtFieldVariables.setDisable(true);
        txtFieldVariables.setText("S," + txtFieldVariables.getText());
        String[] variables = txtFieldVariables.getText().split(",");
        butContinueVariables.setDisable(true);

        //asignar los datos recogidos al modelo
        Variable variableToAdd = new Variable("S",true);
        for (int i = 1; i < variables.length; i++) {
            variableToAdd = new Variable(variables[i],false);
            g.addVariable(variableToAdd);
        }

        //activar lo siguiente
        choiceBoxVariable.setDisable(false);
        txtFieldProduction.setDisable(false);
        choiceBoxTipoRegla.setDisable(false);
        butAddProduction.setDisable(false);
        butContinuarCadena.setDisable(false);

        //asignar los valores a los combobox
        choiceBoxVariable.setValue("S");
        choiceBoxVariable.getItems().addAll(variables);
        choiceBoxTipoRegla.getItems().add("Simple");
        choiceBoxTipoRegla.getItems().add("Binaria");
        choiceBoxTipoRegla.setValue("Simple");
    }
}
