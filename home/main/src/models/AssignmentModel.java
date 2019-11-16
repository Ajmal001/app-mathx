package main.src.models;

import java.util.ArrayList;
import java.util.List;

public class AssignmentModel {

    String assignmentName,grade;
    List<String> questionId = new ArrayList<>();

    public AssignmentModel() {
    }

    public AssignmentModel(String assignmentName, String grade, List<String> questionId) {
        this.assignmentName = assignmentName;
        this.grade = grade;
        this.questionId = questionId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<String> getQuestions() {
        return questionId;
    }

    public void setQuestions(List<String> questions) {
        this.questionId = questions;
    }
}
