package seismes;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
    private JTextArea output;
    
    private JTextField latitude;
    private JTextField longitude;
    private JTextField distance;
    private JTextField date;
    private JTextField minimalMagnitude;
    
    /**
     * Table de hachage qui contient le validateur pour les différents
     * champs d'entrée.
     */
    private Hashtable<JTextField, Validator> validators;
    
    
    public GUI() {
        validators = new Hashtable<JTextField, Validator>();
    }
    
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.createGUI();
    }
    
    
    public Hashtable<JTextField, Validator> getValidators() {
        return validators;
    }

    
    private void createGUI() {
        final JFrame frame = new JFrame("Séismes bing bang boum boum!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Côté gauche de l'interface
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        // Ajouter les différents filtres
        latitude = addTextField(leftPanel, "Latitude de référence");
        longitude = addTextField(leftPanel, "Longitude de référence");
        distance = addTextField(leftPanel, "Distance");
        date = addTextField(leftPanel, "Date de départ");
        minimalMagnitude = addTextField(leftPanel, "Magitude minimale");
        
        // Créer les validateurs
        validators.put(latitude, new RangeValidator(-90, 90));
        validators.put(longitude, new RangeValidator(-180, 180));
        validators.put(distance, new PositiveDoubleValidator());
        validators.put(date, new DateValidator());
        validators.put(minimalMagnitude, new PositiveDoubleValidator());
        
        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hashtable<JTextField, Validator> validators = getValidators();
                String errorMessage = "";
                boolean hasErrors = false;
                
                for (JTextField tf: validators.keySet()) {
                    Validator v = validators.get(tf);
                    if (!v.isValid(tf.getText())) {
                        errorMessage += tf.getName() + ": " + v.getErrorMessage() + "\n";
                        hasErrors = true;
                    }
                }
                
                if (hasErrors)
                    JOptionPane.showMessageDialog(frame, errorMessage);
            }
        });
        leftPanel.add(searchButton);
        
        
        
        // Côté droit de l'interface
        output = new JTextArea();
        output.setEnabled(false);
        
        // Ajouter le côté gauche et droit à l'application.
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(new JScrollPane(output));
        
        frame.add(splitPane);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    private JTextField addTextField(JPanel panel, String labelText) {
        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField textField = new JTextField(10);
        textField.setName(labelText);
        JLabel label = new JLabel(labelText);
        
        label.setLabelFor(textField);
        innerPanel.add(new JLabel(labelText));
        innerPanel.add(textField);
        panel.add(innerPanel);
        
        return textField;
    }
    
}
