package checken;

import Textures.AnimListener;
import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;
//import checken.AnimGLEventListener3;

public class STart extends JFrame {

   

    public static void main(String[] args) {
        new STart(new Cheaken());
    }

    public STart(AnimListener aListener) {
        GLCanvas glcanvas;
        Animator animator;

        Cheaken md = new Cheaken();
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
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
