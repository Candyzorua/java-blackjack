# Java Blackjack 
#### 2022W2 CPSC 210 Term Project by Jiaying Ong

<br>

## Project Description

This desktop app is made for groups of friends who:
- Want to play blackjack,
- Have a deck of cards on hand,
- Don't want to gamble with real money, and
- Don't have a large amount of casino chips on hand.

Blackjack is a classic and fun game, but certain things take the fun out of it -- for instance, the illegality of gambling with money in some countries, the prospect of losing hard-earned money, and the unavailability of casino chips in many situations. This app assists with scorekeeping for Blackjack, and intends to make Blackjack a hassle-free experience in which _only a deck of cards_ is needed. This project interests me because I am a huge fan of tabletop games, and often find myself in a position where a scorekeeping tool would help.

At first, the player will be shown a title screen with 
"New Game" and "Continue Game". Upon clicking either of these, 
a player configuration menu will be displayed where players 
can be added or deleted, and the dealer is designated. 
A round then starts, which follows the usual gameplay of 
Blackjack (sans special rules such as insurance, surrender, 
or double-down). At the start of each round, each player
is dealt two cards as in standard Blackjack. Wagers are recorded, and each player
then takes turns to either "hit" or "stand" if they do not 
obtain Blackjack, which puts them at risk of becoming "bust".
The dealer is the last to "hit", "stand", or bust. Payouts are then automated 
according to the results. Players can 
play as many rounds of Blackjack as they like, with the scores kept 
across rounds if players continue to the next round. In between rounds, 
there will be options to continue to the next round, go to the player configuration 
menu, or save and quit to the title screen.

## User Stories

### General
- As a user, I want to be able to click an exit button at any time to close the application.

### Title Screen
- As a user, I want to be able to click "New Game" to enter the player configuration menu of a new game, where the scores of all players are 0.
- As a user, I want to be able to click "Continue Game" to enter the player configuration menu of a previously played game, where the scores of all players are conserved from the previously played game.

### Player Configuration Menu
- As a user, I want to be able to add or delete players.
- As a user, I want to be able to designate a player as the dealer. If there is already a dealer and I designate a non-dealer player as the dealer then the dealer becomes a non-dealer player.
- As a user, I want to be able to see all player names and scores.

### Gameplay Screen
- As a user, I want to be able to record the wager for every player at the start of every round.
- As a user, I want to be able to "hit" or "stand", after being dealt cards.
- As a user, I want to be able to see the status of every player at any moment in the round, be it "bust", "stand", or "pending", their current wager, and also their current score.
- As a user, at the end of every round, I want to be displayed a summary of player scores, and to be given the option to save and quit to title screen, enter the player configuration menu, or enter a new round.

