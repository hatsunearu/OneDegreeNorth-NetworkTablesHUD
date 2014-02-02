package com.sasrobotics.HUD;

import java.util.Scanner;

public class DistanceInformationHUD {
    public static void main(String[] args) {
        ArduHUD hud = new ArduHUD();
        hud.initialize();
        
        Scanner s = new Scanner(System.in);
        OUTER:
        while (true) {
            String str = s.nextLine();
            switch (str) {
                case "l":
                    hud.left();
                    break;
                case "r":
                    hud.right();
                    break;
                case "b":
                    hud.both();
                    break;
                case "o":
                    hud.off();
                    break;
                default:
                    hud.close();
                    break OUTER;
            }
        }
    }
}
