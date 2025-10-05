package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {

    @Test
    public void insertBackTest() {
        GapBuffer testbuffer = new GapBuffer(16);
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97 + i));
        }

        assertEquals("abcdefghijklmnopqrst", testbuffer.toString());
    }

    @Test
    public void insertMiddleTest() {
        GapBuffer testbuffer = new GapBuffer(16);
        for (int i = 0; i < 5; i++) {
            testbuffer.insert((char) (97 + i));
        }
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.insert('x');

        assertEquals('x', testbuffer.getChar(2));
    }

    @Test
    public void deleteCharTest() {
        GapBuffer testbuffer = new GapBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97 + i));
        }
        testbuffer.delete();

        assertEquals("abcdefghijklmnopqrs", testbuffer.toString());
    }

    @Test
    public void tryDeleteEmptyBufferTest() {
        GapBuffer testbuffer = new GapBuffer();
        testbuffer.delete();

        assertEquals(0, testbuffer.getSize());
    }

    @Test
    public void moveCursorTest() {
        GapBuffer testbuffer = new GapBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97 + i));
        }
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveRight();

        assertEquals(19, testbuffer.getCursorPosition());
    }

    @Test
    public void tryPassBufferFrontTest() {
        GapBuffer testbuffer = new GapBuffer();
        for (int i = 0; i < 5; i++) {
            testbuffer.insert((char) (97 + i));
        }
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();

        assertEquals(0, testbuffer.getCursorPosition());
    }

    @Test
    public void tryPassBufferBackTest() {
        GapBuffer testbuffer = new GapBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97 + i));
        }
        testbuffer.moveRight();

        assertEquals(20, testbuffer.getCursorPosition());
    }

    @Property
    public boolean bufferInsert(@ForAll @IntRange(min = 0, max = 100) int sz) {
        GapBuffer testbuffer = new GapBuffer();
        for (int i = 0; i < sz; i++) {
            testbuffer.insert((char) (97 + (i % 26)));
        }
        return testbuffer.getSize() == sz && testbuffer.toString().length() == sz;
    }
}
