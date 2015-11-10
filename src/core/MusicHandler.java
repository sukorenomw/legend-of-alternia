/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author raiven
 */
public class MusicHandler {

//    OggClip swing;
//    OggClip walk;
//    boolean walked = false;

    public MusicHandler() throws IOException {
    }

//    public void startBackgroundSong() {
//        try {
//            OggClip ogg = new OggClip(new FileInputStream(getClass().getClassLoader().getResource("assets/sounds/dungeon.ogg").getPath()));
//            ogg.setGain(0.7f);
//            ogg.loop();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void swingSFX() {
//        try {
//            swing = load(getClass().getClassLoader().getResource("assets/sounds/knifeSlice.ogg").getPath()));
//            swing.setGain(0.8f);
//            swing.play();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MusicHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void walkSFX() {
//        walked = true;
//        walk.setGain(0.8f);
//        walk.loop();
//    }
//
//    public void stopWalkSFX() {
//        //walk.stop();
//        //walk.pause();
//        walked = false;
//    }
    Clip clip;

    public void load(String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new FileInputStream(getClass().getClassLoader().getResource(path).getPath()));

            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip.isRunning()){
            return;
        }
        if (clip == null) {
            return;
        }
        clip.stop();
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playOnce(){
        clip.stop();
        clip.setFramePosition(0);
        clip.loop(0);
    }
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.close();
    }
}
