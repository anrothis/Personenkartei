import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ViewForm implements ActionListener{
    
    private PersonenFiler pFiler;
    JFrame frame;
    JPanel panel;
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu file;
    JMenuItem saveMenu, loadMenu, refreshMenuItem;
    JFileChooser fChooser;
    File tempFile;
    ActionListener[] actionListeners;


    ViewForm(PersonenFiler pFiler)
    {
        this.pFiler = pFiler;
        textArea = new JTextArea();
        fChooser = new JFileChooser();
        fChooser.setCurrentDirectory(new File("."));
        fChooser.setFileFilter(new FileNameExtensionFilter("Textdatei", "txt"));
        

        textArea.setOpaque(true);
        textArea.setBounds(0, 0, 400, 600);
        textArea.setEditable(false);
        refreshView(pFiler);

        menuBar = new JMenuBar();
        file = new JMenu("Datei");
        saveMenu = new JMenuItem("Speichern...");
        loadMenu = new JMenuItem("Laden...");
        refreshMenuItem = new JMenuItem("Aktuallisieren");

        saveMenu.addActionListener(this);
        loadMenu.addActionListener(this);
        refreshMenuItem.addActionListener(this);

        file.add(saveMenu);
        file.add(loadMenu);
        file.add(refreshMenuItem);
        menuBar.add(file);

        panel = new JPanel();
        panel.add(textArea);
        panel.setLayout(null);

        frame = new JFrame("Peronen Viewer");
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,600);
        frame.setLocation(1100, 300);
        frame.setVisible(true);
    }

    public void refreshView(PersonenFiler pFiler)
    {
        textArea.setText("Vorname\tNachname\tAlter\n");
        for(Person p : pFiler.getPersonArray())
        {
            textArea.append(p.getVname()+"\t"+p.getNname()+"\t"+p.getAge()+"\n");
        }
    }
    public void actionPerformed(ActionEvent event) {

        if(event.getSource().equals(saveMenu))
        {
            System.out.println("save");
            int response = fChooser.showSaveDialog(null);
    
            if(response == JFileChooser.APPROVE_OPTION)
            {
                tempFile = fChooser.getSelectedFile();
                pFiler.writeToFile(tempFile);
            }
            else
            {
                System.out.println("Speichern abgebrochen");
            }
        }
        else if(event.getSource().equals(loadMenu))
        {
            int response = fChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION)
            {
                tempFile = fChooser.getSelectedFile();
                pFiler.readFromFile(tempFile);
                refreshView(pFiler);
            }
            else
            {
                System.out.println("Laden abgebrochen");
            }
        }
        else if(event.getSource().equals(refreshMenuItem))
        {
            refreshView(pFiler);
        }
        
    }

}
