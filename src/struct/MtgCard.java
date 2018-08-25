package struct;


import java.util.*;

public class MtgCard {
    private String name, cost, type, text;
    private int totalCost, toughness, power;
    private CardType cardType;
    private Color color;

    public enum CardType{
        CREATURE, SPELL, LAND
    }

    public enum Color {
        WHITE, BLUE, BLACK, RED, GREEN, MULTICOLOR, COLORLESS, LAND
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        String card = formatter.format("Type:%s, Name:%s, Cost:%s, Text:%s", type, name, cost, text).toString();
        if (power != -1){ // format somehow output all formatted strings appended ?? so I don't need to append.
            card = formatter.format(", Pow:%d, Tough:%d", power, toughness).toString();
        }
        formatter.close();
        return card;
    }
    
    public MtgCard(String type, String name, String cost, String text, int power, int toughness){
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.text = text;
        this.power = power;
        this.toughness = toughness;
        setCost();
        setType();
        setColor();
    }
    private void setCost(){
        if ('0' <= cost.charAt(0) &&  cost.charAt(0) <= '9'){
            totalCost = Integer.parseInt(cost.substring(0, 1)) - 1;
        }
        else if (cost.charAt(0) == 'X'){
            totalCost = -1;
        }
        else {
            totalCost = 0;
        }
        totalCost += cost.length();
    }

    private void setType(){
        if (type.toLowerCase().contains("creature"))
        	this.cardType = CardType.CREATURE;
        else if (type.toLowerCase().contains("land"))
        	this.cardType = CardType.LAND;
        else
        	this.cardType = CardType.SPELL;
    }
    private void setColor(){
    	char c[] = cost.toCharArray();
    	int idx = 0;
    	
    	if (Character.isDigit(c[0]) || c[0] == 'X') {
    		idx = 1;
    	}
    	if (idx == c.length) {
    		color = Color.COLORLESS;
    		return ;
    	}
    	switch (c[idx]){
		    case 'U':
		        color = Color.BLUE; break;
		    case 'B':
		        color = Color.BLACK; break;
		    case 'R':
		        color = Color.RED; break;
		    case 'W':
		        color = Color.WHITE; break;
		    case 'G':
		        color = Color.GREEN; break;
    	} 
    	char col = c[idx];
    	for (++idx; idx < c.length; ++idx) {
    		if (c[idx] != col) {
    			color = Color.MULTICOLOR;
    			return ;
    		}
    	}
    	
    }

    public CardType getType(){
        try {
            return this.cardType;
        }
        catch (Exception e) {
            System.out.print("type!!");
            System.out.print(e);
            return CardType.LAND;
        }
    }
    public Color getColor(){
        try {
            return this.color;
        }
        catch (Exception e) {
            System.out.print("color!!");
            System.out.print(e);
            return Color.COLORLESS;
        }
    }
    public int getCost(){
        return totalCost;
    }
    public String getName(){
        return name;
    }
    
    public static int compareByType(MtgCard c1, MtgCard c2) {
    	if (c1.cardType.ordinal() - c2.cardType.ordinal() != 0) {
    		return c1.cardType.ordinal() - c2.cardType.ordinal();
    	}
    	return compareGeneric(c1, c2);
    }
    
    public static int compareByColor(MtgCard c1, MtgCard c2) {
    	if (c1.color.ordinal() - c2.color.ordinal() != 0) {
    		return c1.color.ordinal() - c2.color.ordinal();		
    	}
    	return compareGeneric(c1, c2);
    
    }
    
    public static int compareGeneric(MtgCard c1, MtgCard c2) {
    	if (c1.getCost()-c2.getCost() != 0)
    		return c1.getCost()-c2.getCost();
    	return c1.name.compareTo(c2.name);
    }
    
}
