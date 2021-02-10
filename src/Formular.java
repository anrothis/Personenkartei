import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;

public class Formular implements ActionListener {
    
    PersonenFiler personList;
    
    JFrame frame;
    JPanel panel;
    JButton addPersonButton, endInpuButton;
    JTextField vNameTextField, nNameTextField, ageTextField;
    JFileChooser fChooser;
    File tempFile;
    JMenuBar menuBar;
    JMenu file;
    JMenuItem saveMenu, loadMenu, clearFormMenu, showDataMenu;
    ViewForm view;
    
    public Formular()
    {
        personList = new PersonenFiler();
        fChooser = new JFileChooser();
        fChooser.setCurrentDirectory(new File("."));
        fChooser.setFileFilter(new FileNameExtensionFilter("Textdatei", "txt"));
        
        vNameTextField = new JTextField("Vorname");
        nNameTextField = new JTextField("Nachname");
        ageTextField = new JTextField("Alter");
        
        vNameTextField.setBounds(10,10,200,30);
        nNameTextField.setBounds(10,50,200,30);
        ageTextField.setBounds(10,90,200,30);

        addPersonButton = new JButton("Person hinzufügen");
        endInpuButton = new JButton("Eingabe beenden");
    
        addPersonButton.addActionListener(this);
        endInpuButton.addActionListener(this);

        addPersonButton.setBounds(220, 10, 200, 45);
        endInpuButton.setBounds(220, 120-45, 200, 45);
        
        menuBar = new JMenuBar();
        file = new JMenu("Datei");
        saveMenu = new JMenuItem("Speichern...");
        loadMenu = new JMenuItem("Laden...");
        clearFormMenu = new JMenuItem("Eingabe Löschen");
        showDataMenu = new JMenuItem("Personen anzeigen...");

        saveMenu.addActionListener(this);
        loadMenu.addActionListener(this);
        clearFormMenu.addActionListener(this);
        showDataMenu.addActionListener(this);

        file.add(saveMenu);
        file.add(loadMenu);
        file.addSeparator();
        // file.add(showDataMenu);
        // file.addSeparator();
        file.add(clearFormMenu);
        menuBar.add(file);
        menuBar.add(showDataMenu);

        panel = new JPanel();
        panel.add(vNameTextField);
        panel.add(nNameTextField);
        panel.add(ageTextField);
        panel.add(addPersonButton);
        panel.add(endInpuButton);
        panel.setLayout(null);

        frame = new JFrame("Personenliste");
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 200);
        frame.setLocation(600, 300);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        boolean unsavedChanges = false;
        
        if (event.getActionCommand().equals("Person hinzufügen"))
        {
            personList.add(new Person(vNameTextField.getText(), nNameTextField.getText(), ageTextField.getText()));            
            try {
                view.refreshView(personList);
            } catch (Exception e) {}
            System.out.println(vNameTextField.getText() + "," + nNameTextField.getText() + "," + ageTextField.getText() + " added!");
            unsavedChanges = true;
        }
        else if (event.getActionCommand().equals("Eingabe beenden"))
        {
            if(unsavedChanges)
            {
                System.out.println("Unsaved Changes lost!");
            }
            System.exit(0);
        }
        else if (event.getActionCommand().equals("Speichern..."))
        {
            int response = fChooser.showSaveDialog(null);
    
            if(response == JFileChooser.APPROVE_OPTION)
            {
                tempFile = fChooser.getSelectedFile();
                personList.writeToFile(tempFile);
                unsavedChanges = false;
            }
            else
            {
                System.out.println("Speichern abgebrochen");
            }
        }
        else if (event.getActionCommand().equals("Laden..."))
        {
            int response = fChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION)
            {
                tempFile = fChooser.getSelectedFile();
                personList.readFromFile(tempFile);
            }
            else
            {
                System.out.println("Laden abgebrochen");
            }

        }
        else if (event.getActionCommand().equals("Eingabe Löschen"))
        {
            vNameTextField.setText("");
            nNameTextField.setText("");
            ageTextField.setText("");
        }
        else if (event.getActionCommand().equals("Personen anzeigen..."))
        {
            view = new ViewForm(personList);
        }
        else if (event.getActionCommand().equals("Eingabe Löschen"))
        {

        }
    }
}
