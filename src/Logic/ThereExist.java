package Logic;

class ThereExist extends Operate {
    public ThereExist(Express left, Express... rights) {
        super(left, rights);
    }

    @Override
    public boolean verify() {
        for (Express expression : rights) {
            if (expression.verify()) {
                return left.verify();
            }
        }
        System.out.println(toString());
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [").append(left).append(" ∃ ");
        for (int i = 0; i < rights.length; i++) {
            sb.append(rights[i]);
            if (i < rights.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("] ");
        return sb.toString();
    }
}
