package org.leanpoker.player.dto;

public class HoldCard {

    public HoldCard(){

    }

    public HoldCard(String rank){
        this.rank = rank;
    }

    public HoldCard(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    private String rank;
    private String suit;

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
