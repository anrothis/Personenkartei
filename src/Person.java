
public class Person {
    
    private String vname, nname, age;

    public Person() {}

    public Person(String vname, String nname, String age) {
        this.vname = vname;
        this.nname = nname;
        this.age = age;
    }
    public void setName(String vname, String nname) {
        this.vname = vname;
        this.nname = nname;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return this.age;
    }
    public String getVname() {
        return this.vname;
    }
    public String getNname() {
        return this.nname;
    }
    public String getPersonAsSting() {
        String person = this.vname + "," + this.nname + "," + this.age;
        return person;
    }
    
}

