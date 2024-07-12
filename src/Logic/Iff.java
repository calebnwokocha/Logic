package Logic;

class Iff extends Operate {
    public Iff(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return left.verify() == right.verify();
    }

    @Override
    public String toString() {
        return "_" + left + "_iff_" + right + "_";
    }
}
