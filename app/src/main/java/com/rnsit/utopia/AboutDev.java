package com.rnsit.utopia;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AboutDev extends DialogFragment implements View.OnClickListener {
    private ImageView photo;
    private ImageView intent_fb,intent_ln,intent_insta;
    private Context context;

    String urlFb="https://www.facebook.com/shashankbhat114";
    String appFb="fb://facewebmodal/f?href="+urlFb;

    String urlLn="https://www.linkedin.com/in/shashank-bhat-924b1bb9/";

    String appIs="http://instagram.com/shashank_bhat__";
    String urlIs="http://instagram.com/_u/shashank_bhat__";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_about_dev, container, false);
        context=this.getActivity();

        photo = view.findViewById(R.id.photo);

        Glide.with(this)
                .load(R.drawable.my_photo)
                .apply(RequestOptions.circleCropTransform())
                .into(photo);

        intent_fb = view.findViewById(R.id.fb);
        intent_ln = view.findViewById(R.id.ln);
        intent_insta = view.findViewById(R.id.insta);

        intent_fb.setOnClickListener(this);
        intent_ln.setOnClickListener(this);
        intent_insta.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fb:
                try {
                    if (isAppInstalled(context, "com.facebook.orca") || isAppInstalled(context, "com.facebook.katana")
                            || isAppInstalled(context, "com.example.facebook") || isAppInstalled(context, "com.facebook.android")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appFb)));
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlFb)));
                    }
                }catch (Exception e){e.printStackTrace();}
                break;

            case R.id.ln:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlLn));
                    intent.setPackage("com.linkedin.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlLn)));
                }
                break;

            case R.id.insta:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(appIs));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlIs)));
                }
                break;
            default:
                break;

        }

    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
