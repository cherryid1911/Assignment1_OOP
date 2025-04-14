package scalar;

public abstract class Scalar {
    public Scalar add(Scalar s){
        return s.add(this);
    }
    public abstract Scalar add(IntegerScalar s);
    public abstract Scalar add(RationalScalar s);

    public Scalar mul(Scalar s){
        return s.mul(this);
    }
    public abstract Scalar mul(IntegerScalar s);
    public abstract Scalar mul(RationalScalar s);

    public abstract Scalar neg();
    public abstract Scalar power(int exponent);
    public abstract int sign();
    public abstract boolean equals(Object o);

}
