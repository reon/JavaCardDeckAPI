package jdransoft.data;

import java.util.Collection;

import jdransoft.model.Deck;

/**
 * Interface that defines the Deck data operations that can be performed
 * 
 * @author jacobr
 */
public interface DeckData {
	/**
	 * Create a new deck of cards using the name provided. If a deck with the
	 * provided name already exists, it will be overwritten.
	 * 
	 * @param deckName
	 *            String name of the deck to be created.
	 * @return boolean flag indicating success or failure of data operation
	 */
	public boolean createDeck(String deckName);

	/**
	 * Shuffle the deck with the name provided. If the deck cannot be found, false
	 * will be returned.
	 * 
	 * @param deckName
	 *            String name of the deck to be shuffled
	 * @return boolean flag indicating success or failure of data operation
	 */
	public boolean shuffleDeck(String deckName);

	/**
	 * Return the names of all of the decks that have been saved
	 * 
	 * @return Collection of String names of the saved decks
	 */
	public Collection<String> getDecks();

	/**
	 * Return the deck with the name provided. If the deck cannot be found, null
	 * will be returned.
	 * 
	 * @param deckName
	 *            String name of the deck to be retrieved
	 * @return the requested Deck
	 */
	public Deck getDeck(String deckName);

	/**
	 * Delete the deck with the name provided. If the deck cannot be found, false
	 * will be returned.
	 * 
	 * @param deckName
	 *            String name of the deck to be deleted
	 * @return boolean flag indicating success or failure of data operation
	 */
	public boolean deleteDeck(String deckName);
}
