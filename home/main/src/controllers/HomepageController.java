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
import java.util.ArrayList;
import java.util.List;


/**
 * The Class HomepageController.
 */
public class HomepageController  {

    /** The submitted assignments. */
    @FXML private javafx.scene.control.ComboBox submittedAssignments;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
//        String labelHead=submittedAssignments.getText()+"\n\n\n";
        try{
            submittedAssignments.getItems().addAll(displayAssignments());
            submittedAssignments.getSelectionModel().selectFirst();
//            JOptionPane.showMessageDialog(null,labelHead+labelContent);
//            submittedAssignments.setVisible(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Display assignments.
     *
     * @return the array list
     * @throws Exception the exception
     */
    @FXML
    ArrayList<String> displayAssignments() throws Exception {

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

        ApiFuture<QuerySnapshot> documentNames = db.collection("assignments").get();
        List<QueryDocumentSnapshot> documents = documentNames.get().getDocuments();
        DocumentReference docRef;
        ApiFuture<DocumentSnapshot> documentApi;
        DocumentSnapshot documentData;
        ArrayList<String> assignmentNum = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            docRef=db.collection("assignments").document(document.getId());
            documentApi = docRef.get();
            documentData=documentApi.get();
            assignmentNum.add(documentData.getId()); //documentData.getId() gets the name of the Document
//            question=documentData.getData().toString();
        }
        return assignmentNum;
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
