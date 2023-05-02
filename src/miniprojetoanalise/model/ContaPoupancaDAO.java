
package miniprojetoanalise.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaPoupancaDAO extends DAO{
    
    private static ContaPoupancaDAO instance;
    
    public static ContaPoupancaDAO getInstance(){
        return(instance == null ? (instance = new ContaPoupancaDAO()) : instance);
    }
    
    public ContaPoupanca create(String dataAbertura, float saldo, float limiteValor, int dataAniverConta, int idCliente){
        try{
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO contaPoupanca"
                    +"(dataAbertura, saldo, limiteValor, dataAniverConta, idCliente)"
                    +"VALUES(?,?,?,?,?)");
            pstm.setString(1, dataAbertura);
            pstm.setFloat(2,saldo);
            pstm.setFloat(3,limiteValor);
            pstm.setInt(4,dataAniverConta);
            pstm.setInt(5,idCliente);
            executeUpdate(pstm);
        }catch(SQLException e){
            Logger.getLogger(ContaPoupancaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
         return this.retrieveById(lastId("contaPoupanca", "id"));
    }
    
    private ContaPoupanca buildObject(ResultSet rs){
        ContaPoupanca contapoupanca = null;
        try{
            contapoupanca = new ContaPoupanca(rs.getInt("id"),
            rs.getString("dataAbertura"),
            rs.getFloat("saldo"),
            rs.getFloat("limiteValor"),
            rs.getInt("dataAniverConta"),
            rs.getInt("idCliente"));
        } catch (SQLException e){
            System.err.println("Exception: "+ e.getMessage());
        }
        return contapoupanca;
    }
       
     public List retrieve(String query) {
        List<ContaPoupanca> contaspoupanca = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                contaspoupanca.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return contaspoupanca;
    }
     
     public List retrieveAll(){
        return this.retrieve("SELECT * FROM contaPoupanca ");
    }
    
    public ContaPoupanca retrieveById(int id) {
        List<ContaPoupanca> contaspoupanca = this.retrieve("SELECT * FROM contaPoupanca WHERE id = " + id);
        return (contaspoupanca.isEmpty() ? null : contaspoupanca.get(0));
    }
    
    public List retrieveByClientCpf(String cpf) {
        return this.retrieve("SELECT * FROM cliente WHERE nome LIKE '%" + cpf + "%'");
    }
    
    public List retrieveAllById(int id) {
        List contasComuns = this.retrieve("SELECT * FROM contaPoupanca WHERE idCliente = " + id);
        return contasComuns;
    }
    
    
    public boolean update(ContaPoupanca contaPoupanca){
         PreparedStatement pstm;
         try{
             pstm = DAO.getConnection().prepareStatement("UPDATE contaPoupanca SET" 
                     +" dataAbertura = ?, saldo = ?, limiteValor = ?, dataAniverConta = ?, idCliente = ?"
                     + " where id = ?");
             pstm.setString(1,contaPoupanca.getDataAbertura());
             pstm.setFloat(2,contaPoupanca.getSaldo());
             pstm.setFloat(3,contaPoupanca.getLimiteValor());
             pstm.setInt(4,contaPoupanca.getDataAniverConta());
             pstm.setInt(5,contaPoupanca.getIdCliente());
             pstm.setInt(6,contaPoupanca.getId());
             executeUpdate(pstm);   
             return true;
         }  catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
        }
    }
    public void delete(ContaPoupanca contaPoupanca) {
        PreparedStatement pstm;
        try {
            pstm = DAO.getConnection().prepareStatement("DELETE FROM contaPoupanca WHERE id = ?");
            pstm.setInt(1, contaPoupanca.getId());
            executeUpdate(pstm);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
