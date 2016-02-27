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

public class Nave extends AdvanceSprite 
{
    BaseLoader miBSLoader;
    Background fond;
    int direccion; //direccion a la cual debe de ver el monito: 0 derecha, 1 derecha, 2 abajo, 3 arriba
    int estado; //0 parado, 1 moviendose
    
    
   public Nave()
   {
        super();
        direccion = 4;
        estado = 0;
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
        switch (arg2)
        {
            case 0: //la nave está parada
                    if (arg1 == 0) 
                    {
                      this.setImages(miBSLoader.getStoredImages("4"));
                    }
                    if (arg1 == 1) 
                    {
                      this.setImages(miBSLoader.getStoredImages("5"));
                    }
                    if (arg1 == 2) 
                    {
                      this.setImages(miBSLoader.getStoredImages("6"));
                    }
                    if (arg1 == 3) 
                    {
                      this.setImages(miBSLoader.getStoredImages("7"));
                    } 
                 this.setAnimate(true);
                 this.setLoopAnim(true);
                 break;
            case 1: //la nave está moviendose
                if (arg3 == 0) {
                      this.setImages(miBSLoader.getStoredImages("0"));
                      this.setAnimate(true);
                      this.setLoopAnim(true);
                    } 
                if (arg3 == 1) {
                      this.setImages(miBSLoader.getStoredImages("1"));
                    } 
                if (arg3 == 2) {
                      this.setImages(miBSLoader.getStoredImages("2"));
                    } 
                if (arg3 == 3) {
                      this.setImages(miBSLoader.getStoredImages("3"));
                    } 
                 this.setAnimate(true);
                 this.setLoopAnim(true);
                break;
        }
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
            move(0,-7);
        else
            setY(0);
        return true;
    }
     
    public boolean derecha() 
    {
        if(getX()+getWidth()+2<fond.getWidth())
            move(7, 0);
        else
            setX(fond.getWidth()-53);
        return true;
    }
         
         
    public boolean bajar() 
    {
        if(getY()+ getHeight() + 40 < fond.getHeight())
            move(0, 7);
        else
            setY(fond.getHeight()-53);
        return true;
    }
         
         
         
    public boolean izquierda() 
    {
        if(getX()-2>0)
            move(-7, 0);
        else
            setX(0);
        return true;
    }
    
    public void choque() 
    {
        setX(300);
        setY(300);
    }
    
    public boolean gana() 
    {
        if(getX()+ getWidth() +70 > fond.getWidth())
        {    
            return true;
        }
        else
        {
            return false;
        }
    }
}
