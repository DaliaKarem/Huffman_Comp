package Huffman;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.PriorityQueue;
import java.util.Comparator;

class HuffmanNode {
    int countitem;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}
class Compa implements Comparator<HuffmanNode> { //to sub 2 obj
    public int compare(HuffmanNode x1, HuffmanNode x2) {
        return x1.countitem - x2.countitem;
    }
}
public class Compression {
    public static char[] out(HuffmanNode root, String word) throws IOException {
        int i=0;
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
           /* try {
                FileWriter myWriter = new FileWriter("TreeCompression.txt");
                myWriter.write(root.c + "   |  " + word);
               // myWriter.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
            }*/
            System.out.println(root.c + "   |  " + word);

          return new char[0] ;
        }
        out(root.left, word + "0");
        out(root.right, word + "1");

        return new char[0];

    }

    public static void main(String[] args) throws IOException {

        System.out.println("enter char");
        Scanner in=new Scanner(System.in);
        String w =in.nextLine();
       /* try {
            File myObj = new File("CharsCompression.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                w= myReader.nextLine();
                //System.out.println(w);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/


        int count =0,count2 = 0;
        int[] counta = new int[w.length()];
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) != ' ')
                count++;//total num of symbols
        }
        ArrayList<Character> c = new ArrayList<Character>();
        Map<Character, Integer> col = new HashMap<>();
        for (int i = 0; i < w.length(); i++) {
            if (!(c.contains(w.charAt(i)))) {
                c.add(w.charAt(i));// take only one repeated char
            }
        }

       // System.out.println(c);
        count2 = 0;
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < w.length(); j++) {
                if (c.get(i) == w.charAt(j)) {
                    count2++; //calculate num of  char in my string
                }
            }
            counta[i] = count2;
           // System.out.println(c.get(i) + "->" + counta[i]);
            col.put(c.get(i), counta[i]);
            count2 = 0;

        }
        //System.out.println(col);
        PriorityQueue<HuffmanNode> testStrings = new PriorityQueue<HuffmanNode>(c.size(), new Compa());

        for (int i = 0; i < c.size(); i++) {
            HuffmanNode nod = new HuffmanNode();
            nod.c = c.get(i);
            nod.countitem = counta[i];
            nod.left = null;
            nod.right = null;
            testStrings.add(nod);

        }
        HuffmanNode root = null;
        while (testStrings.size() != 1) {
            HuffmanNode v1 = testStrings.peek();
            testStrings.poll();
            HuffmanNode v2 = testStrings.peek();
            testStrings.poll();
            HuffmanNode f = new HuffmanNode();
            f.countitem = v1.countitem + v2.countitem;
            f.c = ' ';
            f.right = v1;
            f.left = v2;
            root = f;
            testStrings.add(f);
        }
       //System.out.println("---------------------------------------");
        //System.out.println("huffman Code");
        //System.out.println("---------------------------------------");
        out(root, " ");


    }
}
//aaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbcccccccccccccccddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeff