package game;

import core.CoreEngine;

public class Main {
    public static void main(String[] args){
        CoreEngine engine = new CoreEngine(1366, 768, 100, new TestGame());
        engine.createWindow("HellRaze Engine");
        engine.start();
    }
}
