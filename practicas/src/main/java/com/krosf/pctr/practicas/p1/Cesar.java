package com.krosf.pctr.practicas.p1;

/**
 * Cesar
 */
public class Cesar {

    private String str;

    public Cesar(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }

    /**
     * Encrypt the str with the key following cesar algorithms
     * 
     * @param key
     */
    public void encrypt(int key) {
        String encrypted = "";
        for (int i = 0; i < str.length(); ++i) {
            encrypted += (char) (str.charAt(i) + key % 26);
        }
        str = encrypted;
    }

    public static void main(String[] args) {
        Cesar ex = new Cesar("works on my machine");
        ex.encrypt(22);
        System.out.println(ex);
    }
}