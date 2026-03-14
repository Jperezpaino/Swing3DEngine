package es.noa.rad.test.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class CoreWindowAdaptator extends WindowAdapter
{
    public void windowClosing(WindowEvent e) 
    {
        System.exit(0);
    }
}