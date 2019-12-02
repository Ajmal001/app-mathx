package main.src.models;

/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Mahapatra Manas
 */
/**
 * The Class QuestionAnsModel.
 */
public class QuestionAnsModel {

    /** The id. */
    String grade,question,ans,id;

    /**
     * Instantiates a new question ans model.
     */
    public QuestionAnsModel() {
    }

    /**
     * Instantiates a new question ans model.
     *
     * @param grade the grade
     * @param question the question
     * @param ans the ans
     * @param id the id
     */
    public QuestionAnsModel(String grade, String question, String ans, String id) {
        this.grade = grade;
        this.question = question;
        this.ans = ans;
        this.id = id;
    }

    /**
     * Gets the grade.
     *
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade.
     *
     * @param grade the new grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Gets the question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question.
     *
     * @param question the new question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the ans.
     *
     * @return the ans
     */
    public String getAns() {
        return ans;
    }

    /**
     * Sets the ans.
     *
     * @param ans the new ans
     */
    public void setAns(String ans) {
        this.ans = ans;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }
}
