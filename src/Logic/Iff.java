package Logic;

class Iff extends Operate {
    public Iff(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        boolean result = left.verify() == right.verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        return "_" + left + "_iff_" + right + "_";
    }
}
