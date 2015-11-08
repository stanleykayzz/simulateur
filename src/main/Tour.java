package main;
public class Tour {
    private static boolean timing = true;
    //on crée des boolean pour les bouton start , pause et stop du simulateur
    private boolean pause = true;
    private boolean stop = true;
    private int seconds = 00;
    private int limit = 5;
    
    public int getSeconds()
    {
    	return this.seconds;
    }
    
    public void timer() throws InterruptedException{
        while (seconds < limit){
            System.out.println(seconds);
            seconds++;
            //on passe la vitesse d'excecution en paramètre de la fonction sleep du thread
            Thread.sleep(500);
        }
    }
    
    
    public static void main (String[] args) throws InterruptedException{
        //timer();

        System.out.println(1);
        Thread.sleep(5000);
        System.out.println(2);
    }
}