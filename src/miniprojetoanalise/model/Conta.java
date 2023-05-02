/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojetoanalise.model;

/**
 *
 * @author Luiza Rehbein
 */
public abstract class Conta {
    
    protected int id;
    protected String dataAbertura;
    protected float saldo;
    protected float limiteValor;
    protected int idCliente;

    public Conta(int id, String dataAbertura, float saldo, float limiteValor, int idCliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.limiteValor = limiteValor;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimiteValor() {
        return limiteValor;
    }

    public void setLimiteValor(float limiteValor) {
        this.limiteValor = limiteValor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
    public void depositar(float valor){
        if( valor > 0) { 
            this.saldo += valor;
        }
    }
    
    protected boolean estaNoLimite(float valor){
        return valor <= this.limiteValor;
    }
    
    public boolean sacar(float valor){
        if(estaNoLimite(valor)){
            if( (this.saldo - valor) >= 0){
                this.saldo -= valor;
                return true;
            }
        }
        return false;
    }
    
}
