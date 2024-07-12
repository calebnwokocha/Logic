package Logic;

public class Variable extends Operate {
    public Variable(boolean value) {super(value);}

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
