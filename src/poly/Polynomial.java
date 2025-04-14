package poly;
import scalar.RationalScalar;
import scalar.IntegerScalar;
import scalar.Scalar;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
public class Polynomial {
    private TreeMap<Integer, Monomial> monomials;

    Polynomial(){
        this.monomials = new TreeMap<Integer, Monomial>();
    }

    public TreeMap<Integer, Monomial> getMonomials() {
        return this.monomials;
    }

    public static Polynomial build(String input){
        Polynomial poly = new Polynomial();
        String[] tokens = input.trim().split("\\s+");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            Scalar scalar;
            if (token.contains("/")) {
                String[] parts = token.split("/");
                int numerator = Integer.parseInt(parts[0]);
                int denominator = Integer.parseInt(parts[1]);
                if (numerator==0 || denominator==1){
                    scalar = new IntegerScalar(numerator);
                }else {
                    scalar = new RationalScalar(numerator, denominator);
                }
            } else {
                int number = Integer.parseInt(token);
                scalar = new IntegerScalar(number);
            }
            if (scalar.sign() != 0) {
                Monomial m = new Monomial(i, scalar);
                poly.monomials.put(i, m);
            }
        }
        return poly;
    }
    public Polynomial add(Polynomial p) {
        Polynomial sum = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : this.monomials.entrySet()) {
            sum.monomials.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Integer, Monomial> entry : p.monomials.entrySet()) {
            int exp = entry.getKey();
            Monomial other = entry.getValue();

            if (sum.monomials.containsKey(exp)) {
                Monomial added = sum.monomials.get(exp).add(other);
                if (added != null && added.getCoefficient().sign() != 0) {
                    sum.monomials.put(exp, added);
                } else {
                    sum.monomials.remove(exp);
                }
            } else {
                sum.monomials.put(exp, other);
            }
        }

        return sum;
    }

    public Polynomial mul(Polynomial p) {
        Polynomial product = new Polynomial();
        for (Monomial m1 : this.monomials.values()) {
            for (Monomial m2 : p.monomials.values()) {

                Monomial m = m1.mul(m2);
                int exp = m.getExponent(); // החזקה החדשה

                if (product.monomials.containsKey(exp)) {
                    Monomial sum = product.monomials.get(exp).add(m);
                    if (sum != null && sum.getCoefficient().sign() != 0) {
                        product.monomials.put(exp, sum);
                    } else {
                        product.monomials.remove(exp);
                    }
                } else {
                    product.monomials.put(exp, m);
                }
            }
        }

        return product;
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

*/
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


}
