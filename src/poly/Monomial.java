package poly;
import scalar.IntegerScalar;
import scalar.Scalar;

public class Monomial {
    private int exponent;
    private Scalar coefficient;

    Monomial(int ex, Scalar co){
        this.exponent=ex;
        this.coefficient=co;
    }
    protected int getExponent(){
        return this.exponent;
    }
    protected Scalar getCoefficient(){
        return this.coefficient;
    }

    public Monomial add(Monomial m){
        if (m.getExponent()!=this.exponent) return null;
        return new Monomial(this.exponent, this.coefficient.add(m.getCoefficient()));

    }

    public Monomial mul(Monomial m){
        return new Monomial(this.exponent+m.getExponent(),this.coefficient.mul(m.getCoefficient()));
    }

    public Scalar evaluate(Scalar s){
        return this.coefficient.mul(s.power(this.exponent));
    }

    public Monomial derivative(){
        IntegerScalar exS = new IntegerScalar(this.exponent);
        return new Monomial(this.exponent -1,this.coefficient.mul(exS));
    }

    public int sign(){
        return this.coefficient.sign();
    }

    public boolean equals(Object o){
        if (!(o instanceof Monomial)) return false;
        Monomial other = (Monomial) o;
        return (this.exponent==other.getExponent()) && (this.coefficient==other.getCoefficient());
    }

    public String toString() {
        if (coefficient.sign() == 0) return "0";

        if (exponent == 0) {
            return coefficient.toString();
        }

        StringBuilder sb = new StringBuilder();

        if (coefficient.equals(new IntegerScalar(1))) {
        } else if (coefficient.equals(new IntegerScalar(-1))) {
            sb.append("-");
        } else {
            sb.append(coefficient.toString());
        }

        sb.append("x");
        if (exponent != 1) {
            sb.append("^").append(exponent);
        }
        return sb.toString();
    }




}
