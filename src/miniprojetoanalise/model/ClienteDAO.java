
package miniprojetoanalise.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO extends DAO{
    
    private static ClienteDAO instance;
    
    private ClienteDAO(){
        getConnection();
        createTable();
    }
       
    public static ClienteDAO getInstance(){
        return(instance == null ? (instance = new ClienteDAO()) : instance);
    }
    
    public Cliente create(String nome, String cpf, String dataNascimento){
        try {
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO cliente"
               +"(nome, cpf, dataNascimento)"
               +"VALUES(?,?,?)");
            pstm.setString(1, nome);
            pstm.setString(2, cpf);
            pstm.setString(3, dataNascimento);
            executeUpdate(pstm);
        } catch (SQLException e){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return this.retrieveById(lastId("cliente", "id"));
    }
    
    private Cliente buildObject (ResultSet rs){
        Cliente cliente = null;
        try {
            cliente = new Cliente (rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("cpf"),
            rs.getString("dataNascimento"));
        } catch (SQLException e) {
            System.err.println("Exception: "+ e.getMessage());
        }
        return cliente;
    }
    
    public List retrieve(String query) {
        List<Cliente> clientes = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                clientes.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return clientes;
    }
    
     public List retrieveAll(){
        return this.retrieve("SELECT * FROM cliente ");
    }
    
    public Cliente retrieveById(int id) {
        List<Cliente> clientes = this.retrieve("SELECT * FROM cliente WHERE id = " + id);
        return (clientes.isEmpty() ? null : clientes.get(0));
    }
    
    public List retrieveByCpf(String cpf) {
        return this.retrieve("SELECT * FROM cliente WHERE cpf LIKE '%" + cpf + "%'");
    }
    
     public void update(Cliente cliente) {
        PreparedStatement pstm;
        try {
            pstm = DAO.getConnection().prepareStatement("UPDATE cliente SET nome=?, cpf=?,"
                    + "dataNascimento=? WHERE id=?");
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getCpf());
            pstm.setString(3, cliente.getDataNascimento());
            executeUpdate(pstm);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
     }
     
     public void delete(Cliente cliente) {
        PreparedStatement pstm;
        try {
            pstm = DAO.getConnection().prepareStatement("DELETE FROM cliente WHERE id = ?");
            pstm.setInt(1, cliente.getId());
            executeUpdate(pstm);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
