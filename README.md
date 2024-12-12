[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=17317835)
# Unit Deliverable 3 - Final Project : Duel Masters Reward Tracker

My brother and I have been trying to collect all of the cards in the Duel Masters PS2 video game, but It began to take
more and more time to collect the higher-rarity cards. However, it seemed like duels which gave higher rarity cards also
gave more Reputation Points, and we realized that if we could figure out what factors influence those results, we would be able
to determine the most efficient way to keep going after the cards we're still missing.

To that end, this purpose of this project is to have a way to track rewards for the Duel Masters PS2 video game; to create
a database which we can reference to see if there are any trends such as certain opponents giving better cards or more Reputation Points more often.

To achieve this, the user inputs the information they get from the game on each scene, which progress in the order the player
would be receiving the information in-game. Validity is checked at each stage, and at the end a RewardEvent is added to the
eventDatabase.dat binary file.

The user can later choose an event (read from the eventDatabase.dat file) based on the time it was created and see all of the
information associated with that particular reward event.


## Demo

![Demo](src/main/resources/cs112/ud3/Demo.gif)

## UML Diagram

![UMLDiagram](src/main/resources/cs112/ud3/UML.drawio.png)

## Wireframe

![Wireframe](src/main/resources/cs112/ud3/UD3Wireframe.png)