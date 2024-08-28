package Logic;

public class Axiom extends Operate {
    public Axiom(boolean value, Object... properties) {super(value, properties);}

    @Override
    public boolean verify() {
        System.out.println(toString());
        return value;
    }

    @Override
    public String toString() {
        return "_" + value + "_";
    }

    public Object[] getProperties() {return properties;};
}
