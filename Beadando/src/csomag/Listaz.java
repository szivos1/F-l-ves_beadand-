package csomag;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Listaz extends JDialog {
	private JTable table;
	private TablaMenedzsment etm;


	public Listaz(JFrame f, TablaMenedzsment betm) {
		super(f, "Lámpák", true);
		getContentPane().setBackground(new Color(127, 255, 212));
		etm = betm;
		setBounds(100, 100, 450, 162);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 123);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i == 0 || i == 1) tc.setPreferredWidth(30);
			else if (i == 2) tc.setPreferredWidth(150);
			else {tc.setPreferredWidth(100);}
		}
		table.setOpaque(true);
		table.setBackground(new Color(127, 255, 212));

		table.setAutoCreateRowSorter(true);
		TableRowSorter<TablaMenedzsment> trs = (TableRowSorter<TablaMenedzsment>)table.getRowSorter();
		trs.setSortable(0, false);
	}
}
