package csomag;

import java.awt.EventQueue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.Font;
import java.awt.Color;


public class Modositas extends JDialog {
	private final JPanel contentJPanel = new JPanel();
	private JTable table;
	private TablaMenedzsment tm;
	private Vizsg c = new Vizsg();
	private JTextField id;
	private JTextField nev;
	private JTextField jelleg;
	private JTextField telj;
	private JTextField forg;
	private AdatBCucc dbm = new AdatBCucc();


	public Modositas(JFrame f, TablaMenedzsment betm, int dbkez) {
		super(f, "Lámpa Edit", true);
		getContentPane().setBackground(new Color(127, 255, 212));
		tm= betm;
		
		setBounds(100, 100, 585, 230);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 345, 180);
		getContentPane().add(scrollPane);
		
		table = new JTable(tm);
		table.setOpaque(true);
		table.setBackground(new Color(127, 255, 212));
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i == 0 || i == 1) tc.setPreferredWidth(30);
			else if (i == 2) tc.setPreferredWidth(150);
			else {tc.setPreferredWidth(100);}
		
		}

		table.setAutoCreateRowSorter(true);
		
		JButton btnMod = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 1, jel = 0, x = 0;
				for (x = 0; x < tm.getRowCount(); x++) {
					if ((Boolean)tm.getValueAt(x, 0)) {db++; jel = x;}
					if(db == 0) c.SM("Nincs kijelölve módosítandó rekord!", 0);
					if(db > 2) c.SM("Egyszerre csak 1 rekord módosítható!", 0);
					if(db == 2) {
						if(modDataPc() > 0) {
							boolean ok = true;
							if(c.filled(id)) ok = c.goodInt(id, "ID");
							if (ok && c.filled(telj)) ok =c.goodInt(telj, "Teljesítmény");
							if (ok) {
								if (dbkez == 1) {
									String mkod = tm.getValueAt(jel, 1).toString();
									dbm.Connect();
									if (c.filled(nev)) dbm.Update(mkod, "nev", c.RTF(nev));
									if (c.filled(jelleg)) dbm.Update(mkod, "jelleg", c.RTF(jelleg));
									if (c.filled(telj)) dbm.Update(mkod, "telj", c.RTF(telj));
									if (c.filled(forg)) dbm.Update(mkod, "forg", c.RTF(forg));
									if (c.filled(id)) dbm.Update(mkod, "id", c.RTF(id));
									dbm.Disconnect();
								}
								if (c.filled(id)) tm.setValueAt(c.stringToInt(c.RTF(id)), jel, 1);
								if (c.filled(nev)) tm.setValueAt(c.RTF(nev), jel, 2);
								if (c.filled(jelleg)) tm.setValueAt(c.RTF(jelleg), jel, 3);
								if (c.filled(telj)) tm.setValueAt(c.stringToInt(c.RTF(telj)), jel, 4);
								if (c.filled(forg)) tm.setValueAt(c.RTF(forg), jel, 5);
								if (dbkez == 0) FajlMenedzsment.Insert(tm);
								c.SM("Módosítva", 1);
							}
						}else {
							c.SM("Nincs kijelölve egyetlen módosító adatmezõ sem", 1);
						}
						tm.removeRow(jel); FajlMenedzsment.Insert(tm); dispose(); c.SM("A rekord módosítva!", 1);
					}
				}
			}
		});
		btnMod.setBounds(404, 161, 110, 23);
		btnMod.setOpaque(true);
		btnMod.setBackground(new Color(50, 111, 50));
		btnMod.setForeground(Color.LIGHT_GRAY);
		getContentPane().add(btnMod);
		
		id = new JTextField();
		id.setBounds(473, 6, 86, 20);
		getContentPane().add(id);
		id.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(473, 37, 86, 20);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		jelleg = new JTextField();
		jelleg.setBounds(473, 68, 86, 20);
		getContentPane().add(jelleg);
		jelleg.setColumns(10);
		
		telj = new JTextField();
		telj.setBounds(473, 99, 86, 20);
		getContentPane().add(telj);
		telj.setColumns(10);
		
		forg = new JTextField();
		forg.setBounds(473, 130, 86, 20);
		getContentPane().add(forg);
		forg.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(379, 9, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00E9v:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(379, 40, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Jelleg:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(379, 71, 84, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Teljes\u00EDtm\u00E9ny:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(379, 101, 84, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Forgalmazva:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(379, 132, 84, 14);
		getContentPane().add(lblNewLabel_1_3);
		TableRowSorter<TablaMenedzsment> trs = (TableRowSorter<TablaMenedzsment>)table.getRowSorter();
		trs.setSortable(0, false);
	}
	public int modDataPc() {
		int pc = 0;
		if(c.filled(id)) pc++;
		if(c.filled(nev)) pc++;
		if(c.filled(jelleg)) pc++;
		if(c.filled(telj)) pc++;
		if(c.filled(forg)) pc++;
		return pc;
	}
	public void reset(int i) {
		id.setText("");
		nev.setText("");
		jelleg.setText("");
		telj.setText("");
		forg.setText("");
		tm.setValueAt(false, i, 0);
	}
}
