package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class SoundUtils {
  /**
   * Music is for sounds that will loop
   * Sound for sounds that will play when "events trigger" like summoning a piece
   * call create() in main gameAdaptor class create method to enable sounds
   * call dispose() in main gameAdaptor class dispose method to dispose of sound assets
   */

  private final static Music THEME_SONG = Gdx.audio.newMusic(Gdx.files.internal("sound/summoners_ost_2.wav"));

  public static final float volume = 0.5f; //todo adjust as needed for rice ib sound effects

  private final static Map<String, Sound> SOUND_EFFECTS = new HashMap<>();

  public static final Sound GAME_OVER = Gdx.audio.newSound(Gdx.files.internal("sound/funny_titanic_flute.mp3"));

  public static final Sound SUMMON_PIECE = Gdx.audio.newSound(Gdx.files.internal("sound/summon-sprite.wav"));

  public static final Sound CRASH = Gdx.audio.newSound(Gdx.files.internal("sound/cool_wave_crash_sound.wav"));

  public static final Sound PIECE_LOST = Gdx.audio.newSound(Gdx.files.internal("sound/home_cell_lost.wav"));

  public static void create() {
    THEME_SONG.setLooping(true);
    THEME_SONG.play();
    SOUND_EFFECTS.put("gameover", GAME_OVER);
    SOUND_EFFECTS.put("summon", SUMMON_PIECE);
    SOUND_EFFECTS.put("crash", CRASH);
    SOUND_EFFECTS.put("pieceLost", PIECE_LOST);
//    CRASH.play(0.5f);
  }

  public static void playSound(String sound) {
    SOUND_EFFECTS.get(sound).play();
  }

  public static void playSound(String sound, float volume) {
    SOUND_EFFECTS.get(sound).play(volume);
  }

  public static void playSound(String sound, boolean pauseThemeSong) {
    THEME_SONG.pause();
    SOUND_EFFECTS.get(sound).play();
    THEME_SONG.play();
  }

  public static void playSummonSound() {
    SUMMON_PIECE.play(volume);
  }

  public static void playCrashSound() {
    CRASH.play(volume);
  }

  public static void playPieceLostSound() {
    PIECE_LOST.play(volume);
  }

  public static void dispose() {
    THEME_SONG.dispose();
    SOUND_EFFECTS.forEach((s, sound) -> sound.dispose());
  }
}
