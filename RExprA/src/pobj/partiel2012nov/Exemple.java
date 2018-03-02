package pobj.partiel2012nov;

import pobj.partiel2012nov.test.TestQ4;

class Exemple {
  public static void main(String[] args) {
    VisiteurTS v1 = new VisiteurTS();
    VisiteurTS v2 = new VisiteurTS();
    Expression e1 = TestQ4.e1();
    Expression e2 = TestQ4.e2();
    Expression e3 = TestQ4.e3();
    String s1 = e1.accepte(v1);
    String s2 = e2.accepte(v2);
    String s3 = e3.accepte(v1);
    System.out.println(s1 + "\n" + s2 + "\n" + s3);
  }
}