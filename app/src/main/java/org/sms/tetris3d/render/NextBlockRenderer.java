package org.sms.tetris3d.render;


import com.trippleit.android.tetris3d.shapes.Coords;
import javax.microedition.khronos.opengles.GL10;
import org.sms.tetris3d.players.*;
import org.sms.tetris3d.GameStatus;
import android.opengl.GLU;
/**
 * Created by hsh on 2016. 11. 17..
 */

public class NextBlockRenderer extends com.trippleit.android.tetris3d.render.AbstractOpenGlRenderer {
    protected float cx,cy,cz,cr=-0.05f,rad=0;
    @Override
    public void onDrawFrame(GL10 gl, boolean firstDraw) {
        final DeviceUser du = (DeviceUser)GameStatus.getPlayers().get(0);
        if(!GameStatus.isEnd()) {
            GLU.gluLookAt(gl, 0, -15, 5, 0, 0, 0, 0, 0, 1);

            new Coords(GameStatus.getGridSize(), GameStatus.getGameHeight()).draw(gl);
            if (GameStatus.getPlayers().get(0).getNextObject() == null) {
                final int objNum = du.randInt(0, 5);
                du.setNextObject(du.chooseObject(objNum));
            }
            gl.glTranslatef(2.5f, -5, 4);
            gl.glRotatef(rad, 0.0f, 0.0f, 1);
            rad = (rad + 2) % 360;
            printCurrentObject(gl,du);
        }
    }
    private void printCurrentObject(GL10 gl,User who){
        gl.glPushMatrix();
        gl.glTranslatef(0,0,0);//GameStatus.getCurrentObjectX(), GameStatus.getCurrentObjectY(), GameStatus.getCurrentObjectZ());

        GameStatus.getPlayers().get(0).getNextObject().draw(gl);
        gl.glPopMatrix();
    }
}