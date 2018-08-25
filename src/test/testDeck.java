package test;

import struct.Deck;

public class testDeck {
    public static void main(String args[]){
    	System.out.println(System.getProperty("user.dir"));
        Deck d = new Deck( System.getProperty("user.dir") + "\\src\\test\\Thopter.txt");
        d.print();
        System.out.println("-------------------------------------");	
        d.printByType();
        System.out.println("-------------------------------------");
        d.printByColor();


    }
}
