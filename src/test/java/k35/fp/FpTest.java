package k35.fp;

import junit.framework.TestCase;

public class FpTest extends TestCase {


    private int x2(int a) {
        return a + a;
    }

    private int x3(int b) {
        return b + b + b;
    }

    public void testCompose2() {

        final var f2 = Fp.compose2(this::x2, this::x3);
        final var r = f2.apply(1);

        assertEquals(r.intValue(), 6);
    }

    public void testValue() {
        assertEquals(Fp.value(1).intValue(), 1);
    }

    public void testSet() {
        assertEquals(Fp.set(1).apply(2).intValue(), 1);
    }
}