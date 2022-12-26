
package checken;
import Textures.AnimListener;
import Textures.TextureReader;
import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
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
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Mohamed
 */
public class Cheaken extends AnimListener implements GLEventListener, MouseListener {

    int mx;
    int my;
    int xOfSell = 250;
    int xOfSell2 = 450;
    int counter = 5;
//    int moveSpeedEasy=5;
//    int moveSpeedMediam=10;
//    int moveSpeedHard=5;
    String textureName[] = {
        "hom.png", "level1.png", "ner55.png", "egg2.png", "how.png",
        "EMH.png", "level2.png", "level3.png", "twoplayer.png",
        "loselevel1.png", "loswlevel2.png", "loselevel3.png"
        ,"playeronewin.png","PlayerTwoWin.png"
    };
    TextureReader.Texture texture;
    int textureIndex[] = new int[textureName.length];
    ///music
    FileInputStream music;
    private AudioStream audios;
    boolean musicOn = true;
    ///endmusic

    ////egg
    int eggEasy[] = {
        160, 335, 509
    };
    int eggMediam[] = {
        115, 264, 415, 560
    };
    int eggHard[] = {
        135, 236, 336, 441, 546
    };
    int eggPlayerOne[] = {
        71, 175, 274
    };
    int eggPlayerTwo[]={
        397, 498, 602
    };
    int eggy = 450;
    int eggy2 = 450;
    int number = new Random().nextInt(3);
    int number2 = new Random().nextInt(3);
    int eggx = eggEasy[number];
    int eggx2 = eggPlayerTwo[number];
    ///end egg

    ///time
    String time;
    GLAutoDrawable gldddd;
    TextRenderer renderer = new TextRenderer(new Font("SanasSerif", Font.BOLD, 20));
    TextRenderer renderer1 = new TextRenderer(new Font("Ubuntu", Font.BOLD, 30));
    ///end time

    ///switch
    boolean home = true;
    boolean singlePlayer = false;
    boolean maltiyPlayer = false;
    boolean info = false;
    //
    boolean easy = false;
    boolean mediam = false;
    boolean hard = false;
    //
    boolean left=false;
    boolean Right=false;
    //
    boolean end1 = false;
    boolean end2 = false;
    boolean end3 = false;
    ///end switch

    ///player name
    String name;
    int Score;
    int Score2;
    ///end player

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
        if (xOfSell <= 0) {
            xOfSell = 0;
        }
        if (xOfSell >= 600) {
            xOfSell = 600;
        }
        if (maltiyPlayer) {
            if (xOfSell >= 250) {
                xOfSell = 250;
            }
        }
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

    private int rand() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public void squreOFegg(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);	// Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[index]);

        if ((eggy > 80 && eggy < 95) && (((eggx - 85) < xOfSell) && ((eggx + 35) > xOfSell))) {
            if (easy) {
                number = rand(2);
                eggy = 450;
            }
            if (mediam) {
                number = rand(3);
                eggy = 390;
            }
            if (hard) {
                number = rand(4);
                eggy = 390;
            }
            if (maltiyPlayer) {
                number = rand(2);
                eggy = 450;
            }
            Score = Score + 10;
        }
        if (eggy <= 0) {
            if (easy) {
                number = rand(2);
                eggy = 450;
            }
            if (mediam) {
                number = rand(3);
                eggy = 390;
            }
            if (hard) {
                number = rand(4);
                eggy = 390;
            }
            if (maltiyPlayer) {
                number = rand(2);
                eggy = 450;
            }
            counter--;
        }

        if (easy) {
            eggx = eggEasy[number];
            gl.glPushMatrix();
            eggy = eggy - moveSpeedEasy;
            gl.glTranslated(eggx, eggy, 0);
        }
        if (mediam) {
            eggx = eggMediam[number];
            gl.glPushMatrix();
            eggy = eggy - moveSpeedMediam;
            gl.glTranslated(eggx, eggy, 0);
        }
        if (hard) {
            eggx = eggHard[number];
            gl.glPushMatrix();
            eggy = eggy - moveSpeedHard;
            gl.glTranslated(eggx, eggy, 0);
        }
        if (maltiyPlayer) {
            eggx = eggPlayerOne[number];
            gl.glPushMatrix();
            eggy = eggy - 5;
            gl.glTranslated(eggx, eggy, 0);
        }
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
        
        try {
            music = new FileInputStream(new File("Music//chicken dance song.wav"));
            audios = new AudioStream(music);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        AudioPlayer.player.start(audios);

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
     public void squreOFegg2(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);	// Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[index]);

        if ((eggy2 > 80 && eggy2 < 95) && (((eggx2 - 85) < xOfSell2) && ((eggx2 + 35) > xOfSell2))) {
            if (maltiyPlayer) {
                number2 = rand(2);
                eggy2 = 450;
            }
            Score2=Score2+10;
        }
        if (eggy2 <= 0) {
            if (maltiyPlayer) {
                number2 = rand(2);
                eggy2 = 450;
            }
        }
        if (maltiyPlayer) {
            eggx2 = eggPlayerTwo[number2];
            gl.glPushMatrix();
            eggy2 = eggy2 - 5;
            gl.glTranslated(eggx2, eggy2, 0);
        }
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
     public void DrawTime() throws ParseException {

        String time1 = time;
        String time2 = java.time.LocalTime.now() + "";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();

        String fi = String.format("Time:%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(difference),
                TimeUnit.MILLISECONDS.toSeconds(difference) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference))
        );
        if(easy){
            if ("Time:01:00".equals(fi)){
            
            }
        }
        if(mediam){
             if ("Time:01:00".equals(fi)){
            
            }
        }
        if(hard){
             if ("Time:01:00".equals(fi)){
            
            }
        }
        if(maltiyPlayer){
            if ("Time:01:00".equals(fi)){
                if(Score>Score2){
                    maltiyPlayer=false;
                    left=true;
                }
                else if(Score==Score2){
                    maltiyPlayer=false;
                    home=true;
                }
                else{
                    maltiyPlayer=false;
                    Right=true;
                }
            }
        }

        renderer.beginRendering(gldddd.getWidth(), gldddd.getHeight());
        renderer.draw(fi, 550, 620);
        renderer.endRendering();
    }

    @Override
    public void display(GLAutoDrawable gld) {
        try {
            gldddd = gld;
            GL gl = gld.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
            handleKeyPress();
            if (home) {
                squreOfHome(gl, 0);
            }
            if (singlePlayer) {
                squreOfHome(gl, 1);
                squreOFegg(gl, 3);
                squreOFsell(gl, 2);
                DrawTime();
            }
            if (maltiyPlayer) {

            }
            if (info) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        System.out.println(mx + " " + my);
        if (home) {
            if ((mx > 80 && mx < 260) && (my > (90) && my < (180))) {
                System.out.println("one player");
                home = false;
                singlePlayer = true;
            }

            if ((mx > 80 && mx < 260) && (my > (200) && my < (300))) {
                System.out.println("two player");
            }

            if ((mx > 80 && mx < 260) && (my > (325) && my < (418))) {
                System.out.println("info");
                home = false;
                singlePlayer = false;
                maltiyPlayer = false;
                info = true;
            }

            if ((mx > 80 && mx < 260) && (my > (441) && my < (534))) {
                System.out.println("exit");
                System.exit(0);
            }
            
            if ((mx > 613 && mx < 662) && (my > (49) && my < (103))) {
                System.out.println("sound");
                if(musicOn){
                    musicOn=false;
                    AudioPlayer.player.stop(audios);
                }else{
                    musicOn=true;
                    AudioPlayer.player.start(audios);
                }
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
