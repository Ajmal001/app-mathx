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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.util.List;

public class HomepageController {

    @FXML
    void displayAssignments() throws Exception {

        FileInputStream serviceAccount = new FileInputStream("ser515-team4-firebase-adminsdk-vb9rb-ce177cc4dd.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ser515-team4.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db= FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("assignments").document("Assignment1");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
        } else {
            System.out.println("No such document!");
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
