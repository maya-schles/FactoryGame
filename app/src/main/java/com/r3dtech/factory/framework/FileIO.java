package com.r3dtech.factory.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This interface is used to manage the game files.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public interface FileIO {
    InputStream readFile(String filename) throws IOException;
    OutputStream writeFile(String filename) throws IOException;
}
