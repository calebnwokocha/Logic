package Logic;

class Equate extends Operate {
    public Equate(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean evaluate() {
        return left.evaluate() == right.evaluate();
    }
}
