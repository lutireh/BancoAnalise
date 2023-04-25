
package miniprojetoanalise.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaComumDAO extends DAO{
    
    private static ContaComumDAO instance;
    
    public static ContaComumDAO getInstance(){
        return(instance == null ? (instance = new ContaComumDAO()) : instance);
    }
    
    public ContaComum create(String dataAbertura, float saldo, float limiteValor, int idCliente){
        try{
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO contaComum"
                    +"(dataAbertura, saldo, limiteValor, idCliente)"
                    +"VALUES(?,?,?,?)");
            pstm.setString(1, dataAbertura);
            pstm.setFloat(2,saldo);
            pstm.setFloat(3,limiteValor);
            pstm.setInt(4,idCliente);
            executeUpdate(pstm);
        }catch(SQLException e){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
         return this.retrieveById(lastId("contaComum", "id"));
    }
    
    private ContaComum buildObject(ResultSet rs){
        ContaComum contacomum = null;
        try{
            contacomum = new ContaComum(rs.getInt("id"),
            rs.getString("dataAbertura"),
            rs.getFloat("saldo"),
            rs.getFloat("limiteValor"),
            rs.getInt("idCliente"));
        } catch (SQLException e){
            System.err.println("Exception: "+ e.getMessage());
        }
        return contacomum;
    }
       
     public List retrieve(String query) {
        List<ContaComum> contasComuns = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                contasComuns.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return contasComuns;
    }
     
     public List retrieveAll(){
        return this.retrieve("SELECT * FROM contaComum ");
    }
    
    public ContaComum retrieveById(int id) {
        List<ContaComum> contasComuns = this.retrieve("SELECT * FROM contaComum WHERE id = " + id);
        return (contasComuns.isEmpty() ? null : contasComuns.get(0));
    }
    
    public List retrieveByClientCpf(String cpf) {
        return this.retrieve("SELECT * FROM cliente WHERE nome LIKE '%" + cpf + "%'");
    }
    
    public void update(ContaComum contaComum){
         PreparedStatement pstm;
         try{
             pstm = DAO.getConnection().prepareStatement("UPDATE contaComum SET" 
                     +"dataAbertura = ?, saldo = ?, limiteValor = ?, idCliente = ?");
             pstm.setString(1,contaComum.getDataAbertura());
             pstm.setFloat(2,contaComum.getSaldo());
             pstm.setFloat(3,contaComum.getLimiteValor());
             pstm.setInt(4,contaComum.getIdCliente());
         }  catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    public void delete(ContaComum contaComum) {
        PreparedStatement pstm;
        try {
            pstm = DAO.getConnection().prepareStatement("DELETE FROM contaComum WHERE id = ?");
            pstm.setInt(1, contaComum.getId());
            executeUpdate(pstm);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
