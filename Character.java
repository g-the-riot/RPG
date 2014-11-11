package RPG;

public interface Character {

	//queries
	public String name();
	
	public int hp();
	
	public int level();
	
	public boolean isDead();
	
	public Weapon weapon();
	
	/*
	 * Reduces this PlayerCharacter's hp by the given damage
	 * Requires: damage >= 0
	 * Ensures: this.hp() == this.hp() - damage 
	 *          || if(damage >= this.hp()), this.hp() == 0
	 */
	public void takeDamage(int damage);
	
	// contract here
	public void gainHp(int hp);
	
	public void attack(Character q);

}