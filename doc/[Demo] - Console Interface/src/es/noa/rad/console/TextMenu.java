package es.noa.rad.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class TextMenu
{
  class Menu
  {
    private List<MenuItem> items = new ArrayList<MenuItem>() ;
    private String title ;
 
    public Menu( String title )
    {
      this.title = title ;
    }
 
    public Menu doOption( int option )
    {
      if( option == 0 ) return null ;
      option-- ;
      if( option >= items.size() )
      {
        System.out.println( "Unknown option " + option ) ;
        return this ;
      }
      items.get( option ).select() ;
      Menu next = items.get( option ).getSubMenu() ;
 
      return next == null ? this : next ;
    }
 
    public Menu addItem( MenuItem item )
    {
      items.add( item ) ;
      return this ;
    }
 
    public String toString()
    {
      StringBuilder sb = new StringBuilder() ;
      sb.append( title ).append( "\n" ) ;
      for( int i = 0 ; i < title.length() ; i++ )
      {
        sb.append( "-" ) ;
      }
      sb.append( "\n" ) ;
      for( int i = 0 ; i < items.size() ; i++ )
      {
        sb.append( ( i + 1 ) ).append( ") " ).append( items.get( i ) ).append( "\n" ) ;
      }
      sb.append( "0) Quit" ) ;
      return sb.toString() ;
    }
  }
 
  class MenuItem
  {
    private String title ;
    private Menu submenu ;
    private ActionListener onselect ;
 
    public String getTitle()
    {
      return title;
    }
 
    public MenuItem( String title, Menu submenu, ActionListener onselect )
    {
      this.title = title ;
      this.submenu = submenu ;
      this.onselect = onselect ;
    }
 
    public void setSubMenu( Menu submenu )
    {
      this.submenu = submenu ;
    }
 
    public void select()
    {
      if( onselect != null )
        onselect.actionPerformed( new ActionEvent( this, 0, "select" ) ) ;
    }
 
    public Menu getSubMenu()
    {
      return submenu ;
    }
 
    public String toString()
    {
      return title ;
    }
  }
 
  public Menu createMenuSystem()
  {
    MenuItem backLink = new MenuItem( "Item 2 Goes back to the root menu", null, null ) ; // tie this up in a bit
 
    Menu subMenu2 = new Menu( "Submenu 2" ) ;
    subMenu2.addItem( new MenuItem( "Item 1 prints Tim Yates", null, new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        System.out.println( "Tim Yates!" ) ;
      }
    } ) ).addItem( backLink ) ;
 
    Menu subMenu1 = new Menu( "Submenu 1" ) ;
    subMenu1.addItem( new MenuItem( "Item 1 prints hello", null, new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        System.out.println( "hello!" ) ;
      }
    } ) ).addItem( new MenuItem( "Item 2 goes to the next submenu (submenu2)", subMenu2, null ) )
    .addItem( new MenuItem( "Item 3 quits", null, new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        System.exit( 1 ) ;
      }
    } ) );
 
    Menu rootMenu = new Menu( "ROOT" ) ;
    rootMenu.addItem( new MenuItem( "Go to the 1st submenu (subMenu1)", subMenu1, null ) )
            .addItem( new MenuItem( "Go to the 2nd submenu (subMenu2)", subMenu2, null ) ) ;
 
    // Tie the backlink up
    backLink.setSubMenu( rootMenu ) ;
 
    return rootMenu ;
  }
 
  /** Creates a new instance of TextMenu */
  public TextMenu()
  {
  }
 
  public static void main( String[] args )
  {
    BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
    TextMenu t = new TextMenu() ;
    Menu currentMenu = t.createMenuSystem() ;
    while( currentMenu != null )
    {
      System.out.println( currentMenu ) ;
      System.out.print( "Your Selection : " ) ;
      String inp = "" ;
      try
      {
        inp = br.readLine() ;
        currentMenu = currentMenu.doOption( Integer.parseInt( inp ) ) ;
      }
      catch( Exception ex )
      {
        System.out.println( "Didn't understand " + inp ) ;
      }
    }
  }
}