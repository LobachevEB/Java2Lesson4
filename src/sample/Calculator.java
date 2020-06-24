package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    @FXML public Button b7;
    @FXML public Button b8;
    @FXML public Button b9;
    @FXML public Button b4;
    @FXML public Button b5;
    @FXML public Button b6;
    @FXML public Button b1;
    @FXML public Button b2;
    @FXML public Button b3;
    @FXML public Button b0;
    @FXML public Button bMul;
    @FXML public Button bDiv;
    @FXML public Button bPlus;
    @FXML public Button bComma;
    @FXML public Button bMinus;
    @FXML public Button bResult;
    @FXML public Button bRemove;
    @FXML public TextField resultText;
    @FXML public TextArea history;
    @FXML public HBox buttonsArea;

    private Boolean newIteration = false;
    private ScriptEngineManager mgr;
    private ScriptEngine engine;

    public Calculator() {
        mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    @FXML
    private void btnPress(ActionEvent actionEvent) {
        if(newIteration){
            switch (((Button) (actionEvent.getSource())).getText()) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "-":
                    resultText.setText("0");
                    break;
            }
            newIteration = false;
        }
        StringBuilder Expression = new StringBuilder(resultText.getText());
        switch (((Button) (actionEvent.getSource())).getId()){
            case "bRemove":
                if(Expression.length() > 0 ){
                    Expression.deleteCharAt(Expression.length()-1);
                    resultText.setText(Expression.toString());
                }
                break;
            default:
                switch (((Button) (actionEvent.getSource())).getText()){
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                    case "-":
                        String chk = Expression.toString();
                        if(chk.equals("0") ){
                          Expression.setLength(0);
                        }
                        break;
                }
                resultText.setText(Expression + ((Button) (actionEvent.getSource())).getText());
                break;
        }
    }

    @FXML
    public void doCalculation(ActionEvent actionEvent) {
        newIteration = true;
        String Expression = resultText.getText();
        String result = Expression;
        Boolean success = true;
        if(Expression.length() == 0){
            return;
        }
        try {
            result = engine.eval(Expression) + "";
            if(result.equals("Infinity")){
                result = Expression;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(String.format("Divide by zero: %s",Expression));
                alert.show();
                success = false;
            }
            else {
                try {
                    Double d = Double.parseDouble(result);
                }
                catch (Exception e){
                    result = Expression;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Illegal Result: %s",Expression));
                    alert.show();
                    success = false;
                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(String.format("Illegal Expression: %s",Expression));
            alert.show();
            success = false;
        }
        resultText.setText(result);
        if(success){
            history.appendText(Expression + " = " + result + "\n");
        }
    }
}
