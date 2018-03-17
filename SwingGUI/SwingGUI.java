package aston.view.gui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import aston.simulation.Simulation;
import aston.station.Station;
import aston.resources.Config;

/**
 * Graphical UI
 * 
 * @author Ollie, Gerard
 * @version 1.5
 * @since 8 Mar 2017
 *
 */

public class GUI {
	private JFrame mainFrame;
	private Simulation s;
	private LabelledSlider pSlider;
	private LabelledSlider qSlider;
	
	private JTextField priceEntry;
	private JTextField timeEntry;
	private JCheckBox allowTrucks;
	private JRadioButton pRadio1;
	private JRadioButton pRadio2;
	private JRadioButton pRadio3;
	private JRadioButton tRadio1;
	private JRadioButton tRadio2;
	private JRadioButton tRadio3;
	
	//Default values for parameters.
	private int max = 5; //Maximum probability of 0.5.
	private int min = 1; //Probability cannot go below 0.1.
	private int defaultValue = 1; //Default probability value.

	
	public GUI() {
		
		//this.s = s;
		final String createFrameString = "CreateFrame";

		//Level 1: Created necessary components.
		pSlider = new LabelledSlider("p: ", min, max, defaultValue);
		qSlider = new LabelledSlider("q: ", min, max, defaultValue);
		final JLabel priceLabel = new JLabel ("Price of Gallon: £");
		final JLabel timeLabel = new JLabel ("Running Time: ");
		priceEntry = new JTextField (9);
		timeEntry = new JTextField(9);
		allowTrucks = new JCheckBox("Include Trucks?");
		
		final JButton configButton = new JButton("Configure");
		final JButton closeButton = new JButton("Close");
		final JLabel pumpLabel = new JLabel("Pumps: ");
		pRadio1 = new JRadioButton("Pump 1");
		pRadio2 = new JRadioButton("Pump 2");
		pRadio3 = new JRadioButton("Pump 4");
		
		final JLabel tillLabel = new JLabel("Till: ");
		tRadio1 = new JRadioButton("Till 1");
		tRadio2 = new JRadioButton("Till 2");
		tRadio3 = new JRadioButton("Till 4");
		
		final ButtonGroup group1 = new ButtonGroup();
		group1.add(pRadio1);
		group1.add(pRadio2);
		group1.add(pRadio3);
		
		final ButtonGroup group2 = new ButtonGroup();
		group2.add(tRadio1);
		group2.add(tRadio2);
		group2.add(tRadio3);
		
		configButton.addActionListener((java.awt.event.ActionEvent event) -> saveConfig());
		
		//Level 2: Set properties.
		priceEntry.setEditable(true);
		timeEntry.setEditable(true);
		
		//Level 3: Create containers (panels).
		mainFrame = new JFrame("Petrol-Simulation");
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		closeButton.putClientProperty(createFrameString, mainFrame);
		mainFrame.getContentPane().setLayout(new BorderLayout());
		((JPanel)mainFrame.getContentPane()).setBorder(new EmptyBorder(3, 3, 3, 3));
		
		//Upper half of the GUI
		JPanel slidersPanel = new JPanel();
		JPanel pricePanel = new JPanel();
		JPanel timePanel = new JPanel();
		JPanel priceTime = new JPanel();
		JPanel entryPanel = new JPanel();
		JPanel topPanel = new JPanel();
		//Lower half of the GUI
		JPanel pumpPanel = new JPanel();
		JPanel tillPanel = new JPanel();
		JPanel radioPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		//Level 4: Specify layout managers for panels.
		priceLabel.setLayout(new FlowLayout());
		timeLabel.setLayout(new FlowLayout());
		topPanel.setLayout(new BorderLayout());
		priceTime.setLayout(new BorderLayout());
		pumpLabel.setLayout(new FlowLayout());
		radioPanel.setLayout(new BorderLayout());
		pricePanel.setBorder(BorderFactory.createEtchedBorder());
		timePanel.setBorder(BorderFactory.createEtchedBorder());
		
		//Level 5: Add components to panels
		slidersPanel.add(pSlider);
		slidersPanel.add(qSlider);
		
		pricePanel.add(priceLabel);
		pricePanel.add(priceEntry);
		
		timePanel.add(timeLabel);
		timePanel.add(timeEntry);
		
		priceTime.add(pricePanel, BorderLayout.WEST);
		priceTime.add(timePanel, BorderLayout.EAST);
		
		entryPanel.add(priceTime);
		
		topPanel.add(slidersPanel, BorderLayout.NORTH);
		topPanel.add(entryPanel, BorderLayout.CENTER);
		topPanel.add(allowTrucks, BorderLayout.SOUTH);
		
		pumpPanel.add(pumpLabel);
		pumpPanel.add(pRadio1);
		pumpPanel.add(pRadio2);
		pumpPanel.add(pRadio3);
		
		tillPanel.add(tillLabel);
		tillPanel.add(tRadio1);
		tillPanel.add(tRadio2);
		tillPanel.add(tRadio3);
		
		radioPanel.add(pumpPanel, BorderLayout.NORTH);
		radioPanel.add(tillPanel, BorderLayout.SOUTH);
		
		centerPanel.add(radioPanel);
		
		buttonPanel.add(configButton);
		buttonPanel.add(closeButton);
		bottomPanel.add(buttonPanel);
			
		mainFrame.add(topPanel, BorderLayout.NORTH);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
		mainFrame.add(bottomPanel, BorderLayout.SOUTH);
	
		
		//Level 6: Add listeners.
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		
		closeButton.addActionListener((java.awt.event.ActionEvent event) -> exitApp());
		
		priceEntry.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE) || (c == '.'))) {
		        e.consume();
		      }
		    }
		  });
		
		timeEntry.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE) || (c == '.')))  {
		        e.consume();
		      }
		    }
		  });
		
		//Level 7: Display GUI.
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
		private void exitApp() {
			// Display confirmation dialog before exiting application
			int response = JOptionPane.showConfirmDialog(mainFrame, 
					"Do you really want to quit?",
					"Quit?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		
		private void saveConfig() {
			Config.set("pricePerGallon", Double.parseDouble(priceEntry.getText()));
			Config.set("totalTicks", Double.parseDouble(timeEntry.getText()));
			Config.set("p", new Double(pSlider.getValue() / 100.0));
			Config.set("q", new Double(qSlider.getValue() / 100.0));
			Config.set("allowTrucks", new Double(allowTrucks.isSelected()?1.0:0.0));
			Double pumpCount = 0.0;
			if(pRadio1.isSelected()) {
				pumpCount = 1.0;
			} else if (pRadio2.isSelected()) {
				pumpCount = 2.0;
			} else if (pRadio3.isSelected()) {
				pumpCount = 4.0;
			}
			Config.set("pumpCount", pumpCount);
			Double tillCount = 0.0;
			if(tRadio1.isSelected()) {
				tillCount = 1.0;
			} else if (tRadio2.isSelected()) {
				tillCount = 2.0;
			} else if (tRadio3.isSelected()) {
				tillCount = 4.0;
			}
			Config.set("tillCount", tillCount);
			Simulation.station = Station.getInstance();
		}
}
