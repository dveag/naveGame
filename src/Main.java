/*
 * Asteroides aleatorios y nave que se mueve con las flechas del teclado.
 * Autores: David Ruiz, matricula A01672438
            Daniela Velásquez, matricula A01672439
 */
package naves;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import com.golden.gamedev.*;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import  com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Game {
    Random r = new Random();
    static int DimXP = 800;
    static int DimYP = 600;
    static int DimXClip = 800;
    static int DimYClip = 600;
    Background fond;
    Nave nave;
    Barra barra;
    Asteroide asteroide;
    ArrayList<Asteroide> asteroides = new ArrayList<>();
    Iterator<Asteroide> it;
    int num_ast;
    BufferedImage image;
    ParallaxBackground fondo;
    Background f1, f2, f3, f4, f5;
    int gana=0, pierde=0;
    Background fondo_ganar, fondo_perder;
    long start_clock, end_clock;
    int nivel=1;
    SpriteGroup grupoAsteroides, grupoNave;
    Colision colisionador;
    int bandera_cargar_imagen=0;
    Timer velocidad,tiempo_barra;

    

    public void CargarImagenes()
    {   
        bsLoader.storeImages("0", getImages("images/arriba.png", 4, 1));//arriba
        bsLoader.storeImages("1", getImages("images/derecha.png", 1, 4));//derecha
        bsLoader.storeImages("2", getImages("images/abajo.png", 4, 1));//abajo
        bsLoader.storeImages("3", getImages("images/izquierda.png", 1, 4));//izquierda
        bsLoader.storeImages("4", getImages("images/paradoArriba.png", 4, 1));//parado arriba
        bsLoader.storeImages("5", getImages("images/paradoDerecha.png", 1, 4));//parado
        bsLoader.storeImages("6", getImages("images/paradoAbajo.png", 4, 1));//parado
        bsLoader.storeImages("7", getImages("images/paradoIzquierda.png", 1, 4));//parado
        bsLoader.storeImages("asteroide", getImages("images/asteroide.png", 8, 8));
        bsLoader.storeImages("barra", getImages("images/barra.png", 1, 11));
    }
    
    
    @Override
    public void initResources() {
     //inicializacion de las variables del juego
        grupoAsteroides = new SpriteGroup("Grupo asteroides");
        if(bandera_cargar_imagen==0)
        {
            CargarImagenes();
            bandera_cargar_imagen=1;
        }
        fondo_ganar = new ImageBackground(getImage("images/nivel.png"));
        fondo_ganar.setClip(0, 0, DimXClip, DimYClip);
        fondo_perder = new ImageBackground(getImage("images/game_over.png"));
        fondo_perder.setClip(0, 0, DimXClip, DimYClip);
        f1 = new ImageBackground(getImage("images/esp1.png"));
        f2 = new ImageBackground(getImage("images/esp2.png"));
        f3 = new ImageBackground(getImage("images/esp3.png"));
        f4 = new ImageBackground(getImage("images/esp4.png"));
        fond = new ParallaxBackground(new Background[] {f1,f2,f3,f4});
        if(nivel==1)
        {

            nave = new Nave();
            barra = new Barra();
            nave.setImages(bsLoader.getStoredImages("4"));
            fond.setClip(0, 0, DimXClip, DimYClip);
            nave.setX(200);
            nave.setY(200);
            barra.setX(nave.getX());
            barra.setY(nave.getY() +55);
            barra.setImages(bsLoader.getStoredImages("barra"));
            barra.setAnimate(true);
            barra.setLoopAnim(true);
            barra.obtenerFondo(fond);
            nave.obtenerFondo(fond);
            nave.obtenerBsLoader(bsLoader);
            nave.setBackground(fond);
            barra.setBackground(fond);
            start_clock = System.currentTimeMillis();
            num_ast=30;
            grupoNave = new SpriteGroup("Grupo nave");
            grupoNave.add(nave);
            grupoNave.setBackground(fond);
            grupoAsteroides = new SpriteGroup("Grupo asteroides");
            velocidad= new Timer(200);
            tiempo_barra= new Timer(60000/11);
            for(int i=0;i<num_ast;i++)
            {
                asteroide= new Asteroide();
                asteroide.setImages(bsLoader.getStoredImages("asteroide"));
                asteroide.setX(r.nextInt(Integer.MAX_VALUE)%fond.getWidth());
                asteroide.setY(r.nextInt(Integer.MAX_VALUE)%fond.getHeight());
                asteroide.setBackground(fond);
                asteroide.obtenerFondo(fond);
                asteroide.obtenerBsLoader(bsLoader);
                asteroide.setAnimate(true);
                asteroide.setLoopAnim(true);
                
                grupoAsteroides.add(asteroide);
                asteroides.add(asteroide);
            }
            grupoAsteroides.setBackground(fond);
        }
        
        if(nivel==2)
        {
            fondo_ganar = new ImageBackground(getImage("images/nivel.png"));
            fondo_ganar.setClip(0, 0, DimXClip, DimYClip);
            fondo_perder = new ImageBackground(getImage("images/game_over.png"));
            fondo_perder.setClip(0, 0, DimXClip, DimYClip);
            nave = new Nave();
            barra = new Barra();
            nave.setImages(bsLoader.getStoredImages("4"));
            fond.setClip(0, 0, DimXClip, DimYClip);
            nave.setX(200);
            nave.setY(200);
            barra.setX(nave.getX());
            barra.setY(nave.getY() +55);
            barra.setImages(bsLoader.getStoredImages("barra"));
            barra.setBackground(fond);
            barra.obtenerFondo(fond);
            barra.setAnimate(true);
            barra.setLoopAnim(true);
            nave.setBackground(fond);
            nave.obtenerFondo(fond);
            nave.obtenerBsLoader(bsLoader);
            start_clock = System.currentTimeMillis(); 
            num_ast=30;
            grupoNave = new SpriteGroup("Grupo nave");
            grupoNave.add(nave);
            grupoNave.setBackground(fond);
            velocidad= new Timer(1);
            for(int i=0;i<num_ast;i++)
            {
                Asteroide asteroide= new Asteroide();
                asteroide.setImages(bsLoader.getStoredImages("asteroide"));
                asteroide.setX(r.nextInt(Integer.MAX_VALUE)%fond.getWidth());
                asteroide.setY(r.nextInt(Integer.MAX_VALUE)%fond.getHeight());
                asteroide.setBackground(fond);
                asteroide.obtenerFondo(fond);
                asteroide.obtenerBsLoader(bsLoader);
                asteroide.setAnimate(true);
                asteroide.setLoopAnim(true);
                
                grupoAsteroides.add(asteroide);
                asteroides.add(asteroide);
            }
            grupoAsteroides.setBackground(fond);
        }
        colisionador = new Colision();
        colisionador.setCollisionGroup(grupoNave, grupoAsteroides);

        setFPS(35);
    }
    
    
    @Override
    public void update(long elapsedTime) 
    {
        fond.update(elapsedTime);
        grupoNave.update(elapsedTime);
        grupoAsteroides.update(elapsedTime);
        fond.setToCenter(nave);
        nave.update(elapsedTime); 
        barra.update(elapsedTime);
        fondo_ganar.update(elapsedTime);
        fondo_perder.update(elapsedTime);
        
        colisionador.checkCollision();
        if(nave.getX()==200 && nave.getY()==200)
        {
            barra.setX(nave.getX());
            barra.setY(nave.getY() +55);
        }
            if (keyDown(KeyEvent.VK_UP)) 
            {
                nave.setDirection(0);
                nave.setStatus(1);
                nave.subir();
                barra.subir(nave.getHeight());
            }
            else
            if (keyDown(KeyEvent.VK_DOWN)) 
            {
                nave.setDirection(2);
                nave.setStatus(1);
                nave.bajar();
                barra.bajar((int) nave.getHeight());
            }
            else
            if (keyDown(KeyEvent.VK_RIGHT)) 
            {
                nave.setDirection(1);
                nave.setStatus(1);
                nave.derecha();
                barra.derecha();
            }
            else
            if (keyDown(KeyEvent.VK_LEFT))
            {
                nave.setDirection(3);
                nave.setStatus(1);
                nave.izquierda();
                barra.izquierda(); 
            }
            else
            {
            nave.setStatus(0);   
            }
        

        it=asteroides.iterator();
        while(it.hasNext())
        {
            asteroide = it.next();
            asteroide.mover();
            asteroide.update(elapsedTime);
        }
      
        if(nave.gana())
        {
            gana=1;
            nivel=2;
        }
        
        
        if((end_clock = System.currentTimeMillis()-start_clock)/1000 == 60)
        {
            pierde=1;
            nivel=1;
        }
        
        if(velocidad.action(elapsedTime))
        {
            it=asteroides.iterator();
            while(it.hasNext())
            {
                asteroide = it.next();
                asteroide.mover();
                asteroide.update(elapsedTime);
            }
        }
        
        

        barra.setAnimationTimer(tiempo_barra);
       
        
    }

    @Override
    public void render(Graphics2D g) {
     //rendereo de la pantalla
        if(gana==0 && pierde==0)
        {    
            fond.render(g);
            grupoNave.render(g);
            grupoAsteroides.render(g);
            barra.render(g);
        }
        if(gana==1&&pierde==0)
        {
            fondo_ganar.render(g);
            if (keyDown(KeyEvent.VK_ENTER)) 
            {
                gana=0;
                initResources();
            }
            
        }
        
        if(gana==0&&pierde==1)
        {
            fondo_perder.render(g);
            if (keyDown(KeyEvent.VK_ENTER)) 
            {
                pierde=0;
                initResources();
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        GameLoader game = new GameLoader();
        game.setup(new Main(), new Dimension(DimXP,DimYP), false);
        game.start();
    }

}
