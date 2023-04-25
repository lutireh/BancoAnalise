
package miniprojetoanalise.model;

public class ContaComum {
    private int id;
    private String dataAbertura;
    private float saldo;
    private float limiteValor;
    private int idCliente;
    
    public ContaComum(int id, String dataAbetura, float saldo, float limiteValor, int idCliente){
        this.id = id;
        this.dataAbertura = dataAbetura;
        this.saldo = saldo;
        this.limiteValor = limiteValor;
        this.idCliente = idCliente;
    }
    
    public int getId(){
        return id;
    }
    
    public String getDataAbertura(){
        return dataAbertura;
    }
    
    public void setDataAbertura(String dataAbertura){
        this.dataAbertura = dataAbertura;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    public void setSaldo(float saldo){
        this.saldo = saldo;
    }
    
    public float getLimiteValor(){
        return limiteValor;
    }
    
    public void setLimiteValor(float limiteValor){
        this.limiteValor = limiteValor;
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    
}
