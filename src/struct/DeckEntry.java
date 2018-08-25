package struct;
import struct.MtgCard;

public class DeckEntry {
	public MtgCard card;
	public int count;
	
	public DeckEntry(MtgCard card, int count) {
		this.card = card;
		this.count = count;
	}
}
