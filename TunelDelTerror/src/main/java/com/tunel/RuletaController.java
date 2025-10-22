package com.tunel;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Random;

public class RuletaController {

    @FXML private Label lblBienvenida;
    @FXML private ImageView imgRuleta; // ruleta que gira
    @FXML private ImageView imgFlecha; // flecha fija
    @FXML private Button btnGirar;
    @FXML private Label lblResultado;

    private String nombre, apellidos, curso;

    @FXML
    public void initialize() {
        try {
            // 🔹 Cargar las imágenes desde la carpeta resources
            Image ruletaImg = new Image(getClass().getResource("/com/tunel/ruleta.png").toExternalForm());
            imgRuleta.setImage(ruletaImg);

            Image flechaImg = new Image(getClass().getResource("/com/tunel/flecha.png").toExternalForm());
            imgFlecha.setImage(flechaImg);
        } catch (Exception e) {
            System.out.println("❌ Error al cargar imágenes: " + e.getMessage());
        }
    }

    // 🔹 Recibe los datos desde la pantalla anterior
    public void setDatos(String nombre, String apellidos, String curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        lblBienvenida.setText("👻 " + nombre + " " + apellidos + " - " + curso + " 👻");
    }

    // 🔹 Acción del botón GIRAR
    @FXML
    public void girarRuleta() {
        btnGirar.setDisable(true);
        lblResultado.setText("");

        // Generar giro aleatorio (2 vueltas completas + ángulo aleatorio)
        double giro = 720 + new Random().nextInt(360);

        RotateTransition rot = new RotateTransition(Duration.seconds(3), imgRuleta);
        rot.setByAngle(giro);
        rot.play();

        // Cuando termine la animación:
        rot.setOnFinished(e -> {
            double anguloFinal = giro % 360;
            String resultado;

            // Determina el resultado según el ángulo
            if (anguloFinal >= 0 && anguloFinal < 180) {
                resultado = "🎃 TRUCO 🎃";
                lblResultado.setText(resultado);

                // Evita el error de showAndWait durante la animación
                Platform.runLater(this::mostrarAlertaTruco);

            } else {
                resultado = "🍬 TRATO 🍬";
                lblResultado.setText(resultado);

                Platform.runLater(this::mostrarAlertaTrato);
            }

            btnGirar.setDisable(false);
        });
    }

    // 🎃 Alerta si sale TRUCO
    private void mostrarAlertaTruco() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("🎃 ¡TRUCO!");
        alert.setHeaderText("😈 Has caído en un TRUCO...");
        alert.setContentText("Cuenta una historia de terror o asusta a un compañero 👻");
        alert.showAndWait();
    }

    // 🍬 Alerta si sale TRATO
    private void mostrarAlertaTrato() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("🍬 ¡TRATO!");
        alert.setHeaderText("✨ Has tenido suerte...");
        alert.setContentText("Recibes un dulce virtual 🧁 ¡Feliz Halloween!");
        alert.showAndWait();
    }
}
