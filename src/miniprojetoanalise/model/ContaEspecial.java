
package miniprojetoanalise.model;

public class ContaEspecial {
     private int id;
    private String dataAbertura;
    private float saldo;
    private float limiteValor;
    private float limiteCredito;
    private int idCliente;
    
    public ContaEspecial(int id, String dataAbetura, float saldo, float limiteValor, float limiteCredito, int idCliente) {
        this.id = id;
        this.dataAbertura = dataAbetura;
        this.saldo = saldo;
        this.limiteValor = limiteValor;
        this.limiteCredito = limiteCredito;
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
    
    public float getLimiteCredito(){
        return limiteCredito;
    }
    
    public void setLimiteCredito(float limiteCredito){
        this.limiteCredito = limiteCredito;
    }
    
    public int getidCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
}
