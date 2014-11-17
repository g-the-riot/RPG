package RPG;

// Provides methods to control gameplay
public class GameController {

 public GameController () {}
 
 /* Returns a Character object with the given name as found in the file at the provided path.
  * Assumes all character names are unique.
  */     

 
 public void takeTurn(Character first, Character second) {
      first.attack(second); 
 }
 
 public void reviveCharacter(Character c) {
      
 }
 
 //Auxilliary methods
 
 
 }