package Logic;

import Model.*;
import java.util.*;

public class OpPrelucrare {

    public List<Client> GenerateNClients(int n, int arrvTimeMin, int arrvTimeMax, int servTimeMin, int servTimeMax)
    {
        List<Client> clientList = new ArrayList<Client>();
        int arrivalTime=-1, serviceTime=-1;
        Random r = new Random();

        for(int i=0; i<n; i++)
        {
            arrivalTime = r.nextInt(arrvTimeMax - arrvTimeMin) + arrvTimeMin;
            serviceTime = r.nextInt(servTimeMax - servTimeMin) + servTimeMin;
            clientList.add(new Client(i, arrivalTime, serviceTime));
        }

        return clientList;
    }
}
