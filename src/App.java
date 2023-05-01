import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class representing the application, this handles all user interaction
 * and holds all data that the user may edit such as the key or text
 *
 * @version 1.0
 * @author Rosia E Evans
 */
public class App {
    private Scanner scan;

    private Cipher currentCipher;
    private String key;
    private String unencryptedText;
    private String cipheredText;

    final private String keyFileUrl = "Data/key";

    /**
     * Constructor, instantiates the scanner and a default cipher
     */
    App(){
        scan = new Scanner(System.in);
        currentCipher = new CaesarCipher();
    }

    /**
     * Main method called when program starts
     */
    public void run(){
        loadKey();
        // load texts
        runMenu();
    }

    /**
     * Prints menu before getting user input
     */
    private void printMenu(){
        System.out.println("\n\n\n#### Menu ####");
        System.out.println("1. Select Cipher");
        System.out.println("2. Edit Key");
        System.out.println("3. Display Key");
        System.out.println("4. Load Unencrypted Text");
        System.out.println("5. Save Unencrypted Text");
        System.out.println("6. Display Loaded Unencrypted Text");
        System.out.println("7. Encrypt Unencrypted Text");
        System.out.println("8. Display Ciphered Text");
        System.out.println("9. Save Ciphered Text");
        System.out.println("10. Load Ciphered Text");
        System.out.println("11. Decrypt Ciphered Text");
        System.out.println("12. Exit");
    }

    /**
     * Gets user input and calls necessary method to process
     * and calls requested function
     */
    private void runMenu(){
        while (true){
            printMenu();
            System.out.print("Enter number of menu item: ");
            int input = getNumberFromInput();

            switch (input){
                case 1:
                    switchCipher();
                    break;
                case 2:
                    editKey();
                    break;
                case 3:
                    displayKey();
                case 4:
                    loadUnencryptedText();
                    break;
                case 5:
                    saveUnencryptedText();
                    break;
                case 6:
                    displayUnencryptedText();
                    break;
                case 7:
                    encryptUnencryptedText();
                    break;
                case 8:
                    displayCipheredText();
                    break;
                case 9:
                    loadCipheredText();
                    break;
                case 10:
                    saveCipheredText();
                    break;
                case 11:
                    decryptCipheredText();
                    break;
                case 12:
                    return;
                default:
                    System.out.println(input + " isnt a valid option, please enter something else");
            }
        }
    }

    /**
     * Requests a cipher to switch to from the user, sets that as the
     * current cipher then informs the user of the now current cipher
     */
    private void switchCipher(){
        System.out.println("Pick a Cipher:");
        System.out.println("1. Caesar");
        System.out.println("2. Keyed Caesar");
        System.out.println("3. Vigenere");

        int input = getNumberFromInput();

        switch (input){
            case 1:
                currentCipher = new CaesarCipher();
                break;
            case 2:
                currentCipher = new KeyedCaesarCipher();
                break;
            case 3:
                currentCipher = new VigenereCipher();
                break;
        }

        System.out.println("Current cipher is now " + currentCipher.toString());
        if (!currentCipher.validateKey(key)){
            System.err.println("Warning! Current key does not fit this cipher");
        }
    }

    /**
     * Requests a new key from the user and gives them a note on what key is
     * expected
     */
    private void editKey(){
        System.out.println("Enter new key to use followed by enter: ");
        System.out.println("Note: "+ currentCipher.getKeyHint());
        key = scan.nextLine();

        if (!currentCipher.validateKey(key)){
            System.err.println("Warning! Given key does not fit current cipher");
        }
    }

    /**
     * Displays key
     */
    private void displayKey(){
        System.out.print("Key is: ");
        System.out.println(key);
    }

    /**
     * Displays Unencrypted Text
     */
    private void displayUnencryptedText(){
        System.out.println("Text is: ");
        System.out.println(unencryptedText);
    }

    /**
     * Displays Ciphered Text
     */
    private void displayCipheredText(){
        System.out.println("Text is: ");
        System.out.println(cipheredText);
    }

    /**
     * Calls current ciphers encrypt method on a string, will give the user an error if
     * the key is invalid for the current cipher
     */
    private void encryptUnencryptedText(){
        try{
            cipheredText = currentCipher.encryptString(key, unencryptedText);
            System.out.println("Text encrypted, result below: ");
            System.out.println(cipheredText);
        }catch (NumberFormatException e){
            System.out.println("Unable to encrypt text, given key is invalid, please note given key hint when editing key");
        }
    }

    /**
     * Loads text file from a filepath taken from the user, will first convert to "plain text"
     * and warn a user if any grammar or numbers are removed from the text.
     *
     * If an invalid file is given this will give the user the choice of stopping the action
     * or re-inputting a new url
     */
    private void loadUnencryptedText(){

        unencryptedText = loadFile();

        if (unencryptedText.matches(".*[0-9,.!?\"].*")) {
            System.out.println("Warning: this text contains numbers or grammar, these will be removed before use");
        }
        unencryptedText = convertToPlainText(unencryptedText);
    }

    private void saveUnencryptedText(){
        saveFile(unencryptedText);
    }

    /**
     * Loads text file from a filepath taken from the user, will first convert to "plain text"
     * and warn a user if any grammar or numbers are removed from the text.
     *
     * If an invalid file is given this will give the user the choice of stopping the action
     * or re-inputting a new url
     */
    private void loadCipheredText(){
        cipheredText = loadFile();

        if (cipheredText.matches(".*[0-9,.!?\"].*")) {
            System.out.println("Warning: this text contains numbers or grammar, these will be removed before use");
        }
        cipheredText = convertToPlainText(cipheredText);
    }

    private void saveCipheredText(){
        saveFile(cipheredText);
    }

    /**
     * Loads key from a preset key file
     */
    private void loadKey(){
        try {
            key = getFilesContents(keyFileUrl);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }


    /**
     * Calls current ciphers decrypt method on a string, will give the user an error if
     * the key is invalid for the current cipher
     */
    private void decryptCipheredText(){
        try{
            unencryptedText = currentCipher.decryptString(key, cipheredText);
            System.out.println("Text decrypted, result below: ");
            System.out.println(unencryptedText);
        }catch (NumberFormatException e){
            System.out.println("Unable to decrypt text, given key is invalid, please not given key hint when editing key");
        }
    }


    /**
     * Removes grammar and numbers from a given text and converts to upper case
     * @param text Text to alter
     * @return Text with alterations
     */
    private String convertToPlainText(String text){
        text = text.toUpperCase();
        return text.replaceAll("[0-9,.!?\" ]", "");
    }

    /**
     * Reads the full contents of a file
     * @param url Url of file to read
     * @return String of all file contents
     * @throws FileNotFoundException thrown when given file cannot be located
     */
    private String getFilesContents(String url) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(url));
        StringBuilder stringBuilder = new StringBuilder();
        while (scan.hasNext()){
            stringBuilder.append(scan.nextLine());
        }
        return stringBuilder.toString();
    }

    private void writeStringToFile(String data, String url) throws FileNotFoundException{
        PrintWriter printWriter = new PrintWriter(url);
        printWriter.print(data);
        printWriter.close();
    }

    /**
     * Will request a file from a user and attempt to return its contents as a string, on an invalid file
     * this will give the user an option of re-entering the file or cancelling the action
     * @return String taken from file
     */
    private String loadFile(){
        while (true) {
            try {
                System.out.print("Please enter the path of the file to load: ");
                String fileUrl = scan.nextLine();
                return getFilesContents(fileUrl);

            } catch (FileNotFoundException e) {
                System.out.println("File not found, please pick option:");
                System.out.println("1. Re-enter path");
                System.out.println("2. Cancel action");

                switch (getNumberFromInput()) {
                    case 1: // todo: what if they put something other than 1 or 2
                        break;
                    case 2:
                        return "";
                }
            }
        }
    }

    private void saveFile(String data){
        while (true){
            try{
                System.out.print("Please enter the path of the file to load: ");
                String fileUrl = scan.nextLine();
                writeStringToFile(data, fileUrl);
                return;
            }
            catch (FileNotFoundException e){
                System.out.println("Path invalid, please pick option:");
                System.out.println("1. Re-enter path");
                System.out.println("2. Cancel action");

                switch (getNumberFromInput()) {
                    case 1: // todo: what if they put something other than 1 or 2... again
                        break;
                    case 2:
                        return;
                }
            }
        }

    }

    /**
     * Reads the latest user input, if something other than a numbmer is given it
     * will request another input that is a number
     * @return Int taken from user
     */
    private int getNumberFromInput(){
        int input;
        while (true) {
            try {
                input = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Please Enter a number");
            }
        }
        scan.nextLine(); // clear buffer to next return
        return input;
    }
}