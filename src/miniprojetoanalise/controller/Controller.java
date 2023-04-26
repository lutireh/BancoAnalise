
package miniprojetoanalise.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import miniprojetoanalise.model.Cliente;
import miniprojetoanalise.model.ClienteDAO;
import miniprojetoanalise.model.ContaComum;
import miniprojetoanalise.model.ContaComumDAO;
import miniprojetoanalise.model.ContaEspecial;
import miniprojetoanalise.model.ContaEspecialDAO;
import miniprojetoanalise.model.ContaPoupanca;

import miniprojetoanalise.model.ContaPoupancaDAO;
import miniprojetoanalise.view.table.GenericTableModel;

public class Controller {
    private static List<Cliente> cliente = new ArrayList<>();
    //fazer uma lista de contas com todas ???? para utilizar nos combo box de ações
    
    public static void initializeComboBox(JComboBox combo, String type){
        combo.removeAllItems();
        if(type.equals("Cliente")){
            setComboBox(combo, ClienteDAO.getInstance().retrieveAll(),type);
         }
        if(type.equals("ContaComum")){
            setComboBox(combo,ContaComumDAO.getInstance().retrieveByClientCpf(selectedCliente.getCpf()),type);
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
               cliente.clear();
               for ( Object obj: objs){
                   combo.addItem(((ContaComum)obj).getId());
                   cliente.add((Cliente) obj);
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
    private static ContaComum selectedContaComum = null;
    private static ContaEspecial selectedContaEspecial = null;
    private static ContaPoupanca selectedContaPoupanca = null;
    
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
}
