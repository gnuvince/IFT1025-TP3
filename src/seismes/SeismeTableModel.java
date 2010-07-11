package seismes;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class SeismeTableModel extends AbstractTableModel {
    private static final String[] columns = {
        "Date", "Latitude", "Longitude", "Magnitude", "Profondeur", "Commentaire"
    };
    private Seisme[] seismes;
    
    public SeismeTableModel(Seisme[] seismes) {
        this.seismes = seismes;
    }
    
    private String seismeIndex(Seisme seisme, int columnIndex) {
        switch (columnIndex) {
        case 0: return seisme.getDatetime().toString();
        case 1: return Double.toString(seisme.getCoord().getLatitude());
        case 2: return Double.toString(seisme.getCoord().getLongitude());
        case 3: return Double.toString(seisme.getMagnitude());
        case 4: return Double.toString(seisme.getDepth());
        case 5: return seisme.getComment();
        default: return "?";
        }
    }

    @Override
    public int getRowCount() {
        return seismes.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return seismeIndex(seismes[rowIndex], columnIndex);
    }
}
