package com.wodongso.wodongso.utils;

public class SocietyStateFormat {

    public SocietyStateFormat() {
    }

    public String run(Integer state) {
        switch (state) {
            case 0:
                return "보류";
            case 1:
                return "수락";
            case -1:
                return "거절";
            default:
                return null;
        }
    }

}
