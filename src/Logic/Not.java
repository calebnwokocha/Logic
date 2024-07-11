package Logic;

class Not extends Operate {
    private final Express expression;

    public Not(Express expression) {
        this.expression = expression;
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return !expression.verify();
    }

    @Override
    public String toString() {
        return "(NOT " + expression + ")";
    }
}
