package com.r3dtech.factory.framework.implementation;

import android.content.Context;
import android.util.Log;

import com.r3dtech.factory.framework.FileIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This is an implementation of the FileIO interface on android.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class AndroidFileIO implements FileIO {
    private Context context;

    AndroidFileIO(Context context) {
        this.context = context;
    }

    @Override
    public InputStream readFile(String filename) throws IOException {
        Log.d("FILE_IO", context.getDataDir().getAbsolutePath());
        return context.openFileInput(filename);
    }

    @Override
    public OutputStream writeFile(String filename) throws IOException{
        return context.openFileOutput(filename, Context.MODE_PRIVATE);
    }
}
