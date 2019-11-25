package main.src.models;

import java.util.ArrayList;
import java.util.List;

public class AssignmentModel {

    String assignmentName,grade,id;
    List<String> questionId = new ArrayList<>();

    public AssignmentModel() {
    }

    public AssignmentModel(String assignmentName, String grade, List<String> questionId,String id) {
        this.assignmentName = assignmentName;
        this.grade = grade;
        this.questionId = questionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getQuestionId() {
        return questionId;
    }

    public void setQuestionId(List<String> questionId) {
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

//    public List<String> getQuestions() {
//        return questionId;
//    }
//
//    public void setQuestions(List<String> questions) {
//        this.questionId = questions;
//    }
}
