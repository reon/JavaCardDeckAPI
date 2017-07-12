package jdransoft.data.memory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import jdransoft.data.DeckData;
import jdransoft.model.Deck;

/**
 * Test the MemoryDeckData class
 * 
 * @author jacobr
 */
public class MemoryDeckDataTest {

	private static final String TEST_DECK_NAME = "testDeck";

	/**
	 * Test that the createDeck method creates a new deck with the default
	 * collection of cards
	 */
	@Test
	public void testCreateDeckMethod() {
		DeckData classUnderTest = MemoryDeckData.getInstance();
		classUnderTest.createDeck(TEST_DECK_NAME);
		Collection<String> decks = classUnderTest.getDecks();
		assertTrue("getDecks should return a collection of one", decks.size() == 1);
		String deck = decks.iterator().next();
		assertTrue("the created deck should have the correct name", deck.equals(TEST_DECK_NAME));
		classUnderTest.createDeck(TEST_DECK_NAME);
		assertTrue("getDecks should still return a collection of one", decks.size() == 1);
	}

	/**
	 * Test that the shuffleDeck method shuffles the cards for the deck specified
	 */
	@Test
	public void testShuffleDeckMethod() {
		DeckData classUnderTest = MemoryDeckData.getInstance();
		classUnderTest.createDeck(TEST_DECK_NAME);
		Deck deck = classUnderTest.getDeck(TEST_DECK_NAME);
		ArrayList<String> originalCollection = new ArrayList<String>(deck.getCards());
		classUnderTest.shuffleDeck(TEST_DECK_NAME);
		assertFalse("getCards should return a different collection than the previous collection of cards",
				classUnderTest.getDeck(TEST_DECK_NAME).getCards().equals(originalCollection));
	}

	/**
	 * Test that the getDecks method returns the expected collection of decks
	 */
	@Test
	public void testGetDecksMethod() {
		DeckData classUnderTest = MemoryDeckData.getInstance();
		classUnderTest.createDeck(TEST_DECK_NAME);
		assertTrue("getDecks should return a collection of one from the previous test",
				classUnderTest.getDecks().size() == 1);
	}

	/**
	 * Test that the getDeck method returns the expected deck
	 */
	@Test
	public void testGetDeckMethod() {
		DeckData classUnderTest = MemoryDeckData.getInstance();
		classUnderTest.createDeck(TEST_DECK_NAME);
		Deck deck = classUnderTest.getDeck(TEST_DECK_NAME);
		assertTrue("the retrieved deck should have the correct name", deck.getName().equals(TEST_DECK_NAME));
		assertTrue("the retrieved deck should have the same collection as the constant array of names",
				deck.getCards().equals(Arrays.asList(Deck.CARD_NAMES)));
	}

	/**
	 * Test that the deleteDeck method deletes the expected deck
	 */
	@Test
	public void testDeleteDeckMethod() {
		DeckData classUnderTest = MemoryDeckData.getInstance();
		classUnderTest.createDeck(TEST_DECK_NAME);
		Deck deck = classUnderTest.getDeck(TEST_DECK_NAME);
		assertTrue("the retrieved deck should have the correct name", deck.getName().equals(TEST_DECK_NAME));
		classUnderTest.deleteDeck(TEST_DECK_NAME);
		assertTrue("the specified deck should no longer be found", classUnderTest.getDeck(TEST_DECK_NAME) == null);
	}
}
