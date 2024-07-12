package Logic;

class Not extends Operate {
    public Not(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return left.verify() && !right.verify();
    }

    @Override
    public String toString() {
        return "_" + left + "_not_" + right + "_";
    }
}
