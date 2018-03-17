package aston.view.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Graphical UI LabelledSlider
 * 
 * @author Gerard
 * @version 1.0
 * @param <LabelledSlider>
 * @since 30 Mar 2017
 *
 */

public class LabelledSlider extends JComponent {
	
	private static final long serialVersionUID = 1L;
	private String labelString;
   	private JLabel label;
    	private JSlider slider;

	public LabelledSlider(String text, int minValue, int maxValue, int startValue) {
		this.setDoubleBuffered(true);
		label = new JLabel(text + startValue);
		labelString = new String(text);
		slider = new JSlider(minValue, maxValue, startValue);

		// Set slider properties
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new SliderListener());

		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(slider, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder());
	}
	
	public void setMajorTickSpacing(int spacing) {
		  slider.setMajorTickSpacing(spacing);
		  repaint();
	    }

	    public int getValue() {
		  return slider.getValue();
	    }
	
	private class SliderListener implements ChangeListener {
	public void stateChanged(ChangeEvent e) {
	    	    if (!slider.getValueIsAdjusting()) {
	    		  double number = slider.getValue() / 100.0;
	    		  label.setText(labelString + number);
	    	    }
	}
	
	}
  
}
