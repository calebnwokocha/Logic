package Logic;

public class Variable extends Operate {
    private final boolean value;

    public Variable(boolean value) {
        this.value = value;
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return value;
    }

    @Override
    public String toString() {
        return "Variable(" + value + ")";
    }
}
