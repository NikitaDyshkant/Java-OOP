public class FileReaderChat {
    final static String ABSOLUTE_PATHNAME = "logChat.txt";
    static File file = new File(ABSOLUTE_PATHNAME);
    static FileWriter writeFile = null;

    public static String[] fileReader(){ // Чтение файла
        String [] linesAsArray = null;
        BufferedReader reader;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }else{
                reader = new BufferedReader(new FileReader(file));
                String line;
                List<String> lines = new ArrayList<String>();

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                linesAsArray = lines.toArray(new String[lines.size()]);
            }
        } catch (FileNotFoundException e) { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace();}
        return linesAsArray;
    }

    public static void writeFile(ArrayList<String> listString){ //Запись файла
        try {
            writeFile = new FileWriter(file);
            for (String str : listString)
            writeFile.write(str+"\n");
        } catch (IOException e) { e.printStackTrace();
        } finally {
            if(writeFile != null) {
                try {
                    writeFile.close(); } catch (IOException e)
                {e.printStackTrace();}
            }
        }
    }

}