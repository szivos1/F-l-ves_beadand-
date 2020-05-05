package csomag;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class TablaMenedzsment extends DefaultTableModel {
	public TablaMenedzsment(Object fieldNames[], int rows) {
		super(fieldNames, rows);
	}

	public boolean isCellEditable(int row, int col) {
		if (col == 0) {return true;}
		return false;
	}

	public Class<?> getColumnClass(int index) {
		if (index == 0) return(Boolean.class);
			else if (index == 1 || index == 4) return(Integer.class);
		return(String.class);
		
		
	}
}
