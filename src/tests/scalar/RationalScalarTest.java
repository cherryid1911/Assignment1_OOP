package scalar;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RationalScalarTest {

    private static RationalScalar a;
    private static RationalScalar b;

    @BeforeClass
    public static void setUp() {
        a = new RationalScalar(2, 3);
        b = new RationalScalar(5, 6);
    }

    @Test
    public void testNeg() {
        Scalar result = a.neg();
        assertEquals(new RationalScalar(-2, 3), result);
    }

    @Test
    public void testPower() {
        Scalar result = a.power(3);
        assertEquals(new RationalScalar(8, 27), result);
    }

    @Test
    public void testSignPositive() {
        assertEquals(1, a.sign());
    }

    @Test
    public void testSignNegative() {
        RationalScalar d = new RationalScalar(-5, 4);
        assertEquals(-1, d.sign());
    }

    @Test
    public void testToString() {
        assertEquals("5/6", b.toString());
    }

    @Test
    public void testEquals() {
        RationalScalar x = new RationalScalar(10, 12);
        assertTrue(b.equals(x));
    }

    @Test
    public void testEqualsDifferent() {
        RationalScalar x = new RationalScalar(1, 2);
        assertFalse(b.equals(x));
    }
}