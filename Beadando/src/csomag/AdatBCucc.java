package csomag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.text.StyleContext.SmallAttributeSet;

public class AdatBCucc {
	
	private Statement s = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			SM("Hibás driver regisztáció" + e.getMessage(), 0);
		}
	}
	
	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public void Connect() {
		try {
			String url = "jdbc:sqlite:C:/Users/User/Desktop/Féléves_beadandó/lampa";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			SM("JDBC connect: " + e.getMessage(), 0);
		}
	}

	public void Disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			SM(e.getMessage(), 0);
		}
	}
	
	public TablaMenedzsment ReadAllData() {
		Object emptmn[] = {"Jel", "ID", "Név", "Jelleg", "Teljesítmény", "Forgalmazva"};
		TablaMenedzsment etm = new TablaMenedzsment(emptmn, 0);
		String nev = "", jelleg = "", forg = "";
		int id = 0, telj = 0;
		String sqlp = "select id,nev,jelleg,telj,forg from lampa";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sqlp);
			while (rs.next()) {
				id = rs.getInt("id");
				nev = rs.getString("nev");
				jelleg = rs.getString("jelleg");
				telj = rs.getInt("telj");
				forg = rs.getString("forg");
				etm.addRow(new Object[]{false, id, nev, jelleg, telj, forg});
			}
			rs.close();
		} catch (SQLException e) {
			SM(e.getMessage(), 0);
		}
		return etm;
	}
	public void Insert(String id, String nev, String jelleg, String telj, String forg) {
		String sqlp = "insert into lampa values(" + id + ", '"+nev+"', '"+jelleg+"', "+telj+", '"+forg+"')";
		try {
			s= conn.createStatement();
			s.executeUpdate(sqlp);
		} catch (SQLException e) {
			SM("JDBC Insert: "+ e.getMessage(), 0);
		}
	}
	
	public void DeleteData(String id) {
		String sqlp = "delete from lampa where id="+id;
		try {
			s = conn.createStatement();
			s.executeUpdate(sqlp);
		} catch (SQLException e) {
			SM("JDBC Delete: "+e.getMessage(), 0);
		}
	}
	public void Update(String id, String mnev, String madat) {
		String sqlp = "update lampa set "+mnev+"='"+madat+"' where id="+id;
		try {
			s= conn.createStatement();
			s.executeQuery(sqlp);
		} catch (SQLException e) {
			SM("JDBC Update: "+ e.getMessage(), 0);
		}
	}
}
