import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class PersonenFiler {

    private ArrayList<Person> personenliste;

    public PersonenFiler() {
        personenliste = new ArrayList<Person>();
    }

    public void add(Person person){
        personenliste.add(person);
    }
    public void addByString(String vName, String nName, String age)
    {
        personenliste.add(new Person(vName, nName, age));
    }
    public ArrayList<Person> getPersonArray() {

        return this.personenliste;

    }
    public String getPersonAsString() {
        return personenliste.toString();
    }
    public void deletePerson(int position) {
        this.personenliste.remove(position);
    }
    public void writeToFile(File file) 
    {
        try {
            if(file.createNewFile())
            {
                System.out.println("New File created: " + file.getAbsolutePath());
            }
            else
            {
                System.out.println("File already exists --> gets overwritten!");
            }
            FileWriter fWriter = new FileWriter(file);
            for(Person p : this.personenliste)
            {
                fWriter.write(p.getPersonAsSting() + "\n");
            }
            fWriter.close();
            System.out.println("Written!");

        } catch (IOException e) {
            System.err.println("Can't Write to File!");
            e.printStackTrace();
        }

    }
    public void readFromFile(File file)
    {
        Person tempPerson;
        try {
            Scanner fReader = new Scanner(file);
            while (fReader.hasNextLine()) {
                String line = fReader.nextLine();
                String[] data = line.strip().split(",");
                tempPerson = new Person(data[0], data[1], data[2]);
                personenliste.add(tempPerson);
            }
            fReader.close();
            System.out.println("File read!");
        } 
        catch (FileNotFoundException e) {
            System.err.println("File not found!");
            e.printStackTrace();
        }
    }
}
