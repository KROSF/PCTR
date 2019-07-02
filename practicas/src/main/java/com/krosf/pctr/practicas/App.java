package com.krosf.pctr.practicas;

import com.krosf.pctr.practicas.p1.Cesar;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Cesar str = new Cesar("works");
        System.out.println(str);
    }
}
