package naves;

import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;


public class Barra extends AdvanceSprite
{
    Background fond;
    public Barra()
    {
        super();
    }
    
    public void obtenerFondo(Background fondo)
    {
        this.fond = fondo;
    }
       
    public boolean subir(int altura) 
    {
        System.out.println(altura);
        if(getY() - altura - 2 > 0)
            move(0,-7);
        return true;
    }
     
    public boolean derecha() 
    {
        if(getX()+getWidth()+2<fond.getWidth())
            move(7, 0);
        return true;
    }
         
         
    public boolean bajar(int altura) 
    {
        if(getY()+ getHeight() + 2 < fond.getHeight())
            move(0, 7);
        return true;
    }
         
         
         
    public boolean izquierda() 
    {
        if(getX()-2>0)
            move(-7, 0);
        return true;
    }
}
