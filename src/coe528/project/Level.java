package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 * 
 */
abstract class Level {
    
    protected String level;
    
    public Level(String level) {
        this.level = level;
    }
    
    public Level() {
    }
    
    public abstract double getFee();
    
    public abstract String getLevel();
    
    public static Level updateLevel(double balance) {
        if (balance < 10000) {
            return new SilverLevel();
        } else if (balance < 20000) {
            return new GoldLevel();
        } else {
            return new PlatinumLevel();
        }
    }
    
}
