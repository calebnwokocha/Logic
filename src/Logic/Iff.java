package Logic;

class Iff extends Operate {
    public Iff(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean evaluate() {
        return (left.evaluate() && right.evaluate()) || (!left.evaluate() && !right.evaluate());
    }
}