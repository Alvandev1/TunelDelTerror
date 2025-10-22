package com.tunel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntradaController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> comboCurso;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        comboCurso.getItems().addAll("DAM1","DAM2","DAW1","DAW2","SMR1","SMR2");
    }

    @FXML
    public void entrar(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String curso = comboCurso.getValue();

        if (nombre.isEmpty() || apellidos.isEmpty() || curso == null) {
            lblMensaje.setText("⚠️ Rellena todos los campos antes de entrar");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tunel/ruleta.fxml"));
            Scene scene = new Scene(loader.load());

            RuletaController ruletaController = loader.getController();
            ruletaController.setDatos(nombre, apellidos, curso);

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Ruleta TRUCO/TRATO 🎃");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
