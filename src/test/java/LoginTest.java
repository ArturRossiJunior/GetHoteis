import Models.*;
import org.junit.*;
import static org.junit.Assert.*;

public class LoginTest {
    
    private UsuarioModel usuario;
    
    @Test
    public void loginSucesso(){
        assertNotNull(usuario.getCPF());
        System.out.println("teste");
    }
}