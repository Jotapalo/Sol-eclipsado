package com.example.soleclipsado.models;

import java.text.Normalizer;

public class StringTools {
    //Metodo que permite obtener la normal de la variante acentual de alguna letra del alfabeto
    static public String removeDiacritics(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
