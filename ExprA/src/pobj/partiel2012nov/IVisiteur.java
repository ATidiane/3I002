package pobj.partiel2012nov;

public interface IVisiteur<T> {
    T visite(Constante c);
    T visite(Add e);
    T visite(Mul e);
    T visite(Var v);
}
