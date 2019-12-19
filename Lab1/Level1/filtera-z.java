import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
 
public class Sort {
    static private class MyString implements Comparable<MyString> {
      
        private int sortIndex = -1;
        private String interString;
 
        MyString(String str, int wordNum) {
            this.interString = str;
            this.sortIndex = wordNum;
        }
 
        public String getStr() {
            return interString;
        }

        public int compareTo(MyString obj) {

            if(sortIndex == -1) return interString.length()-obj.getStr().length();
 
            String str1 = null, str2 = null;
 
            try
            {
                str1 = interString.split(" ")[sortIndex];
            }
            catch (Exception e)
            {
                return -1;
            }
            try
            {
                str2 = obj.getStr().split(" ")[sortIndex];
            }
            catch (Exception e)
            {
                return 1;
            }
            return str1.compareTo(str2);
        }
    }
 
    public static void main(String[] args) {
        int sortIndex = -1;
        if(args.length != 0) sortIndex = Integer.valueOf(args[0]);
 
        ArrayList<MyString> sortList = new ArrayList<MyString>();
 
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите список для сортировки: ");

        while(true) {
            String tempStr = sc.nextLine();
            if(tempStr.equals("end")) break;
            sortList.add(new MyString(tempStr, sortIndex));
        }
 
        Collections.sort(sortList);
 
        Iterator iter = sortList.iterator();
        System.out.println("\nОтсортированный список: ");
 
        while(iter.hasNext()) {
            System.out.println( ((MyString) iter.next()).getStr());
        }
        sc.close();
    }
}