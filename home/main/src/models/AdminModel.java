package main.src.models;

/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author 		 Sheran Dass
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/24/2019
 */
public class AdminModel {
    String name;
    String userName;
    String password;
    String address;
    String id;
    public AdminModel(){}
    /**
     * Instantiates a new teacher sign up model.
     *
     * @param name the name
     * @param userName the user name
     * @param password the password
     * @param address the address
     */
    public AdminModel(String name, String userName, String password, String address, String id) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.id = id;
    }
    public String getId(){
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
    public String getName(){return name;}
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getUserName(){return userName;}
    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword(){return password;}
    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
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


}

