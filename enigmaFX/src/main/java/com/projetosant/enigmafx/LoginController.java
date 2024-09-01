package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.projetosant.enigmafx.utils.Alerta;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController extends Application {

    @FXML
    private ImageView goBack;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_login;
    @FXML
    private PasswordField txt_senha;

    @FXML
    public void onClickedGoBack(MouseEvent mouseEvent) throws IOException {
        Application.geraTelas("Inicial.fxml", "Tela Inicial");

    }

    private boolean valida(){
        if (txt_login.getText().isEmpty() || txt_senha.getText().isEmpty()){
            Alerta.erroFaltando();
            return false;
        }
        return true;
    }

    @FXML
    public void onLogar(){
        String sql = "select nome, xp, data_nasc, eh_instrutor, login, senha, lvl_usuario, id from usuario where login=? and senha=?";
        Connection conexao = DB.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;


        try {
            if(valida()){
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_login.getText());
                pst.setString(2, txt_senha.getText()); // se der tempo criptografar e descripto a senha

                rs = pst.executeQuery();

                if (rs.next()) {
                    Application.usuarioLogado = new Usuario(rs.getString(1), rs.getLong(2), rs.getString(3),rs.getBoolean(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8) );
                    DB.closeConnection(conexao);
                    Application.geraTelas("Principal.fxml", "ENIGMA - Tela Principal");
                } else {
                    Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "ERRO", "Login ou senha incorretos.");
                }
            }



        } catch (Exception e) {
            System.out.println(e);
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }


}
