package Logic;

import Model.*;
import java.util.*;

public class Planificator {
    private ArrayList<Server> servere = new ArrayList<Server>();
    private ArrayList<Thread> threads =  new ArrayList<Thread>();
    private Strategie strat = new TimeStrategie();
    private int nrMaxServ;
    private int nrMaxClientPerSv;

    public Planificator(int maxServ, int maxClients){
        this.nrMaxServ = maxServ;
        this.nrMaxClientPerSv = maxClients;
        for(int i=0; i< maxServ; i++){//cream atatea cozi cate am introdus de la tastatura
            Server srv = new Server();
            this.servere.add(srv);
            this.threads.add(new Thread(srv));
        }
    }

    public void startThread() { //pornim cozile/threadurile
        for(Thread t : threads)
            t.start();
    }

    public void dispatchClient(Client c){ //adaugam un client intr-o coada
        strat.addClient(servere, c);
    }

    public ArrayList<Server> getServere(){
        return servere;
    }
}
