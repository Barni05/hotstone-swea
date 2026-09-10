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
- When a minion is placed onto the field, it remains inactive for the turn✅
- Given Findus has 0 mana, and tries to play a card with >0 mana, the mana remains 0, and it doesn't get added to the field

## HERO 
- Given a new game, each player has 30 health and 3 mana
- if the hero is getting damage, then the health decreases
- every time hero uses its power, it draws 2 crystals from available mana

## TURN MANAGEMENT
- Given a new game, the player in turn should be FINDUS 
- Given it is FINDUS'S turn, when endTurn() is called, PEDDERSEN becomes the active player
- After finished turns, each Player's mana is set to exactly 3
- After turn switch, each player draws a card, meaning the deck size increased by 1