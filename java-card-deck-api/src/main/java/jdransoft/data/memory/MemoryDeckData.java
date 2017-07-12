/**
 * 
 */
package jdransoft.data.memory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import jdransoft.data.DeckData;
import jdransoft.model.Deck;

/**
 * In-memory implementation of the DeckData interface. Uses a Singleton so that
 * there is only one in-memory collection.
 * 
 * @author jacobr
 */
public class MemoryDeckData implements DeckData {

	private static MemoryDeckData data = new MemoryDeckData();
	private Map<String, Deck> decks;

	/**
	 * Private constructor so that it can only be used once
	 */
	private MemoryDeckData() {
		decks = new LinkedHashMap<String, Deck>();
	}

	/**
	 * Get the data instance for maintaining the collection of maps
	 * 
	 * @return MemoryDeckData which maintains the collection of maps
	 */
	public static DeckData getInstance() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jdransoft.data.DeckData#createDeck(java.lang.String)
	 */
	@Override
	public boolean createDeck(String deckName) {
		decks.put(deckName, new Deck(deckName));
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jdransoft.data.DeckData#shuffleDeck(java.lang.String)
	 */
	@Override
	public boolean shuffleDeck(String deckName) {
		if (decks.containsKey(deckName)) {
			decks.get(deckName).shuffle();
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jdransoft.data.DeckData#getDecks()
	 */
	@Override
	public Collection<String> getDecks() {
		return decks.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jdransoft.data.DeckData#getDeck(java.lang.String)
	 */
	@Override
	public Deck getDeck(String deckName) {
		return decks.get(deckName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jdransoft.data.DeckData#deleteDeck(java.lang.String)
	 */
	@Override
	public boolean deleteDeck(String deckName) {
		if (decks.containsKey(deckName)) {
			decks.remove(deckName);
			return true;
		}
		return false;
	}

}
