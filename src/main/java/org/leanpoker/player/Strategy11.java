package org.leanpoker.player;

import org.leanpoker.player.dto.CardRank;
import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.HoldCard;

import java.util.List;

public class Strategy11 implements Strategy{

	public int run(GameStateDto state) {
		int check = state.getCurrentBuyIn() - state.getPlayer().get().getBet();
		int minToBet = check + state.getMinimumRaise();

		int maxToBet = getPossibleMax(state);
		// mamy za mało kasy na spełnienie minimum
		if(maxToBet < minToBet){
			return 0;
		}

		Variant variant = Variant.recognize(state.getAllCards());

		// tylko karty z ręki
		if(isBlindPart(state)) {
//			if (state.getBetIndex() >= 3){
//				return check;
//			}
			if(variant == Variant.HIGH_PAIR) {
				//para na ręce
				return minToBet;
			} else if (state.getPlayerCards().stream()
					.map(HoldCard::getRank)
					.map(CardRank::findByValue)
					.map(CardRank::ordinal)
					.filter(rank -> rank >= CardRank.CARD_10.ordinal())
					.findFirst()
					.isPresent()) {
				// 10 lub wyższa na ręce
				return minToBet;
			} else {
				return 0;
			}
		}
		// mamy jakąś figurę
		if(variant!=Variant.NONE && variant!=Variant.PAIR){
			// ile stawiamy: 1 / moc figury i nie mniej niż min
			return minToBet + ((maxToBet-minToBet) / (variant.ordinal() + 1));
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

