# MyMusicPlayer
This app has built in audios and audio info. 
Something different from previous app is that it adds transition animation between main_acitivity and detail_activity for sharing components.
In the song detail activity, it plays audio and updates seekbar accordingly.
It also handles audio focus in some situations.

Things to improve in the future:
1) Implement the audio play that will save the last paused/stopped song's position in order to resume in the future
2) Handle audio focus:
a.when there is incoming phone call
b.notification
c.if the app's audio focus is taken by other audio when it's still playing, after other audios has lost audio focus, this app
will regain focus and resum playing based on the saved song's position
3) The seekbar is still not updating smoothly. Can make it update with even smaller progress instead of 1 second
4) make a spotify listview scrollup effect
5)try out reading audios from the phone
6)try out search function: refer to great_femail_technician app
