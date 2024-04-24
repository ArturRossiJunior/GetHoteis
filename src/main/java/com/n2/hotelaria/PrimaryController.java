package com.n2.hotelaria;

import java.io.IOException;
import java.sql.Connection;

import DAO.ConexaoDAO;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Connection conexao = ConexaoDAO.conectar(); 
        ConexaoDAO.fecharConexao(conexao);
        App.setRoot("secondary");
    }
}
