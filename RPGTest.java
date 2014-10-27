package RPG;

import java.io.IOException;

public class RPGTest {

	public static void main (String[] args) throws IOException {
		Game g = new Game("/Users/Evenie/Desktop/RPG/player_data.txt");
		g.fetchCharacterData();
		//PlayerCharacter p = new PlayerCharacter("Bob");
		//PlayerCharacter q = new PlayerCharacter("NotBob");
		
		//boolean finished = false;
		//while(finished == false) {
		//	if(p.isDead() || q.isDead()) {
		//		if(p.isDead()) {
		//			System.out.println(q.name() + " wins! yay!");
		//		}
		//		else{
		//			System.out.println(p.name() + " wins! yay!");
		//		}
		//		finished = true;
		//	}
		//	else {
		//		p.attack(q);
		//		System.out.println(p.name() + " attacks " + q.name() + "with a " + p.weapon().name() + " and reduces " + q.name() + "'s hp to " + q.hp());
		//		if(!q.isDead()) {
		//			q.attack(p);
		//			System.out.println(q.name() + " attacks " + p.name() + "with a " + q.weapon().name() +  " and reduces " + p.name() + "'s hp to " + p.hp());	
		//		}
		//	}
		//}
		
		//Test the constructor first
		//String name = "Bob";
		//if(name.equals(p.name())) {
		//	System.out.println("name() passes");
		//}
		//else {
		//	System.out.println("name() fails");
		//}
		//if(p.hp() == 100) {
		//	System.out.println("hp() passes");
		//}
		//else{
		//	System.out.println("hp() fails");
		//}
	
	}

}