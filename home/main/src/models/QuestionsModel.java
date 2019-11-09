package main.src.models;

import java.util.HashMap;

public class QuestionsModel {

    String grade;
    HashMap<String,String> queansMap = new HashMap<>();

    public QuestionsModel() {
    }

    public QuestionsModel(String grade, HashMap<String, String> queansMap) {
        this.grade = grade;
        this.queansMap = queansMap;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public HashMap<String, String> getQueansMap() {
        return queansMap;
    }

    public void setQueansMap(HashMap<String, String> queansMap) {
        this.queansMap = queansMap;
    }
}
