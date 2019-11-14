package main.src.models;


/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author 		 Mahapatra Manas
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */
public class TeacherSignUpModel {

    /** The grade. */
    String name,photo,summary,userName,password,address,phoneNo,speciality,grade,id;

    /**
     * Instantiates a new teacher sign up model.
     */
    public TeacherSignUpModel(){

    }

    /**
     * Instantiates a new teacher sign up model.
     *
     * @param name the name
     * @param photo the photo
     * @param summary the summary
     * @param userName the user name
     * @param password the password
     * @param address the address
     * @param phoneNo the phone no
     * @param speciality the speciality
     */
    public TeacherSignUpModel(String name, String photo, String summary, String userName, String password, String address, String phoneNo, String speciality,String grade) {
        this.name = name;
        this.photo = photo;
        this.summary = summary;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
        this.speciality = speciality;
        this.grade = grade;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets the photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the photo.
     *
     * @param photo the new photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets the summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary.
     *
     * @param summary the new summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone no.
     *
     * @return the phone no
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the phone no.
     *
     * @param phoneNo the new phone no
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Gets the speciality.
     *
     * @return the speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * Sets the speciality.
     *
     * @param speciality the new speciality
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
