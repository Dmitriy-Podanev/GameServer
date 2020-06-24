



import java.util.Arrays;
import java.util.Scanner;

public class GameObject {
   private Scanner in = new Scanner(System.in);
   private String[] word;// полное слово
   private String[] step;//Слово с пропусками
    public void ChooseWord(){
        String[] words = {"ЧЕЛОВЕК", "СТОЛОВАЯ", "НОУТБУК"};


       word = words[0+(int)(Math.random()*words.length)].split("");

        step= new String[word.length];

        for (int i = 0; i < word.length; i++) {

            if(i==0||i==word.length-1) {
                System.out.print(word[i]);
                step[i]=word[i];
            }
            else {
                System.out.print("_");
                step[i]="_";
            }


        }
    }
    private boolean CheckMas(String w){
        boolean find =false;

        for (int i = 1; i < word.length-1; i++) {
           if(w.toUpperCase().equals(word[i])){
               step[i]=w.toUpperCase();
               find=true;
           }
        }
        return  find;
    }
    private void ShowWord(){
        for (int i = 0; i < word.length; i++) {
            System.out.print(step[i]);
        }

    }

    public void GameStart()//todo запрашивает слово и вывод букву если правильно, если нет, то кол-во жизней остаток
    {
        if(word==null) throw new NullPointerException("Слово не найдено");
        int live =3;
        while (live!=0) {
            System.out.println("\nВаша буква: ");//todo первая замена на сервере
            String w = in.nextLine();

            if (CheckMas(w)) {
                System.out.println("Такая буква есть!");
                System.out.println("Осталось " + live + " жизней");

            } else {
                System.out.println("Такой буквы нет");
                System.out.println("Осталось " + --live + " жизней");
            }
            ShowWord();
            if(Arrays.equals(step,word)){
                System.out.println("\nВы выйграли, поздравляю");
                break;
            }
        }
       if(live==0) System.out.println("\nВы проиграли");//todo переделать грамотно
    }



}
