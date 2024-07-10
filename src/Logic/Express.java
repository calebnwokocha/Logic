package Logic;

public interface Express {
    boolean verify();

    Express and(Express expression);
    Express or(Express expression);
    Express not();
    Express not(Express express);
    Express equate(Express expression);
    Express imply(Express expression);
    Express iff(Express expression);

    static Express thereExist(Express... expressions) {
        return new ThereExist(expressions);
    }

    static Express forAll(Express... expressions){
        return new ForAll(expressions);
    }
}
