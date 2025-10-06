package edu.grinnell.csc207.texteditor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Text Editor...");
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        GapBuffer buf = new GapBuffer();

        String path = args[0];
        System.out.format("Loading %s...\n", path);
        if (Files.exists(Paths.get(path))) {
            if (Files.isRegularFile(Paths.get(path))) {
                String fileString = Files.readString(Paths.get(path));
                for (int i = 0; i < fileString.length(); i++) {
                    buf.insert(fileString.charAt(i));
                }
                System.out.println("File read.");
            } else {
                Files.writeString(Paths.get(path), buf.toString());
            }
        } else {
            System.err.println("Usage: <filename> must point to valid file or directory");
            System.exit(1);
        }

        boolean isRunning = true;
        while (isRunning) {
            KeyStroke stroke = screen.readInput();

            if (stroke.getKeyType().equals(KeyType.Escape)) {
                isRunning = false;
            } else if (stroke.getKeyType().equals(KeyType.Character)) {
                buf.insert(stroke.getCharacter());
            } else if (stroke.getKeyType().equals(KeyType.ArrowLeft)) {
                buf.moveLeft();
            } else if (stroke.getKeyType().equals(KeyType.ArrowRight)) {
                buf.moveRight();
            } else if (stroke.getKeyType().equals(KeyType.Backspace)) {
                buf.delete();
            }

            drawBuffer(buf, screen);

        }
        screen.stopScreen();

        Files.writeString(Paths.get(path), buf.toString());
    }

    /**
     * helper method renders the entire buffer to screen and updates display
     * @param buf instance of gap buffer
     * @param screen Lanterna screen in use
     * @throws IOException
     */
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        TerminalPosition pos = new TerminalPosition(buf.getCursorPosition(),0);
        screen.clear();

        for (int i = 0; i < buf.getSize(); i++) {
            screen.setCharacter(i, 0, 
            TextCharacter.fromCharacter(
                buf.getChar(i),
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK)[0]);
        }
        screen.setCursorPosition(pos);
    
        screen.refresh();
    }
}
