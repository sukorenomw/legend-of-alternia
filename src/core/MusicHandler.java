/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.easyogg.OggClip;

/**
 *
 * @author raiven
 */
public class MusicHandler {

    OggClip swing;
    OggClip walk;
    boolean walked = false;

    public MusicHandler() throws IOException {
        walk = new OggClip(new FileInputStream(getClass().getClassLoader().getResource("assets/sounds/footstep09.ogg").getPath()));

    }

    public void startBackgroundSong() {
        try {
            OggClip ogg = new OggClip(new FileInputStream(getClass().getClassLoader().getResource("assets/sounds/dungeon.ogg").getPath()));
            ogg.setGain(0.7f);
            ogg.loop();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void swingSFX() {
        try {
            swing = new OggClip(new FileInputStream(getClass().getClassLoader().getResource("assets/sounds/knifeSlice.ogg").getPath()));
            swing.setGain(0.8f);
            swing.play();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void walkSFX() {
        walked = true;
        walk.setGain(0.8f);
        walk.loop();
    }

    public void stopWalkSFX() {
        //walk.stop();
        //walk.pause();
        walked = false;
    }
}
