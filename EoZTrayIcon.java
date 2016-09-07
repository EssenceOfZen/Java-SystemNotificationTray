package ...;

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by GamiShin ZenOokami on 7/28/2016.
 *      This class is designed to add system tray icons for programs.
 *		This class makes use of AWT, but can work alongside javaFX but to an extent.
 *
 *      This was designed based on the tutorial made by: <Looking for source again, will update when found -- didn't
 *      plan on putting this online originally>
 */

class EoZTrayIcon {
    private static final String icon_path = "/resources/EoZ Logo icon.PNG"; //static allows for the variable to be shared between all instances
    private static final String icon_description = "I am your icon's description - icon should be 16x16";


    EoZTrayIcon(Stage primaryStage){ //Constructor
        showNotificationIcon(primaryStage);
    }

    private static void showNotificationIcon(Stage primaryStage){ //Creates the icon in the system tray.
        System.out.println("EoZTrayIcon protocl has been called...");

        if(!SystemTray.isSupported()){
            System.out.println("System Notification Tray is not supported.");
            System.exit(0);
            return;
        }
        System.out.println("System Notification Tray is supported...");

        //todo: later create a variable - if enabled, show description as enabled, else disabled
        TrayIcon tray_icon = new TrayIcon(createIcon(icon_path, icon_description));
        final PopupMenu popup = new PopupMenu();
        final SystemTray system_tray = SystemTray.getSystemTray(); //Set this to not be changed (final)

        /*Icon Right Click Menu Items*/
        /*Enable/Disable*/
        MenuItem disenable_item = new MenuItem("Enable/Disable");

        /*Open Program to main Menu*/
        MenuItem focus_item = new MenuItem("Clickity");
        focus_item.addActionListener(event -> {
            // Add something later
        });

        /*About*/
        MenuItem about_item = new MenuItem("About");

        /*Exit*/
        MenuItem exit_item = new MenuItem("Exit");
        exit_item.addActionListener(event -> {
            Platform.exit();
            system_tray.remove(tray_icon);
        });

        /*Add items=====*/
        popup.add(disenable_item);
        popup.add(focus_item);
        popup.add(about_item);
        popup.addSeparator();
        popup.add(exit_item);

        tray_icon.setPopupMenu(popup); //Connect the popup menu to the icon



        try{ // Try and add the icon to the notification tray.
            system_tray.add(tray_icon);
            System.out.println("Icon added to notification tray.");
        }catch(Exception e){
            System.out.println("Error found: " + e);
        }
    }

    private static Image createIcon(String path, String description){
        URL image_url = EoZTrayIcon.class.getResource(path);
        return(new ImageIcon(image_url, description)).getImage();
    }

    public static void focus_stage(Stage stage){
        if(stage != null){
            stage.show();
            stage.toFront();
        }
    }
}
