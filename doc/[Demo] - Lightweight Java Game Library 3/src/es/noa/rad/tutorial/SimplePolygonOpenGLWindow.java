package es.noa.rad.tutorial;

 import
  org.lwjgl.glfw.GLFW;
 import
  org.lwjgl.opengl.GL11;
 import
  org.lwjgl.opengl.GL15;
 import
  org.lwjgl.system.MemoryUtil;
 import
  java.io.IOException;
 import
  java.nio.FloatBuffer;
 import
  java.nio.IntBuffer;
 import
  org.lwjgl.BufferUtils;
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
 
 public class SimplePolygonOpenGLWindow {
  /**
    * Titulo de la ventana.
    */
  private final String WINDOW_TITLE = "Simple Polygon OpenGL Window Tutorial";
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
     if (error == GLFW.GLFW_VERSION_UNAVAILABLE) {
      System.err.println("This demo requires OpenGL 3.0 or higher.");
     }
     this.delegate.invoke(error, description);
    }

    @Override
    public final void free() {
     this.delegate.free();
    }
   };
   GLFW.glfwSetErrorCallback(this.errorCallback);

   /*
    * Inicializamos 'GLFW' (Graphics Library Framework).
    * La mayoría de las funciones GLFW no funcionarán antes de hacer esto.
    * GLFW es una biblioteca de utilidad ligera para utilizar con OpenGL.
    * Proporciona a los programadores la capacidad de crear y dirigir ventanas
    * y aplicaciones OpenGL, así como recibir la entrada de joystick, teclado y
    * ratón.
    */
   if(!GLFW.glfwInit()) {
    throw new IllegalStateException("Unable to initialize GLFW");
   }

   /*
    * Configuramos el 'GLFW'.
    */
   this.configureGLFW();

   /*
    * Creamos la ventana.
    */
   this.window = GLFW.glfwCreateWindow(
    this.width, this.height, this.WINDOW_TITLE, MemoryUtil.NULL, MemoryUtil.NULL);
   if(this.window == MemoryUtil.NULL) {
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
     if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE ) {
      GLFW.glfwSetWindowShouldClose(window, true);
     }
    }
   };
   GLFW.glfwSetKeyCallback(this.window, this.keyCallback);

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
     && ((SimplePolygonOpenGLWindow.this.width != width)
      || (SimplePolygonOpenGLWindow.this.height != height))) {
      SimplePolygonOpenGLWindow.this.width = width;
      SimplePolygonOpenGLWindow.this.height = height;
     }
    }
   };
   GLFW.glfwSetWindowSizeCallback(this.window, this.windowSizeCallback);

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
     && ((SimplePolygonOpenGLWindow.this.width != width)
      || (SimplePolygonOpenGLWindow.this.height != height))) {
      SimplePolygonOpenGLWindow.this.width = width;
      SimplePolygonOpenGLWindow.this.height = height;
     }
    }
   };
   GLFW.glfwSetFramebufferSizeCallback(this.window, this.framebufferSizeCallback);

   /*
    * Obtenemos la resolución del monitor principal.
    */
   GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

   /*
    * Centramos la ventana.
    */
   GLFW.glfwSetWindowPos(
    this.window,
    (videoMode.width() - width) / 2,
    (videoMode.height() - height) / 2
   );

   try (MemoryStack frame = MemoryStack.stackPush()) {
    IntBuffer framebufferSize = frame.mallocInt(2);
    GLFW.nglfwGetFramebufferSize(
     this.window,
     MemoryUtil.memAddress(framebufferSize),
     MemoryUtil.memAddress(framebufferSize) + 4);
    this.width = framebufferSize.get(0);
    this.height = framebufferSize.get(1);
   }

   /*
    * Establecemos el contexto OpenGL como el actual.
    */
   GLFW.glfwMakeContextCurrent(this.window);

   /*
    * Activamos la sincronización vertical.
    */
   GLFW.glfwSwapInterval(1);

   /*
    * Establecemos la ventana como visible.
    */
   GLFW.glfwShowWindow(this.window);

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
   GLFW.glfwDefaultWindowHints();
   GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 1);
   GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 5);
   /*
    * La ventana permanecerá oculta después de la creación.
    */
   GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
   /*
    * Configuramos la ventana para que sea redimensionable.
    */
   GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
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
    GLFW.glfwDestroyWindow(this.window);
   } catch (Throwable throwable) {
    throwable.printStackTrace();
   } finally {
    /*
     * Liberamos el 'GLFW y el 'CallBack' de errores.
     */
    GLFW.glfwTerminate();
   }
  }

  private void loop() {
   int vbo = GL15.glGenBuffers();
   int ibo = GL15.glGenBuffers();
   float[] vertices = {-0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f};
   int[] indices = {0, 1, 2};
   GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
   GL15.glBufferData(GL15.GL_ARRAY_BUFFER, (FloatBuffer) BufferUtils.createFloatBuffer(vertices.length).put(vertices).flip(), GL15.GL_STATIC_DRAW);
   GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
   GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
   GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer) BufferUtils.createIntBuffer(indices.length).put(indices).flip(), GL15.GL_STATIC_DRAW);
   GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0L);

        
   /*
    * Ejecute el bucle de renderización hasta que el usuario cierre la ventana
    * o presione la tecla de ESCAPE.
    */
   while (!GLFW.glfwWindowShouldClose(this.window)) {

    /*
     * Esta sección es donde irá todo su código de dibujo. Cualquier cosa que planee mostrar en la pantalla se incluirá en esta sección del código. Cada tutorial después de este agregará código a esta sección del programa. Si ya tiene una comprensión de OpenGL, puede intentar crear formas básicas agregando el código de OpenGL debajo de glLoadIdentity () y antes de devolver TRUE. Si eres nuevo en OpenGL, espera mi próximo tutorial. Por ahora, todo lo que haremos es limpiar la pantalla al color que decidimos anteriormente, borrar el búfer de profundidad y restablecer la escena. Aún no dibujaremos nada.
     */

    /*
     * El retorno TRUE le dice a nuestro programa que no hubo problemas. Si desea que el programa se detenga por algún motivo, agregar una línea FALSE de retorno en algún lugar antes de devolver TRUE le indicará a nuestro programa que el código de dibujo falló. El programa se cerrará.
     */
    
    /*
     * Limpiamos el buffer.
     */
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 

    /*
     * El trabajo de la siguiente sección del código es cambiar el tamaño de
     * la escena OpenGL siempre que se haya cambiado el tamaño de la ventana
     * (suponiendo que esté utilizando una ventana en lugar del modo de
     * pantalla completa). Incluso si no puede cambiar el tamaño de la
     * ventana (por ejemplo, está en modo de pantalla completa), esta rutina
     * se seguirá llamando al menos una vez cuando se ejecute el programa por
     * primera vez para configurar nuestra vista en perspectiva. La escena de
     * OpenGL cambiará de tamaño según el ancho y el alto de la ventana en la
     * que se muestra.
     */
    
    /*
     * Restablecemos la vista actual.
     */
    if(this.height == 0) {
     this.height = 1;
    }
    GL11.glViewport(0, 0, this.width, this.height);

    /*
     * Las siguientes líneas configuran la pantalla para una vista en
     * perspectiva ortogonal, lo que significa que las cosas en la distancia
     * se hacen más pequeñas, esto crea una escena de aspecto realista.
     * Calculamos una vista en la que el tamaño horizontal se recalculara según
     * el tamaño de la pantalla, y en el plano vertical mantendrán siempre la
     * misma proporción
     */

    /*
     * glMatrixMode(GL_PROJECTION) indica que las siguientes líneas de código
     * afectarán a la matriz de proyección. La matriz de proyección se encarga
     * de añadir perspectiva a nuestra escena. glLoadIdentity() es similar a un
     * reinicio.
     * Restaura la matriz seleccionada a su estado original. Después de llamar
     * a glLoadIdentity(), configuramos nuestra vista en perspectiva para la
     * escena.
     * glMatrixMode(GL_MODELVIEW) indica que cualquier nueva transformación
     * afectará la matriz de 'Modelview'. La matriz de 'Modelview' es donde se
     * almacena nuestra información de objeto, y por último reajustamos la
     * matriz de 'Modelview'.
     */
      
    /*
     * Seleccionamos la matriz de proyección, y la reseteamos.
     */
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    
    /*
     * Calculamos la relación de aspecto de la ventana.
     */
    float aspectView = ((float) this.width/(float) this.height);
    GL11.glOrtho(-aspectView, aspectView, -1, 1, -1, 1);
    
    /*
     * Seleccionamos la matriz de modelado, y la reseteamos.
     */ 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    
    GL11.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0L);
    
    /* 
     * La siguiente línea permite un sombreado suave. El sombreado suave
     * combina bien los colores en un polígono y suaviza la iluminación.
     */
    
    /* 
     * Establecemos un sombreado suave.
     */
    GL11.glShadeModel(GL11.GL_SMOOTH);
        
    /* 
     * La siguiente línea establece el color de la pantalla cuando se borra. Si
     * no sabes cómo funcionan los colores, lo explicaremos rápidamente. Los
     * valores de color van desde 0.0f a 1.0f. El valor 0.0f es el más oscuro
     * (negro) y 1.0f valor el más claro (Blanco). El primer parámetro de la
     * función 'glClearColor' es la intensidad roja, el segundo parámetro es
     * para verde y el tercero para azul. Cuanto más cercano sea el valor a
     * 1.0f, más claro será ese color específico.
     * El último número es un valor alfa. Cuando se trata de limpiar la
     * pantalla, no nos debemos preocupar ya que al ser un color de fondo no
     * distinguiremos la posible trasparencia.
     */

    /*
     * La manera de crea diferentes colores es al mezclar los tres colores
     * primarios que definen la luz (rojo, verde, azul).
     * Entonces, si tuviéramos glClearColor(0.0f, 0.0f, 1.0f, 0.0f) estaríamos
     * borrando la pantalla con un azul brillante, si tuviéramos 
     * glClearColor(0.5f, 0.0f, 0.0f, 0.0f) estaríamos borrando la pantalla con
     * un rojo medio, ni muy claro (1.0f) ni muy oscuro (0.0f).
     * Para hacer un fondo blanco, se debe establecer todos los parámetros a los
     * valores más claros (1.0f). Para hacer un fondo negro, debemos establecer
     * todos los valores a los más oscuros posibles (0.0f). 
     */
        
    /*
     * Establecemos el color de fondo a negro.
     */
    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
 
    /* 
     * Las siguientes tres líneas tienen que ver con el búfer de profundidad.
     * Pensemos en el búfer de profundidad como multiples capas en la pantalla.
     * El búfer de profundidad realiza un seguimiento de la profundidad de los
     * objetos en la pantalla.
     * Realmente no usaremos el búfer de profundidad en este programa, pero
     * casi todos los programas OpenGL que se dibujan en la pantalla en 3D
     * usarán el búfer de profundidad.
     * Ordena qué objeto dibujar primero para que un cuadrado que dibuje detrás
     * de un círculo no termine encima del círculo.
     * El buffer de profundidad es una parte muy importante de OpenGL.
     */
    
    /*
     * Instalamos el 'Buffer' de profundidad.
     */
    GL11.glClearDepth(1.0f);

    /*
     * Se habilita las pruebas de profundidad.
     */
    GL11.glEnable(GL11.GL_DEPTH_TEST);

    /* 
     * Se establece el tipo de pruebas de profundidad a utilizar.
     */
    GL11.glDepthFunc(GL11.GL_LEQUAL);
    
    /* 
     * A continuación le decimos a OpenGL que queremos que se haga la mejor
     * corrección de perspectiva. Esto causa un impacto de rendimiento muy
     * pequeño, pero hace que la vista en perspectiva se vea un poco mejor.
     */
    GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); 
 
    /*
     * Intercambiamos los 'Buffers'.
     */
    GLFW.glfwSwapBuffers(this.window);

    /*
     * Volcamos los eventos de la ventana. El 'Callback' correspondiente al
     * teclado solo se invocará durante esta llamada.
     */
    GLFW.glfwPollEvents();
   }
  }

  public static void main(String[] args) {
   new SimplePolygonOpenGLWindow().run();
  }

 }