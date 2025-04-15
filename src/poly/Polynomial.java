package poly;
import scalar.RationalScalar;
import scalar.IntegerScalar;
import scalar.Scalar;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    //field
    private Map<Integer, Monomial> monomials;

    //constructor
    public Polynomial(){
        monomials = new HashMap<Integer, Monomial>();
    }

    //methods
    private void addMonomial(Monomial m){
        int e = m.getExponent();
        if (!monomials.containsKey(e))
            this.monomials.put(e, m);
        else{
            monomials.put(e, m.add(monomials.get(e)));
        }
    }

    public static Polynomial build(String input){
        String[] coes = input.split(" ");
        Polynomial output = new Polynomial();
        for (int i = 0; i < coes.length; i++) {
            if (coes[i].contains("/")) {
                String[] nd = input.split("/");
                Scalar c = new RationalScalar(Integer.parseInt(nd[0]), Integer.parseInt(nd[1]));
                Monomial add = new Monomial(i, c);
                output.addMonomial(add);
            }
            else {
                Scalar c = new IntegerScalar(Integer.parseInt(coes[i]));
                Monomial add = new Monomial(i, c);
                output.addMonomial(add);
            }
        }
        return output;
    }

    public Polynomial add (Polynomial p){
        Polynomial output = new Polynomial();
        for (Integer key: monomials.keySet())
            output.addMonomial(monomials.get(key));
        for (Integer key: p.monomials.keySet())
            output.addMonomial(p.monomials.get(key));
        return output;
    }

    public Polynomial mul (Polynomial p){
        Polynomial output = new Polynomial();
        for (Integer key1: monomials.keySet()){
            for (Integer key2: p.monomials.keySet()){
                Monomial add = monomials.get(key1).mul(p.monomials.get(key2));
                output.addMonomial(add);
            }
        }
        return output;
    }

    public Scalar evaluate(Scalar s) {
        Scalar result = new IntegerScalar(0);

        for (Monomial m : monomials.values()) {
            Scalar evaluated = m.evaluate(s);
            result = result.add(evaluated);
        }
        return result;
    }

    public Polynomial derivative() {
        Polynomial result = new Polynomial();
        for (Monomial m : monomials.values()) {
            Monomial derived = m.derivative();
            if (derived != null && derived.getCoefficient().sign() != 0) {
                result.monomials.put(derived.getExponent(), derived);
            }
        }
        return result;
    }

    public String toString() {
        if (monomials.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (Monomial m : monomials.values()) {
            if (m.getCoefficient().sign() == 0) {
                continue;
            }

            String term = m.toString();

            if (isFirst) {
                sb.append(term);
                isFirst = false;
            } else {
                if (term.startsWith("-")) {
                    sb.append(" ").append(term);
                } else {
                    sb.append(" + ").append(term);
                }
            }
        }

        if (sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }

    public boolean equals (Object o){
        if (!(o instanceof Polynomial))
            return false;
        Polynomial other = (Polynomial) o;
        return this.monomials.equals(other.monomials);
    }


}
