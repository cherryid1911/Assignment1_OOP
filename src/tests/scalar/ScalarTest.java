package scalar;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScalarTest {

    private static Scalar int1;
    private static Scalar int2;
    private static Scalar rat1;
    private static Scalar rat2;

    @BeforeClass
    public static void setUp() {
        int1 = new IntegerScalar(2);
        int2 = new IntegerScalar(3);
        rat1 = new RationalScalar(1, 2);
        rat2 = new RationalScalar(2, 3);
    }

    @Test
    public void testAddIntegerWithRational() {
        Scalar result = int1.add(rat1);
        assertEquals(new RationalScalar(5, 2), result);
    }

    @Test
    public void testAddRationalWithInteger() {
        Scalar result = rat1.add(int2);
        assertEquals(new RationalScalar(7, 2), result);
    }

    @Test
    public void testMulIntegerWithRational() {
        Scalar result = int2.mul(rat2);
        assertEquals(new IntegerScalar(2), result);
    }

    @Test
    public void testMulRationalWithInteger() {
        Scalar result = rat2.mul(int1);
        assertEquals(new RationalScalar(4, 3), result);
    }
}
