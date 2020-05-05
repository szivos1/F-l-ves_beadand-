package csomag;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Program extends JFrame {

	private JPanel contentPane;
	private TablaMenedzsment tm;
	private AdatBCucc dbm = new AdatBCucc();
	private int seged = 0;
	private String szin = null;
	private static DefaultTableModel dtm;
	private static PrintWriter pdfKi;
	private static PdfPTable pdfTabla;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public Program() {
		setResizable(false);
		dbm.Reg();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 162);
		
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("List\u00E1z");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(".csv file-b\u00F3l");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm = FajlMenedzsment.CvsReader();
				Listaz el = new Listaz(Program.this, tm);
			    el.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("SQLite adatb\u00E1zisb\u00F3l");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.Connect();
				tm = dbm.ReadAllData();
				dbm.Disconnect();
				Listaz el = new Listaz(Program.this, tm);
			    el.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		/*JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u00D6sszes\u00EDtett");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etm = FajlMenedzsment.CvsReader();
				dbm.Connect();
				etm = dbm.ReadAllData();
				dbm.Disconnect();
				Listaz el = new Listaz(MenuFelulet.this, etm);
			    el.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);*/
		
		JMenu mnNewMenu_1 = new JMenu("\u00DAj adat");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem(".csv file-ba");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UjAdat ne = new UjAdat(seged);
				ne.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("SQLite adatb\u00E1zisba");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UjAdat ne = new UjAdat(seged);
				ne.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Mindkett\u0151be");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UjAdatMind uam = new UjAdatMind(seged);
				uam.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("T\u00F6rl\u00E9s");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem(".csv file-b\u00F3l");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm = FajlMenedzsment.CvsReader();
				Torles ed = new Torles(Program.this, tm, seged);
				ed.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("SQLite adatb\u00E1zisb\u00F3l");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.Connect();
				tm = dbm.ReadAllData();
				dbm.Disconnect();
				Torles ed = new Torles(Program.this, tm, seged);
				ed.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_3 = new JMenu("M\u00F3dos\u00EDt\u00E1s");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem(".csv file-ban");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm=FajlMenedzsment.CvsReader();
				Modositas em = new Modositas(Program.this, tm, seged);
				em.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("SQLite adatb\u00E1zisban");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.Connect();
				tm = dbm.ReadAllData();
				dbm.Disconnect();
				Modositas em = new Modositas(Program.this, tm, seged);
				em.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_4 = new JMenu("\u00DAj f\u00E1jl/Nyomtat\u00E1s");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_4);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar.setBackground(new Color(50, 111, 50));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(new Color(50, 111, 50));
		mnNewMenu.setForeground(Color.LIGHT_GRAY);
		mnNewMenu_1.setOpaque(true);
		mnNewMenu_1.setBackground(new Color(50, 111, 50));
		mnNewMenu_1.setForeground(Color.LIGHT_GRAY);
		mnNewMenu_2.setOpaque(true);
		mnNewMenu_2.setBackground(new Color(50, 111, 50));
		mnNewMenu_2.setForeground(Color.LIGHT_GRAY);
		mnNewMenu_3.setOpaque(true);
		mnNewMenu_3.setBackground(new Color(50, 111, 50));
		mnNewMenu_3.setForeground(Color.LIGHT_GRAY);
		mnNewMenu_4.setOpaque(true);
		mnNewMenu_4.setBackground(new Color(50, 111, 50));
		mnNewMenu_4.setForeground(Color.LIGHT_GRAY);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("PDF-be nyomtat");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pdfTabla = new PdfPTable(new float[] {15,25,20,20,20});
				Document doc = new Document();
				String oszlopok[] = {"ID","Név","Jelleg","Teljesítmény","Forgalmazva"};
				dtm= new DefaultTableModel(oszlopok,0);
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\Féléves_beadandó\\lampa.csv"));
					String szoveg=reader.readLine();
					while(szoveg!=null)
					{
						String[] elem= szoveg.split(";");
						dtm.addRow(new Object[] {elem[0],elem[1],elem[2],elem[3],elem[4]});
						for(int i=0;i<5;i++)
						{
							PdfPCell pdfCella=new PdfPCell(new Paragraph(elem[i]));
							pdfTabla.addCell(pdfCella);
						}
						szoveg=reader.readLine();
					}
					reader.close();
				} catch (IOException e) {
					try {
						System.out.println("Create new lampa.csv");
						pdfKi = new PrintWriter(new FileOutputStream("C:\\Users\\User\\Desktop\\Féléves_beadandó\\lampa.csv"));
						pdfKi.close();
					} catch (FileNotFoundException e1) {
						SM("PDFKeszites: "+e1.getMessage(), 0);
					}
				}
				try {
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\User\\Desktop\\Féléves_beadandó\\Lampa.pdf"));
					doc.open();
					Paragraph par = new Paragraph ("Lámpák");
					doc.add(par);
					par = new Paragraph (" ");
					doc.add(par);
					pdfTabla.setHorizontalAlignment(Element.ALIGN_LEFT);
					pdfTabla.setWidthPercentage(100);
					doc.add(pdfTabla);
					doc.close();
				} catch (FileNotFoundException|DocumentException e) {
					SM("PDFKeszites: "+e.getMessage(), 0);
				}
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(154, 205, 50));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"V\u00E1lassz t\u00E9m\u00E1t!", "Z\u00F6ld (default)", "Piros", "K\u00E9k", "Fekete"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				szin = (String) comboBox.getSelectedItem();
				
				switch (szin) {
				case "Zöld (default)":
					menuBar.setBackground(new Color(50, 111, 50));
					contentPane.setBackground(new Color(127, 255, 212));
					comboBox.setBackground(new Color(154, 205, 50));
					comboBox.setForeground(Color.DARK_GRAY);
					mnNewMenu.setBackground(new Color(50, 111, 50));
					mnNewMenu.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_1.setBackground(new Color(50, 111, 50));
					mnNewMenu_1.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_2.setBackground(new Color(50, 111, 50));
					mnNewMenu_2.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_3.setBackground(new Color(50, 111, 50));
					mnNewMenu_3.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_4.setBackground(new Color(50, 111, 50));
					mnNewMenu_4.setForeground(Color.LIGHT_GRAY);
					
					break;

				case "Piros":
					menuBar.setBackground(new Color(127, 30, 30));
					contentPane.setBackground(new Color(255, 111, 111));
					comboBox.setBackground(new Color(240, 55, 60));
					comboBox.setForeground(Color.LIGHT_GRAY);
					mnNewMenu.setBackground(new Color(127, 30, 30));
					mnNewMenu.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_1.setBackground(new Color(127, 30, 30));
					mnNewMenu_1.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_2.setBackground(new Color(127, 30, 30));
					mnNewMenu_2.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_3.setBackground(new Color(127, 30, 30));
					mnNewMenu_3.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_4.setBackground(new Color(127, 30, 30));
					mnNewMenu_4.setForeground(Color.LIGHT_GRAY);
					
					break;
					
				case "Kék":
					menuBar.setBackground(new Color(0, 80, 160));
					contentPane.setBackground(new Color(60, 170, 235));
					comboBox.setBackground(new Color(0, 128, 255));
					comboBox.setForeground(Color.LIGHT_GRAY);
					mnNewMenu.setBackground(new Color(0, 80, 160));
					mnNewMenu.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_1.setBackground(new Color(0, 80, 160));
					mnNewMenu_1.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_2.setBackground(new Color(0, 80, 160));
					mnNewMenu_2.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_3.setBackground(new Color(0, 80, 160));
					mnNewMenu_3.setForeground(Color.LIGHT_GRAY);
					mnNewMenu_4.setBackground(new Color(0, 80, 160));
					mnNewMenu_4.setForeground(Color.LIGHT_GRAY);
					
					break;
					
				case "Fekete":
					menuBar.setBackground(new Color(75, 75, 75));
					contentPane.setBackground(new Color(0, 0, 0));
					comboBox.setBackground(new Color(115, 115, 115));
					comboBox.setForeground(Color.WHITE);
					mnNewMenu.setBackground(new Color(75, 75, 75));
					mnNewMenu.setForeground(Color.WHITE);
					mnNewMenu_1.setBackground(new Color(75, 75, 75));
					mnNewMenu_1.setForeground(Color.WHITE);
					mnNewMenu_2.setBackground(new Color(75, 75, 75));
					mnNewMenu_2.setForeground(Color.WHITE);
					mnNewMenu_3.setBackground(new Color(75, 75, 75));
					mnNewMenu_3.setForeground(Color.WHITE);
					mnNewMenu_4.setBackground(new Color(75, 75, 75));
					mnNewMenu_4.setForeground(Color.WHITE);
					
					break;
				default:
					break;
				}
			}
		});
		comboBox.setBounds(205, 69, 144, 20);
		contentPane.add(comboBox);
		
		

		
		Object emptmn[] = {"Jel", "ID", "Név", "Jelleg", "Teljesítmény", "Forgalmazva"};
		tm = new TablaMenedzsment(emptmn, 0);
	}
}
