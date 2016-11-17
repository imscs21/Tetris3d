package com.trippleit.android.tetris3d.shapes;

import org.sms.tetris3d.players.User;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube extends AbstractDraw implements IShape {

	String color = "#ffffff";

	boolean objectMatrix[][][];
	float dx=0.001f;
	private float vertices[] = { 
			0.0f+dx, 0.0f+dx, 0.0f+dx, // 0
			1.0f-dx, 0.0f+dx, 0.0f+dx, // 1
			1.0f-dx, 1.0f-dx, 0.0f+dx, // 2
			0.0f+dx, 1.0f-dx, 0.0f+dx, // 3
			
			0.0f+dx, 0.0f+dx, 1.0f-dx, // 4
			1.0f-dx, 0.0f+dx, 1.0f-dx, // 5
			1.0f-dx, 1.0f-dx, 1.0f-dx, // 6
			0.0f+dx, 1.0f-dx, 1.0f-dx  // 7
	};
	
	private float lineVertices[] = { 
			0.0f, 0.0f, 0.0f, // 0
			1.0f, 0.0f, 0.0f, // 1
			1.0f, 1.0f, 0.0f, // 2
			0.0f, 1.0f, 0.0f, // 3
			
			0.0f, 0.0f, 1.0f, // 4
			1.0f, 0.0f, 1.0f, // 5
			1.0f, 1.0f, 1.0f, // 6
			0.0f, 1.0f, 1.0f,  // 7			
	};
	public float[][] packWithPrefixPos(final float prefix){
		float[][] rst = new float[2][vertices.length];
		for(int i = 0; i<vertices.length;i++){
			rst[0][i] = vertices[i]+prefix;
			rst[1][i] = lineVertices[i]+prefix;
		}
		return rst;
	}
	private short[] indices = { 3, 1, 0, 3, 2, 1, 4, 5, 7, 7, 5, 6, 0, 1, 5, 5, 4, 0, 6, 2, 3, 7, 6, 3, 1, 2, 5, 6, 5, 2, 4, 3, 0, 3, 4, 7 };
	
	private short[] lineIndices = { 0, 1, 1, 2, 2, 3, 3, 0, 4, 5, 5, 6, 6, 7, 7, 4, 0, 4, 1, 5, 2, 6, 3, 7 };

	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	private FloatBuffer lineIertexBuffer;
	private ShortBuffer lineIndexBuffer;

	String lineColor = "#000000";
	String boneLineColor = "#ffffff";
	private FloatBuffer colorBuffer;
	private FloatBuffer colorLineBuffer;
	private FloatBuffer colorBoneLineBuffer;
/*
	public Cube() {
		init();
		colorBuffer = null;

	}

	public Cube(String color) {
		init();
		colorBuffer = floatToFloatBuffer(convertColor(color, vertices.length + vertices.length / 3));
	}*/
	public Cube(User usr) {
		setUser(usr);
		init();
		colorBuffer = null;

	}

	public Cube(String color,User usr) {
		setUser(usr);
		init();
		colorBuffer = floatToFloatBuffer(convertColor(color, vertices.length + vertices.length / 3));
	}
	private void init() {		
		vertexBuffer = floatToFloatBuffer(vertices);
		indexBuffer = shortToShortBuffer(indices);
		
		lineIertexBuffer = floatToFloatBuffer(lineVertices);
		lineIndexBuffer = shortToShortBuffer(lineIndices);
		
		colorLineBuffer = floatToFloatBuffer(convertColor(lineColor, vertices.length + vertices.length / 3));
		colorBoneLineBuffer = floatToFloatBuffer(convertColor(boneLineColor, vertices.length + vertices.length / 3));
		objectMatrix = createFalsMatrix(1);
		objectMatrix[0][0][0] = true;
	}

	@Override
	public User getUser() {
		return usr;
	}

	@Override
	public void setUser(User usr) {
		this.usr = usr;
	}

	@Override
	public void draw(GL10 gl) {
		draw(gl, vertexBuffer, indexBuffer, indices);
		if (colorBuffer == null)
			draw(gl, vertexBuffer, indexBuffer, indices);
		else
			draw(gl, vertexBuffer, indexBuffer, indices, colorBuffer);
		drawLines(gl, lineIertexBuffer, lineIndexBuffer, lineIndices,
				colorLineBuffer);
	}
	public void drawLineBone(GL10 gl){
		drawLines(gl, lineIertexBuffer, lineIndexBuffer, lineIndices,
				colorBoneLineBuffer);
	}
	@Override
	public boolean[][][] getObjectMatrix() {
		return objectMatrix;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getXsize() {
		return getXsize(objectMatrix);
	}

	@Override
	public int getYsize() {
		return getYsize(objectMatrix);
	}
	
	@Override
	public int getZsize() {
		return getZsize(objectMatrix);
	}

	@Override
	public void rotate(int axis) {
		switch(axis){
			case 1:
			case 'x':
				objectMatrix = rotateX(objectMatrix,getUser()); break;
			case 2:
			case 'y':
				objectMatrix = rotateY(objectMatrix,getUser()); break;
			case 3:
			case 'z':
				objectMatrix = rotateZ(objectMatrix,getUser()); break;
		}
	}

}
