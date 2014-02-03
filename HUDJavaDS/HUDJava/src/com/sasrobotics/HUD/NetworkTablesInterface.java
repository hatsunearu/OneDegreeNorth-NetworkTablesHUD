package com.sasrobotics.HUD;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class NetworkTablesInterface implements ITableListener {
    
   final double threshold = 3;
   final double deadzone = 0.5;
   final String IPAddress = "10.48.17.2";
   
   NetworkTable table;
   ArduHUD hud;
   double distance;
    
   public NetworkTablesInterface() {
       NetworkTable.setClientMode();
       NetworkTable.setIPAddress(IPAddress);
       table = NetworkTable.getTable("datatable");
       hud = new ArduHUD();
       hud.initialize();
   } 
   
   public void initialize() {
       table.addTableListener(this);
       
       try {
           System.out.println("TableInit");
           Thread.sleep(100000);
       }
       catch (InterruptedException e) {
           System.out.println(e);
       }
       
       hud.close(); //gracefully close serial
   }

   public static void main(String[] args) {
       NetworkTablesInterface i = new NetworkTablesInterface();
       i.initialize();
   }
   
    @Override
    public void valueChanged(ITable source, String key, Object value, boolean isNew) {
        System.out.println("Key: " + key + " Value: " + value + " isNew: "+isNew); 
        if (value instanceof Double && key.equals("Distance")) {
            distance = (Double) value;
        } 
        if (distance > threshold - deadzone && distance < threshold + deadzone) {
            hud.both();
        }
        else if (distance > threshold) {
            hud.left();
        }
        else if (distance < threshold) {
            hud.right();
        }
    }
}
