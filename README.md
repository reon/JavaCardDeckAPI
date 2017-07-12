# Java Card Deck API
This is a Gradle project that builds a microservice to be run in Jetty. The main purpose is to provide an API that allows the creation, retrieval, shuffling, and deletion of named decks of cards.

## API routes
* **PUT /deck** - accepts a JSON body containing the name to use in the creation of a new deck
* **POST /deck** - accepts a JSON body containing the name of the deck to shuffle
* **GET /deck/list** - returns a list of the names of all of the saved decks
* **GET /deck/{deckName}** - returns the deck of cards corresponding to the name provided in the URL
* **DELETE /deck** - accepts a JSON body containing the name of the deck to be deleted

## Build/Deploy instructions
* Clone repository to local workspace
* Make sure that Gradle and Jetty are installed
* Execute "gradle build" at the root of the project
* Copy build/libs/java-card-deck-api.war to your Jetty webapps directory
* Start or restart Jetty service

## Scope reduction
Due to time restraints, I decided to reduce the scope of this project and not implement additional functionality regarding the shuffle algorithm.
