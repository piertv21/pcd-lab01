package pcd.lab01.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// java -cp .\target\pcd-lab-01-1.0-SNAPSHOT.jar pcd.lab01.ex01.TestScreen

public class TestFallingWords {

    public static void main(String[] args) {

        int MAX_LINES = 20; // Screen height
        
        Screen screen = Screen.getInstance();
        screen.clear();

        String sentence = "This is a simple sentence with words ready to fall";
        List<AuxLib.WordPos> words = AuxLib.getWordsPos(sentence);
        
        Random rand = new Random();

        List<Thread> threads = new ArrayList<>();
        for (AuxLib.WordPos wordPos : words) {
            int speed = rand.nextInt(200) + 100; // Random speed
            Thread t = new Thread(new FallingWord(wordPos.word(), wordPos.pos() + 1, speed, MAX_LINES));
            threads.add(t);
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
