package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.adapter.MyCreationAdapter;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityMyCreationBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.model.PlaceData;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;

import com.bumptech.glide.Glide;
import com.google.maps.android.clustering.Cluster;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")

public class MyCreationActivity extends BaseActivity implements OnClickGallery {
    public static Cluster<PlaceData> cluster;
    ActivityMyCreationBinding binding;
    MyCreationAdapter myCreationAdapter;
    int type;
    List<Uri> uriList = new ArrayList();

    @Override
    public void onClickFolder(int i) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(MyCreationActivity.this, SharedHelper.getString(MyCreationActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityMyCreationBinding inflate = ActivityMyCreationBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        Resizer.getheightandwidth(this);
        Resizer.setSize(this.binding.noData, 500, 339, true);
        if (cluster == null) {
            this.type = getIntent().getIntExtra("TYPE", 0);
        }
        initSocketConnection(this, true, true);

        setData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showUserInterDataBack(this, new AllKeyHub.onCrashDataClose() {
                @Override
                public void onDataClose() {
                    onBackPressed();
                }
            });
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    private void setData() {
        this.myCreationAdapter = new MyCreationAdapter(this, this.uriList, this, 2);
        this.binding.recyclerview.setAdapter(this.myCreationAdapter);
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                showUserInterDataBack(MyCreationActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });
    }


    public void m102x323c4bc7(View view) {
        onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        new ProcessAsyncTask().execute(new String[0]);
    }

    @Override
    public void onClickItem(String str) {
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
        if (str.toLowerCase().endsWith(".mp4")) {
            startActivity(
                    new Intent(this, VideoPreviewActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("Path", str)
            );
        } else {
            startActivity(
                    new Intent(this, PreviewActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("Path", str)
            );
        }
    }


    public class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Dialog dialog;
        ProcessAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MyCreationActivity.this.uriList.clear();
            this.binding1 = ProcessDialogLayoutBinding.inflate(MyCreationActivity.this.getLayoutInflater());
            Dialog dialog = new Dialog(MyCreationActivity.this);
            this.dialog = dialog;
            dialog.getWindow().requestFeature(1);
            this.dialog.setContentView(this.binding1.getRoot());
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return true;
                }
            });
            Glide.with((FragmentActivity) MyCreationActivity.this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
            this.dialog.show();
        }

        @Override
        public String doInBackground(String[] strArr) {
            if (MyCreationActivity.cluster == null) {
                if (MyCreationActivity.this.type == 0) {
                    MyCreationActivity myCreationActivity = MyCreationActivity.this;
                    myCreationActivity.uriList = StorageUtils.getFolderData(StorageUtils.create_folder(myCreationActivity.getResources().getString(R.string.app_name)));
                    return null;
                }
                MyCreationActivity myCreationActivity2 = MyCreationActivity.this;
                myCreationActivity2.uriList = StorageUtils.getFolderDataVideo(StorageUtils.create_folder(myCreationActivity2.getResources().getString(R.string.app_name)));
                return null;
            }
            for (PlaceData placeData : MyCreationActivity.cluster.getItems()) {
                if (new File(placeData.getPath()).exists()) {
                    MyCreationActivity.this.uriList.add(Uri.parse(placeData.getPath()));
                }
            }
            return null;
        }

        @Override
        public void onPostExecute(String str) {
            this.dialog.dismiss();
            if (MyCreationActivity.this.uriList != null && MyCreationActivity.this.uriList.size() > 0) {
                MyCreationActivity.this.binding.noData.setVisibility(8);
                MyCreationActivity.this.binding.recyclerview.setVisibility(0);
                MyCreationActivity.this.myCreationAdapter.updateAdapter(MyCreationActivity.this.uriList);
                return;
            }
            MyCreationActivity.this.binding.recyclerview.setVisibility(8);
            MyCreationActivity.this.binding.noData.setVisibility(0);
        }
    }
}
