package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Chat {
    @FXML public Button sendButton;
    @FXML public TextField messageText;
    @FXML public TextArea messageArea;

    public void sendMessage() {
        String message = messageText.getText();
        if(!message.equals("")){
            messageArea.setWrapText(true);
            messageArea.appendText(message + "\n");
            messageText.clear();
        }
        messageText.requestFocus();
    }

    public void textKeyAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            sendMessage();
        }
    }
}
