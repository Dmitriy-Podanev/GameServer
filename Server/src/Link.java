import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Link implements Closeable {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Link(String ip,int port){
        try {
            this.socket = new Socket(ip,port);
            this.reader = getReader();
            this.writer = getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Link(ServerSocket serverSocket){
        try {
            this.socket = serverSocket.accept();
            this.reader = getReader();
            this.writer = getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void  WriteLine(String massage) {
        try {
            writer.write(massage);
            writer.newLine();
            writer.flush();

        }catch (Exception e){throw new RuntimeException(e);}
    }

    public String ReadLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedWriter getWriter() throws IOException {
            return  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private BufferedReader getReader() throws IOException {
            return  new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();

    }
}
