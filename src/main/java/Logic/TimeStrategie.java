package Logic;

import Model.*;
import java.util.*;

public class TimeStrategie implements  Strategie{

    @Override
    public void addClient(ArrayList<Server> servers, Client client) {
            int min = servers.get(0).getPerioadaAsteptare();
            Server aux = servers.get(0);
            for(Server s : servers){ //cautam coada cu perioada cea mai mica de asteptare
                if(min>s.getPerioadaAsteptare()){
                    aux = s;
                    min = s.getPerioadaAsteptare();
                }
            }
            aux.addClient(client);
    }
}
