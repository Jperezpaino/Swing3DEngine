package es.noa.rad.demo;

 import
  org.lwjgl.Version;
 import
  org.lwjgl.glfw.GLFWErrorCallback;
 import
  org.lwjgl.glfw.GLFWVidMode;
 
//import org.lwjgl.*;
//import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

 public class HelloWorld {
  /**
    * Titulo de la ventana. 
    */
  private final String WINDOW_TITLE = "NeHe Lesson 01 - Setting Up An OpenGL Window";
  /**
    * Variable numerica que almacenara el manejador de la ventana que creemos. 
    */
  private long window;
  
  
  public void run() {
   System.out.println("Hello LWJGL " + Version.getVersion() + "!");
   System.out.println("Demo: " + this.WINDOW_TITLE + "!");  
   this.init();
   this.loop();
   
   // Free the window callbacks and destroy the window
   glfwFreeCallbacks(this.window);
   glfwDestroyWindow(this.window);

   // Terminate GLFW and free the error callback
   glfwTerminate();
   glfwSetErrorCallback(null).free();
  }
  
  private void init() {
   /*
    * Configuramos una llamada de respuesta a un error.
    * La implementación predeterminada imprimirá el mensaje de error en 'System.err'   
    */
   GLFWErrorCallback.createPrint(System.err).set();

   /*
    * Inicializamos 'GLFW' (Graphics Library Framework).
    * La mayoría de las funciones GLFW no funcionarán antes de hacer esto.
    * GLFW es una biblioteca de utilidad ligera para utilizar con OpenGL. Proporciona a los
    * programadores la capacidad de crear y dirigir ventanas y aplicaciones OpenGL, así como
    * recibir la entrada de joystick, teclado y ratón.
    */
   if(!glfwInit()) {
    throw new IllegalStateException("Unable to initialize GLFW");
   }

   /*
    * Configuramos el 'GLFW'
    */
   
   /*
    * Opcional, por defecto las opciones de la ventana actual ya son las predeterminadas.
    */
   glfwDefaultWindowHints(); 
   /*
    * La ventana permanecerá oculta después de la creación.
    */
   glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
   
   /*
    * Configuramos la ventana para que sea redimensionable.
    */
   glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

   
   /*
    * Creamos la ventana.
    */
   this.window = glfwCreateWindow(640, 480, this.WINDOW_TITLE, NULL, NULL);
   if(this.window == NULL) {
    throw new RuntimeException("Failed to create the GLFW window");
   }

   // Setup a key callback. It will be called every time a key is pressed, repeated or released.
   glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
    if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
     glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
   });

   // Get the thread stack and push a new frame
   try ( MemoryStack stack = stackPush() ) {
    IntBuffer pWidth = stack.mallocInt(1); // int*
    IntBuffer pHeight = stack.mallocInt(1); // int*

    // Get the window size passed to glfwCreateWindow
    glfwGetWindowSize(this.window, pWidth, pHeight);

    // Get the resolution of the primary monitor
    GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

    // Center the window
    glfwSetWindowPos(
     this.window,
     (vidmode.width() - pWidth.get(0)) / 2,
     (vidmode.height() - pHeight.get(0)) / 2
    );
   } // the stack frame is popped automatically

   // Make the OpenGL context current
   glfwMakeContextCurrent(this.window);
   // Enable v-sync
   glfwSwapInterval(1);

   // Make the window visible
   glfwShowWindow(this.window);
  }
  
  private void loop() {
  // This line is critical for LWJGL's interoperation with GLFW's
  // OpenGL context, or any context that is managed externally.
  // LWJGL detects the context that is current in the current thread,
  // creates the GLCapabilities instance and makes the OpenGL
  // bindings available for use.
  GL.createCapabilities();

  // Set the clear color
  glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

  // Run the rendering loop until the user has attempted to close
  // the window or has pressed the ESCAPE key.
  while ( !glfwWindowShouldClose(window) ) {
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

   glfwSwapBuffers(window); // swap the color buffers

   // Poll for window events. The key callback above will only be
   // invoked during this call.
   glfwPollEvents();
  }
 }
 
 public static void main(String[] args) {
  new HelloWorld().run();
 }

}