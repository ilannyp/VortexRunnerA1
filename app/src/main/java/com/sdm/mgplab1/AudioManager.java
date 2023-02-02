package com.sdm.mgplab1;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    public final static AudioManager Instance = new AudioManager();

    private Resources res = null;
    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    float masterVolume = 1.0f;

    private AudioManager()
    {

    }

    public void Init(SurfaceView _view)
    {



        view = _view;
        //GameSystem.Instance.Init(_view);
        //res = _view.getResources();
        Release();//clear the audiomap
    }

    public void PlayAudio(int _id, float _volume) {
        if (audioMap.containsKey(_id)) {

//            audioMap.get(_id).reset();
//            audioMap.get(_id).start();
            //have the clip
            MediaPlayer curr = audioMap.get(_id);
            curr.seekTo(0);
            curr.setVolume(_volume * masterVolume, _volume * masterVolume);

            curr.start();
        } else{
            MediaPlayer curr = MediaPlayer.create(view.getContext(), _id);
            audioMap.put(_id,curr);
            curr.setVolume(_volume  * masterVolume, _volume  * masterVolume);
            curr.start();
            }

//        // Load the audio
//        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
//        audioMap.put(_id, newAudio);
//        newAudio.start();
    }

    public boolean IsPlaying(int _id)
    {
        if (!audioMap.containsKey(_id))
            return false;


        return audioMap.get(_id).isPlaying();
    }
    public void StopAudio(int _id)
    {
        MediaPlayer curr = audioMap.get(_id);
        curr.stop();
    }

    public void Release(){
        for (Map.Entry<Integer, MediaPlayer> entry : audioMap.entrySet())
        {
            entry.getValue().stop();
            entry.getValue().reset();
            entry.getValue().release();
        }
        //empty
        audioMap.clear();
    }



    private MediaPlayer GetAudio(int _id)
    {
        //check if audio is loaded or not
        if(audioMap.containsKey(_id))
        {
            //has the clip then return it
            return audioMap.get(_id);
        }
        //load it if not
        MediaPlayer result = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id,result);
        return result;
    }

    public void SetMasterVolume(float vol)
    {
       masterVolume = vol;
    }
    public float GetMasterVolume() {return masterVolume;}


}
