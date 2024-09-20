package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 * 
 */
public class GoldLevel extends Level {
    
    public GoldLevel(String level) {
        super(level);
    }

    public GoldLevel() {
    }
    
   @Override
   public double getFee() {
       return 10;
   }
   
   public String getLevel() {
       return "Gold";
   }
    
}
