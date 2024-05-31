package Models;

public class QuartoModel extends PadraoModel {

    private int numeroQuarto;
    private TipoQuartoModel tipoQuarto;

    public QuartoModel(int numeroQuarto, TipoQuartoModel tipoQuarto) {
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public TipoQuartoModel getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuartoModel tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }
}