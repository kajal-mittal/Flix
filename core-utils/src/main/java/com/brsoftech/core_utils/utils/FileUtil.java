package com.brsoftech.core_utils.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Kartik Sharma @ B.R. Softech on 22/3/16.
 */
public class FileUtil {
    private String mName;
    private String mExtension;
    private String mPath;
    private File mFile;
    private String mDirectory;

    public FileUtil() {

    }

    public static String getRealPathOfImageFromURI(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};

        //This method was deprecated in API level 11
        //Cursor cursor = managedQuery(contentUri, proj, null, null, null);

        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        String path = cursor.getString(column_index);

        cursor.close();

        return path;
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};

            try {
                Cursor cursor;

                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }

                cursor.close();
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public FileUtil setName(String name) {
        mName = name;
        return this;
    }

    public FileUtil setExtension(String extension) {
        mExtension = extension;
        return this;
    }

    public FileUtil setPath(String path) {
        mPath = path;
        return this;
    }

    public FileUtil setDirectory(String directory) {
        mDirectory = directory;
        return this;
    }

    public void saveAsync(final String data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                save(data);
                return null;
            }
        }.execute();
    }

    public void save(String data) {
        // get the path to sdcard
        String root = Environment.getExternalStorageDirectory().toString();
        // to this path add a new directory path
        File dir = new File(root + "/" + mDirectory);
        // create this directory if not already created
        dir.mkdirs();
        // create the file in which we will write the contents
        File file = new File(dir, mName + "." + mExtension);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String path) {
        new File(path).delete();
    }
}
