package Logic;

class The extends Operate {
    public The(Express left, Express right) {
        super(left, right);
    }

    @Override
    public boolean verify() {
        // Check if left and its properties are not null
        if (left != null && left.getProperties() != null) {

        }

        for (Object property : left.getProperties()) {
            if (property == right) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "_" + left + "_the_" + right + "_";
    }

    @Override
    public Object[] getProperties() {
        return properties;
    }

}