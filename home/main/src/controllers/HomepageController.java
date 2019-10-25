package main.src.controllers;

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
import java.util.List;

public class HomepageController {

    @FXML
    void displayAssignments() throws Exception {

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
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = documentNames.get().getDocuments();
        DocumentReference docRef;
        ApiFuture<DocumentSnapshot> documentApi;
        DocumentSnapshot documentData;
        String question;
        for (QueryDocumentSnapshot document : documents) {
            docRef=db.collection("assignments").document(document.getId());
            documentApi = docRef.get();
            documentData=documentApi.get();
            question=documentData.getData().toString();
//            System.out.println(documentData.getData());
            System.out.println(question);
            System.out.println("-----");
        }
    }

    @FXML
    void workspaceAction(ActionEvent actionEvent)
    {
        new MainClass().openWorkSpaceWindow();
//        MainClass.homePageStage.close();
    }
    @FXML
    public void display(ActionEvent event) {
        AlertBox.display();
    }
}
