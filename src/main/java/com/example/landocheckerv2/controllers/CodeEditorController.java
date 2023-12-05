package com.example.landocheckerv2.controllers;

import com.example.landocheckerv2.checker.Checker;
import com.example.landocheckerv2.checker.models.Prod;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Stack;

public class CodeEditorController {

    private final Checker checker = new Checker();

    @FXML
    private TextArea codeField;

    @FXML
    private TextFlow outputField;

    @FXML
    void onCheckButton() {
        String logs = "";
        String code = codeField.getText();
        Stack<Prod> initialStack = checker.getInitialStack();
        outputField.getChildren().clear();
        System.out.println("CHECKER: " + checker.separateElements(code));
        checker.setLogs(logs);

        boolean result = checker.check(code, initialStack, 0);

        Text logsText = new Text(checker.getLogs());
        logsText.setFill(Color.GRAY);
        logsText.setFont(javafx.scene.text.Font.font("JetBrainsMonoNL NF Regular", 15));
        outputField.getChildren().add(logsText);

        Text resultText;
        if (result) {
            resultText = new Text("The code is correct");
            resultText.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            resultText = new Text("The code is incorrect");
            resultText.setFill(javafx.scene.paint.Color.RED);
        }
        resultText.setFont(javafx.scene.text.Font.font("JetBrainsMonoNL NF Regular", 15));
        outputField.getChildren().add(resultText);
    }

    @FXML
    void onClearButton() {
        // clear codeField
        codeField.setText("");
        // clear outputField
        outputField.getChildren().clear();
    }
}
