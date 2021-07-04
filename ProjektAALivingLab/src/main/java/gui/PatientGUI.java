package gui;

import java.awt.Container;
/**
 * NUR FÜR DEN PROJEKTTAG! zu Demonstationszwecken.
 */
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class PatientGUI extends JFrame{
	private JPanel oben = new JPanel();
	private JPanel mitte = new JPanel();
	private JPanel unten = new JPanel();
	private JPanel gebPanel = new JPanel();
	private JPanel geschPanel = new JPanel();
	
	JLabel n = new JLabel("Name");
	JLabel vn = new JLabel("Vorname");
	JLabel geb = new JLabel("Geb.Datum: DD-MM-YYYY");
	JLabel ge = new JLabel("Geschlecht");
	JLabel respond = new JLabel("Patient hat ID:");
	
	private JTextArea textArea = new JTextArea(1,1);
	
	
	Container c = this.getContentPane();
	TextField name = new TextField(25);
	TextField vorname = new TextField(40);
	TextField year = new TextField(4);
	TextField month = new TextField(2);
	TextField day = new TextField(2);
	
	String comboBoxListe[] = {"m","f", "d"};
    JComboBox<String> geschlecht = new JComboBox<String>(comboBoxListe);
		
	JButton ok = new JButton("OK");
	
	//constr
	public PatientGUI() {
	this.setTitle("Patientenanlage");
	
	ok.addActionListener(new MyActionListener());
	c.setLayout(new GridLayout(3,1));
	
	//oben
	oben.setLayout(new GridLayout(2,2));
	oben.add(n);
	oben.add(name);
	oben.add(vn);
	oben.add(vorname);
	c.add(oben);
	
	//mitte
	gebPanel.setLayout(new FlowLayout());
	gebPanel.add(geb);
	gebPanel.add(day);
	gebPanel.add(month);
	gebPanel.add(year);
	gebPanel.add(ge);
	geschPanel.setLayout(new FlowLayout());
	geschPanel.add(geschlecht);
	
	mitte.add(gebPanel);
	mitte.add(geschPanel);
	c.add(mitte);
	
	//unten
	JPanel o = new JPanel();
	o.setLayout(new FlowLayout());
	o.add(ok);
	o.add(respond);
	unten.setLayout(new GridLayout(2,1));
	unten.add(o);
	unten.add(textArea);
	
	c.add(unten);
	
	//default
	this.setVisible(true);
	this.setSize(800, 200);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ok.setEnabled(false);
			//System.out.println("Button pressed");
			String date = year.getText()+"-"+month.getText()+"-"+day.getText();
			//System.out.println(date);
			String gender = geschlecht.getItemAt(geschlecht.getSelectedIndex());
			
			textArea.setText(test.MeineTests.createPatient(name.getText(), vorname.getText(), date, gender));
			
		}
		
	}
	
	
	//test
	
	public static void main(String...args) {
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e){
			System.out.println(e);
		}
		
		PatientGUI p = new PatientGUI();
	}
}
