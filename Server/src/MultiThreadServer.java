import java.io.IOException;
import java.net.ServerSocket;

public class MultiThreadServer {
    public static void main(String[] args) {

       try( ServerSocket serverSocket = new ServerSocket(8500))
       {

           System.out.println("Server started!");

           while(true)
           {

               Link link =new Link(serverSocket);
               new Thread(()->{

                    GameObject gameObject = new GameObject();
                    gameObject.ChooseWord();
                    gameObject.GameStart();
                    
                   String request = link.ReadLine();
                   System.out.println("Вы ввели: "+ request);
                   String s = "Hello from server: "+ request.length();
                   System.out.println("s: "+ s);
                   link.WriteLine(s);
                   try {link.close();} catch (IOException e) {e.printStackTrace();}

               }).start();

           }

       }catch (IOException e){ throw  new RuntimeException();}


    }
}