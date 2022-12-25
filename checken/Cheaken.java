/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checken;

import Textures.AnimListener;
import Textures.TextureReader;
import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Mohamed
 */
public class Cheaken extends AnimListener implements GLEventListener, MouseListener {

    int xOfSell = 300;
    String textureName[] = {"hom.png","level1.png", "ner5.png", "egg1.png","how.png"};
    TextureReader.Texture texture;
    int textureIndex[] = new int[textureName.length];
    
    ////egg
    int egg[] = {
        160, 335, 509
    };
    int eggy = 450;
    int number = new Random().nextInt(3);;
    int eggx = egg[number];
    ///end egg
    
    ///time
    String time = java.time.LocalTime.now() + "";
    GLAutoDrawable gldddd;
    TextRenderer renderer = new TextRenderer(new Font("SanasSerif", Font.BOLD, 20));
    ///end time
    
    ///switch
    boolean home=true;
    boolean singlePlayer=false;
    boolean maltiyPlayer=false;
    boolean info=false;
    boolean exit=false;
    ///end switch
    
    public void DrawTime() throws ParseException {

        String time1 = time;
        String time2 = java.time.LocalTime.now() + "";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();

        String fi = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(difference),
                TimeUnit.MILLISECONDS.toSeconds(difference)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference))
        );

        renderer.beginRendering(gldddd.getWidth(), gldddd.getHeight());
        renderer.draw(fi, 600, 620);
        renderer.endRendering();
    }
    
    public void squreOFsell(GL gl, int index) {

        gl.glEnable(GL.GL_BLEND);	// Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[index]);

        gl.glPushMatrix();

        gl.glTranslated(xOfSell, 50, 0);

        gl.glBegin(GL.GL_QUADS);

        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0f, 0f, -1.0f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(100, 0f, -1.0f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(100f, 70, -1.0f);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0f, 70, -1.0f);

        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    
    private int rand(){
        Random rand = new Random();
        return  rand.nextInt(3);
    }

    public void squreOFegg(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);	// Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[index]);
        
        if(eggy==70 && (((eggx-50)<xOfSell)&&((eggx+50)>xOfSell))){
            number=rand();
            eggy=450;
        }
        if(eggy==0){
            number=rand();
            eggy=450;
        }
        System.out.println(number);
        eggx = egg[number];
        gl.glPushMatrix();
        eggy=eggy-5;
        gl.glTranslated(eggx, eggy, 0);
        gl.glBegin(GL.GL_QUADS);

        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0f, 0f, -1.0f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(30, 0f, -1.0f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(30, 50, -1.0f);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0f, 50, -1.0f);

        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void squreOfHome(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);	// Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[index]);
        
        gl.glPushMatrix();
        
        gl.glBegin(GL.GL_QUADS);

        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(0f, 0f, -1.0f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(700, 0f, -1.0f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(700f, 700f, -1.0f);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(0f, 700f, -1.0f);

        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glViewport(0, 0, 700, 700);
//        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);    //This Will Clear The Background Color To Black
        gl.glMatrixMode(GL.GL_PROJECTION);
//        gl.glOrtho(-450, 450, -250, 250, -1.0, 1.0);
        gl.glOrtho(0, 700, 0, 700, -1.0, 1.0);

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        //number of textures, array to hold the indeces
        gl.glGenTextures(textureName.length, textureIndex, 0);

        for (int i = 0; i < textureName.length; i++) {
            try {
                texture = TextureReader.readTexture(assetsFolderName + "//" + textureName[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[i]);

//          mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture.getWidth(), texture.getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture.getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void display(GLAutoDrawable gld) {
        try {
            gldddd = gld;
            GL gl = gld.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
            handleKeyPress();
            if(home){
                squreOfHome(gl, 0);
            }
            if(singlePlayer){
                squreOfHome(gl, 1);
                squreOFegg(gl, 3);
                squreOFsell(gl, 2);
                DrawTime();
            }
            if(maltiyPlayer){
                
            }
            if(info){
                squreOfHome(gl, 4);
            }
            
        } catch (ParseException ex) {
            
        }
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
    int mx;
    int my;
    @Override
    public void mouseClicked(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        System.out.println(mx + " " + my);
       if(home){
            if((mx>80&&mx<260)&&(my>(90)&&my<(180))){
                System.out.println("one player");
                home=false;
                singlePlayer=true;
            }
            
            if((mx>80&&mx<260)&&(my>(200)&&my<(300))){
                System.out.println("two player");
            }
            
            if((mx>80&&mx<260)&&(my>(325)&&my<(418))){
                System.out.println("info");
                home=false;
                singlePlayer=false;
                maltiyPlayer=false;
                info=true;
            }
            
             if((mx>80&&mx<260)&&(my>(441)&&my<(534))){
                System.out.println("exit");
                System.exit(0);
            }
        }
    }

    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            xOfSell = xOfSell - 10;
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            xOfSell = xOfSell + 10;
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {

        }
        if (isKeyPressed(KeyEvent.VK_UP)) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }
    GLCanvas glc;
}