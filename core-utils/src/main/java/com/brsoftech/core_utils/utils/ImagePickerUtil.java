package com.brsoftech.core_utils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.brsoftech.core_utils.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kartik Sharma @ B.R. Softech on 5/4/16.
 */
public class ImagePickerUtil {
    private static final int RC_PICK_IMAGE_CAMERA = 0;
    private static final int RC_PICK_IMAGE_STORAGE = 1;

    private final static int CAMERA_PERMISSION_RC = 1337;

    private static final String TAG = "ImagePickerUtil";

    private Context mContext;
    private Fragment mFragment;
    private OnImagePickerListener mOnImagePickerListener;
    private String mCurrentPhotoPath;
    private boolean mPickFromCamera;

    public ImagePickerUtil(Context context) {
        this.mContext = context;
    }

    public ImagePickerUtil(Fragment fragment) {
        mContext = fragment.getContext();
        mFragment = fragment;
    }

    public void pickImage(OnImagePickerListener onImagePickerListener) {
        mOnImagePickerListener = onImagePickerListener;

        new AlertDialog.Builder(mContext)
                .setTitle(R.string.pick_image)
                .setSingleChoiceItems(R.array.pick_image_types, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                pickImageFromCamera();
                                break;
                            case 1:
                                pickImageFromStorage();
                                break;
                        }
                    }
                })
                .setPositiveButton(R.string.choose, null)
                .show();
    }


    public void pickImageFromCamera() {
        mPickFromCamera = true;

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(mContext.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(TAG, "IOException");
                mOnImagePickerListener.onImagePickerFailure(ex.getMessage());
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                if (mFragment == null) {
                    ((Activity) mContext).startActivityForResult(cameraIntent, RC_PICK_IMAGE_CAMERA);
                } else {
                    mFragment.startActivityForResult(cameraIntent, RC_PICK_IMAGE_CAMERA);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.e("CurrentPhotoPath", mCurrentPhotoPath);
        return image;
    }

    public void pickImageFromStorage() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (mFragment == null) {
            ((Activity) mContext).startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    RC_PICK_IMAGE_STORAGE);
        } else {
            mFragment.startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    RC_PICK_IMAGE_STORAGE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RC_PICK_IMAGE_CAMERA:
                    mOnImagePickerListener.onImagePickerSuccess(mCurrentPhotoPath, true);
                    break;
                case RC_PICK_IMAGE_STORAGE:
                    Uri photoUri = data.getData();
                    mOnImagePickerListener.onImagePickerSuccess(FileUtil.getRealPathOfImageFromURI(mContext, photoUri), false);
                    break;
            }
        }
    }

    public void onDestroy() {
        if (mPickFromCamera) {
            new FileUtil().deleteFile(mCurrentPhotoPath);
        }
    }

    public interface OnImagePickerListener {
        void onImagePickerSuccess(String imgPath, boolean fromCamera);

        void onImagePickerFailure(String message);
    }
}
