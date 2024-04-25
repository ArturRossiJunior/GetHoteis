package Controllers;

import java.time.LocalDate;
import DAO.CadastroDAO;
import Models.PadraoModel;
import javafx.scene.control.Alert;

public class PadraoController<T extends PadraoModel> {
    
    protected void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected boolean validacaoCadastro(CadastroDAO dao, String email, String cpf, LocalDate data) {
        if (data.isAfter(LocalDate.now())) {
            showAlert(Alert.AlertType.ERROR, "Erro", "A data de nascimento não pode ser posterior à data atual.");
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            showAlert(Alert.AlertType.ERROR, "Erro", "E-mail inválido.");
            return false;
        }
        if (dao.emailOuCpfExiste(email, cpf)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Este e-mail ou CPF já está cadastrado.");
            return false;
        }
        return true;
    }
}