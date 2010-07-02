package seismes;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
    private JTextArea output;
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Séismes bing bang boum boum!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        
        // Côté gauche de l'interface
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        JTextField latField = null;
        JTextField longField = null;
        leftPanel.add(linePanel("Latitude de référence", latField));
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(linePanel("Longitude de référence", longField));

        
        // Côté droit de l'interface
        output = new JTextArea();
        
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(new JScrollPane(output));
        
        frame.add(splitPane);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    private JPanel linePanel(String labelText, JTextField associatedTextField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new JLabel(labelText));
        associatedTextField = new JTextField(10);
        panel.add(associatedTextField);
        return panel;
    }
}
