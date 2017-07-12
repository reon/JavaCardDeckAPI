# Java Card Deck API
This is a Gradle project that builds a microservice to be run in Jetty. The main purpose is to provide an API that allows the creation, retrieval, shuffling, and deletion of named decks of cards.

## API routes
* **PUT /deck** - accepts a JSON body containing the name to use in the creation of a new deck
* **POST /deck/shuffle** - accepts a JSON body containing the name of the deck to shuffle
* **GET /deck/list** - returns a list of the names of all of the saved decks
* **GET /deck/{deckName}** - returns the deck of cards corresponding to the name provided in the URL
* **DELETE /deck/{deckName}** - deletes the deck of cards which has the provided name

## Build/Deploy instructions
* Clone repository to local workspace
* Make sure that Gradle and Jetty are installed
* Execute "gradle build" at the root of the project
* Copy build/libs/java-card-deck-api.war to your Jetty webapps directory. For example, I used the following command from the root direclty of the project: "sudo cp build/libs/java-card-deck-api.war /usr/share/jetty9/webapps"
* Start or restart Jetty service (I used the following in my dev VM: sudo service jetty9 restart)

## Scope reduction
Due to time restraints, I decided to reduce the scope of this project and not implement additional functionality regarding the shuffle functionality. I didn't define a custom shuffle algorithm, to simulate a more realistic shuffle, and I didn't make the shuffle algorithm configurable at runtime.
