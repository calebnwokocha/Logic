package Logic;

class Imply extends Operate {
    public Imply(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean evaluate() {
        return !left.evaluate() || right.evaluate();
    }
}
