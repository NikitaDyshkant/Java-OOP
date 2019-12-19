import java.util.HashMap;
import java.util.Scanner;

public class Converter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите текст на русском:");
        System.out.println(convertToLatin(sc.nextLine()));

    }

    public static String convertToLatin(String str){
        StringBuilder sb = new StringBuilder();
        
        HashMap<Character,String> map = getAlphabet();
        
        for (int i = 0; i < str.length(); i++) {

            if(map.get(str.charAt(i))!=null){
              
                sb.append(map.get(str.charAt(i)));
            }
            else {
                sb.append(str.charAt(i));
            }

        }
        return sb.toString();
    }


    public static HashMap<Character,String> getAlphabet(){
        HashMap<Character,String> map = new HashMap<Character, String>();
        
        String[] latin = {"a", "b", "v","g","d", "e", "zh", "z", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "x", "c", "ch", "sh", "shh", "#", "y", "'", "e", "yu", "ya"};
        
        for (int i = 0; i< 64; i++) {
           
            char c = (char)(1040+i);
            
            if(c>'Я'){
                map.put(c,latin[i-latin.length]);
            }
            else {
                map.put(c,latin[i].toUpperCase());
            }


        }
        
        map.put('ё',"yo");
        map.put('Ё',"YO");


        return map;
    }
}