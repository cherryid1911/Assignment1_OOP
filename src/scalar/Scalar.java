package scalar;

public abstract class Scalar {
    public abstract Scalar add(Scalar s);
    public abstract Scalar add(IntegerScalar s);
    public abstract Scalar add(RationalScalar s);

    public abstract Scalar mul(Scalar s);
    public abstract Scalar mul(IntegerScalar s);
    public abstract Scalar mul(RationalScalar s);

    public abstract Scalar neg();
    public abstract Scalar power(int exponent);
    public abstract int sign();
    public abstract boolean equals(Object o);

}
