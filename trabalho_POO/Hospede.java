public class Hospede extends Pessoa {
    
    private String endereco;
    private String documento;
    
    public Hospede(String nome, String endereco, String documento){
        super(nome);
        this.endereco = endereco;
        this.documento = documento;
    
    }
    public String getNome() {

        return super.getNome();
    }
    public String getEndereco(){
        return endereco;
    }
    public String getDocumento(){
        return documento;
    }
}
