package Logic;

public interface Express {
    boolean verify();
    String toString();

    Express and(Express expression);
    Express or(Express expression);
    Express not();
    Express not(Express express);
    Express equate(Express expression);
    Express imply(Express expression);
    Express iff(Express expression);
    Express thereExist(Express... expressions);
    Express forAll(Express... expressions);
}
