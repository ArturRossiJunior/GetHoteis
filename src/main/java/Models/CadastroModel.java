package Models;
public class CadastroModel extends PadraoModel {
    private String nomeCompleto;
    private String email;
    private String CPF;
    private String dataNascimento;
    private String senha;

    public CadastroModel(String nomeCompleto, String email, String CPF, String dataNascimento, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}