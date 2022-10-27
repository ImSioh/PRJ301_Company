package dal;

import java.util.Random;

public class RandomSQL {

    public static String RandomID() {
        Random random = new Random();

        String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//        int randomInt = random.nextInt(setOfCharacters.length());

        String character = "";
        String randomCharacter = "";

        for (int i = 0; i < 5; i++) {
            character = Character.toString(setOfCharacters.charAt(random.nextInt(setOfCharacters.length())));
            randomCharacter += character;
        }
        return randomCharacter;
    }
    
    public static void main(String[] args) {
        System.out.println(RandomID());
    }
}
