package main.src.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Mahapatra Manas
 */
/**
 * The Class AssignmentModel.
 */
public class AssignmentModel {

    /** The id. */
    String assignmentName,grade,id;
    
    /** The question id. */
    List<String> questionId = new ArrayList<>();

    /**
     * Instantiates a new assignment model.
     */
    public AssignmentModel() {
    }

    /**
     * Instantiates a new assignment model.
     *
     * @param assignmentName the assignment name
     * @param grade the grade
     * @param questionId the question id
     * @param id the id
     */
    public AssignmentModel(String assignmentName, String grade, List<String> questionId,String id) {
        this.assignmentName = assignmentName;
        this.grade = grade;
        this.questionId = questionId;
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

    /**
     * Gets the question id.
     *
     * @return the question id
     */
    public List<String> getQuestionId() {
        return questionId;
    }

    /**
     * Sets the question id.
     *
     * @param questionId the new question id
     */
    public void setQuestionId(List<String> questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets the assignment name.
     *
     * @return the assignment name
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Sets the assignment name.
     *
     * @param assignmentName the new assignment name
     */
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
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

//    public List<String> getQuestions() {
//        return questionId;
//    }
//
//    public void setQuestions(List<String> questions) {
//        this.questionId = questions;
//    }
}
