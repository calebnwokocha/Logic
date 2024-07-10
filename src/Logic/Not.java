package Logic;

class Not extends Operate {
    private final Express expression;

    public Not(Express expression) {
        this.expression = expression;
    }

    @Override
    public boolean verify() {
        return !expression.verify();
    }
}
