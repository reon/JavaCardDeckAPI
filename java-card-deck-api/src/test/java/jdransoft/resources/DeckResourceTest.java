package jdransoft.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;

import jdransoft.data.memory.MemoryDeckData;
import jdransoft.model.Deck;
import jdransoft.model.DeckRequest;

/**
 * Test the Deck resource
 * 
 * @author jacobr
 */
public class DeckResourceTest extends JerseyTest {

	private static final String TEST_DECK_NAME = "testDeck";
	private static final String NONEXISTANT_DECK_NAME = "nonexistantDeck";

	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(DeckResource.class);
	}

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new JettyTestContainerFactory();
	}

	/**
	 * Test that a new deck can be successfully created
	 */
	@Test
	public void testPutDeckMethod() {
		DeckRequest request = new DeckRequest();
		request.setName(TEST_DECK_NAME);
		Response output = target("/deck").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));
		assertNotNull("Should return deck by name", MemoryDeckData.getInstance().getDeck(TEST_DECK_NAME));
	}

	/**
	 * Test that a deck can be successfully shuffled
	 */
	@Test
	public void testShuffleDeckMethod() {
		DeckRequest request = new DeckRequest();
		request.setName(TEST_DECK_NAME);

		// Make sure that the deck exists
		Response output = target("/deck").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));
		Deck deck = MemoryDeckData.getInstance().getDeck(TEST_DECK_NAME);
		ArrayList<String> originalCards = new ArrayList<String>(deck.getCards());

		// Test shuffling the deck
		output = target("/deck/shuffle").request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));
		deck = MemoryDeckData.getInstance().getDeck(TEST_DECK_NAME);
		assertFalse("The collection of cards should be different", deck.getCards().equals(originalCards));

		// Test failure when trying to shuffle a deck that doesn't exist
		request.setName(NONEXISTANT_DECK_NAME);
		output = target("/deck/shuffle").request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 500", 500, output.getStatus());
	}

	/**
	 * Test that the list of decks can be successfully retrieved
	 */
	@Test
	public void testGetDecksMethod() {
		// Make sure that the expected deck exists
		DeckRequest request = new DeckRequest();
		request.setName(TEST_DECK_NAME);
		Response output = target("/deck").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));

		// Test retrieving the list of decks
		output = target("/deck/list").request().get();
		assertEquals("Should return status 200", 200, output.getStatus());
		@SuppressWarnings("unchecked")
		Collection<String> decks = output.readEntity(Collection.class);
		assertTrue("Should return 'Success'", decks.contains(TEST_DECK_NAME));
	}

	/**
	 * Test that a deck can be successfully retrieved
	 */
	@Test
	public void testGetDeckMethod() {
		// Make sure that the expected deck exists
		DeckRequest request = new DeckRequest();
		request.setName(TEST_DECK_NAME);
		Response output = target("/deck").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));

		// Test retrieving the deck
		output = target("/deck/" + TEST_DECK_NAME).request().get();
		assertEquals("Should return status 200", 200, output.getStatus());
		// Read into a String, since Deck doesn't have a zero parameter constructor or
		// setters for Jackson to use
		String deck = output.readEntity(String.class);
		assertTrue("Should return 'Success'", deck.contains("\"name\":\"" + TEST_DECK_NAME + "\""));

		// Test failure when trying to retrieve a deck that doesn't exist
		output = target("/deck/" + NONEXISTANT_DECK_NAME).request().get();
		assertEquals("Should return status 404", 404, output.getStatus());
	}

	/**
	 * Test that a deck can be successfully deleted
	 */
	@Test
	public void testDeleteDeckMethod() {
		// Make sure that the deck exists
		DeckRequest request = new DeckRequest();
		request.setName(TEST_DECK_NAME);
		Response output = target("/deck").request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));

		// Test deleting the deck
		output = target("/deck/" + TEST_DECK_NAME).request().delete();
		assertEquals("Should return status 200", 200, output.getStatus());
		assertEquals("Should return 'Success'", "Success", output.readEntity(String.class));
		output = target("/deck/" + TEST_DECK_NAME).request().get();
		assertEquals("Should return status 404", 404, output.getStatus());

		// Test failure when trying to shuffle a deck that doesn't exist
		output = target("/deck/" + NONEXISTANT_DECK_NAME).request().delete();
		assertEquals("Should return status 500", 500, output.getStatus());
	}
}
