package poly;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import scalar.*;

public class MonomialTest {

    private static Monomial m1;
    private static Monomial m2;
    private static Monomial m3;
    private static Scalar x;

    @BeforeClass
    public static void setUp() {
        m1 = new Monomial(2, new IntegerScalar(3));
        m2 = new Monomial(2, new IntegerScalar(4));
        m3 = new Monomial(3, new IntegerScalar(5));
        x = new IntegerScalar(2);
    }

    @Test
    public void testAddSameExponent() {
        Monomial result = m1.add(m2);
        assertNotNull(result);
        assertEquals(new Monomial(2, new IntegerScalar(7)), result);
    }

    @Test
    public void testAddDifferentExponent() {
        assertNull(m1.add(m3));
    }

    @Test
    public void testMul() {
        Monomial result = m1.mul(m3);
        assertEquals(new Monomial(5, new IntegerScalar(15)), result);
    }

    @Test
    public void testEvaluate() {
        Scalar result = m1.evaluate(x);
        assertEquals(new IntegerScalar(12), result);
    }

    @Test
    public void testDerivative() {
        Monomial result = m1.derivative();
        assertEquals(new Monomial(1, new IntegerScalar(6)), result);
    }

    @Test
    public void testSignPositive() {
        assertEquals(1, m1.sign());
    }

    @Test
    public void testSignNegative() {
        Monomial neg = new Monomial(2, new IntegerScalar(-5));
        assertEquals(-1, neg.sign());
    }

    @Test
    public void testToStringStandard() {
        assertEquals("3x^2", m1.toString());
    }

    @Test
    public void testToStringExponentZero() {
        Monomial constant = new Monomial(0, new IntegerScalar(7));
        assertEquals("7", constant.toString());
    }

    @Test
    public void testToStringExponentOne() {
        Monomial linear = new Monomial(1, new IntegerScalar(2));
        assertEquals("2x", linear.toString());
    }

    @Test
    public void testToStringCoefficientOne() {
        Monomial x = new Monomial(1, new IntegerScalar(1));
        assertEquals("x", x.toString());
    }

    @Test
    public void testToStringCoefficientMinusOne() {
        Monomial x = new Monomial(1, new IntegerScalar(-1));
        assertEquals("-x", x.toString());
    }

    @Test
    public void testEqualsTrue() {
        Monomial m = new Monomial(2, new IntegerScalar(3));
        assertTrue(m.equals(m1));
    }

    @Test
    public void testEqualsFalseExponent() {
        assertFalse(m1.equals(m3));
    }

    @Test
    public void testEqualsFalseCoefficient() {
        Monomial diff = new Monomial(2, new IntegerScalar(99));
        assertFalse(m1.equals(diff));
    }
}
