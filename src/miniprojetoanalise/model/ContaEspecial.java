
package miniprojetoanalise.model;

public class ContaEspecial extends Conta{
    
    private float limiteCredito;
    
    public ContaEspecial(int id, String dataAbetura, float saldo, float limiteValor, float limiteCredito, int idCliente) {
        super(id, dataAbetura, saldo, limiteValor, idCliente);
        this.limiteCredito = limiteCredito;
    } 

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
  
    
    @Override
    public boolean sacar(float valor){
        if(estaNoLimite(valor)){
            if(((this.saldo + this.limiteCredito) - valor) >= 0){
                this.saldo -= valor;
                return true;
            }
            else{
                System.out.println("tu ta no serasa");
            }
        }
        return false;
    }
}
