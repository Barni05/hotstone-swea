# TESTLIST
## DECK, HAND, FIELD
- Should have Uno Dos Tres cards in Hand initially for both players
- Both players decks has 4 cards initially (after first draw)
- Initially all cards should be inactive
- Dos card has 2,2,2 as stats
- If player plays a card, his/her hand size decreases by 1
- If player plays a card onto the field at index i, respective player's field i-th index is that card
- When a player draws a card, their deck size decreases by exactly 1
- When a player draws a card, their hand size increases by exactly 1
- When a player draws a card from an empty deck, it returns a NO_MORE_CARDS status, and suffers 2 health penalty
- Given a hands of 3 cards. When the player plays the card from index 0, the card on index 1 shifts to index 0
- When a minion is placed onto the field, it remains inactive for the turn