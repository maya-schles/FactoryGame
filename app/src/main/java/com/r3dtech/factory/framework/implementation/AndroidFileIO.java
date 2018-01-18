package com.r3dtech.factory.framework.implementation;

import android.os.Environment;

import com.r3dtech.factory.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This is an implementation of the FileIO interface on android.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class AndroidFileIO implements FileIO {
    private String externalStoragePath;

    public AndroidFileIO() {
        externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }

    @Override
    public InputStream readFile(String filename) throws IOException {
        return new FileInputStream(externalStoragePath+filename);
    }

    @Override
    public OutputStream writeFile(String filename) throws IOException{
        return new FileOutputStream(externalStoragePath + filename);
    }
}
