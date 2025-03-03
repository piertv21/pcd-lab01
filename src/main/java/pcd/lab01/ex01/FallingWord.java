package pcd.lab01.ex01;

import org.fusesource.jansi.Ansi.Color;

public class FallingWord implements Runnable {

    private String word;
    private int column;
    private int speed;
    private Screen screen;
    private int maxLines;

    public FallingWord(String word, int column, int speed, int maxLines) {
        this.word = word;
        this.column = column;
        this.speed = speed;
        this.screen = Screen.getInstance();
        this.maxLines = maxLines;
    }

    @Override
    public void run() {
        int line = 0;
        while (line < maxLines) {
            screen.writeStringAt(line, column, Color.GREEN, word);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            screen.writeStringAt(line, column, Color.BLACK, word);
            line++;
        }
        screen.writeStringAt(line, column, Color.GREEN, word); // Stop at bottom
    }
}
