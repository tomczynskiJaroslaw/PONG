import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.PortableServer.POAPackage.AdapterAlreadyExists;

/**Instrukcja funkcjonalnosci gry dla GRACZA i PODSTAWOWE USTAWIENIA GRY*/
public class Pomoc extends JFrame {
	public Pomoc() {
//		plutno.obszarRysowania();
		JCheckBox m = new JCheckBox("mysz");
		JCheckBox d = new JCheckBox("dŸwiêk");
		m.setLocation(0, 670);
		d.setLocation(200, 670);
		m.setSize(200, 30);
		d.setSize(200, 30);
		m.setSelected(Sterowanie.mysz);
		d.setSelected(Dzwiek.dzwiek);
		add(d);
		add(m);
		JButton nextLevel = new JButton(">");
		JButton previusLevel = new JButton("<");
		nextLevel.setLocation(450, 670);
		previusLevel.setLocation(400, 670);
		nextLevel.setSize(50, 30);
		previusLevel.setSize(50, 30);
		add(nextLevel);
		add(previusLevel);
		add(new JLabel(new ImageIcon("zasoby/pomoc.gif")));//Instrukcja w formiepliku graficznego
		pack();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLocationRelativeTo(Okienko.ramka);
		setResizable(false);
		setBackground(Color.BLACK);
		setVisible(true);
		m.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sterowanie.mysz=!Sterowanie.mysz;
				requestFocus();
			}
		});
		d.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dzwiek.dzwiek=!Dzwiek.dzwiek;
				requestFocus();
			}
		});

		requestFocus();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE || e.getKeyCode()==KeyEvent.VK_F1 || e.getKeyCode()==KeyEvent.VK_SPACE){
					dispose();
				}
			}
		});
		nextLevel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (new File(Gra.prefix+(Gra.level+1)).exists()){
					Gra.level++;
					Gra.zerujPoziom();
					ustawOkna();
				}
				requestFocus();
			}

			
		});
		previusLevel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Gra.level-1>0){
					Gra.level--;
					Gra.zerujPoziom();
				}
				ustawOkna();
				requestFocus();
			}
		});
	}
	private void ustawOkna() {
		setLocation(Okienko.ramka.getX()-Okienko.getSzerokosc(), getY());
		if (getX()<0) {
			setLocation(0, getY());
			Okienko.ramka.setLocation(getWidth(), Okienko.ramka.getY());
		}		
	}
}
