package replicaSystem.util;

import java.util.*;

public class StudentRecord {
    private int bNumber;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    private Set<String> skills = new HashSet<>();
    static Set<String> newSkill = new HashSet<>();

    public StudentRecord(int bNumber, String firstName, String lastName, Double gpa, String major, List<String> skills) {
        this.bNumber = bNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.major = major;
        this.skills.addAll(skills);
        newSkill.addAll(skills);
    }

    /**
     *
     * @return
     * The following are getter and setter methods for all the properties of StudentRecord.
     */

    public int getbNumber() {
        return bNumber;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skill) {
        this.skills.addAll(skill);
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

    public double getGpa() {
        return gpa;
    }

    /*public void setGpa(double gpa) {
        this.gpa = gpa;
    }*/

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * The following methods are to modify the respective changes.
     * @param name
     */

    public void modifyFirstName(String name){
        setFirstName(name);
    }

    public void modifyMajor(String name){
        setMajor(name);
    }
    public void modifyLastName(String name){
        setLastName(name);
    }

    public void modifySkill(String oldValue, String newValue) {
        List<String> list = new ArrayList<>();
        list.addAll(0, skills);
        //System.out.println(list);

        if(list.contains(oldValue)){
            int i = list.indexOf(oldValue);
            list.add(i, newValue);
            list.remove(oldValue);
        }

        skills.removeAll(skills);
        skills.addAll(list);
    }
}

