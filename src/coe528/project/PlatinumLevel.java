package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 * 
 */
public class PlatinumLevel extends Level {
    
    public PlatinumLevel(String level) {
        super(level);
    }

    public PlatinumLevel() {
    }
    
   @Override
   public double getFee() {
       return 0;
   }
   
   public String getLevel() {
       return "Platinum";
   }
}
