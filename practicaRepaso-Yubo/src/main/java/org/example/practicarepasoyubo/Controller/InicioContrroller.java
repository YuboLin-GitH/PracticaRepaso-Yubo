package org.example.practicarepasoyubo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.practicarepasoyubo.DAO.DataUsuario;

/**
 * ClassName: InicioContrroller
 * Package: org.example.practicarepasoyubo.Controller
 * Description:
 *
 * @Author Yubo
 * @Create 26/09/2025 20:33
 * @Version 1.0
 */
public class InicioContrroller {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button validarButton;
    @FXML
    private Button salirButton;


    private DataUsuario dataUsuario = new DataUsuario();

    @FXML
    public void initialize() {

        validarButton.setOnAction(event -> validarUsuario());

        salirButton.setOnAction(event -> salir());
    }


    private void validarUsuario() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();


        boolean usuarioExiste = dataUsuario.validarUsuario(email, password);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de Validación");
        alert.setContentText(usuarioExiste ? "Usuario existe" : "Usuario no existe");
        alert.showAndWait();


        emailField.clear();
        passwordField.clear();
    }

    private void salir() {
        Stage stage = (Stage) salirButton.getScene().getWindow();
        stage.close();
    }




}
