package Week06.Task03;

import java.io.*;
import java.util.*;

public class Department {
    private String depName;
    private String teamLead;
    private Set<Employee> employees;
    private double budget;

    public Department() {
    }

    public Department(String depName, String teamLead, Set<Employee> employees, double budget) {
        this.depName = depName;
        this.teamLead = teamLead;
        this.employees = employees;
        this.budget = budget;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


}
