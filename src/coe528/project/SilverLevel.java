package coe528.project;

/**
 *
 * @author Bilal Irfan | 501176502
 * 
 */
public class SilverLevel extends Level {
    
    public SilverLevel(String level) {
        super(level);
    }

    public SilverLevel() {
    }
    
   @Override
   public double getFee() {
       return 20;
   }
   
   public String getLevel(){
       return "Silver";
   }
    
}
