
package miniprojetoanalise.model;

public class ContaPoupanca extends Conta{

    private int dataAniverConta;
    
    public ContaPoupanca(int id, String dataAbertura, float saldo, float limiteValor, int dataAniverConta, int idCliente){
        super(id, dataAbertura, saldo, limiteValor, idCliente);
        this.dataAniverConta = dataAniverConta;
    }
    
   
    public int getDataAniverConta(){
        return dataAniverConta;
    }
    
    public void setDataAniverConta(int dataAniverConta){
        this.dataAniverConta = dataAniverConta;
    }
    
}
