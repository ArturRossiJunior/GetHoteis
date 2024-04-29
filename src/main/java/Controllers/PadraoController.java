package Controllers;

import java.time.*;
import java.time.format.*;
import java.util.regex.*;
import DAO.*;
import Models.*;
import javafx.scene.control.*;

public class PadraoController<T extends PadraoModel> {

    protected void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected boolean validaRecuperarSenha(String senhaAntiga, String senhaNova, String confirmarSenhaNova) {
        if (!senhaNova.equals(confirmarSenhaNova)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "As novas senhas não coincidem");
            return false;
        }
        if(senhaNova.equals(senhaAntiga)){
            showAlert(Alert.AlertType.ERROR, "Erro", "Senha nova não pode ser igual a antiga");
            return false;
        }
        return true;
    }

    protected boolean regexEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected boolean regexData(String data){
        Pattern pattern = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
        Matcher matcher = pattern.matcher(data); 
        if (matcher.matches()) {
            LocalDate dataLD = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return dataLD.isBefore(LocalDate.now());
        }
        return false;
    }

    protected boolean regexCPF(String cpf){
        Pattern pattern =  Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    protected boolean validacaoCadastro(CadastroDAO dao, String email, String cpf, String data) {
        if (!regexData(data)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Data inválida");
            return false;
        }
        if (!regexEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Email inválido");
            return false;
        }
        if(!regexCPF(cpf)){
            showAlert(Alert.AlertType.ERROR, "Erro", "CPF inválido");
            return false;
        }
        if (dao.consultaEmailouCPF(email, cpf)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Este e-mail ou CPF já está cadastrado");
            return false;
        }
        return true;
    }
}