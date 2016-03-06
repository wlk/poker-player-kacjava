package org.leanpoker.player;

import org.leanpoker.player.dto.GameStateDto;

import java.util.Optional;

/**
 * @author Paweł Bilewicz
 */
public class ExceptionCatcherStrategy implements Strategy {

	private Strategy strategy;

	public ExceptionCatcherStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	@Override
	public int run(Optional<GameStateDto> gameStateDto) {
		try{
			return strategy.run(gameStateDto);
		} catch (Exception e) {
			return new StrategyDefault().run(gameStateDto);
		}
	}
}
