
package miniprojetoanalise.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    public static final String DBURL = "";//completar link banco
     private static Connection con;
     
     public static Connection getConnection(){
         if(con == null){
             try {
                 con = DriverManager.getConnection(DBURL);
                 if(con != null){
                    DatabaseMetaData meta = con.getMetaData();
                }
             } catch (SQLException e){
                System.err.println("Exception "+ e.getMessage());
            }
         } return con;
     } 
     
     protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;

        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }
     
    protected final boolean createTable(){
        try{
            PreparedStatement stmt;
            
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente (\n"
            + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR,\n"
                    + "dataNascimento VARCHAR);\n");
            executeUpdate(stmt);
            
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS contaComum (\n"
            + "id INTEGER PRIMARY KEY, \n"
                    + "dataAbertura VARCHAR, \n"
                    + "saldo FLOAT,\n"
                    + "limiteValor FLOAT, \n"
                    + "idCliente INTEGER);\n");
            executeUpdate(stmt);
            
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS contaEspecial (\n"
            + "id INTEGER PRIMARY KEY, \n"
                    + "dataAbertura VARCHAR, \n"
                    + "saldo FLOAT,\n"
                    + "limiteValor FLOAT, \n"
                    + "limiteCredito FLOAT, \n"
                    + "idCliente INTEGER);\n");
            executeUpdate(stmt);
            
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS contaPoupanca (\n"
            + "id INTEGER PRIMARY KEY, \n"
                    + "dataAbertura VARCHAR, \n"
                    + "saldo FLOAT,\n"
                    + "limiteValor FLOAT, \n"
                    + "dataAniverConta INTEGER, \n"
                    + "idCliente INTEGER);\n");
            executeUpdate(stmt);
            
            return true;
            } catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
    
    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }
    
    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }
    
    public static void finish() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
