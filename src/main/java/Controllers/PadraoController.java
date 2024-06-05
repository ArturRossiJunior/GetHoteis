package Controllers;

import java.security.*;
import java.time.*;
import java.time.format.*;
import java.util.Optional;
import java.util.regex.*;
import DAO.*;
import javafx.animation.*;
import Models.*;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class PadraoController <T extends PadraoModel> {

    @FXML
    protected ComboBox<String> perguntaSegurancaCombo;

    protected void exibirPopupTemporizado(long duracao, TextField[] camposTexto, Button... botoes) {
        for (TextField campo : camposTexto) 
            campo.setDisable(true);
    
        for (Button botao : botoes) 
            botao.setDisable(true);
    
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duracao), event -> {
            for (TextField campo : camposTexto) 
                campo.setDisable(false);
    
            for (Button botao : botoes) 
                botao.setDisable(false);
            
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }    
    
    protected void perguntaSegurancaCombo(){
        perguntaSegurancaCombo.getItems().addAll(
            "Qual é o nome do seu cachorro?",
            "Qual é o nome da sua mãe?",
            "Qual é a sua cor preferida?"
        );
    }

    protected void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected boolean regexCPF(String cpf){
        Pattern pattern =  Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
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
    
    protected String inverterData(String dataOriginal) {
        String[] partes = dataOriginal.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        
        LocalDate data = LocalDate.of(ano, mes, dia);
        String dataInvertida = data.toString();
        return dataInvertida;
    }

    protected String desinverterData(String dataInvertida) {
        LocalDate data = LocalDate.parse(dataInvertida);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    protected boolean regexEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

    protected boolean regexSenha(String senha) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$");
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }    
   
    protected void mascaraSenha(PasswordField senhaField) {
        senhaField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            
            if (novoValor.contains(" ")) {
                novoValor = novoValor.replaceAll("\\s", "");
                senhaField.setText(novoValor);
            }

            if (novoValor.length() > 15)
                senhaField.setText(novoValor.substring(0, 15));
        });
    }

    protected void mascaraResposta(TextField respostaField) {
        respostaField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            
            if (novoValor.length() > 15)
                respostaField.setText(novoValor.substring(0, 15));
        });
    }

    protected String criptografar(String senha){
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

    protected void mascaraNumero(TextField numeroField) {
        numeroField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
            String numeros = novoValor.replaceAll("[^\\d]", "");
            numeroField.setText(numeros);
        });
    }
    
    protected void mascaraValor(TextField valorField) {
        valorField.textProperty().addListener((observador, valorAntigo, novoValor) -> {
            if (novoValor == null) return;
    
            String numeros = novoValor.replaceAll("(\\d{1,8})(\\.\\d{0,2})?.*|[^\\d.]+", "$1$2");
    
            valorField.setText(numeros);
        });
    }
    
    protected boolean validacaoCadastroUsuario(UsuarioDAO dao, String cpf, String nome, String data, String email, String senha, String confirmaSenha) {
        if (!regexCPF(cpf) || dao.existeEmailouCPF(email, cpf.replaceAll("[.\\-]", "")) || nome.length() < 1 || !nome.contains(" ") || 
                !regexData(data) || !regexEmail(email) || !regexSenha(senha) ||!senha.equals(confirmaSenha)) {
            showAlert(Alert.AlertType.ERROR, "Erro", 
                !regexCPF(cpf) ? "CPF inválido" :
                dao.existeEmailouCPF(email, cpf.replaceAll("[.\\-]", "")) ? "Este e-mail ou CPF já está cadastrado" :
                (nome.length() < 1 || !nome.contains(" ")) ? "Nome completo obrigatório" :
                !regexData(data) ? "Data inválida" :
                !regexEmail(email) ? "Email inválido" :
                !regexSenha(senha) ? "A senha deve conter no mínimo 6 caracteres, pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial" :
                !senha.equals(confirmaSenha) ? "As senhas não coincidem" :
                "Erro não tratado ainda");
            return false;
        }
        return true;
    }

    protected boolean validacaoEnvioEmail(String email, EnvioEmailDAO dao) {
        if (!regexEmail(email) || !dao.existeEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Erro", 
                !regexEmail(email) ? "Email inválido" :
                !dao.existeEmail(email) ? "Email inexistente":
                "Erro não tratado ainda");
            return false;
        }
        return true;
    }

    protected boolean validacaoPerguntaSeguranca(PerguntaSegurancaDAO dao, String email, String senhaNova, String confirmarSenhaNova, String perguntaSeguranca, String resposta) {
        if (!regexEmail(email) || dao.consultaEmail("Senha", email).equals(criptografar(senhaNova)) || !regexSenha(senhaNova) || !senhaNova.equals(confirmarSenhaNova) || 
                !dao.consultaEmail("Pergunta_Seguranca", email).equals(perguntaSeguranca) || !dao.consultaEmail("Resposta", email).equals(criptografar(resposta))) {
            showAlert(Alert.AlertType.ERROR, "Erro", 
                !regexEmail(email) ? "Email inválido" :
                dao.consultaEmail("Senha", email).equals(criptografar(senhaNova)) ? "Senha nova não pode ser igual a antiga" :
                !regexSenha(senhaNova) ? "A senha deve conter no mínimo 6 caracteres, pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial" :
                !senhaNova.equals(confirmarSenhaNova) ? "As senhas não coincidem" :
                !dao.consultaEmail("Pergunta_Seguranca", email).equals(perguntaSeguranca) || !dao.consultaEmail("Resposta", email).equals(resposta) ? "Palavra-Chave incorreta" :
                "Erro não tratado ainda");
            return false;
        }
        return true;
    }

    protected boolean validacaoCadastroTipoQuarto(TipoQuartoDAO dao, String nomeTipoQuarto, String quantidadeCamas, String valorDiaria, String descricao) {
        int quantidade = Integer.parseInt(quantidadeCamas);
        double valor = Double.parseDouble(valorDiaria);
    
        if (nomeTipoQuarto.isEmpty() || quantidade <= 0 || quantidade > 5 || valor < 0 || descricao.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", 
                nomeTipoQuarto.isEmpty() ? "Nome inválido" :
                quantidade <= 0 || quantidade > 5 ? "Quantidade de camas deve ser entre 1 e 5" :
                valor < 0 ? "Valor inválido" :
                descricao.isEmpty() ? "Descrição inválida" :
                "Por favor, verifique os campos inseridos");
            return false;
        }
        return true;
    }

    protected String formatarQuarto(QuartoModel quarto) {
        return "Número do Quarto: " + quarto.getNumeroQuarto() +
               " - Nome do Tipo: " + quarto.getTipoQuarto().getNome() +
               " - Quantidade de Camas: " + quarto.getTipoQuarto().getQuantidadeCamas() +
               " - Valor da Diária: R$" + quarto.getTipoQuarto().getValorDiaria() +
               " - Descrição: " + quarto.getTipoQuarto().getDescricao();
    }

    protected String formatarTipoQuarto(TipoQuartoModel quarto) {
        return "Nome do Tipo: " + quarto.getNome() +
               " - Quantidade de Camas: " + quarto.getQuantidadeCamas() +
               " - Valor da Diária: R$" + quarto.getValorDiaria() +
               " - Descrição: " + quarto.getDescricao();
    }

    protected String formatarCliente(ClienteModel cliente) {
        return  "Nome do Cliente: " + cliente.getNome() + 
                " - CPF do Cliente: " + cliente.getCpf();
    }

    protected String formatarReserva(ReservaModel reserva) {
        return  "ID: " + reserva.getID() +
                " - Nome do Cliente: " + reserva.getCliente().getNome() +
                " - Numero do Quarto: " + reserva.getQuarto().getNumeroQuarto() +
                " - Quantidade de Pessoas: " + reserva.getQtdPessoas() +
                " - Valor Entrada: " + reserva.getValorEntrada() + 
                " - Data da Reserva: " + reserva.getDataReserva().replaceAll("-", "/") +
                " - Check-In: " + reserva.getDiaCheckIn().replaceAll("-", "/") +
                " - Check-Out: " + reserva.getDiaCheckOut().replaceAll("-", "/");
    }

    protected Optional<ButtonType> confirmaExclusao(){
         Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmação de Exclusão");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Você tem certeza que deseja excluir?");
        return confirmationAlert.showAndWait();
    }

    protected boolean validarCadastroCliente(String cpf, String nome, String data){
        if (!regexCPF(cpf) || nome.length() < 1 || !nome.contains(" ") || !regexData(data)){
            showAlert(Alert.AlertType.ERROR, "Erro", 
                !regexCPF(cpf) ? "CPF inválido" :
                (nome.length() < 1 || !nome.contains(" ")) ? "Nome completo obrigatório" :
                !regexData(data) ? "Data inválida" :
                "Erro não tratado ainda");
            return false;
        }
        return true;
    }
}