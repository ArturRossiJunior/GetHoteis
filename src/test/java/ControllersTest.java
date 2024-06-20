// import org.junit.*;
// import static org.junit.Assert.*;
// import javafx.scene.control.*;
// import Controllers.*;

// public class ControllersTest extends PadraoController {

//     @Test
//     public void testEmailsValidos() {
//         assertTrue(regexEmail("teste@teste.com"));
//         assertTrue(regexEmail("teste@mail.teste.com"));
//         assertTrue(regexEmail("teste+123@teste.com"));
//         assertTrue(regexEmail("teste123@teste.com"));
//         assertTrue(regexEmail("teste-teste@teste.com"));
//         assertTrue(regexEmail("teste.teste@teste.com"));
//     }

//     @Test
//     public void testEmailsInvalidos() {
//         assertFalse(regexEmail("testeteste.com"));
//         assertFalse(regexEmail("teste@.com"));
//         assertFalse(regexEmail("teste@te!ste.com"));
//         assertFalse(regexEmail("teste@teste"));
//         assertFalse(regexEmail("teste @teste.com"));
//         assertFalse(regexEmail("teste@@teste.com"));
//         assertFalse(regexEmail("teste@teste.123"));
//     }

//     @Test
//     public void testCpfsValidos() {
//         assertTrue(regexCPF("123.456.789-00"));
//         assertTrue(regexCPF("987.654.321-99"));
//     }

//     @Test
//     public void testCpfsInvalidos() {
//         assertFalse(regexCPF("12345678900"));
//         assertFalse(regexCPF("123.456.78-00"));
//         assertFalse(regexCPF("123.456.789-0X"));
//     }

//     @Test
//     public void testDatasValidas() {
//         assertTrue(regexData("01/01/2020"));
//         assertTrue(regexData("31/12/1999"));
//     }

//     @Test
//     public void testDatasInvalidas() {
//         assertFalse(regexData("32/01/2020"));
//         assertFalse(regexData("01/13/2020"));
//         assertFalse(regexData("01-01-2020"));
//         assertFalse(regexData("01/01/3020"));
//     }

//     @Test
//     public void testSenhaValida() {
//         assertTrue(regexSenha("Aa1@abc"));
//         assertTrue(regexSenha("Xy9#Zz"));
//     }

//     @Test
//     public void testSenhaInvalida() {
//         assertFalse(regexSenha("aabbcc"));
//         assertFalse(regexSenha("A1@bcd"));
//         assertFalse(regexSenha("Aa1@abc abc"));
//     }

//     @Test
//     public void testCriptografia() {
//         String senha = "senha123";
//         String hash = criptografar(senha);
//         assertNotNull(hash);
//         assertEquals(64, hash.length());
//         String sameHash = criptografar(senha);
//         assertEquals(hash, sameHash);
//     }

//     @Test
//     public void testMascaraCPF() {
//         TextField cpfField = new TextField();
//         mascaraCPF(cpfField);
        
//         cpfField.setText("12345678900");
//         assertEquals("123.456.789-00", cpfField.getText());
//     }

//     @Test
//     public void testMascaraNome() {
//         TextField nomeField = new TextField();
//         mascaraNome(nomeField);
        
//         nomeField.setText("João da Silva!");
//         assertEquals("João da Silva", nomeField.getText());
//     }

//     @Test
//     public void testMascaraData() {
//         TextField dataField = new TextField();
//         mascaraData(dataField);
        
//         dataField.setText("01012020");
//         assertEquals("01/01/2020", dataField.getText());
//     }

//     @Test
//     public void testMascaraEmail() {
//         TextField emailField = new TextField();
//         mascaraEmail(emailField);
        
//         emailField.setText("teste@te!ste.com");
//         assertEquals("teste@teste.com", emailField.getText());
//     }

//     @Test
//     public void testMascaraSenha() {
//         PasswordField senhaField = new PasswordField();
//         mascaraSenha(senhaField);
        
//         senhaField.setText("Senha1234 ");
//         assertEquals("Senha1234", senhaField.getText());
//     }

//     @Test
//     public void testMascaraResposta() {
//         TextField respostaField = new TextField();
//         mascaraResposta(respostaField);
        
//         respostaField.setText("Minha resposta muito longa");
//         assertEquals("Minha resposta ", respostaField.getText());
//     }
// }