package org.leanpoker.player;

import java.util.Optional;

import org.leanpoker.player.dto.GameStateDto;

public class StrategyDefault implements Strategy{

	@Override
	public int run(GameStateDto gameStateDto) {
		return gameStateDto.getMinimumRaise()+1;
	}

}
