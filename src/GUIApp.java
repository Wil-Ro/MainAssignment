import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.scene.control.ComboBox;

public class GUIApp extends Application {

    Cipher currentCipher;
    Scene mainScene;
    Stage mainStage;


    ComboBox<CipherOptions> cipherComboBox;
    TextArea encryptBox;
    TextArea decryptBox;
    TextField keyBox;
    Button encryptButton;
    Button decryptButton;
    Label hintLabel;
    Label errorLabel;
    Button saveEncryptButton;
    Button loadEncryptButton;
    Button saveDecryptButton;
    Button loadDecryptButton;


    /**
     *  Loads the programs fxml file into the stage and sets up its properties
     *  Additionally initialises the class
     * @param stage Stage to display content in
     */
    public void start(Stage stage){
        mainStage = stage;

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("MainStage.fxml"));
        }
        catch (IOException e){
            System.out.println("Failed to load main scene for GUI");
            return;
        }
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.setTitle("CS12320 Cipher Program");
        mainStage.getIcons().add(new Image("icon.png"));
        mainStage.setResizable(false);
        mainStage.show();

        locateComponents();

        initialiseComponents();

    }

    /**
     * Private method that locates components manually defined in the programs fxml file
     */
    private void locateComponents(){
        cipherComboBox = ((ComboBox<CipherOptions>)mainScene.lookup("#cipherComboBox"));
        encryptBox = ((TextArea)mainScene.lookup("#encryptBox"));
        decryptBox = ((TextArea)mainScene.lookup("#decryptBox"));
        keyBox = ((TextField)mainScene.lookup("#keyBox"));
        encryptButton = ((Button)mainScene.lookup("#encryptButton"));
        decryptButton = ((Button)mainScene.lookup("#decryptButton"));
        hintLabel = ((Label)mainScene.lookup("#hintLabel"));
        errorLabel = ((Label)mainScene.lookup("#errorLabel"));
        saveEncryptButton = ((Button)mainScene.lookup("#saveEncryptButton"));
        loadEncryptButton = ((Button)mainScene.lookup("#loadEncryptButton"));
        saveDecryptButton = ((Button)mainScene.lookup("#saveDecryptButton"));
        loadDecryptButton = ((Button)mainScene.lookup("#loadDecryptButton"));
    }

    /**
     * Sets components of the GUI into their default state
     */
    private void initialiseComponents(){
        cipherComboBox.getItems().addAll(CipherOptions.Caesar, CipherOptions.KeyedCaesar, CipherOptions.Vigenere);
        cipherComboBox.setValue(cipherComboBox.getItems().get(0));
        cipherComboBox.setOnAction(e -> this.updateCipherChoice());

        encryptButton.setOnAction(e -> this.encrypt());
        decryptButton.setOnAction(e -> this.decrypt());

        saveEncryptButton.setOnAction(e -> this.saveStringFromTextBox(encryptBox));
        loadEncryptButton.setOnAction(e -> this.loadStringIntoTextBox(encryptBox));
        saveDecryptButton.setOnAction(e -> this.saveStringFromTextBox(decryptBox));
        loadDecryptButton.setOnAction(e -> this.loadStringIntoTextBox(decryptBox));

        updateCipherChoice();
    }

    /**
     * Reads cipher ComboBox's value, sets the currentCipher accordingly and updates the hint text
     */
    public void updateCipherChoice(){
        switch (cipherComboBox.getValue()){
            case Caesar:
                currentCipher = new CaesarCipher();
                break;
            case KeyedCaesar:
                currentCipher = new KeyedCaesarCipher();
                break;
            case Vigenere:
                currentCipher = new VigenereCipher();
                break;
        }

        hintLabel.setText("Key Hint: " + currentCipher.getKeyHint());
    }

    /**
     * Run when encrypt button is hit, encrypts text
     */
    public void encrypt(){
        if (!isCurrentKeyValid()){
            errorLabel.setText("Current key is invalid, check key hint");
            return;
        }

        String key = keyBox.getText();
        String text = convertToPlainText(encryptBox.getText());

        currentCipher.setKey(key);
        decryptBox.setText(currentCipher.encryptString(text));

        errorLabel.setText("");
    }

    /**
     * Run when decrypt button is hit, decrypts text
     */
    public void decrypt(){
        if (!isCurrentKeyValid()){
            errorLabel.setText("Current key is invalid, check key hint");
            return;
        }

        String key = keyBox.getText();
        String text = convertToPlainText(decryptBox.getText());

        currentCipher.setKey(key);
        encryptBox.setText(currentCipher.decryptString(text));

        errorLabel.setText("");
    }

    /**
     * validates current key in keyBox
     * @return
     */
    public boolean isCurrentKeyValid(){
        if (!currentCipher.validateKey(keyBox.getText())){
            return false;
        }
        return true;
    }

    /**
     * Requests a file from the user, validates the file then loads a string from it into the given text box
     * @param box Box to load string into
     */
    public void loadStringIntoTextBox(TextArea box){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        fileChooser.setTitle("Select a file to load");
        File file = fileChooser.showOpenDialog(mainStage);

        if (file == null){
            return;
        }

        Scanner scan;
        StringBuilder stringBuilder;
        try {
            scan = new Scanner(file);
            stringBuilder = new StringBuilder();
            while (scan.hasNext()){
                stringBuilder.append(scan.nextLine());
            }
        }
        catch (FileNotFoundException e){
            errorLabel.setText("Unable to load given file");
            return;
        }

        box.setText(stringBuilder.toString());
    }

    /**
     * Requests a file from the user, validates the file then saves a string from the given text box into it
     * @param box Box to load string into
     */
    public void saveStringFromTextBox(TextArea box){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to save to");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File file = fileChooser.showSaveDialog(mainStage);

        if (file == null){
            return;
        }


        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println(box.getText());
            printWriter.close();
        }
        catch (FileNotFoundException e){
            errorLabel.setText("Unable to write to given file");
            return;
        }
    }

    /**
     * Converts given text to uppercase, removing all punctuation and numbers
     * @param text Text to alter
     * @return Altered version of text
     */
    private String convertToPlainText(String text){
        text = text.toUpperCase();
        return text.replaceAll("[0-9,.!?\" ]", "");
    }

    /**
     * Runs Code for GUI app
     */
    public static void run() {
        launch("");
    }

}