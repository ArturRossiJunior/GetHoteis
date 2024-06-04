package Models;


public class ReservaModel extends PadraoModel {

    private QuartoModel quarto;
    private ClienteModel cliente;
    private int qtdPessoas;
    private double valorEntrada;
    private String dataReserva;
    private String diaCheckIn;
    private String diaCheckOut;

    public ReservaModel(QuartoModel quarto, ClienteModel cliente, int qtdPessoas, double valorEntrada, String dataReserva, String diaCheckIn, String diaCheckOut) {
        this.quarto = quarto;
        this.cliente = cliente;
        this.qtdPessoas = qtdPessoas;
        this.valorEntrada = valorEntrada;
        this.dataReserva = dataReserva;
        this.diaCheckIn = diaCheckIn;
        this.diaCheckOut = diaCheckOut;
    }

    public QuartoModel getQuarto() {
        return quarto;
    }

    public void setQuarto(QuartoModel quarto) {
        this.quarto = quarto;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getDiaCheckIn() {
        return diaCheckIn;
    }

    public void setDiaCheckIn(String diaCheckIn) {
        this.diaCheckIn = diaCheckIn;
    }

    public String getDiaCheckOut() {
        return diaCheckOut;
    }

    public void setDiaCheckOut(String diaCheckOut) {
        this.diaCheckOut = diaCheckOut;
    }
}