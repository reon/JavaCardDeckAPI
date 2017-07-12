package jdransoft.resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import jdransoft.data.memory.MemoryDeckData;
import jdransoft.model.Deck;
import jdransoft.model.DeckRequest;

/**
 * Resource that defines the API for interacting with decks of cards
 * 
 * @author jacobr
 */
@Path("deck")
public class DeckResource {

	/**
	 * Method for creating a new deck of cards with a specific name
	 * 
	 * @param deckRequest
	 *            contains the name of the deck to create
	 * @return String indicating success or failure
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response putDeck(DeckRequest deckRequest) {
		String deckName = deckRequest.getName();
		ResponseBuilder builder = Response.status(Status.OK).entity("Success").type(MediaType.TEXT_PLAIN);
		if (!MemoryDeckData.getInstance().createDeck(deckName)) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR).entity("There was an issue creating the deck")
					.type(MediaType.TEXT_PLAIN);
		}

		return builder.build();
	}

	/**
	 * Method for shuffling the cards of a deck by providing the name of the deck
	 * 
	 * @param deckRequest
	 *            contains the name of the deck to shuffle
	 * @return String indicating success or failure
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response shuffleDeck(DeckRequest deckRequest) {
		String deckName = deckRequest.getName();
		ResponseBuilder builder = Response.status(Status.OK).entity("Success").type(MediaType.TEXT_PLAIN);
		if (!MemoryDeckData.getInstance().shuffleDeck(deckName)) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR).entity("There was an issue shuffling the deck")
					.type(MediaType.TEXT_PLAIN);
		}

		return builder.build();
	}

	/**
	 * Method for retrieving the collection of decks
	 * 
	 * @return Collection of decks
	 */
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeckList() {
		ResponseBuilder builder = Response.status(Status.NOT_FOUND);
		Collection<String> decks = MemoryDeckData.getInstance().getDecks();
		if (decks != null) {
			builder = Response.status(Status.OK).entity(decks).type(MediaType.APPLICATION_JSON_TYPE);
		}

		return builder.build();
	}

	/**
	 * Method for retrieving a deck of cards by name
	 * 
	 * @param deckName
	 *            String name of the deck to retrieve
	 * @return Deck of cards with the provided name
	 */
	@Path("{deckName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeck(@PathParam("deckName") String deckName) {
		ResponseBuilder builder = Response.status(Status.NOT_FOUND);
		Deck deck = MemoryDeckData.getInstance().getDeck(deckName);
		if (deck != null) {
			builder = Response.status(Status.OK).entity(deck).type(MediaType.APPLICATION_JSON_TYPE);
		}

		return builder.build();
	}

	/**
	 * Method for deleting a deck of cards using the name
	 * 
	 * @param deckRequest
	 *            contains the name of the deck to delete
	 * @return String indicating success or failure
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response deleteDeck(DeckRequest deckRequest) {
		String deckName = deckRequest.getName();
		ResponseBuilder builder = Response.status(Status.OK).entity("Success").type(MediaType.TEXT_PLAIN);
		if (!MemoryDeckData.getInstance().deleteDeck(deckName)) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR).entity("There was an issue deleting the deck")
					.type(MediaType.TEXT_PLAIN);
		}

		return builder.build();
	}
}
