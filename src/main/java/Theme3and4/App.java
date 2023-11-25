package Theme3and4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException {
        ArrayList<Character> characters = new ArrayList<>();
        String name;
        String gameClass;
        int level;
        int attack;
        int armor;
        int money;
        ObjectMapper jsonMapper = new JsonMapper();
        /*------------------------------------*/
        if (new File("characters.json").exists()) {
            characters = jsonMapper.readValue(new File("characters.json"), new TypeReference<ArrayList<Character>>(){});

            characters.forEach(x -> System.out.println(x.toString()));
        }
        else {
            int n = 3;
            for (int i = 0; i < n; i++) {
                Faker faker = new Faker();

                name = faker.name().firstName();
                gameClass = faker.job().position();
                level = new Random().nextInt(12);
                attack = new Random().nextInt(50) + 25;
                armor = new Random().nextInt(10) + 5;
                money = new Random().nextInt(2000);
                characters.add(new Character(name, gameClass, level, attack, armor, money));
            }
            System.out.println(characters);
        }
        /*------------------------------------*/
        Optional<Character> bestCharacter = characters.stream()
                .max(Comparator.comparingInt(Character::getLevel));

        if (bestCharacter.isPresent()) {
            System.out.println("1) Наибольший уровень:");
            System.out.println(bestCharacter.get());
        }
        /*------------------------------------*/
        System.out.println("2) Данные после увеличения атаки в 1,5 раза:");
        characters.stream()
                .peek(character -> {
                    character.setAttack((int) (character.getAttack() * 1.5));
                })
                .forEach(System.out::println);
        /*------------------------------------*/
        Optional<Character> firstRichestCharacter = characters.stream()
                .max(Comparator.comparingInt(Character::getMoney));

        if (firstRichestCharacter.isPresent()) {
            System.out.println("3.1) Самый богатый до увеличения:");
            System.out.println(firstRichestCharacter.get());
        }

        System.out.println("Данные после увеличения золота в 2 раза:");
        characters.stream()
                .filter(character -> character.getLevel()*character.getArmor() % 2 == 0)
                .peek(character -> {
                    character.setMoney(character.getMoney() * 2);
                })
                .forEach(System.out::println);

        Optional<Character> secondRichestCharacter = characters.stream()
                .max(Comparator.comparingInt(Character::getMoney));

        if (secondRichestCharacter.isPresent()) {
            System.out.println("3.2) Самый богатый после увеличения:");
            System.out.println(secondRichestCharacter.get());
        }
        /*------------------------------------*/
        System.out.println("4) Данные после привязки защиты к уровню:");
        characters.stream()
                .peek(character -> {
                    character.setArmor(character.getArmor() + character.getLevel());
                })
                .forEach(System.out::println);
        /*------------------------------------*/
        jsonMapper.writeValue(new File("characters.json"), characters);
    }
}
