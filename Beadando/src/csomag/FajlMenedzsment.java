package csomag;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FajlMenedzsment {
	
	public static TablaMenedzsment CvsReader() {
		Object emptmn[] = {"Jel", "ID", "Név", "Jelleg", "Teljesítmény", "Forgalmazva"};
		TablaMenedzsment etm = new TablaMenedzsment(emptmn, 0);


		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\Féléves_beadandó\\lampa.csv"));
			String s = in.readLine();
			s = in.readLine();
			while (s != null) {
				String[] st = s.split(";");
				etm.addRow(new Object[] {false, st[0], st[1], st[2], st[3], st[4]});
				s = in.readLine();
			}
			in.close();
		} catch (IOException ioe) {
			System.out.println("CvsReader: " + ioe.getMessage());
		}
		return etm;
	}
	
	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public static void Insert(String id, String nev, String jelleg, String telj, String forg) {
		String x = ";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("lampa.csv", true));
			out.println(id+x+nev+x+jelleg+x+telj+x+forg);
			out.close();
			SM("Adatok kiírva", 1);
		} catch (IOException ioe) {
			SM("CsvWriter: " + ioe.getMessage(), 0);			
		}
		
	}
	
	public static void Insert(TablaMenedzsment etm) {
		String x = ";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream("lampa.csv"));
			out.println("ID;Név;Jelleg;Teljesítmény;Forgalmazva");
			for (int i = 0; i < etm.getRowCount(); i++) {
				String id=etm.getValueAt(i, 1).toString();
				String nev=etm.getValueAt(i, 2).toString();
				String jelleg=etm.getValueAt(i, 3).toString();
				String telj=etm.getValueAt(i, 4).toString();
				String forg=etm.getValueAt(i, 5).toString();
				out.println(id+x+nev+x+jelleg+x+telj+x+forg);
			}
			out.close();
			
		} catch (IOException ioe) {
			SM("FM.Insert: " + ioe.getMessage(), 0);			
		}
		
	}
	
}
