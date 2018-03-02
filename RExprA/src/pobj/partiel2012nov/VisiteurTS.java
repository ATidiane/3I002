package pobj.partiel2012nov;

public class VisiteurTS implements IVisiteur<String> {
    public String visite(Constante c){return ( Integer.toString(c.getValue()));}
    public String visite(Var v){return v.getNom();}
    public String visite(Add a){
        String s1 = a.getFg().accepte(this);
        String s2 = a.getFd().accepte(this);
        return "( "+ s1 + " + " + s2 + " )";
    }
    public String visite(Mul a){
        String s1 = a.getFg().accepte(this);
        String s2 = a.getFd().accepte(this);
        return s1 + " * " + s2 ;
    }
    
}
