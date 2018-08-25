package struct;
import struct.MtgCard;
import struct.DeckEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Deck {
	
	 // create a comparator class which implements Comparator<T> class
	 // to be used by vector<T>.sort() 
	 // T is value type to compare (in this case DeckEntry)
    public class CompareByType implements Comparator<DeckEntry> {
        @Override
        // negative number means x comes before y
        // zero = equal
        // positive number means x comes after y 
		public int compare(DeckEntry x, DeckEntry y){
        	// implement how you compare two values here
        	// this implementation call class (static) method defined in MtgCard.java
        	return MtgCard.compareByType(x.card, y.card);
        }
    }
    public class CompareByColor implements Comparator<DeckEntry> {
        @Override
        public int compare(DeckEntry x, DeckEntry y){
        	return MtgCard.compareByColor(x.card, y.card);
        }
    }
    
    private Vector<DeckEntry> cards = new Vector<DeckEntry>(),
            cardC = new Vector<DeckEntry>(), // cards sorted by Color
            cardT = new Vector<DeckEntry>(); // cards sorted by Type
    public Deck(String fileName){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String currentLine;

            while ((currentLine = br.readLine()) != null){
                String data[] = currentLine.split(", ");
                for (int i=0; i<data.length; i++)
                    data[i] = data[i].trim();
                String type = data[0], name = data[1], cost = data[2], text = data[3];
                int power, toughness, count;
                power = (data[4].equals("null"))? -1 :  Integer.parseInt(data[4]);
                toughness = (data[4].equals("null"))? -1 :  Integer.parseInt(data[5]);
                count = Integer.parseInt(data[6]);
                cards.add(new DeckEntry(new MtgCard(type, name, cost, text, power, toughness), count));
            }
            br.close();
            cardC = new Vector<DeckEntry>(cards);
            cardC.sort(new CompareByColor());
            cardT = new Vector<DeckEntry>(cards);
            cardT.sort(new CompareByType());
        }
        catch (IOException e){
                e.printStackTrace();
        }
    }
    public void print(){
        for (DeckEntry p: cards){
            System.out.println(p.card);
        }
    }
    public void printByType(){
        for (DeckEntry p: cardT){
            System.out.println(p.card);
        }
    }
    public void printByColor(){
        for (DeckEntry p: cardC){
            System.out.println(p.card);
        }
    }
}
