package Logic;

class ThereExist extends Operate {
    private final Express[] expressions;

    public ThereExist(Express... expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean verify() {
        for (Express expression : expressions) {
            if (expression.verify()) {
                return true;
            }
        }
        return false;
    }
}
