package Models;

import lombok.Getter;
import lombok.Setter;

public class CadastroModel extends PadraoModel {

    public CadastroModel(String nomeCompleto, String email, String CPF, String dataNascimento, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    @Getter @Setter private String nomeCompleto;
    @Getter @Setter private String email;
    @Getter @Setter private String CPF;
    @Getter @Setter private String dataNascimento;
    @Getter @Setter private String senha;
}