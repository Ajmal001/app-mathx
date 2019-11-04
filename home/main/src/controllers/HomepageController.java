package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author 		 Mehta Ria
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.MainClass;
import java.io.InputStream;
import java.io.FileInputStream;


/**
 * The Class HomepageController.
 */
public class HomepageController  {

    /** The assignment elements. */
    @FXML private javafx.scene.control.ComboBox submittedAssignments;
    @FXML private javafx.scene.control.ComboBox notSubmittedAssignments;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
//        String labelHead=submittedAssignments.getText()+"\n\n\n";
        try{
            displaySubmittedAssignments();
            submittedAssignments.getSelectionModel().selectFirst();
            notSubmittedAssignments.getSelectionModel().selectFirst();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Display assignments.
     */
    @FXML
    private void displaySubmittedAssignments() throws Exception {

        InputStream serviceAccount = new FileInputStream("/Users/riamehta/IdeaProjects/app-mathx/home/main/src/controllers/ser515-team4-firebase-adminsdk-vb9rb-90250893a1.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setDatabaseUrl("https://ser515-team4.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db= FirestoreClient.getFirestore();
//        DocumentReference docRef = db.collection("assignments").document("Assignment1");
//        ApiFuture<DocumentSnapshot> future = docRef.get();
//        DocumentSnapshot document = future.get();
//        if (document.exists()) {
//            System.out.println("Document data: " + document.getData());
//        } else {
//            System.out.println("No such document!");
//        }

        /**
         * Logic for displaying list of assignments - submitted and not submitted
         */

        String userEmail = "karandeep@gmail.com";
        Iterable<DocumentReference> docRefUpcoming  = db.collection("UserAssignmentStatus").document(userEmail).collection("NotSubmitted").listDocuments();
        ApiFuture<DocumentSnapshot> documentApi;
        DocumentSnapshot documentData;

        for(DocumentReference doc :docRefUpcoming){
            documentApi = doc.get();             //Gets reference of document
            documentData=documentApi.get();
            notSubmittedAssignments.getItems().addAll(documentData.getId());
        }

        Iterable<DocumentReference> docRefSolved  = db.collection("UserAssignmentStatus").document(userEmail).collection("Submitted").listDocuments();
        ApiFuture<DocumentSnapshot> documentApiSolved;
        DocumentSnapshot documentDataSolved;

        for(DocumentReference doc :docRefSolved){
            documentApiSolved = doc.get();             //Gets reference of document
            documentDataSolved=documentApiSolved.get();
            submittedAssignments.getItems().addAll(documentDataSolved.getId());
        }
    }

    /**
     * Workspace action.
     *
     * @param actionEvent the action event
     */
    @FXML
    void workspaceAction(ActionEvent actionEvent)
    {
        new MainClass().openWorkSpaceWindow();
//        MainClass.homePageStage.close();
    }
    
    /**
     * Display.
     *
     * @param event the event
     */
    @FXML
    public void display(ActionEvent event) {
        AlertBox.display();
    }
}
