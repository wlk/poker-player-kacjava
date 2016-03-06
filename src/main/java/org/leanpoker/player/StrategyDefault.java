package org.leanpoker.player;

import java.util.Optional;

import org.leanpoker.player.dto.GameStateDto;

public class StrategyDefault implements Strategy{

	@Override
	public int run(Optional<GameStateDto> gameStateDto) {
      return gameStateDto
      .filter(game -> game.getMinimumRaise()>0)
      .map(Player::bet)
      .orElse(300);
	}

}
