package harfAlayim;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;





public class Main {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static List<String> words = new ArrayList<>();
    static String selectedWord;
    static char[] tempSelectedWord;
    static int selectedIndex;
    static char mod;
    static String myTahmin;

    /*
   1. kelime listesinden rastgele kelime seç, program
   2. kullanıcıya her seferinde harfmi tahminmi  diye sor
   3. harf ise -> rastgele bir harf aç ör: --o---,
   4. tahmin ise -> kullanıcıdan tahmin alınır tahmin == kelime congrations, else unfortunatly
   5. döngü harf iste ör: p-o--- -> p-o--a-
     */
    public static void main(String[] args) throws Exception {

        File file = new File("src/harfAlayim/words.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            words.add(st);
        }


        selectedWord = words.get(random.nextInt(0, words.size()));
        tempSelectedWord = new char[selectedWord.length()];

        for (int i = 0; i < selectedWord.length(); i++) {
            tempSelectedWord[i] = '-';
        }

        System.out.println(tempSelectedWord);
        boolean isFinish = false;
        int hak = 3;
        int puan = selectedWord.length() * 100;
        while (!isFinish) {
            if (hak <= 0)
                break;
            System.out.print("harf istiyosan 'H' veye 'h' tusuna bas\n tahmin yapacaksan 'T' 't' tusuna bas");
            mod = scanner.next().charAt(0);
            switch (mod) {
                case 'T', 't':
                    myTahmin = scanner.next();
                    if (myTahmin.equals(selectedWord)) {
                        System.out.println("tebrikler: paunınz: " + puan);
                        isFinish = true;
                    } else {
                        hak--;
                        System.out.println(hak + "  hak kaldı tahmin için");
                    }

                    break;
                case 'H', 'h':
                    puan -= 100;
                    char selectedChar = giveMeChar();
                    if (Arrays.toString(tempSelectedWord).equals(selectedWord))
                        isFinish = true;
                    for (int i = 0; i < selectedWord.length(); i++) {
                        if (i == selectedIndex)
                            tempSelectedWord[i] = selectedChar;
                    }
                    System.out.println(tempSelectedWord);
                    break;
                default:
                    System.out.println("lutfen doğru seçim yapın");
            }

        }
        System.out.println("--------------------------------------");
        System.out.println("GAME OVER");
        System.out.println(selectedWord);
        System.out.println("--------------------------------------");


    }

    public static char giveMeChar() {
        while (true) {
            selectedIndex = random.nextInt(0, selectedWord.length());
            if (tempSelectedWord[selectedIndex] == '-')
                break;
        }
        return selectedWord.charAt(selectedIndex);
    }
}