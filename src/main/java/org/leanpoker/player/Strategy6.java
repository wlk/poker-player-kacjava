package org.leanpoker.player;

import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.HoldCard;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Strategy6 implements Strategy{

	public int run(GameStateDto state) {

		if (isBlindPart(state) && containsParisOnHand(state) || containsColors(state)) {
			return getPossibleMax(state) / 2 > state.getCurrentBuyIn() ? getPossibleMax(state) / 2: getPossibleMax(state);
		}
		if (state.countActivePlayers() > 2) {
			return state.getCurrentBuyIn() - state.getPlayer().get().getBet();
		}
		return 0;
	}

	private boolean containsColors(GameStateDto state) {
		List<HoldCard> holdCards = getHoldCards(state);
		return holdCards.stream().map(hc -> hc.getSuit()).collect(Collectors.groupingBy(String::toString))
				.values().stream().filter(list -> list.size() >= 5).findFirst().isPresent();
	}

	private boolean containsPairsWithCommunityCards(GameStateDto state) {
		List<HoldCard> holdCards = getHoldCards(state);

		return holdCards.stream().map(hc -> hc.getRank()).collect(Collectors.groupingBy(String::toString))
				.values().stream().filter(list -> list.size() > 1).findFirst().isPresent();

	}

	private List<HoldCard> getHoldCards(GameStateDto state) {
		List<HoldCard> holdCards = new LinkedList<>();
		holdCards.addAll(state.getPlayerCards());
		holdCards.addAll(state.getCommunityCards());
		return holdCards;
	}

	private static boolean isBlindPart(GameStateDto state) {
		return state.getCommunityCards()!=null && state.getCommunityCards().isEmpty();
	}

	private static boolean containsParisOnHand(GameStateDto state) {
		List<HoldCard> playerCards = state.getPlayerCards();
		return playerCards.get(0).getRank().equals(playerCards.get(1).getRank());
	}

	private static int getPossibleMax(GameStateDto state) {
		return state.getPlayer().get().getStack();
	}

}

