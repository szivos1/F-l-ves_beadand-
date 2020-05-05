package csomag;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UjAdat extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField id;
	private JTextField nev;
	private JTextField jelleg;
	private JTextField telj;
	private JTextField forg;
	private Vizsg c = new Vizsg();
	private AdatBCucc dbm = new AdatBCucc();
	
	public UjAdat(int dbkez) {
		setBounds(100, 100, 235, 250);
		setTitle("Új adat bevitele");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(127, 255, 212));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 17, 46, 14);
		contentPanel.add(lblID);
		
		JLabel lblNev = new JLabel("N\u00E9v:");
		lblNev.setBounds(10, 42, 46, 14);
		contentPanel.add(lblNev);
		
		JLabel lblJelleg = new JLabel("Jelleg:");
		lblJelleg.setBounds(10, 67, 74, 14);
		contentPanel.add(lblJelleg);
		
		JLabel lblTelj = new JLabel("Teljes\u00EDtm\u00E9ny:");
		lblTelj.setBounds(10, 92, 90, 14);
		contentPanel.add(lblTelj);
		
		JLabel lblForg = new JLabel("Forgalmazva:");
		lblForg.setBounds(10, 117, 90, 14);
		contentPanel.add(lblForg);
		
		id = new JTextField();
		id.setBounds(110, 14, 86, 20);
		contentPanel.add(id);
		id.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(110, 39, 86, 20);
		contentPanel.add(nev);
		nev.setColumns(10);
		
		jelleg = new JTextField();
		jelleg.setBounds(110, 64, 86, 20);
		contentPanel.add(jelleg);
		jelleg.setColumns(10);
		
		telj = new JTextField();
		telj.setBounds(110, 89, 86, 20);
		contentPanel.add(telj);
		telj.setColumns(10);
		
		forg = new JTextField();
		forg.setBounds(110, 114, 86, 20);
		contentPanel.add(forg);
		forg.setColumns(10);
		
		JButton btnBeszur = new JButton("Besz\u00FAr");
		btnBeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(c.goodInt(id, "ID"))
					if(c.filled(nev, "Név"))
						if(c.filled(jelleg, "Jelleg"))
							if(c.goodInt(telj, "Teljesítmény"))
								if(c.goodDate(forg, "Forgalmazva"))
									if(dbkez == 0) {
										FajlMenedzsment.Insert(RTF(id), RTF(nev), RTF(jelleg), RTF(telj), RTF(forg));
									} else {
										dbm.Connect();
										dbm.Insert(RTF(id), RTF(nev), RTF(jelleg), RTF(telj), RTF(forg));
										dbm.Disconnect();
									}
			}
		});
		btnBeszur.setBounds(63, 162, 89, 23);
		contentPanel.add(btnBeszur);
		btnBeszur.setOpaque(true);
		btnBeszur.setBackground(new Color(50, 111, 50));
		btnBeszur.setForeground(Color.LIGHT_GRAY);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
