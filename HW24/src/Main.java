import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static String trimmer(String input){
        input = input.replaceAll("\\D", "" );
        if (input.length() == 11){
            input = "7" + input.substring(1);
        } else if (input.length() == 10) {
            input = "7" + input;
        }
        return input;
    }
    public static Boolean isInMap(Map<String, String> map, String value){
        String found;
        Boolean isInFlag = false;
        if (map.containsValue(value)){
            for (Map.Entry<String, String> entry:
                    map.entrySet()) {
                isInFlag = true;
                found = entry.getKey();
                System.out.println("This phone number has already been added,\n" +
                        "it has this username:\n"
                        + found);
            }
        } else if (map.containsKey(value)) {
            for (Map.Entry<String, String> entry:
                    map.entrySet()) {
                isInFlag = true;
                found = entry.getValue();
                System.out.println("This user has already been added,\nhe has this phone number:\n"
                        + found);
            }
        }
        return isInFlag;
    }
    public static final String ISNUMBRE = "([78])?(\\d){10}";
    public static final String ISNAME = "([А-яёЁ]+\\s?)+";

    public static void main(String[] args) {
        Map<String,String> phoneBook = new TreeMap<>();
        phoneBook.put("Петя", "79138861310");
        Boolean connectionFlag = true;
        while (connectionFlag) {
            System.out.println("Please enter a phone number, name or command");
            String input = new Scanner(System.in).nextLine();
            String secondInput;
            if ((trimmer(input).matches(ISNUMBRE))&&(!isInMap(phoneBook,trimmer(input)))){
                System.out.println("Please enter username for this contact");
                secondInput = new Scanner(System.in).nextLine();                    //По хорошему следует разнести разные добавления
                while(!(secondInput.matches(ISNAME))){                          //по методам, как и функции. в Общем реализовать солид, усложнить функционал.
                    System.out.println("Try again");
                    secondInput = new Scanner(System.in).nextLine();
                }
                input = phoneBook.get(secondInput)+ "; " + trimmer(input);
                phoneBook.put(secondInput, input);
            } else if ((input.matches(ISNAME))&&(!isInMap(phoneBook,input))) {
                System.out.println("Please enter phone number for this contact");
                secondInput = new Scanner(System.in).nextLine();
                while (!(trimmer(secondInput).matches(ISNUMBRE))||(isInMap(phoneBook, secondInput))){
                    System.out.println("Try again");
                    secondInput = new Scanner(System.in).nextLine();
                }
                phoneBook.put(input, trimmer(secondInput));
            } else if (input.equals("LIST")) {
                for (Map.Entry<String, String> entry:
                     phoneBook.entrySet()) {
                    System.out.println(entry);
                }
            } else if (input.equals("Stop")) {
                connectionFlag = false;
            } else {
                System.out.println("Sorry, that has been a mistake, please try again");
            }

        }
    }

}

     /*
    Напишите программу, которая будет работать как телефонная книга:
·Если вводим новое имя, программа просит ввести номер телефона и запоминает его.
·Если новый номер телефона — просит ввести имя и также запоминает.
·Если вводим существующее имя или номер телефона, программа выводит телефон(ы) или имя абонента соответственно.
·При вводе команды LIST программа печатает в консоль список всех абонентов в алфавитном порядке с номерами.

     */
