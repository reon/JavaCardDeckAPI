package jdransoft.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the DeckRequest class
 * 
 * @author jacobr
 */
public class DeckRequestTest {

	private static final String TEST_DECK_NAME = "testDeck";

	/**
	 * Test that the getName method returns the expected name
	 */
	@Test
	public void testGetNameMethod() {
		DeckRequest classUnderTest = new DeckRequest();
		classUnderTest.setName(TEST_DECK_NAME);
		assertTrue("getName should return '" + TEST_DECK_NAME + "'", classUnderTest.getName().equals(TEST_DECK_NAME));
	}
}
