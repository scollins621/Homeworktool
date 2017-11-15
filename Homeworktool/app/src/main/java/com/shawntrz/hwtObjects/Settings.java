package com.shawntrz.hwtObjects;

import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.NonReadableChannelException;
import java.util.stream.Collectors;

/**
 * Created by shawn on 11/7/17.
 */

public class Settings extends com.shawntrz.homeworktool.Settings{
    public boolean smsNotifications;
    public boolean pushNotifications;
    public boolean emailNotifications;

    public Settings(Context context){
        Log.v("settings" ,"DONE!!yay");
        File filesdir = context.getFilesDir();
    }

    private void initFromFile(Context context) {
        File file = new File(context.getFilesDir(), "settings");
        try {
            readFromFileAndSetVarsFromFile(file);
        }
        catch(NonReadableChannelException e){
            initializeFileAndSetVarsFromDefaults(file);
        }
    }

    private void readFromFileAndSetVarsFromFile(File file) throws NonReadableChannelException{
        //assuming XML.
        String contents = "";
        if(file.canRead()){
            try {
                FileReader fr = new FileReader(file.toString());
                BufferedReader bufferedReader = new BufferedReader(fr);
                String line;
                while((line = bufferedReader.readLine()) != null){
                    contents += line;
                }
                Log.d("settings", contents);

            }
            catch(FileNotFoundException e){
                throw new NonReadableChannelException();
            }
            catch(IOException e){

            }
        }
    }

    private void initializeFileAndSetVarsFromDefaults(File file){
        Log.d("settings", "No file found...");
    }

}
