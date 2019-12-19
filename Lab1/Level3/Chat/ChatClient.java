public class ChatClient {
    static ChatClient chatClient;
    final Socket socket;
    final BufferedReader socketReader;
    final BufferedWriter socketWriter;
    final BufferedReader userInput;

    private static String CheckMassage = "";

    public static void main(String[] args)  {
        try {
            chatClient = new ChatClient("localhost", 45000);
            chatClient.run();
        } catch (IOException e) {
            GuiForm.changeArea("%%%Unable to connect. Server not running?%%%");
        }
    }

    public ChatClient(String host, int port) throws IOException {
        new GuiForm();
        socket = new Socket(host, port);
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        userInput = new BufferedReader(new InputStreamReader(System.in));
        new Thread(new Receiver()).start();
    }

    public static void setCheckMassage(String string){
        synchronized (CheckMassage){
            CheckMassage.notify();
            CheckMassage = string;
        }
    }

    private String getCheckMassage(){
        synchronized (CheckMassage){
            try {
                CheckMassage.wait();
            } catch (InterruptedException e) { e.printStackTrace();}
        }
        return CheckMassage;
    }

    private class Receiver implements Runnable{
        public void run() {
            while (!socket.isClosed()) {
                String line = null;
                try {
                    line = socketReader.readLine();
                } catch (IOException e) {
                    GuiForm.changeArea("---Connection lost---");
                    close();
                }
                    GuiForm.changeArea(line);
            }
        }
    }

    public void run() {
        GuiForm.changeArea("---Введите свое имя---");
        while (true) {
            String userString = getCheckMassage();
            try {
                socketWriter.write(userString);
                socketWriter.write("\n");
                socketWriter.flush();
            } catch (IOException e) {
                close();
            }
        }
    }

    public synchronized void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                System.exit(0);
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

}