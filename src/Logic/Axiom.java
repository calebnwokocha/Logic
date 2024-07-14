package Logic;

public class Axiom extends Operate {
    public Axiom(boolean value) {super(value);}

    @Override
    public boolean verify() {
        System.out.println(toString());
        return value;
    }

    @Override
    public String toString() {
        return "_" + value + "_";
    }
}
