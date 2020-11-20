package br.usjt.classes;

import java.awt.Color;

public class Cor {
    //cor acme b12322
    public static Color converteRGB(String hex) {
        String sr = hex.substring(0,2);
        String sg = hex.substring(2,4);
        String sb = hex.substring(4,6);

        int r = Integer.parseInt(sr, 16);
        int g = Integer.parseInt(sg, 16);
        int b = Integer.parseInt(sb, 16);

        Color color = new Color(r,g,b);
        
        return color;
    }
}
