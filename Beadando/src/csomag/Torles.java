package csomag;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Torles extends JDialog {
	private final JPanel contentJPanel = new JPanel();
	private JTable table;
	private TablaMenedzsment etm;
	private Vizsg c = new Vizsg();
	private AdatBCucc dbm = new AdatBCucc();
	private JButton btnTrls;
	
	public Torles(JFrame f, TablaMenedzsment betm, int dbkez) {
		super(f, "Lámpa törlése", true);
		getContentPane().setBackground(new Color(127, 255, 212));
		etm= betm;
		
		setBounds(100, 100, 450, 215);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 130);
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

		table.setAutoCreateRowSorter(true);
		table.setOpaque(true);
		table.setBackground(new Color(127, 255, 212));
		
		
		btnTrls = new JButton("Adatok t\u00F6rl\u00E9se");
		
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 1, jel = 0, x = 0;
				for (x = 0; x < etm.getRowCount(); x++) {
					if ((Boolean)etm.getValueAt(x, 0)) {db++; jel = x;}
					if(db == 0) c.SM("Nincs kijelölve semmi!", 0);
					if(db > 2) c.SM("Egyszerre csak 1 rekord törölhetõ!", 0);
					if(db == 2) {
						String kod = etm.getValueAt(jel, 1).toString();
						etm.removeRow(jel); 
						if (dbkez == 0) FajlMenedzsment.Insert(etm);
						else {
							dbm.Connect();
							dbm.DeleteData(kod);
							dbm.Disconnect();
						}
						dispose(); 
						}
				}
			}
		});
		btnTrls.setBounds(148, 141, 131, 23);
		btnTrls.setOpaque(true);
		btnTrls.setBackground(new Color(50, 111, 50));
		btnTrls.setForeground(Color.LIGHT_GRAY);
		getContentPane().add(btnTrls);
		TableRowSorter<TablaMenedzsment> trs = (TableRowSorter<TablaMenedzsment>)table.getRowSorter();
		trs.setSortable(0, false);
		//btnTrls.addKeyListener(new MyKeyListener());
	}
	/*public class MyKeyListener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if (e.getKeyChar() == KeyEvent.VK_ENTER) {

            	btnTrls.doClick();

            }

        }

    }*/
}
