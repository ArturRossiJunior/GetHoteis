package Controllers;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import Models.*;
import com.n2.hotelaria.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.*;
import DAO.*;

public class TipoQuartoController extends PadraoController<QuartoModel> {

    
    
    @FXML
    private void initialize() {
        
    }
    
     @FXML
    private void irCadastroTipoQuarto(ActionEvent event){
        try {
             App.openNewWindow("CadastroTipoQuarto");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}