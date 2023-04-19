
package miniprojetoanalise.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaEspecialDAO extends DAO{
    
    private static ContaEspecialDAO instance;
    
    public static ContaEspecialDAO getInstance(){
        return(instance == null ? (instance = new ContaEspecialDAO()) : instance);
    }
    
    public ContaEspecial create(String dataAbertura, float saldo, float limiteValor, float limiteCredito, int idCliente){
        try{
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO contaEspecial"
                    +"(dataAbertura, saldo, limiteValor, limiteCredito, idCliente)"
                    +"VALUES(?,?,?,?,?)");
            pstm.setString(1, dataAbertura);
            pstm.setFloat(2,saldo);
            pstm.setFloat(3,limiteValor);
            pstm.setFloat(4,limiteCredito);
            pstm.setInt(5,idCliente);
            executeUpdate(pstm);
        }catch(SQLException e){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
         return this.retrieveById(lastId("contaEspecial", "id"));
    }
    
    private ContaEspecial buildObject(ResultSet rs){
        ContaEspecial contaEspecial = null;
        try{
            contaEspecial = new ContaEspecial(rs.getInt("id"),
            rs.getString("dataAbertura"),
            rs.getFloat("saldo"),
            rs.getFloat("limiteValor"),
            rs.getFloat("limiteCredito"),
            rs.getInt("idCliente"));
        } catch (SQLException e){
            System.err.println("Exception: "+ e.getMessage());
        }
        return contaEspecial;
    }
       
     public List retrieve(String query) {
        List<ContaEspecial> contasEspeciais = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                contasEspeciais.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return contasEspeciais;
    }
     
     public List retrieveAll(){
        return this.retrieve("SELECT * FROM contaEspecial ");
    }
    
    public ContaEspecial retrieveById(int id) {
        List<ContaEspecial> contasEspeciais = this.retrieve("SELECT * FROM contaEspecial WHERE id = " + id);
        return (contasEspeciais.isEmpty() ? null : contasEspeciais.get(0));
    }
    
    public List retrieveByClientCpf(String cpf) {
        return this.retrieve("SELECT * FROM contaEspecial WHERE nome LIKE '%" + cpf + "%'");
    }
    
    public void update(ContaEspecial contaEspecial){
         PreparedStatement pstm;
         try{
             pstm = DAO.getConnection().prepareStatement("UPDATE contaEspecial SET" 
                     +"dataAbertura = ?, saldo = ?, limiteValor = ?,limiteCredito = ?,  idCliente = ?");
             pstm.setString(1,contaEspecial.getDataAbertura());
             pstm.setFloat(2,contaEspecial.getSaldo());
             pstm.setFloat(3,contaEspecial.getLimiteValor());
             pstm.setFloat(4,contaEspecial.getLimiteCredito());
             pstm.setInt(5,contaEspecial.getidCliente());
         }  catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    public void delete(ContaEspecial contaEspecial) {
        PreparedStatement pstm;
        try {
            pstm = DAO.getConnection().prepareStatement("DELETE FROM contaEspecial WHERE id = ?");
            pstm.setInt(1, contaEspecial.getId());
            executeUpdate(pstm);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
