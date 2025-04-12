package scalar;

public class IntegerScalar extends Scalar{
    //fields
    private int number;
    // constructor
    public IntegerScalar(int n){
        this.number=n;
    }
    public int num(){
        return this.number;
    }

    @Override
    public Scalar add(Scalar s) {
        return s.add(this);
    }
    @Override
    public Scalar add(IntegerScalar s){
        return new IntegerScalar(number+s.num());
    }
    @Override
    public Scalar add(RationalScalar s){
        return new RationalScalar(number*s.d()+s.n(),s.d());
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mul(this);
    }

    @Override
    public Scalar mul(IntegerScalar s) {
        return new IntegerScalar(this.number * s.num());
    }

    @Override
    public Scalar mul(RationalScalar s) {
        return new RationalScalar(this.number * s.n(), s.d());
    }

    @Override
    public Scalar neg() {
        return new IntegerScalar(-this.number);
    }

    @Override
    public Scalar power(int exponent) {
        return new IntegerScalar((int) Math.pow(this.number, exponent));
    }

    @Override
    public int sign() {
        if (this.number > 0) return 1;
        if (this.number < 0) return -1;
        return 0;
    }

    public String toString() {
        return "" + this.number;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof IntegerScalar){
            IntegerScalar other = (IntegerScalar) o;
            return other.num()==number;
        } else if (o instanceof RationalScalar) {
            RationalScalar other = (RationalScalar) o;
            return number==other.n() && other.d()==1;
        }
        return false;
    }


}
