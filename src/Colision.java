/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naves;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

    
public class Colision extends BasicCollisionGroup  {
    {
        pixelPerfectCollision = true;
    }

    public void collided(Sprite nave, Sprite asteroides)  
    {
       nave.setX(200);
       nave.setY(200);
    }
}
