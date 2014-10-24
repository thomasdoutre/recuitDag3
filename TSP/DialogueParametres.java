import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// Cette classe permet a l'utilisateur de modifier facilement les parametres du probleme

@SuppressWarnings("serial")
public class DialogueParametres extends JDialog {

	private JTextField textTemperature;
	private JTextField textTaux;
	private JTextField textK;
	
	private double temperature;
	private double taux;
	private double K;
	
	private boolean ok;

	private JButton bOk;
	private JButton bCancel;
    
	// Que se passe t il quand on appuie sur ok ?
	public boolean okButton() {
		return this.ok;
	}

	// Recuperer la temperature
	public double getTemperature() {
		return this.temperature;
	}

	public double getTaux() {
		return this.taux;
	}
	
	public double getK() {
		return this.K;
	}


	public DialogueParametres(Frame parent, String titre, double temperature, double taux, double K) {
		super(parent, titre, true);
		this.ok = false;

		this.temperature = temperature;
		this.taux = taux;
		this.K = K;
		
		Container panel = getContentPane();
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		panel.setLayout(grid);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		JLabel label = new JLabel("Parametres du recuit");
		grid.setConstraints(label, constraints);
		panel.add(label);

		// parametre temperature
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		label = new JLabel("Temperature:");
		grid.setConstraints(label, constraints);
		panel.add(label);

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.textTemperature = new JTextField(String.valueOf(temperature), 10);
		grid.setConstraints(this.textTemperature, constraints);
		panel.add(this.textTemperature);

		// position en Y
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		label = new JLabel("Taux");
		grid.setConstraints(label, constraints);
		panel.add(label);

		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.textTaux = new JTextField(String.valueOf(taux), 10);
		grid.setConstraints(this.textTaux, constraints);
		panel.add(this.textTaux);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		label = new JLabel("K");
		grid.setConstraints(label, constraints);
		panel.add(label);

		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.textK = new JTextField(String.valueOf(K), 10);
		grid.setConstraints(this.textK, constraints);
		panel.add(this.textK);

		// bouton ok
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.bOk = new JButton("Ok");
		grid.setConstraints(this.bOk, constraints);
		panel.add(this.bOk);

		// bouton ok
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.bCancel = new JButton("Annuler");
		grid.setConstraints(this.bCancel, constraints);
		panel.add(this.bCancel);

		this.bOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (checkValues()) {
					DialogueParametres.this.ok = true;
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null,
							"Certaines valeurs sont incorrectes");
				}
			}

		});

		this.bCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DialogueParametres.this.ok = false;
				setVisible(false);
			}

		});

	}

	protected boolean checkValues() {
		try {
			this.temperature = Double.parseDouble(this.textTemperature.getText());
			this.taux = Double.parseDouble(this.textTaux.getText());
			this.K = Double.parseDouble(this.textK.getText());
			
			if ((this.temperature < 0) || (this.taux < 0) || (this.K < 0)) {
				return false;
			}

			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
