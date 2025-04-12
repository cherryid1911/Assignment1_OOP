package scalar;

public class RationalScalar extends Scalar{
    //fields
    private int numerator;
    private int denominator;
    // constructor
    public RationalScalar(int n, int d){
        this.numerator=n;
        this.denominator=d;
        reduce();
    }
    public int n(){
        return numerator;
    }
    public int d(){
        return denominator;
    }
    private void reduce() {
        int g = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= g;
        denominator /= g;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public Scalar add(Scalar s) {
        return s.add(this);
    }
    @Override
    public Scalar add(IntegerScalar s){
        return s.add(this);
    }
    @Override
    public Scalar add(RationalScalar s){
        return new RationalScalar(s.d()*numerator+s.n()*denominator,s.d()*denominator);
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mul(this);
    }

    @Override
    public Scalar mul(IntegerScalar s) {
        return new RationalScalar(this.numerator * s.num(), this.denominator);
    }

    @Override
    public Scalar mul(RationalScalar s) {
        return new RationalScalar(this.numerator * s.n(), this.denominator * s.d());
    }

    @Override
    public Scalar neg() {
        return new RationalScalar(-this.numerator, this.denominator);
    }

    @Override
    public Scalar power(int exponent) {
        int newNumerator = (int) Math.pow(this.numerator, exponent);
        int newDenominator = (int) Math.pow(this.denominator, exponent);
        return new RationalScalar(newNumerator, newDenominator);
    }

    @Override
    public int sign() {
        int a =numerator*denominator;
        if (a>0) return 1;
        if (a<0) return -1;
        return 0;
    }

    public String toString() {
        if (denominator == 1) {
            return Integer.toString(numerator);
        }
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RationalScalar) {
            RationalScalar other = (RationalScalar) o;
            return this.numerator == other.n() && this.denominator == other.d();
        } else if (o instanceof IntegerScalar) {
            IntegerScalar other = (IntegerScalar) o;
            return this.denominator == 1 && this.numerator == other.num();
        }
        return false;
    }




}
