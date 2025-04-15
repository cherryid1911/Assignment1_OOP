package poly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import scalar.*;

public class PolynomialTest {

    private static Polynomial p1;
    private static Polynomial p2;
    private static Scalar s;

    @BeforeClass
    public static void setUp() {
        p1 = Polynomial.build("1 2 3");
        p2 = Polynomial.build("4 0 5");
        s = new IntegerScalar(2);
    }

    @Test
    public void testToString() {
        assertEquals("1 + 2x + 3x^2", p1.toString());
    }

    @Test
    public void testAdd() {
        Polynomial result = p1.add(p2);
        Polynomial expected = Polynomial.build("5 2 8");
        assertEquals(expected, result);
    }

    @Test
    public void testMul() {
        Polynomial result = p1.mul(p2);
        Polynomial expected = Polynomial.build("4 8 17 10 15");
        assertEquals(expected, result);
    }

    @Test
    public void testEvaluate() {
        assertEquals(new IntegerScalar(17), p1.evaluate(s));
    }

    @Test
    public void testDerivative() {
        Polynomial expected = Polynomial.build("2 6");
        assertEquals(expected, p1.derivative());
    }

    @Test
    public void testToStringZero() {
        Polynomial zero = Polynomial.build("0 0 0");
        assertEquals("0", zero.toString());
    }

    @Test
    public void testEqualsTrue() {
        Polynomial a = Polynomial.build("1 2 3");
        Polynomial b = Polynomial.build("1 2 3");
        assertTrue(a.equals(b));
    }

    @Test
    public void testEqualsFalse() {
        Polynomial a = Polynomial.build("1 2 3");
        Polynomial b = Polynomial.build("1 2 4");
        assertFalse(a.equals(b));
    }
}