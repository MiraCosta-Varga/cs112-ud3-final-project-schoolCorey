package cs112.ud3.models;

import java.io.Serializable;

/**
 * Class which represents a Spell DMCard in the Duel Master video game. It doesn't have any more
 * information than DMCard does, but seems to be needed to keep the deserializer from getting
 * confused when it's trying to read different kinds of DMCard objects from a binary file.
 */
public class DMSpell extends DMCard implements Serializable {
    public DMSpell(){
        super();
    }

    public DMSpell(String name,  int civilization, int cardType, String textbox,
                         int rarity, int cost, boolean hasShieldTrigger, int idNum) throws IllegalArgumentException{
        super(name, civilization, cardType, textbox, rarity, cost, hasShieldTrigger, idNum);
    }

    public DMSpell(DMSpell other) throws IllegalArgumentException{
        super(other);
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || (! (other instanceof DMSpell))){
            return false;
        } else{
            DMCard otherCard = (DMCard) other;
            return this.name.equals(otherCard.name) && (this.civilization==otherCard.civilization)
                    && (this.cardType==otherCard.cardType) && this.textbox.equals(otherCard.textbox)
                    && this.rarity == otherCard.rarity && this.cost == otherCard.cost
                    && this.hasShieldTrigger == otherCard.hasShieldTrigger && this.idNum == otherCard.idNum;
        }
    }
}
