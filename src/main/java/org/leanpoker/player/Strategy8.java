package org.leanpoker.player;

import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.HoldCard;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Strategy8 implements Strategy{

	public int run(GameStateDto state) {
		int minToBet = state.getCurrentBuyIn() - state.getPlayer().get().getBet() + state.getMinimumRaise();

		int maxToBet = getPossibleMax(state);
		// mamy za mało kasy na spełnienie minimum
		if(maxToBet < minToBet){
			return 0;
		}
		
		// tylko karty z ręki
		if(isBlindPart(state)) {
			if(containsParisOnHand(state)) {
				return maxToBet / 2 > state.getCurrentBuyIn() ? maxToBet / 2: maxToBet;
			}
			else {
				state.getMinimumRaise();
			}
		}
		Variant variant = Variant.recognize(state.getAllCards());
		// mamy jakąś figurę
		if(variant!=Variant.NONE && variant!=Variant.PAIR){
			// ile stawiamy: 1 / moc figury i nie mniej niż min
			return minToBet + (maxToBet / (variant.ordinal() + 1));
		}
		
		return 0;
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

