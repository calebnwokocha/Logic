package Logic;

class ThereExist extends Operate {
    private final Express[] expressions;

    public ThereExist(Express... expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean verify() {
        System.out.println(toString());
        for (Express expression : expressions) {
            if (expression.verify()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("THERE_EXIST(");
        for (int i = 0; i < expressions.length; i++) {
            sb.append(expressions[i]);
            if (i < expressions.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
