package Logic;

class Not implements Express {
    private final Express expression;

    public Not(Express expression) {
        this.expression = expression;
    }

    @Override
    public boolean evaluate() {
        return !expression.evaluate();
    }

    @Override
    public Express and(Express expression) {
        return new And(this, expression);
    }

    @Override
    public Express or(Express expression) {
        return new Or(this, expression);
    }

    @Override
    public Express not() {
        return new Not(this);
    }

    public Express not(Express expression) {
        return new Not(expression);
    }

    @Override
    public Express equate(Express expression) {
        return new Equate(this, expression);
    }

    @Override
    public Express imply(Express expression) {
        return new Imply(this, expression);
    }

    public Express iff(Express expression) {
        return new Iff(this, expression);
    }
}
