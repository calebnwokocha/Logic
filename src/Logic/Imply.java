package Logic;

class Imply extends Operate {
    public Imply(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        return !left.verify() || right.verify();
    }

    @Override
    public String toString() {
        return "_" + left + "_imply_" + right + "_";
    }
}
