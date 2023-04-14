import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private Scanner scan;

    private Cipher currentCipher;
    private String key;
    private String UnencryptedText;
    private String CipheredText;


    App(){
        scan = new Scanner(System.in);
        currentCipher = new CaesarCipher();
    }

    public void run(){
        // load key
        // load texts
        while (true){
            printMenu();
            runMenu();
        }
    }

    private void printMenu(){
        System.out.println("#### Menu ####");
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

    private void runMenu(){
        System.out.print("Enter number of menu item: ");
        int input = getNumberFromInput();

        switch (input){
            case 1:
                switchCipher();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
        }
    }

    private void switchCipher(){
        System.out.println("Pick a Cipher:");
        System.out.println("1. Caesar");
        System.out.println("2. Keyed Caesar");
        System.out.println("3. Vignere");

        int input = getNumberFromInput();

        switch (input){
            case 1:
                currentCipher = new CaesarCipher();
                break;
            case 2:
                currentCipher = new KeyedCaesarCipher();
                break;
            case 3:
                currentCipher = new VignereCipher();
                break;
        }
    }

    private void loadKey(){

    }

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
        return input;
    }
}
