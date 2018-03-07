package com.example.android.mymusicplayer;

/**
 * Created by ceciliaHumlelu on 2018-03-07.
 */

public class Utilities {
    public String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        //convert total duration into time
        int hours = (int) (milliseconds/(1000*60*60));
        int minutes = (int) (milliseconds % (1000*60*60))/(1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60))% (1000*60) / 1000) ;

        // add hours if there
        if (hours > 0 ) {
            finalTimerString = hours + ":";
        }

        //prepending 0 to seconds if it is one digit
        if (seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        return finalTimerString;

    }


    public int getProgressPercentage(long currentDuration,long totalDuration){
        Double percentage = (double) 0;
        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        //calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        return percentage.intValue();
    }

    public int progressToTimer(int progress, int totalDuration){
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) (((double) progress) /100) * totalDuration;

        return currentDuration * 1000;
    }
}
