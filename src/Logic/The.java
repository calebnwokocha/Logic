package Logic;

class The extends Operate {
    public The(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        return  true;
    }

    @Override
    public String toString() {
        return "_" + left + "_the_" + right + "_";
    }


}