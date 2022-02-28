import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    String expected,actual,expectedAddress;
    String [] args = new String[2];
    @Test
    void t0() {
        Main.inputAddress = "src/test/inputs/t0.cool";
        Main.outputAddress = "src/test/actual/t0.s";
        expectedAddress = "src/test/expected/t0.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t1() {
        Main.inputAddress = "src/test/inputs/t1.cool";
        Main.outputAddress = "src/test/actual/t1.s";
        expectedAddress = "src/test/expected/t1.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t2(){
        Main.inputAddress = "src/test/inputs/t2.cool";
        Main.outputAddress = "src/test/actual/t2.s";
        expectedAddress = "src/test/expected/t2.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t3(){
        Main.inputAddress = "src/test/inputs/t3.cool";
        Main.outputAddress = "src/test/actual/t3.s";
        expectedAddress = "src/test/expected/t3.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t4(){
        Main.inputAddress = "src/test/inputs/t4.cool";
        Main.outputAddress = "src/test/actual/t4.s";
        expectedAddress = "src/test/expected/t4.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t5(){
        Main.inputAddress = "src/test/inputs/t5.cool";
        Main.outputAddress = "src/test/actual/t5.s";
        expectedAddress = "src/test/expected/t5.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t6(){
        Main.inputAddress = "src/test/inputs/t6.cool";
        Main.outputAddress = "src/test/actual/t6.s";
        expectedAddress = "src/test/expected/t6.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t7(){
        Main.inputAddress = "src/test/inputs/t7.cool";
        Main.outputAddress = "src/test/actual/t7.s";
        expectedAddress = "src/test/expected/t7.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t8(){
        Main.inputAddress = "src/test/inputs/t8.cool";
        Main.outputAddress = "src/test/actual/t8.s";
        expectedAddress = "src/test/expected/t8.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t9(){
        Main.inputAddress = "src/test/inputs/t9.cool";
        Main.outputAddress = "src/test/actual/t9.s";
        expectedAddress = "src/test/expected/t9.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t10(){
        Main.inputAddress = "src/test/inputs/t10.cool";
        Main.outputAddress = "src/test/actual/t10.s";
        expectedAddress = "src/test/expected/t10.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t11(){
        Main.inputAddress = "src/test/inputs/t11.cool";
        Main.outputAddress = "src/test/actual/t11.s";
        expectedAddress = "src/test/expected/t11.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t12(){
        Main.inputAddress = "src/test/inputs/t12.cool";
        Main.outputAddress = "src/test/actual/t12.s";
        expectedAddress = "src/test/expected/t12.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t13(){
        Main.inputAddress = "src/test/inputs/t13.cool";
        Main.outputAddress = "src/test/actual/t13.s";
        expectedAddress = "src/test/expected/t13.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t14(){
        Main.inputAddress = "src/test/inputs/t14.cool";
        Main.outputAddress = "src/test/actual/t14.s";
        expectedAddress = "src/test/expected/t14.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t15(){
        Main.inputAddress = "src/test/inputs/t15.cool";
        Main.outputAddress = "src/test/actual/t15.s";
        expectedAddress = "src/test/expected/t15.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t16(){
        Main.inputAddress = "src/test/inputs/t16.cool";
        Main.outputAddress = "src/test/actual/t16.s";
        expectedAddress = "src/test/expected/t16.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t17(){
        Main.inputAddress = "src/test/inputs/t17.cool";
        Main.outputAddress = "src/test/actual/t17.s";
        expectedAddress = "src/test/expected/t17.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t18(){
        Main.inputAddress = "src/test/inputs/t18.cool";
        Main.outputAddress = "src/test/actual/t18.s";
        expectedAddress = "src/test/expected/t18.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t19(){
        Main.inputAddress = "src/test/inputs/t19.cool";
        Main.outputAddress = "src/test/actual/t19.s";
        expectedAddress = "src/test/expected/t19.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
    @Test
    void t20(){
        Main.inputAddress = "src/test/inputs/t20.cool";
        Main.outputAddress = "src/test/actual/t20.s";
        expectedAddress = "src/test/expected/t20.s";
        try {
            Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            expected = new String(Files.readAllBytes(Paths.get(expectedAddress)));
            actual = new String(Files.readAllBytes(Paths.get(Main.outputAddress)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
}