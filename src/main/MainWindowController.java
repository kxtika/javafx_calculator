package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindowController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnClose, btnMinimize;
    @FXML private Label lblResult;

    private double x, y;
    private double num1 = 0;
    private String operator = ".";
    private boolean isDotUsed = false;

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    // Update the result label text
    private void updateResultLabel(String text) {
        if (operator.equals(".") && text.startsWith(".")) {
            lblResult.setText(text.replaceFirst("^\\.", ""));
        } else if (operator.equals(".") && text.endsWith(".0")) {
            lblResult.setText(text.replaceAll("\\.0$", ""));
        } else {
            lblResult.setText(text);
        }

        if (text.equals(".")) {
            isDotUsed = true;
            lblResult.setText("0" + text);
        } else if (isDotUsed) {
            isDotUsed = false;
            if (!text.contains(".")) {
                lblResult.setText(text);
            }
        }
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        String sourceId = ((Pane) event.getSource()).getId().replace("btn", "");
        String currentText = lblResult.getText();

        if (operator.equals(".") && currentText.equals("0.0") && !sourceId.equals("0")) {
            lblResult.setText(sourceId);
        } else if (operator.equals(".") && currentText.equals("0.0") && sourceId.equals("0")) {
            // Do nothing
        } else if (operator.equals(".") && currentText.equals("0") && !sourceId.equals("0")) {
            lblResult.setText(sourceId);
        } else if (operator.equals(".") && currentText.equals("0") && sourceId.equals("0")) {
            // Do nothing
        } else if (operator.equals(".") && currentText.endsWith(".")) {
            lblResult.setText(currentText + sourceId);
        } else if (operator.equals(".") && currentText.endsWith(".0")) {
            lblResult.setText(currentText.replaceAll("\\.0$", "") + sourceId);
        } else if (operator.equals(".") && currentText.endsWith("0")) {
            lblResult.setText(currentText.replaceAll("0$", "") + sourceId);
        } else if (operator.equals(".") && currentText.endsWith(".0") && sourceId.equals("0")) {
            lblResult.setText(currentText.replaceAll("\\.0$", ""));
        } else if (operator.equals(".") && currentText.endsWith(".0") && !sourceId.equals("0")) {
            lblResult.setText(currentText.replaceAll("\\.0$", "") + sourceId);
        } else if (operator.equals(".") && currentText.endsWith("0") && !sourceId.equals("0")) {
            lblResult.setText(currentText.replaceAll("0$", "") + sourceId);
        } else if (operator.equals(".") && currentText.equals("")) {
            lblResult.setText(sourceId);
        } else if (operator.equals(".") && currentText.equals(".")) {
            // Do nothing
        } else if (currentText.equals("0.0") || currentText.equals("0")) {
            lblResult.setText(sourceId);
        } else if (currentText.endsWith(".0")) {
            lblResult.setText(currentText.replaceAll("\\.0$", "") + sourceId);
        } else if (currentText.endsWith("0")) {
            lblResult.setText(currentText.replaceAll("0$", "") + sourceId);
        } else if (currentText.endsWith(".0") && sourceId.equals("0")) {
            lblResult.setText(currentText.replaceAll("\\.0$", ""));
        } else if (currentText.endsWith(".") && !sourceId.equals("0")) {
            lblResult.setText(currentText + sourceId);
        } else if (currentText.endsWith(".0") && !sourceId.equals("0")) {
            lblResult.setText(currentText.replaceAll("\\.0$", "") +sourceId);
        } else if (currentText.endsWith("0") && !sourceId.equals("0")) {
            lblResult.setText(currentText.replaceAll("0$", "") + sourceId);
        } else if (currentText.equals("") && !sourceId.equals("0")) {
            lblResult.setText(sourceId);
        } else if (operator.equals(".") && currentText.equals("0") && sourceId.equals(".")) {
            lblResult.setText(currentText + sourceId);
        } else {
            lblResult.setText(currentText + sourceId);
        }
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn", "");
        if (symbol.equals("Equals")) {
            double num2 = Double.parseDouble(lblResult.getText());
            switch (operator) {
                case "+" -> lblResult.setText(String.valueOf(num1 + num2));
                case "-" -> lblResult.setText(String.valueOf(num1 - num2));
                case "*" -> lblResult.setText(String.valueOf(num1 * num2));
                case "/" -> lblResult.setText(String.valueOf(num1 / num2));
            }
            operator =".";
        } else if (symbol.equals("Clear")){
            lblResult.setText("0.0");
            operator = ".";
        } else if (symbol.equals("Dot")) {
            if (!lblResult.getText().contains(".")) {
                updateResultLabel(lblResult.getText() + ".");
                operator = ".";
            }
        } else {
            if (!lblResult.getText().equals("0.0") && !operator.equals(".")) {
                double num2 =Double.parseDouble(lblResult.getText());
                switch (operator) {
                    case "+" -> lblResult.setText(String.valueOf(num1 + num2));
                    case "-" -> lblResult.setText(String.valueOf(num1 - num2));
                    case "*" -> lblResult.setText(String.valueOf(num1 * num2));
                    case "/" -> lblResult.setText(String.valueOf(num1 / num2));
                }
            }
            switch (symbol) {
                case "Plus" -> {
                    operator = "+";
                    num1 = Double.parseDouble(lblResult.getText());
                    lblResult.setText("0.0");
                }
                case "Minus" -> {
                    operator = "-";
                    num1 = Double.parseDouble(lblResult.getText());
                    lblResult.setText("0.0");
                }
                case "Multiply" -> {
                    operator = "*";
                    num1 = Double.parseDouble(lblResult.getText());
                    lblResult.setText("0.0");
                }
                case "Divide" -> {
                    operator = "/";
                    num1 = Double.parseDouble(lblResult.getText());
                    lblResult.setText("0.0");
                }
            }
        }
    }
}