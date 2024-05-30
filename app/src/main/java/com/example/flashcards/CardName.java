package com.example.flashcards;

public class CardName {
    String CardName;
    String CardDetail;
    boolean visibility;


    public CardName(String cardName, String cardDetail) {
        CardName = cardName;
        CardDetail = cardDetail;
        visibility = false;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getCardDetail() {
        return CardDetail;
    }

    public void setCardDetail(String cardDetail) {
        CardDetail = cardDetail;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
