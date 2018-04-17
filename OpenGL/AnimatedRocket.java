CS2150Coursework.java
 * University Username: alinasg
 * Full Name: Gerard Alinas
 * Course: Computer Science
 * Year: 2nd Year
 * Statement: This work is fully designed and created by Gerard Alinas with only a small amount of 
 * 	          code used from other labs and internet such as the unit plane, rotation, launch method and textures. 
 * 			  However all objects, rest of the logic and the implementation of objects are done by me.
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *  +--[S(127.5,1.0,27.5) T(20.75,-12.5,-3.0)] Ground Plane
 *  |
 *  +--[S(150.0,1.0,15.0) Rx(-90.0) T(20.0, 2.0, 35.0)] Background Plane
 *  |
 *  +--T(15.0,7.0,8.5)] Rx(rotateEarth) Earth
 *  |
 *  +--T(7.0,13.0,10.5)] Rx(rotateMoon) Moon
 *  |
 *  +--T(moveAsteroid,13.0,15.0)] Rx(rotateAsteroid) Asteroid
 *  |
 *  +--T(0.0,rocketPosition,-10.0)] Main Head and Body
 *  |	|	|
 *  	|	|
 *  |	+--T(1.0,0.0, 0.0)] Left Head and Body
 *	|	|	|
 *	|	|	+--T(2.0,0.0,0.0)] Left Hexagon
 *	|	|	|
 *	|	|	|
 *	|	+--T(-2.0,0.0,0.0)] Right Head and Body
 *	|	|	|
 * 	|	|	+--T(0.0,-5.25,0.0)] Right Hexagon
 * 	|
 * 	+--T(-1.0,shuttle,-1.25)] Shuttle
 * 		|
 * 		+--T(2.5,0.0f,0.0)] Left Wing
 * 		|
 * 		+--T(-5.0,0.0f,0.0)] Right Wing
 */
package coursework.alinasg;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This submission is based on an outer space theme representing a rocket and a shuttle, and with sphere objects consisting of planet Earth,
 * moon and an asteroid. In terms of animation, the rocket and shuttle can launch upwards once the 'L' key is pressed. It will rotate on its
 * own axis and seperate automatically once it reaches a specific point. An asteroid which is another sphere will move across during the process 
 * of the launch. Furthermore, all sphere objects will constantly rotate on its own axis within the background. The application can be reset 
 * which will return all of the objects into their starting position once the 'SPACE' key is pressed. 
 * 
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>Press the 'L' key to launch the rocket and shuttle along with the asteroid going across the screen.
 * <li>Press the 'Space' key to reset all animations, placing all objects on its starting position.
 * <li>Hold the 'R' key to rotate the rocket and shuttle object.
 * </ul>
 *
 */
public class CS2150Coursework extends GraphicsLab
{
	private final int mainbodyList = 1;
	private final int leftbodyList = 2;
	private final int rightbodyList = 3;
	private final int rightHexagonList = 4;
	private final int leftHexagonList = 5;
	private final int shuttleList = 6;
	private final int leftWingList = 7;
	private final int rightWingList = 8;
	private final int backgroundList = 10;
	private Texture groundTexture;
	private Texture backgroundTexture;
	private Texture earthTexture;
	private Texture moonTexture;
	private Texture asteroidTexture;

	private boolean takeOff = false;
	private boolean asteroidBool = false;
	private float objectRotationAngle= 0.0f;
	private float resetAngle = 0.0f;
	private float rotateEarth = 0.0f;
	private float rotateMoon = 0.0f;
	private float rotateAsteroid = 0.0f;
	private float rocketPosition = 0.0f;
	private float resetPosition = 0.0f;
	private float moveAsteroid = -32.0f;
	private float resetAsteroid = -32.0f;
	private float rotatingPoint = 2.5f;
	private float shuttle = 1.25f;
	private float resetShuttle = 1.25f;
	private float seperationPoint = 6.0f;

	public static void main(String args[])
	{   new CS2150Coursework().run(WINDOWED,"CS2150 Animation Coursework",0.1f);
	}

	/**
	 * Loads all of the textures and objects into the screen.
	 */
	protected void initScene() throws Exception
	{
		// Loads the ground (Mars) texture..
		groundTexture = loadTexture("coursework/alinasg/textures/ground.bmp");
		// Loads the background (Galaxy) texture..
		backgroundTexture = loadTexture("coursework/alinasg/textures/space.bmp");
		// Loads the Earth mapping texture..
		earthTexture = loadTexture("coursework/alinasg/textures/earthTexture.bmp");
		// Loads the moon mapping texture..
		moonTexture = loadTexture("coursework/alinasg/textures/moon.bmp");
		// Loads the asteroid mapping texture..
		asteroidTexture = loadTexture("coursework/alinasg/textures/asteroid.bmp");
		
		// Draws the middle head and body of the rocket..
		GL11.glNewList(mainbodyList,GL11.GL_COMPILE);
		{   
			drawMainHead();	
			drawMainBody();
		}
		GL11.glEndList();
		// Draws the left head and body of the rocket..
		GL11.glNewList(leftbodyList,GL11.GL_COMPILE);
		{   
			drawLeftHead();
			drawLeftBody();
		}
		GL11.glEndList();
		// Draws the right head and body of the rocket..
		GL11.glNewList(rightbodyList,GL11.GL_COMPILE);
		{   
			drawRightHead();
			drawRightBody();
		}
		GL11.glEndList();
		// Draws the right hexagon of the rocket..
		GL11.glNewList(rightHexagonList,GL11.GL_COMPILE);
		{   
			drawRightHexagon();
		}
		GL11.glEndList();
		// Draws the left hexagon of the rocket..
		GL11.glNewList(leftHexagonList,GL11.GL_COMPILE);
		{   
			drawLeftHexagon();
		}
		GL11.glEndList();
		// Draws the shuttle..
		GL11.glNewList(shuttleList,GL11.GL_COMPILE);
		{   
			drawShuttle();
		}
		GL11.glEndList();
		// Draws the left wing of the shuttle..
		GL11.glNewList(leftWingList,GL11.GL_COMPILE);
		{   
			drawLeftWing();
		}
		GL11.glEndList();
		// Draws the right wing of the shuttle..
		GL11.glNewList(rightWingList,GL11.GL_COMPILE);
		{   
			drawRightWing();
		}
		GL11.glEndList();
		// Draw the unit plane.. 
		GL11.glNewList(backgroundList,GL11.GL_COMPILE);
		{   
			drawUnitPlane();
		}
		GL11.glEndList();
	}

	/**
	 * Basic user interaction with the object once a specific button in the keyboard is hold or pressed.
	 */
	protected void checkSceneInput()
	{
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R)) // Makes the rocket and shuttle rotate around if the 'R' is hold..
		{
			objectRotationAngle += 7.0f * getAnimationScale(); // Rotate the object in this speed..
			if (objectRotationAngle > 360.0f)                  // Wrap the angle back around into 0-360 degrees..
			{  
				objectRotationAngle = 0.0f;
			}
		}
			
		if(Keyboard.isKeyDown(Keyboard.KEY_L)) // Launches the rocket, and shuttle upwards and moves the 
											   // asteroid across once the 'L' key is pressed..
		{
			takeOff = true;
			asteroidBool = true;  
		}  
		else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) // Reset the animation process by calling the 
														//'resetAnimations' method once the 'space' key is pressed..
		{
			resetAnimations();
		}
	}

	/** 
	 * Reset all attributes that are modified by user controls or animations.
	 */
	protected void resetAnimations()
	{
		rocketPosition = resetPosition;  // Resets the rocket into its starting position..
		shuttle = resetShuttle;	 		 // Resets the shuttle into its starting position..
		moveAsteroid = resetAsteroid;    // Resets the asteroid into its starting position..
		objectRotationAngle = resetAngle;// Resets the rocket and shuttle into its starting angle..
		takeOff = false;	             // Resets the boolean value..
		asteroidBool = false;            // Resets the boolean value..

	}

	/**
	 * Calls and executes all the methods containing the animation process of all objects.
	 */
	protected void updateScene()
	{
		rotateSpheres();        // Calls the method which rotates the spheres including the Earth, moon and asteroid..
		launchRocket();	        // Calls the method which launches both the rocket and shuttle..
		rotateRocketShip();     // Calls the method which rotates the rocket and shuttle..
		seperationRocketShip(); // Calls the method which seperates the rocket and shuttle..
		moveAsteroid();	        // Calls the method which moves the asteroid..
	}

	/**
	 * Method is responsible for moving the asteroid across the screen.
	 */
	protected void moveAsteroid() 
	{
		if(asteroidBool && moveAsteroid == moveAsteroid)
		{
			moveAsteroid += 0.75f * getAnimationScale(); // Moves the asteroid at a specific speed..
		}	
	}

	/**
	 * Method is responsible for rotating all the sphere objects.
	 */
	protected void rotateSpheres()
	{	//Rotate the spheres at specific speed..
		rotateEarth    += 0.4f  * getAnimationScale(); 
		rotateMoon     += 1.75f * getAnimationScale();    
		rotateAsteroid -= 45.0f * getAnimationScale();
	}

	/**
	 * Method is responsible for launching the rocket upwards.
	 */
	protected void launchRocket()
	{	//
		if(takeOff && rocketPosition == rocketPosition) 
		{
			rocketPosition += 0.075f * getAnimationScale(); // Launches the rocket at a specific speed..
		}
	}

	/**
	 * Method is responsible for rotating the rocket and shuttle while going upwards.
	 */
	protected void rotateRocketShip() 
	{
		// Rotates the rocket and shuttle once it reaches a specific point Y..
		if (rotatingPoint < rocketPosition) {
			objectRotationAngle += 7.5f * getAnimationScale();
		}
	}
	
	/**
	 * Method is responsible for seperating the rocket and shuttle.
	 */
	protected void seperationRocketShip()
	{
		// Seperates the rocket and shuttle once it reaches a specific point Y..
		if(rocketPosition > seperationPoint) {
			shuttle += 0.3 * getAnimationScale();
		}
	}
	
	/**
	 * Method is responsible for rendering the textures, objects, lighting, scaling and the point of locations.
	 */
	protected void renderScene()
	{
		// Renders the ground texture..
		GL11.glPushMatrix();
		{
			// disable lighting calculations so that they don't affect..
			// the appearance of the texture..
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture..
			// is bright and details can be seen clearly..
			Colour.WHITE.submit();
			// enable texturing and bind an appropriate texture..
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTexture.getTextureID());

			// position, scale and draw the ground plane using its display list..
			GL11.glTranslatef(20.75f,-12.5f, -3.0f);
			GL11.glScalef(127.5f, 1.0f, 27.5f);
			GL11.glCallList(backgroundList);

			// disable textures and reset any local lighting changes..
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		// Renders the background texture..
		GL11.glPushMatrix();
		{
			// disable lighting calculations so that they don't affect..
			// the appearance of the texture..
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture..
			// is bright and details can be seen clearly..
			Colour.WHITE.submit();
			// enable texturing and bind an appropriate texture..
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,backgroundTexture.getTextureID());

			// position, scale and draw the back plane
			GL11.glTranslatef(20.0f, 2.0f, 35.0f);
			GL11.glRotatef(-90.0f, 2.0f, 0.0f, 0.0f);
			GL11.glScaled(150.0f, 1.0f, 15.0f);
			GL11.glCallList(backgroundList);

			// disable textures and reset any local lighting changes..
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		// Renders the earth sphere object..
		GL11.glPushMatrix();
		{
			// draw a sphere object w/ textures, rotation speed and positions..
			Sphere planet = new Sphere();
			planet.setDrawStyle(GLU.GLU_FILL);
			planet.setTextureFlag(true);
			planet.setNormals(GLU.GLU_SMOOTH);
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,earthTexture.getTextureID());
			GL11.glTranslatef(15.0f, 7.0f, 8.5f);
			GL11.glRotatef(rotateEarth, 0.5f, 1.0f, 0.0f);
			planet.draw(4.75f, 25, 25);
		}
		GL11.glPopMatrix();

		// Renders the moon sphere object..
		GL11.glPushMatrix();
		{
			// draw a sphere object w/ textures, rotation speed and positions..
			Sphere moon = new Sphere();
			moon.setDrawStyle(GLU.GLU_FILL);
			moon.setTextureFlag(true);
			moon.setNormals(GLU.GLU_SMOOTH);
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,moonTexture.getTextureID());
			GL11.glTranslatef(7.0f, 13.0f, 10.5f);
			GL11.glRotatef(rotateMoon, 0.5f, 1.0f, 0.0f);
			moon.draw(2.0f, 100, 100);
		}
		GL11.glPopMatrix();

		// Renders the asteroid sphere object..
		GL11.glPushMatrix();
		{
			// draw a sphere object w/ textures, rotation speed and positions..
			Sphere asteroid = new Sphere();
			asteroid.setDrawStyle(GLU.GLU_FILL);
			asteroid.setTextureFlag(true);
			asteroid.setNormals(GLU.GLU_SMOOTH);
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,asteroidTexture.getTextureID());
			GL11.glTranslatef(moveAsteroid, 13.0f, 15.0f);
			GL11.glRotatef(rotateAsteroid, 0.5f, 1.0f, 0.0f);

			asteroid.draw(1.25f, 100, 100);
		}
		GL11.glPopMatrix();

		// Renders the position of the camera and all of the objects..
		GL11.glPushMatrix();
		{   
			// How shiny are the front faces of the space shuttle (specular exponent)..
            float objectFrontShininess  = 75.0f;
            // Specular reflection of the front faces of the space shuttle..
            float objectFrontSpecular[] = {0.90f, 0.90f, 0.90f, 1.0f};
            // Diffuse reflection of the front faces of the space shuttle..
            float objectFrontDiffuse[]  = {0.90f, 0.70f, 0.25f, 1.0f};
            // Positon of the lighting towards the space shuttle..
            float position0[] = { 0.0f, 5.0f, -35.0f, 0.0f};
            // Set the material properties for the space shuttle using OpenGL..
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, objectFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(objectFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(objectFrontDiffuse));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(objectFrontDiffuse));
            GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
      
            // Enable the first light..
            GL11.glEnable(GL11.GL_LIGHT0);
            // Enable lighting calculations..
            GL11.glEnable(GL11.GL_LIGHTING);
            // Ensure that all normals are re-normalised after transformations automatically..
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            
			GL11.glTranslatef(0.0f, rocketPosition, -10.0f);
			GL11.glRotatef(objectRotationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glCallList(mainbodyList);

			GL11.glTranslatef(1.0f, 0.0f, 0.0f); 
			GL11.glCallList(leftbodyList);

			GL11.glTranslatef(-2.0f, 0.0f, 0.0f);
			GL11.glCallList(rightbodyList);

			GL11.glTranslatef(0.0f, -5.25f, 0.0f);
			GL11.glCallList(rightHexagonList);

			GL11.glTranslatef(2.0f, 0.0f, 0.0f);
			GL11.glCallList(leftHexagonList);

			GL11.glTranslatef(-1.0f, shuttle, -1.25f);
			GL11.glCallList(shuttleList);

			GL11.glTranslatef(2.5f, 0.0f, 0.0f);
			GL11.glCallList(leftWingList);

			GL11.glTranslatef(-5.0f, 0.0f, 0.0f);
			GL11.glCallList(rightWingList);
		}
		GL11.glPopMatrix();
	}
	/**
	 * Method is responsible for setting up the camera.
	 */
	protected void setSceneCamera()
	{
		// Sets the camera/user's point of view of the object..
		// Call the default behaviour defined in GraphicsLab. This will set a default perspective projection..
		// and default camera settings ready for some custom camera positioning below...  
		super.setSceneCamera();
		GLU.gluLookAt(0.0f, 0.0f, -37.5f,   // viewer location..        
				0.0f, 0.0f, 0.0f,    		// view point location..
				0.0f, 1.0f, 0.0f);   		// view-up vector..
	}

	protected void cleanupScene()
	{
	}
	/**
	 * Method is responsible for designing the prism of unit length, width and height.
	 * This is the main head of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawMainHead() 
	{
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.50f, 0.75f,  0.50f); 
		Vertex v2 = new Vertex( 0.50f, 0.75f,  0.50f); 
		Vertex v3 = new Vertex(-0.50f, 0.75f, -0.50f); 
		Vertex v4 = new Vertex( 0.50f, 0.75f, -0.50f);	
		Vertex v5 = new Vertex( 0.25f, 1.50f,  0.25f); 
		Vertex v6 = new Vertex( 0.25f, 1.50f, -0.25f);
		Vertex v7 = new Vertex(-0.25f, 1.50f,  0.25f);
		Vertex v8 = new Vertex(-0.25f, 1.50f, -0.25f);
		
		// Draw the top side of the head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(),v6.toVector(),v8.toVector(),v7.toVector()).submit();
			v5.submit();
			v6.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		
		// Draw the back side of the head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(),v7.toVector(),v1.toVector(),v2.toVector()).submit();
			v5.submit();
			v7.submit();
			v1.submit();
			v2.submit();
		}
		GL11.glEnd();

		// Draw the front side of the head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v3.toVector(),v8.toVector(),v6.toVector()).submit();
			v4.submit();
			v3.submit();
			v8.submit();
			v6.submit();
		}
		GL11.glEnd();

		// Draw the right side of the head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v5.toVector(),v6.toVector(),v3.toVector()).submit();
			v1.submit();
			v7.submit();
			v8.submit();
			v3.submit();

		}
		GL11.glEnd();

		// Draw the left side of the head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v6.toVector(),v5.toVector(),v2.toVector()).submit();
			v4.submit();
			v6.submit();
			v5.submit();
			v2.submit();
		}
		GL11.glEnd();
	}
	/**
	 * Method is responsible for designing the cuboid of unit length, width and height.
	 * This is the main body of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawMainBody()
	{
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.5f, -4.50f,  0.5f);
		Vertex v2 = new Vertex( 0.5f, -4.50f,  0.5f);
		Vertex v3 = new Vertex( 0.5f,  0.75f,  0.5f);
		Vertex v4 = new Vertex(-0.5f,  0.75f,  0.5f);
		Vertex v5 = new Vertex( 0.5f, -4.50f, -0.5f);
		Vertex v6 = new Vertex(-0.5f, -4.50f, -0.5f);
		Vertex v7 = new Vertex(-0.5f,  0.75f, -0.5f);
		Vertex v8 = new Vertex( 0.5f,  0.75f, -0.5f);

		// Draw the back side of the main body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v1.toVector(),v2.toVector(),v3.toVector()).submit();
			v4.submit();
			v1.submit();
			v2.submit();
			v3.submit();
		}
		GL11.glEnd();

		// Draw the right side of the main body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(),v6.toVector(),v1.toVector(),v4.toVector()).submit();
			v7.submit();
			v6.submit();
			v1.submit();
			v4.submit();
		}
		GL11.glEnd();

		// Draw the left side of the main body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v3.toVector(),v2.toVector(),v5.toVector()).submit();
			v8.submit();
			v3.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the bottom side of the main body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(),v5.toVector(),v2.toVector(),v1.toVector()).submit();
			v6.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();

		// Draw the front side of the main body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v5.toVector(),v6.toVector(),v7.toVector()).submit();
			v8.submit();
			v5.submit();
			v6.submit();
			v7.submit();
		}
		GL11.glEnd();
	}
	/**
	 * Method is responsible for designing the prism of unit length, width and height.
	 * This is the left head of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawLeftHead() 
	{
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.5f, -0.225f,  0.50f); 
		Vertex v2 = new Vertex( 0.5f, -0.225f,  0.50f); 
		Vertex v3 = new Vertex(-0.5f, -0.225f, -0.50f); 
		Vertex v4 = new Vertex( 0.5f, -0.225f, -0.50f); 
		Vertex v5 = new Vertex( 0.0f,  1.250f,  0.25f); 
		Vertex v6 = new Vertex( 0.0f,  1.250f, -0.25f);

		// Draw the back side of the left head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v1.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the front side of the left head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v4.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();

		// Draw the right side of the left head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v5.toVector(),v6.toVector(),v3.toVector()).submit();
			v1.submit();
			v5.submit();
			v6.submit();
			v3.submit();

		}
		GL11.glEnd();

		// Draw the left side of the left head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v6.toVector(),v5.toVector(),v2.toVector()).submit();
			v4.submit();
			v6.submit();
			v5.submit();
			v2.submit();
		}
		GL11.glEnd();
	}
	/**
	 * Method is responsible for designing the cuboid of unit length, width and height.
	 * This is the left body of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawLeftBody()
	{
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.5f, -4.75f,  0.5f);
		Vertex v2 = new Vertex( 0.5f, -4.75f,  0.5f);
		Vertex v3 = new Vertex( 0.5f, -0.20f,  0.5f);
		Vertex v4 = new Vertex(-0.5f, -0.20f,  0.5f);
		Vertex v5 = new Vertex( 0.5f, -4.75f, -0.5f);
		Vertex v6 = new Vertex(-0.5f, -4.75f, -0.5f);
		Vertex v7 = new Vertex(-0.5f, -0.20f, -0.5f);
		Vertex v8 = new Vertex( 0.5f, -0.20f, -0.5f);

		// Draw the back side of the left body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v1.toVector(),v2.toVector(),v3.toVector()).submit();
			v4.submit();
			v1.submit();
			v2.submit();
			v3.submit();
		}
		GL11.glEnd();

		// Draw the hidden right side of the left body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(),v6.toVector(),v1.toVector(),v4.toVector()).submit();
			v7.submit();
			v6.submit();
			v1.submit();
			v4.submit();
		}
		GL11.glEnd();

		// Draw the left side of the left body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v3.toVector(),v2.toVector(),v5.toVector()).submit();
			v8.submit();
			v3.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the bottom side of the left body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(),v5.toVector(),v2.toVector(),v1.toVector()).submit();
			v6.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();

		// Draw the front side of the left body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v5.toVector(),v6.toVector(),v7.toVector()).submit();
			v8.submit();
			v5.submit();
			v6.submit();
			v7.submit();
		}
		GL11.glEnd();
	}

	/**
	 * Method is responsible for designing the prism of unit length, width and height.
	 * This is the right head of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawRightHead() 
	{
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.5f, -0.225f,  0.50f); 
		Vertex v2 = new Vertex( 0.5f, -0.225f,  0.50f); 
		Vertex v3 = new Vertex(-0.5f, -0.225f, -0.50f); 
		Vertex v4 = new Vertex( 0.5f, -0.225f, -0.50f); 
		Vertex v5 = new Vertex( 0.0f,  1.250f,  0.25f); 
		Vertex v6 = new Vertex( 0.0f,  1.250f, -0.25f);

		// Draw the back side of the right head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v1.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the front side of the right head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v4.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();

		// Draw the right side of the right head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v5.toVector(),v6.toVector(),v3.toVector()).submit();
			v1.submit();
			v5.submit();
			v6.submit();
			v3.submit();

		}
		GL11.glEnd();

		// Draw the left side of the right head..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v6.toVector(),v5.toVector(),v2.toVector()).submit();
			v4.submit();
			v6.submit();
			v5.submit();
			v2.submit();
		}
		GL11.glEnd();

	}
	/**
	 * Method is responsible for designing the cuboid of unit length, width and height.
	 * This is the right body of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawRightBody()
	{
		Vertex v1 = new Vertex(-0.5f, -4.75f,  0.5f);
		Vertex v2 = new Vertex( 0.5f, -4.75f,  0.5f);
		Vertex v3 = new Vertex( 0.5f, -0.20f,  0.5f);
		Vertex v4 = new Vertex(-0.5f, -0.20f,  0.5f);
		Vertex v5 = new Vertex( 0.5f, -4.75f, -0.5f);
		Vertex v6 = new Vertex(-0.5f, -4.75f, -0.5f);
		Vertex v7 = new Vertex(-0.5f, -0.20f, -0.5f);
		Vertex v8 = new Vertex( 0.5f, -0.20f, -0.5f);

		// Draw the back side of the right body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v1.toVector(),v2.toVector(),v3.toVector()).submit();
			v4.submit();
			v1.submit();
			v2.submit();
			v3.submit();
		}
		GL11.glEnd();

		// Draw the right side of the right body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(),v6.toVector(),v1.toVector(),v4.toVector()).submit();
			v7.submit();
			v6.submit();
			v1.submit();
			v4.submit();
		}
		GL11.glEnd();

		// Draw the hidden left side of the right body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v3.toVector(),v2.toVector(),v5.toVector()).submit();
			v8.submit();
			v3.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the bottom side of the right body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(),v5.toVector(),v2.toVector(),v1.toVector()).submit();
			v6.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();

		// Draw the front side of the right body..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v5.toVector(),v6.toVector(),v7.toVector()).submit();
			v8.submit();
			v5.submit();
			v6.submit();
			v7.submit();
		}
		GL11.glEnd();
	}

	/**
	 * Method is responsible for designing the six-sided hexagon of unit length, width and height.
	 * This is the right hexagon of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawRightHexagon()
	{
		// Draw the front prism of the right hexagon..
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.55f,  0.5f, -0.75f);
		Vertex v2 = new Vertex( 0.55f,  0.5f, -0.75f);
		Vertex v3 = new Vertex(-0.55f, -0.5f, -0.75f);
		Vertex v4 = new Vertex( 0.55f, -0.5f, -0.75f);
		Vertex v5 = new Vertex( 0.00f, -0.5f, -1.25f);
		Vertex v6 = new Vertex( 0.00f,  0.5f, -1.25f);

		// Draw the cuboid part of the hexagon..
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v13 = new Vertex(-0.55f, -0.5f, -0.75f);
		Vertex v14 = new Vertex( 0.55f, -0.5f, -0.75f);
		Vertex v15 = new Vertex(-0.55f,  0.5f, -0.75f);
		Vertex v16 = new Vertex( 0.55f,  0.5f, -0.75f);
		Vertex v17 = new Vertex(-0.55f, -0.5f,  0.75f);
		Vertex v18 = new Vertex( 0.55f, -0.5f,  0.75f);
		Vertex v19 = new Vertex(-0.55f,  0.5f,  0.75f);
		Vertex v20 = new Vertex( 0.55f,  0.5f,  0.75f);

		// Draw the back prism of the right hexagoin..
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v7  = new Vertex(-0.55f, -0.5f, 0.75f);
		Vertex v8  = new Vertex(-0.55f,  0.5f, 0.75f);
		Vertex v9  = new Vertex( 0.55f,  0.5f, 0.75f);
		Vertex v10 = new Vertex( 0.55f, -0.5f, 0.75f);
		Vertex v11 = new Vertex( 0.00f,  0.5f, 1.25f);
		Vertex v12 = new Vertex( 0.00f, -0.5f, 1.25f); 

		// Draw the left face of the front prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(),v6.toVector(),v2.toVector(),v4.toVector()).submit();
			v5.submit();
			v6.submit();
			v2.submit();
			v4.submit();
		}
		GL11.glEnd();

		// Draw the right face of the front prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(),v5.toVector(),v3.toVector(),v1.toVector()).submit();
			v6.submit();
			v5.submit();
			v3.submit();
			v1.submit();
		}
		GL11.glEnd();

		// Draw the left face of the back prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v7.toVector(),v12.toVector(),v11.toVector()).submit();
			v8.submit();
			v7.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

		// Draw the right face of the back prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(),v12.toVector(),v10.toVector(),v9.toVector()).submit();
			v11.submit(); 
			v12.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();

		// Draw the inner(left) side of the hexagon..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v20.toVector(),v18.toVector(),v14.toVector(),v16.toVector()).submit();
			v20.submit();
			v18.submit();
			v14.submit();
			v16.submit();
		}
		GL11.glEnd();

		// Draw the outer(right) side of the hexagon..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v19.toVector(),v15.toVector(),v13.toVector(),v17.toVector()).submit();
			v19.submit();
			v15.submit();
			v13.submit();
			v17.submit();
		}
		GL11.glEnd();

		// Draw the top side of the hexagon. Connecting the top vertices of the three objects..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v11.submit();
			v9.submit();
			v20.submit();
			v16.submit();
			v2.submit();
			v6.submit();
			v1.submit();
			v15.submit();
			v19.submit();
			v8.submit();
		}
		GL11.glEnd();

		// Draw the bottom side of the hexagon. Connecting the bottom vertices of the three objects..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v12.submit();
			v7.submit();
			v17.submit();
			v13.submit();
			v3.submit();
			v5.submit();
			v4.submit();
			v14.submit();
			v18.submit();
			v10.submit();

		}
		GL11.glEnd();
	}
	
	/**
	 * Method is responsible for designing the six-sided hexagon of unit length, width and height.
	 * This is the left hexagon of the rocket including its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawLeftHexagon()
	{
		// Draw the front prism of the left hexagon..
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v1 = new Vertex(-0.55f,  0.5f, -0.75f);
		Vertex v2 = new Vertex( 0.55f,  0.5f, -0.75f);
		Vertex v3 = new Vertex(-0.55f, -0.5f, -0.75f);
		Vertex v4 = new Vertex( 0.55f, -0.5f, -0.75f);
		Vertex v5 = new Vertex( 0.00f, -0.5f, -1.25f);
		Vertex v6 = new Vertex( 0.00f,  0.5f,- 1.25f);

		// Draw the cuboid of the left hexagon..
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v13 = new Vertex(-0.55f, -0.5f, -0.75f);
		Vertex v14 = new Vertex( 0.55f, -0.5f, -0.75f);
		Vertex v15 = new Vertex(-0.55f,  0.5f, -0.75f);
		Vertex v16 = new Vertex( 0.55f,  0.5f, -0.75f);
		Vertex v17 = new Vertex(-0.55f, -0.5f,  0.75f);
		Vertex v18 = new Vertex( 0.55f, -0.5f,  0.75f);
		Vertex v19 = new Vertex(-0.55f,  0.5f,  0.75f);
		Vertex v20 = new Vertex( 0.55f,  0.5f,  0.75f);
		
		// Draw the back prism of the left hexagon..
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		Vertex v7  = new Vertex(-0.55f, -0.5f, 0.75f);
		Vertex v8  = new Vertex(-0.55f,  0.5f, 0.75f);
		Vertex v9  = new Vertex( 0.55f,  0.5f, 0.75f);
		Vertex v10 = new Vertex( 0.55f, -0.5f, 0.75f);
		Vertex v11 = new Vertex( 0.00f,  0.5f, 1.25f);
		Vertex v12 = new Vertex( 0.00f, -0.5f, 1.25f);


		// Draw the left face of the front prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(),v6.toVector(),v2.toVector(),v4.toVector()).submit();
			v5.submit();
			v6.submit();
			v2.submit();
			v4.submit();
		}
		GL11.glEnd();

		// Draw the right face of the front prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(),v5.toVector(),v3.toVector(),v1.toVector()).submit();
			v6.submit();
			v5.submit();
			v3.submit();
			v1.submit();
		}
		GL11.glEnd();

		// Draw the left face of the back prism.. 
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v7.toVector(),v12.toVector(),v11.toVector()).submit();
			v8.submit();
			v7.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

		// Draw the right face of the back prism..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(),v12.toVector(),v10.toVector(),v9.toVector()).submit();
			v11.submit();
			v12.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();

		// Draw the outer(left) side of the hexagon..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v20.toVector(),v18.toVector(),v14.toVector(),v16.toVector()).submit();
			v20.submit();
			v18.submit();
			v14.submit();
			v16.submit();
		}
		GL11.glEnd();

		// Draw the inner(right) side of the hexagon..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v19.toVector(),v15.toVector(),v13.toVector(),v17.toVector()).submit();
			v19.submit();
			v15.submit();
			v13.submit();
			v17.submit();
		}
		GL11.glEnd();

		// Draw the top side of the hexagon..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v11.submit();
			v9.submit();
			v20.submit();
			v16.submit();
			v2.submit();
			v6.submit();
			v1.submit();
			v15.submit();
			v19.submit();
			v8.submit();
		}
		GL11.glEnd();

		// Draw the bottom side of the hexagon..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v12.submit();
			v7.submit();
			v17.submit();
			v13.submit();
			v3.submit();
			v5.submit();
			v4.submit();
			v14.submit();
			v18.submit();
			v10.submit();          	
		}
		GL11.glEnd();

	}
	
	 /**
     * Method is responsible for designing the plane of unit length, width and height.
     * 
     */
	protected void drawUnitPlane() 
	{
		Vertex v1 = new Vertex(-0.5f, 5.0f, -2.5f); 
		Vertex v2 = new Vertex( 0.5f, 5.0f, -2.5f); 
		Vertex v3 = new Vertex( 0.5f, 5.0f,  2.5f); 
		Vertex v4 = new Vertex(-0.5f, 5.0f,  2.5f); 

		// draw the plane geometry. order the vertices so that the plane faces up
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();

			GL11.glTexCoord2f(0.0f,0.0f);
			v4.submit();

			GL11.glTexCoord2f(1.0f,0.0f);
			v3.submit();

			GL11.glTexCoord2f(1.0f,1.0f);
			v2.submit();

			GL11.glTexCoord2f(0.0f,1.0f);
			v1.submit();
		}
		GL11.glEnd();

		// if the user is viewing an axis, then also draw this plane
		// using lines so that axis aligned planes can still be seen
		if(isViewingAxis())
		{
			// also disable textures when drawing as lines
			// so that the lines can be seen more clearly
			GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				v4.submit();
				v3.submit();
				v2.submit();
				v1.submit();
			}
			GL11.glEnd();
			GL11.glPopAttrib();
		}
	}
	
	/**
	 * Method is responsible for designing the cuboid of unit length, width and height.
	 * This is the shuttle that supposedly attached to the rocket that includes its calculations and the ordering of its vertices.
	 * 
	 */
	protected void drawShuttle() 
	{
		Vertex v1 = new Vertex( 0.50f,  3.75f, -0.25f); 
		Vertex v2 = new Vertex(-0.50f,  3.75f, -0.25f); 
		Vertex v3 = new Vertex(-1.00f,  4.50f,  0.50f);
		Vertex v4 = new Vertex( 1.00f,  4.50f,  0.50f); 
		Vertex v5 = new Vertex( 0.75f, -0.50f, -1.25f); 
		Vertex v6 = new Vertex(-0.75f, -0.50f, -1.25f); 
		Vertex v7 = new Vertex(-1.25f, -0.50f,  0.50f);
		Vertex v8 = new Vertex( 1.25f, -0.50f,  0.50f);

		// Draw the top (front window) of the shuttle..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v1.toVector(),v2.toVector(),v3.toVector()).submit();
			v4.submit();
			v1.submit();
			v2.submit();
			v3.submit();
		}
		GL11.glEnd();

		// Draw the bottom (back window) of the shuttle..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v7.toVector(),v6.toVector(),v5.toVector()).submit();
			v8.submit();
			v7.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the right side of the shuttle..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(),v6.toVector(),v7.toVector(),v3.toVector()).submit();
			v2.submit();
			v6.submit();
			v7.submit();
			v3.submit();
		}
		GL11.glEnd();

		// Draw the left side of the shuttle..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v4.toVector(),v8.toVector(),v5.toVector()).submit();
			v1.submit();
			v4.submit();
			v8.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the front side (roof) of the shuttle..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(),v1.toVector(),v5.toVector(),v6.toVector()).submit();
			v2.submit();
			v1.submit();
			v5.submit();
			v6.submit();
		}
		GL11.glEnd();

		// Draw the back side (hidden side) of the shuttle..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(),v7.toVector(),v8.toVector(),v4.toVector()).submit();
			v3.submit();
			v7.submit();
			v8.submit();
			v4.submit();
		}
		GL11.glEnd();
	}
	 /**
     * Method is responsible for designing the cuboid of unit length, width and height.
     * This is the left wing of the shuttle including its calculations and the connections of its vertices.
     * 
     */
	protected void drawLeftWing()
	{
		Vertex v1 = new Vertex(-1.00f, 3.5f,  0.3f); 
		Vertex v2 = new Vertex(-1.75f, 2.5f, -0.3f); 
		Vertex v3 = new Vertex(-1.75f, 2.5f,  0.3f);
		Vertex v4 = new Vertex(-1.00f, 2.5f, -0.3f);
		Vertex v5 = new Vertex(-1.50f, 0.0f,  0.3f); 
		Vertex v6 = new Vertex( 1.50f, 0.0f,  0.3f);
		Vertex v7 = new Vertex( 1.50f, 0.0f, -0.3f);
		Vertex v8 = new Vertex(-1.50f, 0.0f, -0.3f);

		// Draw the top side of the left wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v4.toVector(),v2.toVector(),v3.toVector()).submit();
			v1.submit();
			v4.submit();
			v2.submit();
			v3.submit();

		}
		GL11.glEnd();

		// Draw the bottom side of the left wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v7.toVector(),v6.toVector(),v5.toVector()).submit();
			v8.submit();
			v7.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the right (hidden) side of the left wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(),v8.toVector(),v5.toVector(),v3.toVector()).submit();
			v2.submit();
			v8.submit();
			v5.submit();
			v3.submit();
		}
		GL11.glEnd();

		// Draw left (outer) side of the left wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v1.toVector(),v6.toVector(),v7.toVector()).submit();
			v4.submit();
			v1.submit();
			v6.submit();
			v7.submit();
		}
		GL11.glEnd();

		// Draw the front side of the left wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(),v4.toVector(),v7.toVector(),v8.toVector()).submit();
			v2.submit();
			v4.submit();
			v7.submit();
			v8.submit();
		}
		GL11.glEnd();

		// Draw the back side of the left wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v3.toVector(),v5.toVector(),v6.toVector()).submit();
			v1.submit();
			v3.submit();
			v5.submit();
			v6.submit();
		}
		GL11.glEnd();
	}
	
	 /**
     * Method is responsible for designing the cuboid of unit length, width and height.
     * This is the right wing of the shuttle including its calculations and the connections of its vertices.
     * 
     */
	protected void drawRightWing()
	{
		Vertex v1 = new Vertex( 1.75f, 2.5f,  0.3f); 
		Vertex v2 = new Vertex( 1.75f, 2.5f, -0.3f); 
		Vertex v3 = new Vertex( 1.50f, 0.0f, -0.3f);
		Vertex v4 = new Vertex( 1.50f, 0.0f,  0.3f);
		Vertex v5 = new Vertex(-1.50f, 0.0f,  0.3f); 
		Vertex v6 = new Vertex(-1.50f, 0.0f, -0.3f); 
		Vertex v7 = new Vertex( 1.00f, 3.50f, 0.3f);
		Vertex v8 = new Vertex( 1.00f, 2.50f,-0.3f);


		// Draw the back side of the right wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v1.toVector(),v7.toVector(),v5.toVector()).submit();
			v4.submit();
			v1.submit();
			v7.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the front side of the right wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(),v2.toVector(),v3.toVector(),v6.toVector()).submit();
			v8.submit();
			v2.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();

		// Draw the left (hidden) side of the right wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(),v4.toVector(),v3.toVector(),v2.toVector()).submit();
			v1.submit();
			v4.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();

		// Draw the top side of the right wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(),v1.toVector(),v2.toVector(),v8.toVector()).submit();
			v7.submit();
			v1.submit();
			v2.submit();
			v8.submit();
		}
		GL11.glEnd();

		// Draw the right (outer) side of the right wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(),v8.toVector(),v6.toVector(),v5.toVector()).submit();
			v7.submit();
			v8.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		// Draw the bottom side of the right wing..
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(),v5.toVector(),v6.toVector(),v3.toVector()).submit();
			v4.submit();
			v5.submit();
			v6.submit();
			v3.submit();
		}
		GL11.glEnd();
	}     
}
