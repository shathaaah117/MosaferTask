package org.modules.helpers;

import java.util.Random;

import static java.time.chrono.JapaneseEra.values;

public class ModulesEnum {

    public enum CreditCards {
      //  MASTER_CARD_SECURE("5123450000000008"),
        MASTER_CARD("5111111111111118"),
     //   VISA_SECURE("4508750015741019"),
        VISA("4012000033330026"),
    //    BENEFIT("4600410123456789"),
        MADA("4464040000000007");

        String creditCard;

        CreditCards(String creditCard) {
            this.creditCard = creditCard;
        }

        public String getCreditCard() {
            return this.creditCard;
        }
    }
    public static String getRandomCreditCard() {
            return CreditCards.values()[new Random().nextInt(values().length-1)].creditCard;
        }
}