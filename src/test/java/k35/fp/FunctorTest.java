package k35.fp;

import junit.framework.TestCase;

public class FunctorTest extends TestCase {


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

        final var f1 = Functor.of(this::x2).map(this::x3).map(this::str);

        assertEquals(f1.transform.apply(1), "6");

    }

}