package edu.polytech.ihmtd2dechet.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import edu.polytech.ihmtd2dechet.interfaces.PictureInterface;
import edu.polytech.ihmtd2dechet.R;

public class PictureFragment extends Fragment {

    ImageView picture;

    public PictureFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_picture, container, false);

        picture = rootView.findViewById(R.id.pictureButton);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, PictureInterface.REQUEST_CAMERA);
                } else {
                    takePicture();
                }
            }
        });
        return rootView;
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent, PictureInterface.REQUEST_CAMERA);
    }

    public void setImage(Bitmap bitmap) {
        picture.setImageBitmap(bitmap);
    }
}
