
package miniprojetoanalise.view.table;

import java.util.List;
import miniprojetoanalise.model.Cliente;
import miniprojetoanalise.model.ClienteDAO;
import miniprojetoanalise.model.ContaEspecial;
import miniprojetoanalise.model.ContaEspecialDAO;

public class ContaEspecialTableModel extends GenericTableModel{
    
    public ContaEspecialTableModel(List vDados){
        super(vDados, new String[]{"DataAbertura","Saldo","LimiteValor","LimiteCredito","Cliente"});
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
        ContaEspecial contaEspecial = (ContaEspecial) vDados.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return contaEspecial.getDataAbertura();
            case 1:
                return contaEspecial.getSaldo();  
            case 2:
                return contaEspecial.getLimiteValor(); 
            case 3:
                return contaEspecial.getLimiteCredito();
            case 4:
               Cliente cliente = ClienteDAO.getInstance().retrieveById(contaEspecial.getidCliente());
               if(cliente != null){
                   return cliente.getNome();
               } else return "";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }  
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
         ContaEspecial contaEspecial = (ContaEspecial) vDados.get(rowIndex);
         switch(columnIndex) {
            case 0:
                contaEspecial.setDataAbertura((String) aValue);
                break;
            case 1:
                contaEspecial.setSaldo((Float) aValue);  
            case 2:
                contaEspecial.setLimiteValor((Float) aValue); 
            case 3:
                contaEspecial.setLimiteCredito((Float) aValue);
            case 4:
                contaEspecial.setIdCliente((int) aValue) ;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
         }
         ContaEspecialDAO.getInstance().update(contaEspecial);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
