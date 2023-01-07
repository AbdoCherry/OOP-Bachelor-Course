package Week11.Script.SerializingObjects;

import java.io.Serializable;

public class MyStudents implements Serializable {

    private int matriculationNo;
    private String firstName, lastName;

    public MyStudents() {

    }

    public MyStudents(int matriculationNo, String firstName, String lastName) {
        this.matriculationNo = matriculationNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void print(){
        System.out.println("\nMatriculation Number: " + matriculationNo);
        System.out.println("Name:\t" + firstName + " " + lastName);
    }

    public int getMatriculationNo() {
        return matriculationNo;
    }

    public void setMatriculationNo(int matriculationNo) {
        this.matriculationNo = matriculationNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
