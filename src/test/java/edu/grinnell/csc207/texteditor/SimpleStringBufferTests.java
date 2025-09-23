package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleStringBufferTests {
    
    @Test
    public void insertAtBackTest() {
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97+i));
        }

        assertEquals("abcdefghijklmnopqrs", testbuffer.toString());
    }

    @Test
    public void deleteCharTest() {
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97+i));
        }
        testbuffer.delete();

        assertEquals("abcdefghijklmnopqr", testbuffer.toString());
    }

    @Test
    public void tryDeleteEmptyBufferTest() {
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.delete();

        assertEquals(0, testbuffer.getSize());
        }

    @Test
    public void moveCursorTest() {
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97+i));
        }
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveRight();

        assertEquals(18, testbuffer.getCursorPosition());
    }

    @Test
    public void tryPassBufferEndTest() {
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        for (int i = 0; i < 20; i++) {
            testbuffer.insert((char) (97+i));
        }
        testbuffer.moveRight();

        assertEquals(19, testbuffer.getCursorPosition());
    }

    // add property tests here
    
 }
