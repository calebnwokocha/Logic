package Logic;

class ThereExist implements Express {
    private final Express[] expressions;

    public ThereExist(Express... expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean evaluate() {
        for (Express expression : expressions) {
            if (expression.evaluate()) {
                return true;
            }
        }
        return false;
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

    @Override
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
}
