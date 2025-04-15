package scalar;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class IntegerScalarTest {

    private static IntegerScalar a;
    private static IntegerScalar b;

    @BeforeClass
    public static void setUp() {
        a = new IntegerScalar(2);
        b = new IntegerScalar(3);
    }

    @Test
    public void testNeg() {
        assertEquals(new IntegerScalar(-3), b.neg());
    }

    @Test
    public void testPow1() {
        assertEquals(new IntegerScalar(81), b.power(4));
    }

    @Test
    public void testPow2() {
        assertEquals(new IntegerScalar(1), b.power(0));
    }

    @Test
    public void testSign1() {
        assertEquals(1, b.sign());
    }

    @Test
    public void testSign2() {
        IntegerScalar d = new IntegerScalar(-3);
        assertEquals(-1,d.sign());
    }

    @Test
    public void testToString() {
        assertEquals("3", b.toString());
    }

    @Test
    public void testEquals1() {
        IntegerScalar d = new IntegerScalar(3);
        assertTrue(b.equals(d));
    }

    @Test
    public void testEquals2() {
        assertFalse(b.equals(a));
    }

}
