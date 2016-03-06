package org.leanpoker.player;

import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.HoldCard;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paweł Bilewicz
 */
public class Strategy7 implements Strategy{

	public int run(GameStateDto state) {

		int minToBet = state.getCurrentBuyIn() - state.getPlayer().get().getBet() +  + state.getMinimumRaise();
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
		if(variant!=Variant.NONE){
			// ile stawiamy: 1 / moc figury i nie mniej niż min
			return Math.min(minToBet, maxToBet / (variant.ordinal() + 1));
		}
		
		if(containsPairsWithCommunityCards(state) || containsColors(state)) {
			return maxToBet / 2 > state.getCurrentBuyIn() ? maxToBet / 2: maxToBet;
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

