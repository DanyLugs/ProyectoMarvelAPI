package com.danylugo.bottomnavigationproyecto.Model;

public class SpiderCard {

        String name;
        String id;
        int cardImage;

        public SpiderCard(String name, String id, int cardImage) {
            this.name = name;
            this.id = id;
            this.cardImage = cardImage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCardImage() {
            return cardImage;
        }

        public void setCardImage(int cardImage) {
            this.cardImage = cardImage;
        }

}
