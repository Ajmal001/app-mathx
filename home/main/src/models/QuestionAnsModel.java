package main.src.models;

public class QuestionAnsModel {

    String grade,question,ans,id;

    public QuestionAnsModel() {
    }

    public QuestionAnsModel(String grade, String question, String ans, String id) {
        this.grade = grade;
        this.question = question;
        this.ans = ans;
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
