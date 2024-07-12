package Logic;

class Imply extends Operate {
    public Imply(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        boolean result = !left.verify() || right.verify();
        System.out.println(toString());
        return result;
    }

    @Override
    public String toString() {
        return "_" + left + "_imply_" + right + "_";
    }
}
