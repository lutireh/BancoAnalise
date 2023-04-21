
package miniprojetoanalise.controller;

import java.util.ArrayList;
import java.util.List;
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
    private static List<ContaComum> contaComum = new ArrayList<>();
    private static List<ContaEspecial> contaEspecial = new ArrayList<>();
    private static List<ContaPoupanca> contaPoupanca = new ArrayList<>();

    private static Cliente getCliente (int id){
        return cliente.get(id);
    }
    
    private static ContaComum getContaComum (int id) {
        return contaComum.get(id);
    }
    
    private static ContaEspecial getContaEspecial (int id) {
        return contaEspecial.get(id);
    }
    
    private static ContaPoupanca getContaPoupanca (int id) {
        return contaPoupanca.get(id);
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
    
    public void deletarCliente (JTable table) {
        ClienteDAO.getInstance().delete(selectedCliente);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public void deletarContaComum (JTable table) {
        ContaComumDAO.getInstance().delete(selectedContaComum);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public void deletarContaEspecial (JTable table) {
        ContaEspecialDAO.getInstance().delete(selectedContaEspecial);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public void deletarContaPoupanca (JTable table) {
        ContaPoupancaDAO.getInstance().delete(selectedContaPoupanca);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
}
