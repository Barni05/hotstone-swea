# TESTLIST
## DECK, HAND, FIELD
- Should have Uno Dos Tres cards in Hand initially for both players✅
- Both players decks has 4 cards initially (after first draw)✅
- Initially all cards should be inactive✅
- Dos card has 2,2,2 as stats✅
- When a player plays a card, their hand size decreases by exactly 1✅
- If player plays a card onto the field at index i, respective player's field i-th index is that card✅
- When a player draws a card, their deck size decreases by exactly 1✅
- When a player draws a card, their hand size increases by exactly 1✅
- When a player draws a card from an empty deck, it returns a NO_MORE_CARDS status✅
- Given a hands of 3 cards. When the player plays the card from index 0, the card on index 1 shifts to index 0✅
- When a minion is placed onto the field, it remains inactive for the turn
- Given Findus has 0 mana, and tries to play a card with >0 mana, the mana remains 0, and it doesn't get added to the field

## HERO 
- Given a new game, each player has 21 health and 3 mana
- if the hero is getting damage, then the health decreases
- every time hero uses its power, it draws 2 crystals from available mana
- Given an initialized Gammastone game, Findus should have ThaiChef as starting hero
- Given an initialized Gammastone game, Peddersen should have DanishChef as a starting hero
- Given an initialized Gammastone game, Findus plays his hero power, Peddersen's health is decreased by 2
- Given an initialized Gammastone game, Peddersen plays his hero power, his field will contain a Sovs card in the beginning
- Player cannot play his/her hero power when its not his/her turn

## TURN MANAGEMENT
- Given a new game, the player in turn should be FINDUS 
- Given it is FINDUS'S turn, when endTurn() is called, PEDDERSEN becomes the active player
- After finished turns, each Player's mana is set to exactly 3
- After turn switch, each player draws a card, meaning the deck size increased by 1
- After at the start of Findus's fifth turn, game ends and Findus is the winner

## COMBAT
- Given Findus has an active card at index 0 with 1 attack, and Peddersen has an inactive card with 2 health, Peddersen's card's health decreases from 2 to 1
- Given an attacking card has 3 health, and a defending card has 2 health and 2 attack. The defending card deals retaliation damage, and the attacking card's health will be 1
- If a card on index 0 is finished, it gets removed from the field
- An attacking card attacks the opponent's hero, when there's no card in the opponents respective field
## WINNING
- Given an initialized gammastone game, in round 4, Peddersen's field is empty and Findus's not, Finus wins
- Given an initialized gammastone game in round 2, when Findus clears Peddersen’s field entirely then getWinner() returns null because the round number has not yet been passed.
- EDGE CASE: Given the game is progressing through round 3 (turn 5 or 6), when Peddersen's field has 0 minions, getwinner still returns null
- Given the game is in round 4, when both Findus and Peddersen have 0 minions on their field, Peddersen wins
- Given the game is in round 4, when both Findus and Peddersen have at least 1 minion in their fields, getwinner is null