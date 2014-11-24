package RPG;

public interface Pickup{

     public String look();
     
     public String getName();
     
     public void use(AbstractCharacter q);
     
     public boolean isItKeyItem();
     
     public boolean isItReusable();
         
}

