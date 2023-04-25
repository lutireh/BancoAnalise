
package miniprojetoanalise.view.table;

import java.util.List;
import miniprojetoanalise.model.Cliente;
import miniprojetoanalise.model.ClienteDAO;
import miniprojetoanalise.model.ContaPoupanca;
import miniprojetoanalise.model.ContaPoupancaDAO;

public class ContaPoupancaTableModel extends GenericTableModel{
       
    public ContaPoupancaTableModel(List vDados){
        super(vDados, new String[]{"DataAbertura","Saldo","LimiteValor","DataAniverConta","Cliente"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContaPoupanca contaPoupanca = (ContaPoupanca) vDados.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return contaPoupanca.getDataAbertura();
            case 1:
                return contaPoupanca.getSaldo();  
            case 2:
                return contaPoupanca.getLimiteValor(); 
            case 3:
                return contaPoupanca.getDataAniverConta();
            case 4:
               Cliente cliente = ClienteDAO.getInstance().retrieveById(contaPoupanca.getidCliente());
               if(cliente != null){
                   return cliente.getNome();
               } else return "";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
     @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
         ContaPoupanca contaPoupanca = (ContaPoupanca) vDados.get(rowIndex);
         switch(columnIndex) {
            case 0:
                contaPoupanca.setDataAbertura((String) aValue);
                break;
            case 1:
                contaPoupanca.setSaldo((Float) aValue);  
            case 2:
                contaPoupanca.setLimiteValor((Float) aValue); 
            case 3:
                contaPoupanca.setDataAniverConta((int) aValue);
            case 4:
                contaPoupanca.setIdCliente((int) aValue) ;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
         }
         ContaPoupancaDAO.getInstance().update(contaPoupanca);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
