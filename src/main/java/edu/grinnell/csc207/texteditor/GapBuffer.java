package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private char[] buffer;
    private int sz;
    private int cur1;
    private int cur2;

    public GapBuffer() {
        this.sz = 16;
    }

    public GapBuffer(int capacity) {
        sz = capacity;
        buffer = new char[sz];
        cur1 = 0;
        cur2 = 15;
    }

    public void insert(char ch) {
        if (cur1 >= cur2) {
            
            sz = sz * 2;
            char[] newBuffer = new char[sz];

            System.arraycopy(buffer, 0, newBuffer, 0, cur1);
            System.arraycopy(buffer, 0, newBuffer, cur2, buffer.length - cur2);
            cur2 = cur1 + sz;
            buffer = newBuffer;
        }

        buffer[cur1] = ch;
        cur1++;
    }

    public void delete() {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public int getCursorPosition() {
        throw new UnsupportedOperationException("Unimplemented method 'getCursorPosition'");
    }

    public void moveLeft() {
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    public void moveRight() {
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }

    public int getSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    public char getChar(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'getChar'");
    }

    @Override
    public String toString() {
        String stringBuffer = "";
        for (int i = 0; i < cur1; i++) {
            stringBuffer = stringBuffer + String.valueOf(buffer[i]);
        } 
        for (int i = cur2; i < buffer.length; i++) {
            stringBuffer = stringBuffer + String.valueOf(buffer[i]);
        } 
        return stringBuffer;
    }
}
