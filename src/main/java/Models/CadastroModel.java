package Models;

import lombok.Getter;
import lombok.Setter;

public class CadastroModel extends PadraoModel {
    
    public CadastroModel(String usuario , String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Getter @Setter public String usuario;
    @Getter @Setter public String senha;

}
