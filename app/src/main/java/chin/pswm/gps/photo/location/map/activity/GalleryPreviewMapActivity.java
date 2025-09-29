package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showDynamicNativeData;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.adapter.MyPagerAdapter;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityGalleryPreviewMapBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.BackDialogLayoutBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
@SuppressWarnings("all")

public class GalleryPreviewMapActivity extends BaseActivity {
    ActivityGalleryPreviewMapBinding binding;
    BackDialogLayoutBinding binding1;
    Dialog dialog;
    public ArrayList<Integer> layoutArrayList = Common.getLayoutList();
    int type;

    @Override 
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(GalleryPreviewMapActivity.this, SharedHelper.getString(GalleryPreviewMapActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityGalleryPreviewMapBinding inflate = ActivityGalleryPreviewMapBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.type = getIntent().getIntExtra("Type", 1);
        Resizer.getheightandwidth(this);
        Resizer.setSize(this.binding.layoutRl, 1080, 580, true);
        Resizer.setSize(this.binding.footer, 1080, 200, true);
        Resizer.setSize(this.binding.save, 892, 142, true);
        initSocketConnection(this, true, true);

        setData();
    }

    private void setData() {
        Glide.with((FragmentActivity) this).load(GalleryPreviewActivity.bitmap).into(this.binding.preview);
        Common.pictureBitmap = GalleryPreviewActivity.bitmap;
        this.binding.save.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                GalleryPreviewMapActivity.this.m85x170fab08(view);
            }
        });
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                showUserInterDataBack(GalleryPreviewMapActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });
        this.binding.viewPager.setAdapter(new MyPagerAdapter(this, this.layoutArrayList));
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


    public  void m85x170fab08(View view) {
        if (this.type == 1) {
            startActivity(new Intent(this, AdvanceCreateActivity.class).setFlags(536870912));
            finish();
            return;
        }
        new ProcessAsyncTask().execute(new String[0]);
    }


    public  void m86x8613a89(View view) {
        onBackPressed();
    }

    
    public class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Bitmap bitmap;
        Dialog dialog;
        String name;
        String path;

        ProcessAsyncTask() {
        }

        @Override 
        protected void onPreExecute() {
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(GalleryPreviewMapActivity.this.getLayoutInflater());
            Dialog dialog = new Dialog(GalleryPreviewMapActivity.this);
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
            Glide.with((FragmentActivity) GalleryPreviewMapActivity.this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
            this.dialog.show();
        }

        @Override 
        public String doInBackground(String[] strArr) {
            this.bitmap = GalleryPreviewMapActivity.this.getBitmapFromView();
            this.path = StorageUtils.create_folder(GalleryPreviewMapActivity.this.getResources().getString(R.string.app_name));
            this.name = "Image_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
            return null;
        }

        @Override 
        public void onPostExecute(String str) {
            try {
                final String saveImageWithLocation = StorageUtils.saveImageWithLocation(GalleryPreviewMapActivity.this, this.bitmap, this.path, this.name, Double.parseDouble(Common.latTemplate), Double.parseDouble(Common.lonTemplate));
                StorageUtils.scanFile(GalleryPreviewMapActivity.this, saveImageWithLocation);
                new Handler().postDelayed(new Runnable() {
                    @Override 
                    public final void run() {
                        GalleryPreviewMapActivity.ProcessAsyncTask.this.m87xc270e184(saveImageWithLocation);
                    }
                }, 2000L);
            } catch (Exception unused) {
            }
        }


        public  void m87xc270e184(final String str) {
            this.dialog.dismiss();
            if (str != null) {
                GalleryPreviewMapActivity.this.startActivity(new Intent(GalleryPreviewMapActivity.this, PreviewActivity.class).setFlags(536870912).putExtra("Path", str));
                GalleryPreviewMapActivity.this.finish();
            }
        }
    }

    public Bitmap getBitmapFromView() {
        Bitmap createBitmap = Bitmap.createBitmap(this.binding.dataRl.getWidth(), this.binding.dataRl.getHeight(), Bitmap.Config.ARGB_8888);
        this.binding.dataRl.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    @Override 
    public void onBackPressed() {
        this.binding1 = BackDialogLayoutBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
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
        this.binding1.title.setText(getResources().getString(R.string.gps51));
        this.binding1.no.setText(getResources().getString(R.string.gps52));
        this.binding1.yes.setText(getResources().getString(R.string.gps53));
        this.dialog.show();
        showDynamicNativeData(GalleryPreviewMapActivity.this,null, binding1.AdBanner, true);

        this.binding1.no.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                GalleryPreviewMapActivity.this.m83x7814f67a(view);
            }
        });
        this.binding1.yes.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                GalleryPreviewMapActivity.this.m84x696685fb(view);
            }
        });
    }


    public  void m83x7814f67a(View view) {
        this.dialog.dismiss();
        finish();
    }

    public  void m84x696685fb(View view) {
        this.dialog.dismiss();
    }
}
