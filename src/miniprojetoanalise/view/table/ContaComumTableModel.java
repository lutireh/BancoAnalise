
package miniprojetoanalise.view.table;

import java.util.List;
import miniprojetoanalise.model.Cliente;
import miniprojetoanalise.model.ClienteDAO;
import miniprojetoanalise.model.ContaComum;
import miniprojetoanalise.model.ContaComumDAO;

public class ContaComumTableModel extends GenericTableModel{
    
    public ContaComumTableModel(List vDados){
        super (vDados, new String[]{"DataAbertura","Saldo","LimiteValor","Cliente"});
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
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContaComum contaComum = (ContaComum) vDados.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return contaComum.getDataAbertura();
            case 1:
                return contaComum.getSaldo();  
            case 2:
                return contaComum.getLimiteValor(); 
            case 3:
               Cliente cliente = ClienteDAO.getInstance().retrieveById(contaComum.getIdCliente());
               if(cliente != null){
                   return cliente.getNome();
               } else return "";
             default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }        
    }
    
     @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ContaComum contaComum = (ContaComum) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                contaComum.setDataAbertura((String) aValue);
                break;
            case 1:
                contaComum.setSaldo((Float) aValue);
                break;
            case 2:
                contaComum.setLimiteValor((Float) aValue);
                break;
            case 3:
                contaComum.setIdCliente((int) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }

        ContaComumDAO.getInstance().update(contaComum);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
