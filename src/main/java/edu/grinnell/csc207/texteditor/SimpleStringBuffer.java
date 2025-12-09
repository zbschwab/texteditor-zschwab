package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    private String buffer;
    private int cursor;

    public SimpleStringBuffer() {
        buffer = "";
        cursor = 0;
    }

    /**
     * inserts ch after cursor.
     * @param ch
     */
    public void insert(char ch) {
        String preString = buffer.substring(0, cursor);
        String postString = buffer.substring(cursor, buffer.length());
        buffer = preString + String.valueOf(ch) + postString;
        cursor++;
    }

    /**
     * deletes char before cursor.
     */
    public void delete() {
        if (!"".equals(buffer) && (getCursorPosition() > 0)) {
            String preString = buffer.substring(0, cursor-1);
            String postString = buffer.substring(cursor, buffer.length());
            buffer = preString + postString;
            cursor--;
        }
    }

    public int getCursorPosition() {
        return cursor;
    }

    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    public void moveRight() {
        if (cursor < buffer.length()) {
            cursor++;
        }
    }

    public int getSize() {
        return buffer.length();
    }

    public char getChar(int i) {
        return buffer.charAt(i);
    }

    @Override
    public String toString() {
        return buffer;
    }
}
