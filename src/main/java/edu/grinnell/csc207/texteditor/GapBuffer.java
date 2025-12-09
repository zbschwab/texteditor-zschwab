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
        this(16);
    }

    public GapBuffer(int capacity) {
        sz = capacity;
        buffer = new char[sz];
        cur1 = 0;
        cur2 = 16;
    }

    /**
     * inserts ch after cur1.
     * 
     * @param ch
     */
    public void insert(char ch) {
        if ((cur2 - cur1) <= 1) {
            sz = sz * 2;
            char[] newBuffer = new char[sz];

            System.arraycopy(buffer, 0, newBuffer, 0, cur1);
            System.arraycopy(buffer, cur2, newBuffer, newBuffer.length - (buffer.length - cur2),
                    buffer.length - cur2);
            cur2 = newBuffer.length - (buffer.length - cur2);
            buffer = newBuffer;
        }

        buffer[cur1] = ch;
        cur1++;
    }

    /**
     * deletes char by expanding left side of gap
     */
    public void delete() {
        if (cur1 > 0) {
            cur1--;
        }
    }

    public int getCursorPosition() {
        return cur1;
    }

    public void moveLeft() {
        if (cur1 > 0) {
            cur1--;
            cur2--;
            buffer[cur2] = buffer[cur1];
        }
    }

    public void moveRight() {
        if (cur2 < buffer.length) {
            buffer[cur1] = buffer[cur2];
            cur1++;
            cur2++;
        }
    }

    public int getSize() {
        return buffer.length - (cur2 - cur1);
    }

    public char getChar(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException();
        } else if (i < cur1) {
            return buffer[i];
        } else {
            return buffer[i + (cur2 - cur1)];
        }
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
