package org.leanpoker.player.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameStateDto {

    @SerializedName("minimum_raise")
    private int minimumRaise;

    @SerializedName("bet_index")
    private int betIndex;

    @SerializedName("current_buy_in")
    private int currentBuyIn;

    private List<PlayerDto> players;
    @SerializedName("community_cards")
    private List<HoldCard> communityCards;

    public int getMinimumRaise() {
        return minimumRaise;
    }
    public void setMinimumRaise(int minimumRaise) {
        this.minimumRaise = minimumRaise;
    }

    public int getBetIndex() {
        return betIndex;
    }
    public void setBetIndex(int betIndex) {
        this.betIndex = betIndex;
    }

    public int getCurrentBuyIn() {
        return currentBuyIn;
    }
    public void setCurrentBuyIn(int currentBuyIn) {
        this.currentBuyIn = currentBuyIn;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }
    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public List<HoldCard> getCommunityCards() {
        return communityCards;
    }
    public void setCommunityCards(List<HoldCard> communityCards) {
        this.communityCards = communityCards;
    }
}
