package checken;

import Textures.AnimListener;
import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;
//import checken.AnimGLEventListener3;

public class STart extends JFrame {
    static int y =700;
    static int x =700;
   
    

    public STart(AnimListener aListener,String name) {
        GLCanvas glcanvas;
        Animator animator;
        
        Cheaken md = new Cheaken(name);
        glcanvas = new GLCanvas();

        glcanvas.addGLEventListener(md);
        glcanvas.addMouseListener(md);
        glcanvas.addKeyListener(md);
        md.setGLCanvas(glcanvas);
        
        
        add(glcanvas, BorderLayout.CENTER);
        
        
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        
        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
