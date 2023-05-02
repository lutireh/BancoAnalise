
package miniprojetoanalise.model;

public class ContaComum extends Conta{
        
    public ContaComum(int id, String dataAbertura, float saldo, float limiteValor, int idCliente){
        super(id, dataAbertura, saldo, limiteValor, idCliente);
    }
        
    public void transferir(float valor){
        if(estaNoLimite(valor)){
            if( (this.saldo - valor) >= 0){
                this.saldo -= valor;
            }
        }
    } 
}
