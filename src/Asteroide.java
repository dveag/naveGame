/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Random;

public class Asteroide extends AdvanceSprite 
{
    BaseLoader miBSLoader;
    Background fond;
    int direccion; //direccion a la cual debe de ver el monito: 0 derecha, 1 derecha, 2 abajo, 3 arriba
    Random r = new Random();
    
    
   public Asteroide()
   {
        super();
        direccion = r.nextInt(Integer.MAX_VALUE)%5;
   }
    
     /*
     * arg0 
     * arg1
     * arg2: status
     * arg3: dir
     */
    protected void animationChanged(int arg0, int arg1, int arg2, int arg3)
    {
        super.animationChanged(arg0, arg1, arg2, arg3);
        this.setImages(miBSLoader.getStoredImages("asteroide"));
    }
                
                
    public void obtenerBsLoader(BaseLoader bsLoader)
    {
        this.miBSLoader = bsLoader;
    }
    
    public void obtenerFondo(Background fondo)
    {
        this.fond = fondo;
    }

    public boolean subir() 
    {
        if(getY()-2 > 0)
            move(0, -10);
        else
            setY(0);
        return true;
    }
     
    public boolean derecha() 
    {
        if(getX()+ getWidth() +150 < fond.getWidth())
            move(10, 0);
        else
            setX(fond.getWidth()-150);
        return true;
    }
         
         
    public boolean bajar() 
    {
        if(getY()+ getHeight() + 2 < fond.getHeight())
            move(0, 10);
        else
            setY(fond.getHeight()-53);
        return true;
    }
         
             
    public boolean izquierda() 
    {
        if(getX()-2>0)
            move(-10, 0);
        else
            setX(0);
        return true;
    }
    
    
    public void mover ()
    {
    int dir = r.nextInt(Integer.MAX_VALUE)%4;
    int cdir= r.nextInt(Integer.MAX_VALUE)%10;
    if(cdir<1)
    {
        switch(dir)
        {
        case 0:
            subir();
            direccion=0;
            break;
        case 1:
            derecha();
            direccion=1;
            break;
        case 2:
            bajar();
            direccion=2;
            break;
        case 3:
            izquierda();
            direccion=3;
            break;
        }
    }
    else //direccion anterior
        {
            switch(direccion)
            {
            case 0:
                subir();
                direccion=0;
                break;
            case 1:
                derecha();
                direccion=1;
                break;
            case 2:
                bajar();
                direccion=2;
                break;
            case 3:
                izquierda();
                direccion=3;
                break;
            }       
        }
    }
}

