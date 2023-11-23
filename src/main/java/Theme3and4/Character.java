package Theme3and4;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Character {
    private String name;
    private String gameClass;
    private int level;
    private int attack;
    private int armor;
    private int money;

    @Override
    public String toString() {
        return new String()
                .concat(getName())
                .concat("\n")
                .concat("name: ").concat(getName().toString())
                .concat("\n")
                .concat("gameClass: ").concat(getGameClass().toString())
                .concat("\n")
                .concat("level: ").concat(""+getLevel())
                .concat("\n")
                .concat("attack: ").concat(""+getAttack())
                .concat("\n")
                .concat("armor: ").concat(""+getArmor())
                .concat("\n")
                .concat("money: ").concat(""+getMoney())
                .concat("\n---------------------------\n");
    }
}




