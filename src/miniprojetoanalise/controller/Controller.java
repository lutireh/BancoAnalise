
package miniprojetoanalise.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import miniprojetoanalise.model.Cliente;
import miniprojetoanalise.model.ClienteDAO;
import miniprojetoanalise.model.Conta;
import miniprojetoanalise.model.ContaComum;
import miniprojetoanalise.model.ContaComumDAO;
import miniprojetoanalise.model.ContaEspecial;
import miniprojetoanalise.model.ContaEspecialDAO;
import miniprojetoanalise.model.ContaPoupanca;

import miniprojetoanalise.model.ContaPoupancaDAO;
import miniprojetoanalise.view.table.GenericTableModel;

public class Controller {
    private static List<Cliente> cliente = new ArrayList<>();
    private static List<ContaComum> contaComum = new ArrayList<>();
    private static List<ContaEspecial> contaEspecial = new ArrayList<>();
    private static List<ContaPoupanca> contaPoupanca = new ArrayList<>();

    
    public static void initializeComboBox(JComboBox combo, String type){
        combo.removeAllItems();
        if(type.equals("Cliente")){
            setComboBox(combo, ClienteDAO.getInstance().retrieveAll(),type);
            setSelected(cliente.get(0));
        }
        else if(type.equals("ContaComum")){
            setComboBox(combo,ContaComumDAO.getInstance().retrieveAllById(selectedCliente.getId()),type);
        }
        else if(type.equals("ContaEspecial")){
            setComboBox(combo,ContaEspecialDAO.getInstance().retrieveAllById(selectedCliente.getId()),type);
        }
        else if(type.equals("ContaPoupanca")){
            setComboBox(combo,ContaPoupancaDAO.getInstance().retrieveAllById(selectedCliente.getId()),type);
        }
        else if(type.equals("TipoConta")){
            setComboBox(combo,new ArrayList<>(List.of("Conta Comum","Conta Especial","Conta Poupança")) ,"TipoConta");
        }
       
    }
    
    private static void setComboBox(JComboBox combo, List<Object> objs,String type){
        switch (type) {
            case "Cliente":
               cliente.clear();
               for ( Object obj: objs){
                   combo.addItem(((Cliente)obj).getNome());
                   cliente.add((Cliente) obj);
               }
            break;
            case "ContaComum":
               contaComum.clear();
               for ( Object obj: objs){
                   combo.addItem(((ContaComum)obj).getId());
                   contaComum.add((ContaComum) obj);
               }
            break;
            case "ContaEspecial":
               contaEspecial.clear();
               for ( Object obj: objs){
                   combo.addItem(((ContaEspecial)obj).getId());
                   contaEspecial.add((ContaEspecial) obj);
               }
            break;
            case "ContaPoupanca":
               contaPoupanca.clear();
               for ( Object obj: objs){
                   combo.addItem(((ContaPoupanca)obj).getId());
                   contaPoupanca.add((ContaPoupanca) obj);
               }
            break;
            case "TipoConta":
               for(Object obj: objs){
                   combo.addItem(obj);
               }  
            break;
            default:
               break;
        }
    }
    
    public static Cliente getCliente (int id){
        return cliente.get(id);
    }
    
    private static Cliente selectedCliente = null;
    private static ContaComum selectedContaComum = null , selectedTransferToContaComum = null;
    private static ContaEspecial selectedContaEspecial = null, selectedTransferToContaEspecial = null;
    private static ContaPoupanca selectedContaPoupanca = null, selectedTransferToContaPoupanca = null;
    
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);
    }
    
    public static Cliente getSelectedCliente(){
        return selectedCliente;
    }
    
    public static ContaComum getSelectedContaComum(){
        return selectedContaComum;
    }
    
    public static ContaEspecial getSelectedContaEspecial(){
        return selectedContaEspecial;
    }
    
    public static ContaPoupanca getSelectedContaPoupanca(){
        return selectedContaPoupanca;
    }
    
    public static Cliente criarCliente(String nome, String cpf, String dataNascimento){
        return ClienteDAO.getInstance().create(nome, cpf, dataNascimento);
    }
    
    public static ContaComum criarContaComum(String dataAbertura, float saldo, float limiteValor){
        return ContaComumDAO.getInstance().create(dataAbertura, saldo, limiteValor, selectedCliente.getId());
    }
    
     public static ContaEspecial criarContaEspecial(String dataAbertura, float saldo, float limiteValor, float limiteCredito){
        return ContaEspecialDAO.getInstance().create(dataAbertura, saldo, limiteValor, limiteCredito, selectedCliente.getId());
    }
     
   public static ContaPoupanca criarContaPoupanca(String dataAbertura, float saldo, float limiteValor, int dataAniverConta){
        return ContaPoupancaDAO.getInstance().create(dataAbertura, saldo, limiteValor, dataAniverConta, selectedCliente.getId());
    }
    
    public static void setSelected(Object selected){
        if (selected instanceof Cliente) {
            selectedCliente = (Cliente) selected;
        }
        else if (selected instanceof ContaComum){
            selectedContaComum = (ContaComum) selected;
        }
        else if (selected instanceof ContaEspecial){
            selectedContaEspecial = (ContaEspecial) selected;
        }
        else if (selected instanceof ContaPoupanca){
            selectedContaPoupanca = (ContaPoupanca) selected;
        }
    }
    
        public static void setSelectedByIndex (JComboBox combo ,String type){
            int index = 0;
            if(combo.getSelectedIndex() != -1){
                index = combo.getSelectedIndex();
            }
         switch (type) {
            case "Cliente":
               selectedCliente = cliente.get(index);
            break;
            case "Conta Comum":
                    selectedContaComum = contaComum.get(index);
            break;
            case "Conta Especial":
                selectedContaEspecial = contaEspecial.get(index);
            break;
            case "Conta Poupanca":
            case "Conta Poupança":
               selectedContaPoupanca = contaPoupanca.get(index);
            break;
            default:
               break;
        }
    }
        
        
    public static void setTransferTo (JComboBox combo ,String type){
           setSelectedByIndex(combo,type);
            switch (type) {
            case "Conta Comum":
                selectedTransferToContaComum = selectedContaComum;
                break;
            case "Conta Especial":
                selectedTransferToContaEspecial = selectedContaEspecial;
            break;
            case "Conta Poupanca":
            case "Conta Poupança":
               selectedTransferToContaPoupanca = selectedContaPoupanca;
            break;
            default:
               break;
        }   
    }
    
    public static void deletarCliente (JTable table) {
        ClienteDAO.getInstance().delete(selectedCliente);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public static void deletarContaComum (JTable table) {
        ContaComumDAO.getInstance().delete(selectedContaComum);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public static void deletarContaEspecial (JTable table) {
        ContaEspecialDAO.getInstance().delete(selectedContaEspecial);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public static void deletarContaPoupanca (JTable table) {
        ContaPoupancaDAO.getInstance().delete(selectedContaPoupanca);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public static void depositarContaComum (float valor){
        selectedContaComum.depositar(valor);
        System.out.println(selectedContaComum.getSaldo());
        ContaComumDAO.getInstance().update(selectedContaComum);
    }
    
    public static void depositarContaEspecial (float valor){
        selectedContaEspecial.depositar(valor);
        System.out.println(selectedContaEspecial.getSaldo());
        ContaEspecialDAO.getInstance().update(selectedContaEspecial);
    }
        
    public static void depositarContaPoupanca (float valor){       
        selectedContaPoupanca.depositar(valor);
        System.out.println(selectedContaPoupanca.getSaldo());
        ContaPoupancaDAO.getInstance().update(selectedContaPoupanca);
    }
    
    public static void sacarContaComum (float valor){
        selectedContaComum.sacar(valor);
        System.out.println(selectedContaComum.getSaldo());
        ContaComumDAO.getInstance().update(selectedContaComum);
    }
    
    public static void sacarContaEspecial (float valor){
        selectedContaEspecial.sacar(valor);
        System.out.println(selectedContaEspecial.getSaldo());
        ContaEspecialDAO.getInstance().update(selectedContaEspecial);
    }
        
    public static void sacarContaPoupanca (float valor){       
        selectedContaPoupanca.sacar(valor);
        System.out.println(selectedContaPoupanca.getSaldo());
        ContaPoupancaDAO.getInstance().update(selectedContaPoupanca);
    }
   
    
    public static void transferir (float valor,String tipoContaOrigem, String tipoContaDestino){  
        Conta transferirDe = null, transferirPara = null;
        switch (tipoContaOrigem) {
            case "Conta Comum":
                transferirDe = selectedContaComum;
            break;
            case "Conta Especial":
                transferirDe = selectedContaEspecial;
            break;
            case "Conta Poupanca":
            case "Conta Poupança":
               transferirDe = selectedContaPoupanca;
            break;
            default:
               break;
        }
        switch (tipoContaDestino) {
           case "Conta Comum":
                transferirPara = selectedTransferToContaComum;
            break;
            case "Conta Especial":
                transferirPara = selectedTransferToContaEspecial;
            break;
            case "Conta Poupanca":
            case "Conta Poupança":
               transferirPara = selectedTransferToContaPoupanca;
            break;
            default:
               break;
        }
        if(transferirDe != null && transferirPara != null){
            if(transferirDe.sacar(valor)){
                
                transferirPara.depositar(valor);

                System.out.println("De Conta:"+transferirDe.getId()+"\nSaldo: "+ transferirDe.getSaldo());
                System.out.println("Para Conta:"+transferirPara.getId()+"\nSaldo: "+ transferirPara.getSaldo());

               if("Conta Comum".equals(tipoContaOrigem)){
                    ContaComumDAO.getInstance().update((ContaComum) transferirDe);
               }else if("Conta Especial".equals(tipoContaOrigem)){
                    ContaEspecialDAO.getInstance().update((ContaEspecial) transferirDe);
               }else{
                    ContaPoupancaDAO.getInstance().update((ContaPoupanca) transferirDe);
               }
                if("Conta Comum".equals(tipoContaDestino)){
                    ContaComumDAO.getInstance().update((ContaComum) transferirPara);
               }else if("Conta Especial".equals(tipoContaDestino)){
                    ContaEspecialDAO.getInstance().update((ContaEspecial) transferirPara);
               }else{
                    ContaPoupancaDAO.getInstance().update((ContaPoupanca) transferirPara);
               }
          }
        }
        
    }
}
