package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;

public class Controller {

    @FXML
    private TextArea texto;

    private Stage stage;
    private Button but;
    private String guardado;
    private MenuItem menu;
    private Menu menu1;

    public void openFile (ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files","*.txt"));

        Window mainStage = texto.getScene().getWindow();

        File selFile = fileChooser.showOpenDialog(mainStage);
    }

    public void guardarFile (ActionEvent actionEvent){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar como...");
            File file = fileChooser.showSaveDialog(stage);

            if(file != null){
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(texto.getText());
                out.close();
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error: " + e);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Sobre l'Editor");
        alert.setContentText("Autor: Jandry Joel Aguilar Serrano 2n DAM: Editor de Texto.");
        alert.show();
    }
    public void onClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onSelected(ActionEvent actionEvent) {
        if (!texto.getSelectedText().equals("")) {
            if (menu1.getId().equals("edit")) {
                menu1.setDisable(false);
            }
        }

    }


    public void copiar (ActionEvent actionEvent){
        guardado = texto.getSelectedText();
    }

    public void cortar (ActionEvent actionEvent){
        guardado = texto.getSelectedText();
        texto.replaceSelection("");
    }

    public void pegar (ActionEvent actionEvent){
        texto.setText(texto.getText() + guardado);
    }

    public void tamaFuente (ActionEvent actionEvent){
        String opc = ((RadioMenuItem) actionEvent.getSource()).getId();
        switch (opc){
            case "peq":
                texto.setStyle("-fx-font-size: 10px");
                break;
            case "pd":
                texto.setStyle("-fx-font-size: 22px");
                break;
            case "gran":
                texto.setStyle("-fx-font-size: 30px");
                break;
        }
    }

    public void estFuente (ActionEvent actionEvent){
        String opc = ((RadioMenuItem) actionEvent.getSource()).getId();
        switch (opc){
            case "ari":
                texto.setStyle("-fx-font-family: Arial");
                break;
            case "times":
                texto.setStyle("-fx-font-family: Times New Roman");
                break;
            case "verd":
                texto.setStyle("-fx-font-family: Verdana");
                break;
        }
    }


}
