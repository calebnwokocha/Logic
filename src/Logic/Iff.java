package Logic;

class Iff extends Operate {
    public Iff(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        return left.verify() == right.verify();
    }
}
