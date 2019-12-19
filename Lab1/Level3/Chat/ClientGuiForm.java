public class GuiForm extends JFrame{

    eHandler handler = new eHandler();
    JButton jButton;
    JTextField jTextField;
    private static ArrayList<String> listString = new ArrayList<String>();
    private static final int quantityLines = 27;
    private static JScrollPane jScroll;
    private static JTextArea jText;

    GuiForm(){
        GuiForm GuiForm = new GuiForm("Chat");
        GuiForm.setLayout(new FlowLayout());
        GuiForm.setVisible(true);
        GuiForm.setSize(700, 505);
        GuiForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GuiForm.setResizable(false);
        GuiForm.setLocationRelativeTo(null);
        
        GuiForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                ChatClient.chatClient.close();
                System.exit(0);
            }
        });
        GuiForm.loadLogChat();
    }

    public GuiForm(String str){
        super(str);
        new FileReaderChat();
        jTextField = new JTextField(50);
        jButton = new JButton("Отправить");

        final int jTextCountColumns = 60; //количество символов в строке
        final int jTextCountLine = quantityLines; // строк

        jText = new JTextArea(jTextCountLine, jTextCountColumns);
        jText.setBackground(null);
        jText.setLineWrap(true);

        jText.addKeyListener(new java.awt.event.KeyAdapter() { // Запрет ввода текста в JTextArea
            public void keyTyped(java.awt.event.KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)
                        && (a != '.')
                        && (a != '\b')) {
                    e.consume();
                }
            }
        });

        jScroll = new JScrollPane(jText);
        jScroll.setBackground(null);
        jScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jScroll);

        add(jTextField);
        add(jButton);

        jButton.addActionListener(handler);

        for(int i =0 ; i<29 ; i++){
            listString.add("");
        }

        jTextField.addKeyListener(new KeyAdapter() { // слушатель нажатия ENTER
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    ChatClient.setCheckMassage(jTextField.getText());
                    jTextField.setText("");
                }
            }
        });
    }

    public static void changeArea(String str){  // Печать текста в JTextArea
            listString.remove(0);
            listString.add(str);
        String localString = "";
        for (String strs : listString){
            localString +=strs + "\n";
        }

        jText.setText(localString);
        jScroll.revalidate();

        saveLogChat();
    }

    static void loadLogChat(){ //загружает лог сообщений из файла
        String [] localStringArray = FileReaderChat.fileReader();
        for (String str: localStringArray){
            changeArea(str);
        }
    }

    static void saveLogChat(){ //сохраняет лог сообщений в файл
        ArrayList<String> listString = new ArrayList<String>();
        for (String localJTextArea: GuiForm.listString){
            listString.add(localJTextArea);
        }
        FileReaderChat.writeFile(listString);
    }

    public class eHandler implements ActionListener { // слушатель кнопки отправить
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==jButton){
                ChatClient.setCheckMassage(jTextField.getText());
                jTextField.setText("");
            }
        }
    }

}