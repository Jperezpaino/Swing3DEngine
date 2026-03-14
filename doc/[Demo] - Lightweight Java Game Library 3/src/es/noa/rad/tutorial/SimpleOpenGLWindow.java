package es.noa.rad.tutorial;

 import static
  org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
 import static
  org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
 import static
  org.lwjgl.glfw.GLFW.GLFW_FALSE;
 import static
  org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
 import static
  org.lwjgl.glfw.GLFW.GLFW_RELEASE;
 import static
  org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
 import static
  org.lwjgl.glfw.GLFW.GLFW_TRUE;
 import static
  org.lwjgl.glfw.GLFW.GLFW_VERSION_UNAVAILABLE;
 import static
  org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
 import static
  org.lwjgl.glfw.GLFW.glfwCreateWindow;
 import static
  org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
 import static
  org.lwjgl.glfw.GLFW.glfwDestroyWindow;
 import static
  org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
 import static
  org.lwjgl.glfw.GLFW.glfwGetVideoMode;
 import static
  org.lwjgl.glfw.GLFW.glfwInit;
 import static
  org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
 import static
  org.lwjgl.glfw.GLFW.glfwPollEvents;
 import static
  org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
 import static
  org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
 import static
  org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
 import static
  org.lwjgl.glfw.GLFW.glfwSetWindowPos;
 import static
  org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
 import static
  org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
 import static
  org.lwjgl.glfw.GLFW.glfwShowWindow;
 import static
  org.lwjgl.glfw.GLFW.glfwSwapBuffers;
 import static
  org.lwjgl.glfw.GLFW.glfwSwapInterval;
 import static
  org.lwjgl.glfw.GLFW.glfwTerminate;
 import static
  org.lwjgl.glfw.GLFW.glfwWindowHint;
 import static
  org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
 import static
  org.lwjgl.glfw.GLFW.nglfwGetFramebufferSize;
 import static
  org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
 import static
  org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
 import static
  org.lwjgl.opengl.GL11.glClear;
 import static
  org.lwjgl.opengl.GL11.glClearColor;
 import static
  org.lwjgl.system.MemoryUtil.NULL;
 import static
  org.lwjgl.system.MemoryUtil.memAddress;
 import
  java.io.IOException;
 import
  java.nio.IntBuffer;
 import
  org.lwjgl.Version;
 import
  org.lwjgl.glfw.GLFWErrorCallback;
 import
  org.lwjgl.glfw.GLFWFramebufferSizeCallback;
 import
  org.lwjgl.glfw.GLFWKeyCallback;
 import
  org.lwjgl.glfw.GLFWVidMode;
 import
  org.lwjgl.glfw.GLFWWindowSizeCallback;
 import
  org.lwjgl.opengl.GL;
 import
  org.lwjgl.opengl.GLUtil;
 import
  org.lwjgl.system.Callback;
 import
  org.lwjgl.system.MemoryStack;

 public class SimpleOpenGLWindow {
  /**
    * Titulo de la ventana.
    */
  private final String WINDOW_TITLE = "Simple OpenGL Window Tutorial";
  /**
    * Variable numérica que almacenara el manejador de la ventana que creemos.
    */
  private long window; 
  private GLFWErrorCallback errorCallback;
  private GLFWKeyCallback keyCallback;
  private GLFWWindowSizeCallback windowSizeCallback;
  private GLFWFramebufferSizeCallback framebufferSizeCallback;
  private Callback debugProc;

  private int width = 1024;
  private int height = 768;

  private void init() throws IOException {
   /*
    * Configuramos un 'CallBack' para gestionar la respuesta de los distintos
    * errores.
    */
   this.errorCallback = new GLFWErrorCallback() {
    /*
     * Establecemos una implementación predeterminada que imprimirá el mensaje
     * de error a través del 'System.err'.
     */
    private GLFWErrorCallback delegate
     = GLFWErrorCallback.createPrint(System.err);

    @Override
    public final void invoke(
           final int error,
           final long description) {
     if (error == GLFW_VERSION_UNAVAILABLE) {
      System.err.println("This demo requires OpenGL 3.0 or higher.");
     }
     this.delegate.invoke(error, description);
    }

    @Override
    public final void free() {
     this.delegate.free();
    }
   };
   glfwSetErrorCallback(this.errorCallback);

   /*
    * Inicializamos 'GLFW' (Graphics Library Framework).
    * La mayoría de las funciones GLFW no funcionarán antes de hacer esto.
    * GLFW es una biblioteca de utilidad ligera para utilizar con OpenGL.
    * Proporciona a los programadores la capacidad de crear y dirigir ventanas
    * y aplicaciones OpenGL, así como recibir la entrada de joystick, teclado y
    * ratón.
    */
   if(!glfwInit()) {
    throw new IllegalStateException("Unable to initialize GLFW");
   }

   /*
    * Configuramos el 'GLFW'.
    */
   this.configureGLFW();

   /*
    * Creamos la ventana.
    */
   this.window = glfwCreateWindow(
    this.width, this.height, this.WINDOW_TITLE, NULL, NULL);
   if(this.window == NULL) {
    throw new RuntimeException("Failed to create the GLFW window");
   }

   /*
    * Configuramos un 'CallBack' para gestionar la respuesta del teclado y
    * ratón, se llamará cada vez que se presione, se mantenga pulsada o suelte
    * una tecla.
    */
   this.keyCallback = new GLFWKeyCallback() {
    @Override
    public final void invoke(
           final long window,
           final int key,
           final int scancode,
           final int action,
           final int mods) {
     /*
      * Se detectará dentro de nuestro bucle de renderizado.
      */
     if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
      glfwSetWindowShouldClose(window, true);
     }
    }
   };
   glfwSetKeyCallback(this.window, this.keyCallback);

   /*
    * Configuramos un 'CallBack' para gestionar la redimensión de la pantalla.
    */
   this.windowSizeCallback = new GLFWWindowSizeCallback() {
    @Override
    public final void invoke(
           final long window,
           final int width,
           final int height) {
     if((width > 0)
     && (height > 0)
     && ((SimpleOpenGLWindow.this.width != width)
      || (SimpleOpenGLWindow.this.height != height))) {
      SimpleOpenGLWindow.this.width = width;
      SimpleOpenGLWindow.this.height = height;
     }
    }
   };
   glfwSetWindowSizeCallback(this.window, this.windowSizeCallback);

   /*
    * Configuramos un 'CallBack' para gestionar la redimensión del buffer de la
    * pantalla.
    */
   this.framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
    @Override
    public final void invoke(
           final long window,
           final int width,
           final int height) {
     if((width > 0)
     && (height > 0)
     && ((SimpleOpenGLWindow.this.width != width)
      || (SimpleOpenGLWindow.this.height != height))) {
      SimpleOpenGLWindow.this.width = width;
      SimpleOpenGLWindow.this.height = height;
     }
    }
   };
   glfwSetFramebufferSizeCallback(this.window, this.framebufferSizeCallback);

   /*
    * Obtenemos la resolución del monitor principal.
    */
   GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

   /*
    * Centramos la ventana.
    */
   glfwSetWindowPos(
    this.window,
    (videoMode.width() - width) / 2,
    (videoMode.height() - height) / 2
   );

   try (MemoryStack frame = MemoryStack.stackPush()) {
    IntBuffer framebufferSize = frame.mallocInt(2);
    nglfwGetFramebufferSize(
     this.window,
     memAddress(framebufferSize),
     memAddress(framebufferSize) + 4);
    this.width = framebufferSize.get(0);
    this.height = framebufferSize.get(1);
   }

   /*
    * Establecemos el contexto OpenGL como el actual.
    */
   glfwMakeContextCurrent(this.window);

   /*
    * Activamos la sincronización vertical.
    */
   glfwSwapInterval(1);

   /*
    * Establecemos la ventana como visible.
    */
   glfwShowWindow(this.window);

   /*
    * Esta línea es crítica para la interoperabilidad de LWJGL con el contexto
    * OpenGL de GLFW, o cualquier contexto que sea manejado externamente. LWJGL
    * detecta el contexto actual en el subproceso actual, crea la instancia de
    * GLCapabilities y hace que los enlaces OpenGL estén disponibles para su
    * uso.
    */
   GL.createCapabilities();

   this.debugProc = GLUtil.setupDebugMessageCallback();
  }

  /**
    * Configuramos el 'GLFW'.
    */
  private void configureGLFW() {
   /*
    * Establecemos las opciones por defecto para las ventanas. (Opcional, por
    * defecto las opciones de la ventana actual ya son las predeterminadas).
    */
   glfwDefaultWindowHints();
   glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 1);
   glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
   /*
    * La ventana permanecerá oculta después de la creación.
    */
   glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
   /*
    * Configuramos la ventana para que sea redimensionable.
    */
   glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
  }

  private void run() {
   System.out.println("Version LWJGL: '" + Version.getVersion() + "'");
   System.out.println("Demo: '" + this.WINDOW_TITLE + "'");
   try {
    this.init();
    this.loop();
    /*
     * Liberamos los 'CallBack' de ventana y la propia ventana.
     */
    this.errorCallback.free();
    this.keyCallback.free();
    this.windowSizeCallback.free();
    this.framebufferSizeCallback.free();
    if (this.debugProc != null) {
     this.debugProc.free();
    }
    glfwDestroyWindow(this.window);
   } catch (Throwable throwable) {
    throwable.printStackTrace();
   } finally {
    /*
     * Liberamos el 'GLFW y el 'CallBack' de errores.
     */
    glfwTerminate();
   }
  }

  private void loop() {
   /*
    * Establecemos el color de fondo.
    */
   glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

   /*
    * Ejecute el bucle de renderización hasta que el usuario cierre la ventana
    * o presione la tecla de ESCAPE.
    */
   while (!glfwWindowShouldClose(this.window)) {
    /*
     * Limpiamos el buffer.
     */
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    /*
     * Intercambiamos los 'Buffers'.
     */
    glfwSwapBuffers(this.window);
    /*
     * Volcamos los eventos de la ventana. El 'Callback' correspondiente al
     * teclado solo se invocará durante esta
     * llamada.
     */
    glfwPollEvents();
   }
  }

  public static void main(String[] args) {
   new SimpleOpenGLWindow().run();
  }

 }