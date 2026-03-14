import java.io.IOException;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.ExtendedTerminal;
import com.googlecode.lanterna.terminal.MouseCaptureMode;



/**
 * testing mouse, application closes when click is performed in empty space of a window or on the screen
 * (mouse reporting remains enabled and in the terminal mouse reporting is still visible).
 * @author rajatt
 *
 */
public class lanternagroup {

    public static void main(String[] args) throws IOException, InterruptedException {
       
        //is it better to create a terminal and calling the setMouseCaptureMode on the default factory and
        //then setting it to null by casting terminal to EXtendedTerminal or same as I have done?
        ExtendedTerminal ter = (ExtendedTerminal) new DefaultTerminalFactory().setForceTextTerminal(true).createTerminal();
        ter.setMouseCaptureMode(MouseCaptureMode.CLICK);
        final Screen screen = new TerminalScreen(ter);
        screen.startScreen();
        screen.clear();
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);        

        Panel menubar = new Panel();
        menubar.setLayoutManager(new LinearLayout(Direction.HORIZONTAL).setSpacing(1));
       
        TextBox text = new TextBox(new TerminalSize(10,10),TextBox.Style.MULTI_LINE);
        menubar.addComponent(text);
       
        menubar.addComponent(new Button("Open", new Runnable(){

            @Override
            public void run() {
                Window op = new BasicWindow("Select file");
                gui.addWindow(op);
                op.setComponent(new Button("Close", new Runnable(){
                   
                    @Override
                    public void run() {
                        op.close();                        
                    }                    
                }));
               
            }            
        }));
        menubar.addComponent(new Button("Save"));
        menubar.addComponent(new Button("Exit", new Runnable(){
            public void run(){
                try {
                    gui.getActiveWindow().close();
                    screen.stopScreen();
                    ter.setMouseCaptureMode(null);
                   
                } catch (IOException e) {
                    System.out.println("Error exit");
                }
            }
        }));
       
       
       
       
       
        Window main = new BasicWindow("Test");
        main.setComponent(menubar);    
        gui.addWindowAndWait(main);
       
       
       
        screen.stopScreen();
        ter.setMouseCaptureMode(null);
       
       
    }

}
