package k35.fp;

import junit.framework.TestCase;

public class PipeTest extends TestCase {

    int x2(int a) {
        return a * 2;
    }

    int x3(int a) {
        return a * 3;
    }

    String str(int a) {
        return String.valueOf(a);
    }

    public void test() {
        final var v = Pipe.of(1).map(this::x2).map(this::x3).map(this::str).out();

        assertEquals(v, "6");
    }

}