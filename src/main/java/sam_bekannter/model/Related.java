package sam_bekannter.model;

import java.io.*;


public class Related implements Serializable {
    private String name;
    private String age;
    private String relation;
    private String residence;
    private String job;
    private String holiday;


    public Related(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getRelation() {
        return relation;
    }

    public String getResidence() {
        return residence;
    }

    public String getJob() {
        return job;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public boolean isNameValid(){
        return (this.name != null && !this.name.isEmpty());
    }
    public boolean isAgeValid(){
        return (this.age != null && !this.age.isEmpty());
    }
    public boolean isRelationValid(){
        return (this.relation != null && !this.relation.isEmpty());
    }
    public boolean isResidenceValid(){
        return (this.residence != null && !this.residence.isEmpty());
    }
    public boolean isJobValid(){
        return (this.job != null && !this.job.isEmpty());
    }
    public boolean isHolidayValid(){
        return (this.holiday != null && !this.holiday.isEmpty());
    }
}
