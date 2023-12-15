
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAnimatorControl;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator; 
 

@SuppressWarnings("serial")
public class Games extends GLCanvas
         implements GLEventListener, KeyListener {

   private static final int FPS = 60; 


   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            GLCanvas canvas = new Games();
 
            FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
 
            JFrame frame = new JFrame();
            frame.getContentPane().add(canvas);
            frame.setUndecorated(true);    
            frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
            frame.setVisible(true);
            animator.start(); 			
         }
      });
   }
 

 
   private GLU glu;  
   public Games() {

	   
      this.addGLEventListener(this);   
      this.addKeyListener(this); 		
      this.setFocusable(true);
      this.requestFocus();
   }
 
 
	char typing;
	float Rmove = (float)0.0f;
	float Rmoves= (float)-480/384f;
	int Rshot = 0;
	
	float Bmove = (float) 0.0;
	float Bmoves= (float) 480/384;
	int Bshot = 0;
	
	float BmoveX=(float) 0;
	float BmoveY=(float) 32/384;

	float Rsize=(float) 32/384;
	float Bsize=(float) 32/384;
	
	float BPlaceX1=(float)448/384;
	float BPlaceX2=(float)512/384;
	
	float RPlaceX1=(float)-448/384;
	float RPlaceX2=(float)-512/384;
	
	int Bbase = 1;
	int Rbase = 1;
	
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      
      glu = new GLU();                       
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
      gl.glClearDepth(1.0f);     
      gl.glEnable(GL_DEPTH_TEST); 
      gl.glDepthFunc(GL_LEQUAL); 
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
      gl.glShadeModel(GL_SMOOTH); 
   }
 
   public void BBox(GLAutoDrawable drawable){
	   if (Bbase >0){		   
			   GL2 gl = drawable.getGL().getGL2();
		    gl.glColor3f( 0.0f, 0.0f, 1f );    
			gl.glBegin(GL_POLYGON);
			gl.glVertex2f((float) BPlaceX1,(float) BmoveX-Bsize+Bmove);
			gl.glVertex2f((float) BPlaceX1,(float) BmoveX+Bsize+Bmove);
			gl.glVertex2f((float) BPlaceX2,(float) BmoveX+Bsize+Bmove);
			gl.glVertex2f((float) BPlaceX2,(float) BmoveX-Bsize+Bmove);
			gl.glEnd();		
			BBullet(drawable);	
	   }
   }
   
   public void RBox(GLAutoDrawable drawable){
	   if (Rbase > 0){ 
	   GL2 gl = drawable.getGL().getGL2();
	      gl.glColor3f( 1.0f, 0.0f, 0 );        
	  	gl.glBegin(GL_POLYGON);
		gl.glVertex2f((float) RPlaceX2,(float) -Rsize+Rmove);
		gl.glVertex2f((float) RPlaceX2,(float)  Rsize+Rmove);
		gl.glVertex2f((float) RPlaceX1,(float)  Rsize+Rmove);
		gl.glVertex2f((float) RPlaceX1,(float) -Rsize+Rmove);
		gl.glEnd();
		RBullet(drawable);
	   }
   }
   
   public void RBullet(GLAutoDrawable drawable){
	   if(Rshot == 1){
		   if(Rshot == 1){
		   Rmoves = Rmoves + 32/384f;
		   }
	   GL2 gl = drawable.getGL().getGL2();
	      gl.glColor3f( 1.0f, 0.0f, 0 );       
	  	gl.glBegin(GL_POLYGON);
		gl.glVertex2f(Rmoves-Rsize,(float) -Rsize+Rmove);
		gl.glVertex2f(Rmoves-Rsize,(float)  Rsize+Rmove);
		gl.glVertex2f(Rmoves+Rsize,(float)  Rsize+Rmove);
		gl.glVertex2f(Rmoves+Rsize,(float) -Rsize+Rmove);
		gl.glEnd();
		if(BPlaceX2 <= Rmoves && Rmoves >= BPlaceX1 && Rmove == Bmove ){
			Bbase = 0;
			}
		
	   }
   }
   
   public void BBullet(GLAutoDrawable drawable){
	   if(Bshot == 1){
		   if(Bshot == 1){
		   Bmoves = Bmoves - 32/384f;
		   }
	   GL2 gl = drawable.getGL().getGL2();
	      gl.glColor3f( 0.0f, 0.0f, 1 );        
	  	gl.glBegin(GL_POLYGON);
		gl.glVertex2f((float) Bmoves-Bsize,(float) -Bsize+Bmove);
		gl.glVertex2f((float) Bmoves-Bsize,(float)  Bsize+Bmove);
		gl.glVertex2f((float) Bmoves+Bsize,(float)  Bsize+Bmove);
		gl.glVertex2f((float) Bmoves+Bsize,(float) -Bsize+Bmove);
		gl.glEnd();
		if(RPlaceX2 >= Bmoves && Bmoves <= RPlaceX1 && Bmove == Rmove ){
			Rbase = 0;
			}
	   }
   }
   
   @Override
   public void display(GLAutoDrawable drawable) {
	   
	
	      GL2 gl = drawable.getGL().getGL2(); 
	      gl.glLoadIdentity();              
	      gl.glTranslatef( (float) -0/768, (float) -0/768,(float) -2.5 ); 
	
	      gl.glColor3f( 0.0f, 0.0f, 0.0f );        
	  	gl.glBegin(GL_POLYGON);
	  	gl.glVertex2f(-(float) 1024/384,(float) -768/384);
	  	gl.glVertex2f(-(float) 1024/384,(float) 768/384);
	  	gl.glVertex2f((float) 1024/384,(float) 768/384);
	  	gl.glVertex2f((float) 1024/384,-(float) 768/384);
	  	gl.glEnd();
	      
	      gl.glColor3f( 0.0f, 0.1f, 0.1f );       
	  	gl.glBegin(GL_POLYGON);
	  	gl.glVertex2f(-(float) 512/384,(float) -384/384);
	  	gl.glVertex2f(-(float) 512/384,(float) 384/384);
	  	gl.glVertex2f((float) 512/384,(float) 384/384);
	  	gl.glVertex2f((float) 512/384,-(float) 384/384);
	  	gl.glEnd();
	      
	  	
	    gl.glColor3f( 0.5f, 0.0f, 0.5f );     
		gl.glBegin(GL_POLYGON);
		gl.glVertex2f(-(float) 89/512,(float) -512/512);
		gl.glVertex2f(-(float) 89/512,(float) -576/512);
		gl.glVertex2f((float) 89/512,(float) -576/512);
		gl.glVertex2f((float) 89/512,-(float) 512/512);
		gl.glEnd();

		BBox(drawable);
		RBox(drawable);

   }
   
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  
 
      if (height == 0) height = 1;  
      float aspect = (float)width / height;
 
      gl.glViewport(0, 0, width, height);
 
      gl.glMatrixMode(GL_PROJECTION);  
      gl.glLoadIdentity();             
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); 
 
      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity(); 
      
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
      gl.glLoadIdentity(); 
 

   }
 
   /**
    * Called back by the animator to perform rendering.
    */

 
   /**
    * Called back before the OpenGL context is destroyed. Release resource such as buffers.
    */
   

	
   @Override
   public void dispose(GLAutoDrawable drawable) { }
 
   // ------ Implement methods declared in KeyListener ------
 
   @Override
   public void keyTyped(KeyEvent e) {
	 	typing = e.getKeyChar();
   }
 
   @Override
   public void keyPressed(KeyEvent e) {
	 typing = e.getKeyChar();
      int keyCode = e.getKeyCode();
      switch (keyCode) {
         case KeyEvent.VK_ESCAPE: // quit
            // Use a dedicate thread to run the stop() to ensure that the
            // animator stops before program exits.
            new Thread() {
               @Override
               public void run() {
                  GLAnimatorControl animator = getAnimator();
                  if (animator.isStarted()) animator.stop();
                  System.exit(0);
               }
            }.start();
            break;
            
            
         case KeyEvent.VK_W:
			  Rmove = (float) (Rmove + (float) 64/384);
			 break;

         case KeyEvent.VK_S:
			  Rmove = (float) (Rmove - (float) 64/384);
			 break;
			 
         case KeyEvent.VK_I:
			  Bmove = (float) (Bmove + (float) 64/384);
			 break;

        case KeyEvent.VK_K:
			  Bmove = (float) (Bmove - (float) 64/384);
			 break;
			 
        case KeyEvent.VK_D:
			  Rshot = 1;
			 break;
			
        case KeyEvent.VK_J:
			  Bshot = 1;
			 break;
      }
      
      
   }
 
   public void more(int beta){
	   	System.out.println("[keyReleased] keychar : "+ beta);
	   }
	   


   
   @Override
   public void keyReleased(KeyEvent e) {
	 	
   }
   
}