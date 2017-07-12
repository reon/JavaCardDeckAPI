package jdransoft.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Test the Deck class
 * 
 * @author jacobr
 */
public class DeckTest {

	private static final String TEST_DECK_NAME = "testDeck";

	/**
	 * Test that the getName method returns the expected name and indirectly tests
	 * the constructor as well
	 */
	@Test
	public void testGetNameMethod() {
		Deck classUnderTest = new Deck(TEST_DECK_NAME);
		assertTrue("getName should return '" + TEST_DECK_NAME + "'", classUnderTest.getName().equals(TEST_DECK_NAME));
	}

	/**
	 * Test that the getCards method returns the collection of cards and indirectly
	 * tests the constructor as well
	 */
	@Test
	public void testGetCardsMethod() {
		Deck classUnderTest = new Deck(TEST_DECK_NAME);
		assertTrue("getCards should return the same collection as the constant array of names",
				classUnderTest.getCards().equals(Arrays.asList(Deck.CARD_NAMES)));
	}

	/**
	 * Test that the shuffle method randomizes the order of the deck of cards
	 */
	@Test
	public void testShuffleMethod() {
		Deck classUnderTest = new Deck(TEST_DECK_NAME);
		classUnderTest.shuffle();
		assertFalse("getCards should return a different collection than the constant array of names",
				classUnderTest.getCards().equals(Arrays.asList(Deck.CARD_NAMES)));
	}
}
