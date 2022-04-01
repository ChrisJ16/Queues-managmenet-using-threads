package GUI;

import Logic.*;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller implements ActionListener {
    private View view;

    public Controller(View v) {
        this.view = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int n=-1, q=-1, simTime=-1;
        int servTimeMin=-1, servTimeMax=-1;
        int arrvTimeMin=-1, arrvTimeMax=-1;
        boolean ok=true;

        //OpPrelucrare operatii = new OpPrelucrare();

        String sClients = view.getNCLients().getText();
        String sThreads = view.getQThreads().getText();
        String sSimTime = view.getSimTime().getText();
        String sArrivalTime = view.getArrivalTime().getText();
        String sServiceTime = view.getServiceTime().getText();

        try{
            n = Integer.parseInt(sClients);
            q = Integer.parseInt(sThreads);
            simTime = Integer.parseInt(sSimTime);

            int index1 = sArrivalTime.indexOf("-");
            arrvTimeMin = Integer.parseInt(sArrivalTime.substring(0,index1));
            arrvTimeMax = Integer.parseInt(sArrivalTime.substring(index1+1, sArrivalTime.length()));

            int index2 = sServiceTime.indexOf("-");
            servTimeMin = Integer.parseInt(sServiceTime.substring(0,index2));
            servTimeMax = Integer.parseInt(sServiceTime.substring(index2+1, sServiceTime.length()));

        }catch (Exception ex)
        {
            view.setAreaText("A aparut o eroare la introducerea datelor!");
            ok=false;
        }

        switch (command)
        {
            case "Start":
                view.clearAreaText();
                if(ok) {
                    //List<Client> clienti = operatii.GenerateNClients(n, arrvTimeMin, arrvTimeMax, servTimeMin, servTimeMax);
                    ManagerSim sim = new ManagerSim(view, simTime, q, n, arrvTimeMin, arrvTimeMax, servTimeMin, servTimeMax);

                    Thread t = new Thread(sim);
                    t.start();
                }
                else
                    view.setAreaText("A aparut o eroare la introducerea datelor!");
                break;
            case "EX1":
                view.getNCLients().setText("4");
                view.getQThreads().setText("2");
                view.getSimTime().setText("60");
                view.getArrivalTime().setText("2-30");
                view.getServiceTime().setText("2-4");
                view.clearAreaText();
                break;
            case "EX2":
                view.getNCLients().setText("50");
                view.getQThreads().setText("5");
                view.getSimTime().setText("60");
                view.getArrivalTime().setText("2-40");
                view.getServiceTime().setText("1-7");
                view.clearAreaText();
                break;
            case "EX3":
                view.getNCLients().setText("1000");
                view.getQThreads().setText("20");
                view.getSimTime().setText("200");
                view.getArrivalTime().setText("10-1000");
                view.getServiceTime().setText("3-9");
                view.clearAreaText();
                break;
        }
    }
}
