package Controllers;

import java.security.*;
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

    protected boolean regexEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected boolean regexData(String data){
        Pattern pattern = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
        Matcher matcher = pattern.matcher(data);
        
        if (matcher.matches()) {
            int dia = Integer.parseInt(matcher.group(1));
            int mes = Integer.parseInt(matcher.group(2));
            
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12) 
            return false;
            
            try {
                LocalDate dataLD = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return dataLD.isBefore(LocalDate.now());
            } catch (DateTimeException e) {
                return false;
            }
        }
        return false;
    }

    protected boolean regexCPF(String cpf){
        Pattern pattern =  Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    protected void mascaraNome(TextField nomeField) {
        nomeField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            String nome = novoValor.replaceAll("\\s+", " ").replaceAll("[^\\p{L}\\s]", "");
            
            if (nome.length() > 35)
                nome = nome.substring(0, 35);
            
            nomeField.setText(nome);
            nomeField.positionCaret(nome.length());
        });
    }    

    protected void mascaraEmail(TextField emailField) {
        emailField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            String email = novoValor.replaceAll("[^a-zA-Z0-9._@-]", "");
            
            if (email.length() > 35)
                email = email.substring(0, 35);
            
            emailField.setText(email);
            emailField.positionCaret(email.length());
        });
    }    
    
    protected void mascaraCPF(TextField cpfField){
        cpfField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            String numeros = novoValor.replaceAll("[^\\d]", "");
            
            if (numeros.length() > 11) 
                numeros = numeros.substring(0, 11);
            
            StringBuilder mascaraCPF = new StringBuilder(numeros);

            if (numeros.length() > 3) mascaraCPF.insert(3, '.');
            if (numeros.length() > 6) mascaraCPF.insert(7, '.');
            if (numeros.length() > 9) mascaraCPF.insert(11, '-');

            cpfField.setText(mascaraCPF.toString());
            cpfField.positionCaret(mascaraCPF.length());
        });
    }

    protected void mascaraData(TextField dataField){
        dataField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            String numeros = novoValor.replaceAll("[^\\d]", "");
            
            if (numeros.length() > 8) 
                numeros = numeros.substring(0, 8);
            
            StringBuilder mascaraData = new StringBuilder(numeros);
    
            if (numeros.length() > 2) mascaraData.insert(2, '/');
            if (numeros.length() > 5) mascaraData.insert(5, '/');
    
            dataField.setText(mascaraData.toString());
            dataField.positionCaret(mascaraData.length());
        });
    }

    protected void mascaraSenha(PasswordField senhaField) {
        senhaField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            
            if (novoValor.length() > 15)
                senhaField.setText(novoValor.substring(0, 15));
        });
    }    

    protected String criptogafarSenha(String senha){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] encodedHash = digest.digest(senha.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : encodedHash) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    protected boolean validacaoCadastro(CadastroDAO dao, String nome, String email, String cpf, String data, String senha) {
        if (nome.length() < 1 || !regexEmail(email) || !regexCPF(cpf) || !regexData(data) || senha.length() < 6 || dao.consultaEmailouCPF(email, cpf)) {
            showAlert(Alert.AlertType.ERROR, "Erro", 
                (nome.length() < 1) ? "Nome deve conter ao menos 1 carácter" :
                !regexEmail(email) ? "Email inválido" :
                !regexCPF(cpf) ? "CPF inválido" :
                !regexData(data) ? "Data inválida" :
                (senha.length() < 6) ? "Senha deve conter ao menos 6 carácteres" :
                "Este e-mail ou CPF já está cadastrado");
            return false;
        }
        return true;
    }

    protected boolean validacaoRecuperarSenha(String email, String senhaAntiga, String senhaNova, String confirmarSenhaNova) {
        if (email.length() < 1 || senhaAntiga.length() < 6 || senhaNova.length() < 6 || !senhaNova.equals(confirmarSenhaNova) || senhaNova.equals(senhaAntiga)) {
            showAlert(Alert.AlertType.ERROR, "Erro", 
                (senhaNova.length() < 6 ) ? "A senha deve conter ao menos carácteres" :
                !senhaNova.equals(confirmarSenhaNova) ? "As novas senhas não coincidem" :
                "Senha nova não pode ser igual a antiga");
            return false;
        }
        return true;
    }
}