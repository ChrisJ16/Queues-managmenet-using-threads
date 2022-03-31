package Model;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{ //server = coada
    private BlockingDeque<Client> clientList =new LinkedBlockingDeque<Client>();
    private AtomicInteger perioadaAsteptare;

    public Server()
    {
        this.perioadaAsteptare = new AtomicInteger(0);
    }

    public Server(int p){
        this.perioadaAsteptare = new AtomicInteger(p);
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {e.printStackTrace();}
            if(!clientList.isEmpty()){ //verificam dacam mai avem clienti in coada
                clientList.element().setServiceTime(clientList.element().getServiceTime()-1);
                perioadaAsteptare = new AtomicInteger(perioadaAsteptare.intValue()-1);
                if(clientList.element().getServiceTime() == 0){//primul din coada si-a terminat treaba, deci pleaca
                    try {
                        clientList.take(); //eliminam prima persoana din coada
                    } catch (InterruptedException e) {e.printStackTrace();}
                }
            }
        }
    }

    public void addClient(Client c){
        clientList.add(c);
        perioadaAsteptare = new AtomicInteger(perioadaAsteptare.intValue() + c.getServiceTime());
    }

    public int getPerioadaAsteptare(){
        return this.perioadaAsteptare.intValue();
    }

    public int getSizeOfQueue() {
        return clientList.size();
    }

    public ArrayList<Client> getClient(){
        ArrayList<Client> clients = new ArrayList<Client>();
        for(Client c : clientList)
            clients.add(c);
        return clients;
    }

    @Override
    public String toString(){
        String s = new String();
        for(Client c : clientList)
            s = s + " " + c.toString();
        return s;
    }
}
