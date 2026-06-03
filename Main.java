import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String wordList =  "src\\word.txt";
        ArrayList<String> words = new ArrayList<>();

         try(BufferedReader reader = new BufferedReader(new FileReader(wordList))){
             String line;
             while((line = reader.readLine()) != null){
                 words.add(line.trim());
             }
         }
         catch(FileNotFoundException e){
             System.out.println("File not found");
         }
         catch (IOException e){
             System.out.println("Something went wrong");
         }
        Random random = new Random();
         int randomNum = random.nextInt(words.size());

        String word = words.get(randomNum).toUpperCase();
        //Any updates in word file is converted to uppercase to match user input conversion
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }

        System.out.println("Welcome to Java Hangman");
        System.out.println("All words are related to tech and discipline");
        System.out.print("Word: ");
        for(char wordDisplay : wordState){
            System.out.print(wordDisplay + " ");
        }


        while(wrongGuesses < 5){
            System.out.print("\nEnter guess: ");
            char guess = scanner.next().toUpperCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                System.out.println("\nCorrect guess");
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }
                System.out.print("Word: ");

                for (char wordDisplay : wordState) {
                    System.out.print(wordDisplay + " ");
                }

                if(!(wordState.contains('_'))){
                    System.out.println("\nYou Win!");
                    System.out.println("The word was "+ word);
                    break;
                }

            } else {
                System.out.println("\nWrong guess");
                System.out.println(getHangManArt(wrongGuesses));
                if(wrongGuesses == 4){
                    System.out.println("You lose!");
                    System.out.println("The word was " + word);
                }
                wrongGuesses++;
            }
        }

        scanner.close();
    }

    static String getHangManArt(int wrongGuess){
        return switch(wrongGuess) {
            case 0 -> """
          _______
          |/    |
          |     O
          |
          |
          |
          =========
          """;
            case 1 -> """
          _______
          |/    |
          |     O
          |    /
          |
          |
          =========
          """;
            case 2 -> """
          _______
          |/    |
          |     O
          |    /|\\
          |
          |
          =========
          """;
            case 3 -> """
          _______
          |/    |
          |     O
          |    /|\\
          |    /
          |
          =========
          """;
            case 4 -> """
          _______
          |/    |
          |     O
          |    /|\\
          |    / \\
          |
          =========
          GAME OVER!
          """;
            default -> "";
        };
    }
}
