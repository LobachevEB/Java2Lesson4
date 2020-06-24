package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML public Button chatButton;
    @FXML public Button calculatorButton;

    Stage chatWindow;
    Stage calculatorWindow;

    public void startChatAction(ActionEvent actionEvent) {
        if(chatWindow == null){
            chatWindow = createChatWindow();
        }
        chatWindow.show();
    }

    private Stage createChatWindow(){
        Stage chat = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat.fxml"));
            Parent root = fxmlLoader.load();
            chat = new Stage();
            chat.setTitle("Chat with anybody");
            chat.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chat;
    }

    public void startCalculatorAction(ActionEvent actionEvent) {
        if(calculatorWindow == null){
            calculatorWindow = createCalculatorWindow();
        }
        calculatorWindow.show();
    }

    private Stage createCalculatorWindow(){
        Stage calc = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculator.fxml"));
            Parent root = fxmlLoader.load();
            calc = new Stage();
            calc.setTitle("Simple Calculator");
            calc.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calc;
    }
}
