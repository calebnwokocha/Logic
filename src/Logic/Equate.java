package Logic;

class Equate extends Operate {
    public Equate(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        return left.verify() == right.verify();
    }
}
