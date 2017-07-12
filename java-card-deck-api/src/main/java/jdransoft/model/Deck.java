/**
 * 
 */
package jdransoft.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class that represents a deck of cards and the operations that can be
 * performed on the deck
 * 
 * @author jacobr
 */
public class Deck {

	// Have the names of all of the cards as hard-coded string constants
	public static final String[] CARD_NAMES = new String[] { "A-heart", "2-heart", "3-heart", "4-heart", "5-heart",
			"6-heart", "7-heart", "8-heart", "9-heart", "10-heart", "J-heart", "Q-heart", "K-heart",

			"A-diamond", "2-diamond", "3-diamond", "4-diamond", "5-diamond", "6-diamond", "7-diamond", "8-diamond",
			"9-diamond", "10-diamond", "J-diamond", "Q-diamond", "K-diamond",

			"A-club", "2-club", "3-club", "4-club", "5-club", "6-club", "7-club", "8-club", "9-club", "10-club",
			"J-club", "Q-club", "K-club",

			"A-spade", "2-spade", "3-spade", "4-spade", "5-spade", "6-spade", "7-spade", "8-spade", "9-spade",
			"10-spade", "J-spade", "Q-spade", "K-spade", };

	private String name;

	// Use an ArrayList for the collection of cards that make up a deck, since it's
	// an ordered list
	private ArrayList<String> cards;

	/**
	 * Construct a new deck of cards in the default order
	 */
	public Deck(String deckName) {
		this.name = deckName;
		cards = new ArrayList<String>(Arrays.asList(CARD_NAMES));
	}

	/**
	 * @return the String name of the deck of cards
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the ordered collection of cards that make up a deck
	 */
	public ArrayList<String> getCards() {
		return cards;
	}

	/**
	 * Shuffle the collection of cards
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
}
