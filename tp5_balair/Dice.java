
public class Dice{
  String reponseDice(int n){
      int rand = (int)(Math.random() * (n));
      return "Chiffre aléatoire: "+rand;
  }
  String reponseDiceSix(){
    return reponseDice(6);
  }
}
