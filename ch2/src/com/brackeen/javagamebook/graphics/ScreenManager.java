package com.brackeen.javagamebook.graphics;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 The ScreenManager class manages initializing and displaying
 full screen graphics modes.
*/
public class ScreenManager {

// private GraphicsDevice device;
 private JFrame frame;

 /**
  Creates a new ScreenManager object.
 */
 public ScreenManager() {
 	frame = new JFrame();
 	frame.setIgnoreRepaint(true);
	frame.setResizable(false);  
	frame.setSize(800, 600);
 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	
 	frame.setVisible(true); 	
 }

/**
  Enters full screen mode and changes the display mode.
  If the specified display mode is null or not compatible
  with this device, or if the display mode cannot be
  changed on this system, the current display mode is used.
  <p>
  The display uses a BufferStrategy with 2 buffers.
 */
 public void setFullScreen() {
 	frame.createBufferStrategy(2); 	
 }


 /**
  Gets the graphics context for the display. The
  ScreenManager uses double buffering, so applications must
  call update() to show any graphics drawn.
  <p>
  The application must dispose of the graphics object.
 */
 public Graphics2D getGraphics() {
  if (frame != null) {
   return (Graphics2D) frame.getBufferStrategy().getDrawGraphics();
  }
  else {
   return null;
  }
 }


 /**
  Updates the display.
 */
 public void update() {

  if (frame != null) {
   BufferStrategy strategy = frame.getBufferStrategy();
   if (!strategy.contentsLost()) {
    strategy.show();
   }
  }
  // Sync the display on some systems.
  // (on Linux, this fixes event queue problems)
  Toolkit.getDefaultToolkit().sync();
 }


 /**
  Returns the window currently used in full screen mode.
  Returns null if the device is not in full screen mode.
 */
 public Window getFullScreenWindow() {
  return (Window)frame;
 }


 /**
  Returns the width of the window currently used in full
  screen mode. Returns 0 if the device is not in full
  screen mode.
 */
 public int getWidth() {  
  if (frame != null) {
  	return (int)frame.getContentPane().getSize().getWidth();
  }
  else {
   return 0;
  }
 }


 /**
  Returns the height of the window currently used in full
  screen mode. Returns 0 if the device is not in full
  screen mode.
 */
 public int getHeight() {  
  if (frame != null) {
  	return (int)frame.getContentPane().getSize().getHeight();
  }
  else {
   return 0;
  }
 }


 /**
  Restores the screen's display mode.
 */
 public void restoreScreen() {  
  frame.setVisible(false);
  frame.dispose();
 }
 
 
 public BufferedImage createCompatibleImage(int w, int h,
	        int transparency)
	    {
	        Window window = (Window)frame;
	        if (window != null) {
	            GraphicsConfiguration gc =
	                window.getGraphicsConfiguration();
	            return gc.createCompatibleImage(w, h, transparency);
	        }
	        return null;
	    }



}