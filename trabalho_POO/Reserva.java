public class Reserva {
    private String dataEntrada;
    private String dataSaida;
    private Hospede[] hospedes;
    private Quarto quarto;
    private int contador;
    private Veiculo veiculo;

    public Reserva(String dataEntrada, String dataSaida, Quarto quarto, Veiculo veiculo) {

        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.quarto = quarto;
        this.veiculo = veiculo;
    
    }
    public void checkin(Hospede[] h) {

        this.hospedes =new Hospede[h.length];

        for(int i = 0; i<h.length; i++){

            this.hospedes[i] = h[i];

        }

        this.contador = h.length;


    }   

    public void checkout() {

        System.out.println("Checkout realizado!");
        
    }

    public void cancelar() {

        this.hospedes = null;
        this.contador = 0;

        System.out.println("Reserva cancelada");

    }
     public int getQuantidadeHospedes() {
        return contador;
    }
    public String getDataEntrada() {
    return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Hospede[] getHospedes() {
        return hospedes;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    
}
    