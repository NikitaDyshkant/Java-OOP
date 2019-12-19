public class ChatServer {

    public static void main(String[] args) {
        try {
            new ChatServer(45000).run();
        } catch (IOException e) {
            System.out.println("Этот порт занят");
        }
    }

    private ServerSocket serverSocket;
    private Thread serverThread;
    private int port;
    BlockingQueue<SocketProcessor> queueList = new LinkedBlockingQueue<SocketProcessor>();
    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    void run() {
        serverThread = Thread.currentThread();
        while (true) {
            Socket socketRun = null;
                try {
                    socketRun = serverSocket.accept();
                    System.out.println("New user connected");
                } catch (IOException e) {e.printStackTrace();}
                if (socketRun != null){
                try {
                    final SocketProcessor processor = new SocketProcessor(socketRun);
                    ExecutorService exec = Executors.newCachedThreadPool();
                    exec.execute(processor);
                    queueList.offer(processor);
                }
                catch (IOException ignored) {}
            }
        }
    }

    private class SocketProcessor implements Runnable{
        Socket localSocket;
        BufferedReader reader;
        BufferedWriter writer;
        String nameUser;

        SocketProcessor(Socket socketParam) throws IOException {
            localSocket = socketParam;
            reader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(localSocket.getOutputStream()));
        }

        public void run() {
            try {
                nameUser = reader.readLine();
            } catch (IOException e) {
                System.out.println("Потеряно соединение с клиентом");
            }
                send("<" + nameUser + ">", " #Ваше имя#");

                for (SocketProcessor sp: queueList) {
                    if (nameUser!=sp.nameUser){
                        sp.send(nameUser, " #Зашел в чат#");
                    }
                }

            while (!localSocket.isClosed()) { //принимаем сообщение от клиента
                String massage = null;
                try {
                    massage = reader.readLine();
                } catch (IOException e) { close(); }

                if (massage == null) {
                    close();
                } else {
                    for (SocketProcessor sp: queueList) {
                        sp.send(nameUser, massage);
                    }
                }
            }
        }

        public synchronized void send(String userName, String line) { // отправляем сообщение клиенту
            try {
                writer.write(userName + " : " + line);
                writer.write("\n");
                writer.flush();
            } catch (IOException e) { close(); }
        }

        public synchronized void close() {
            queueList.remove(this);
            if (!localSocket.isClosed()) {
                try {
                    localSocket.close();
                    System.out.println(nameUser + " closed Chat");
                    for (SocketProcessor sp: queueList) {
                            sp.send(nameUser, " #closed Chat#");
                    }
                } catch (IOException ignored) {}
            }
        }
    }
}