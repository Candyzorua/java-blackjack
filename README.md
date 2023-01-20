# Blackjack Scorekeeper
#### 2022W2 CPSC 210 Term Project by Jiaying Ong

<br>

## Project Description

This desktop app is made for groups of friends who:
- Want to play blackjack,
- Have a deck of cards on hand,
- Don't want to gamble with real money, and
- Don't own a large amount of casino chips.

Blackjack is a classic and fun game, but certain things take the fun out of it -- for instance, the illegality of gambling with money in some countries, the prospect of losing a month's worth of rent to your friends, and the unavailability of casino chips in many situations. This app intends to make Blackjack a hassle-free experience in which _only a deck of cards_ is needed. 

At first, the player will be shown a title screen with "New Game" and "Continue Game". Upon clicking either of these, a player configuration menu will be displayed where players can be added or deleted, and the dealer is designated. A round then starts, which follows the usual gameplay of Blackjack (sans special rules such as insurance, surrender, or double-down). During the round, the app automates payouts depending on whether the players or dealer "bust" or "stand". Players can play as many rounds of Blackjack as they like, with the scores kept across rounds if players continue to the next round. In between rounds, there will be options to continue to the next round, go to the player configuration menu, or save and quit to the title screen.

## User Stories

### General
- As a user, I want to be able to click an exit button at any time to close the application.

### Title Screen
- As a user, I want to be able to click "New Game" to enter the player configuration menu of a new game, where the scores of all players are 0, when I'm at the title screen.
- As a user, I want to be able to click "Continue Game" to enter the player configuration menu of a previously played game, where the scores of all players are conserved from the previously played game.

### Player Configuration Menu
- As a user, I want to be able to add or delete players on the player configuration menu.
- As a user, I want to be able to designate a player as the dealer. If there is already a dealer and I designate a non-dealer player as the dealer then the dealer becomes a non-dealer player.
- As a user, I want to be able to view players' scores beside their entries in the player configuration menu.

### Gameplay Screen
- As a user, I want to be able to record the wager for every player at the start of every round.
- As a user, I want to be able to indicate whether or not the dealer has a blackjack at the start of every round, and whether or not any other player has a blackjack.
- As a user, I want to be able to indicate whether a certain non-dealer player "busts" or "stands" when it's their turn to be dealt cards by the dealer.
- As a user, I want to be able to indicate whether the dealer "busts" or "stands" when they deal cards to himself or herself.
- As a user, I want to be able to see the status of every player at any moment in the round, be it "bust", "stand", or "pending", their wager, and also their score.
- As a user, at the end of every round, I want to be displayed a summary of player scores, and to be given the option to save and quit to title screen, enter the player configuration menu, or enter a new round.

