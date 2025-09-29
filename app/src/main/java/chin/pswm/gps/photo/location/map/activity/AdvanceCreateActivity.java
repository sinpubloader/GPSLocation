package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showDynamicNativeData;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityAdvanceCreateBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.BackDialogLayoutBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;

@SuppressWarnings("all")

public class AdvanceCreateActivity extends BaseActivity implements OnMapReadyCallback {
    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final int ZOOM = 2;
    ActivityAdvanceCreateBinding binding;
    BackDialogLayoutBinding binding1;
    Dialog dialog;
    private boolean isOutSide;
    private boolean isZoomAndRotate;
    private GoogleMap mMap;
    public Typeface typeface;
    private float xCoOrdinate;
    private float yCoOrdinate;
    float[] lastEvent = null;
    float d = 0.0f;
    float newRot = 0.0f;
    private int mode = 0;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1.0f;
    public boolean ac2 = false;
    public boolean ac4 = false;
    private long mLastClickTime = 0;
    public ArrayList<Integer> fontList = Common.getFontList();
    public int fontListPos = 0;
    long clickTime = 0;
    public boolean isMapView = true;
    public ArrayList<Integer> layoutArrayList = Common.getLayoutList();
    public boolean isLayoutView = true;
    ActivityResultLauncher<Intent> galleryResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult activityResult) {
            Intent data;
            if (activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
                return;
            }
            Glide.with((FragmentActivity) AdvanceCreateActivity.this).load(data.getStringExtra("Choice")).into(AdvanceCreateActivity.this.binding.mapPreview);
        }
    });

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(AdvanceCreateActivity.this, SharedHelper.getString(AdvanceCreateActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityAdvanceCreateBinding inflate = ActivityAdvanceCreateBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.binding.mapView.onCreate(bundle);
        this.binding.mapView.getMapAsync(this);
        this.binding.mapView.onResume();
        setData();
        this.binding.ac1.setEnabled(false);
        this.binding.ac2.setEnabled(false);
        this.binding.ac3.setEnabled(false);
        this.binding.ac4.setEnabled(false);
        this.binding.ac5.setEnabled(false);
        this.binding.ac6.setEnabled(false);
        initSocketConnection(this, true, true);

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
        Glide.with((FragmentActivity) this).load(Common.pictureBitmap).into(this.binding.gcPreview);
        setTouchLis(false);
        this.binding.cityCountry.setText(Common.cityTemplate);
        this.typeface = ResourcesCompat.getFont(this, this.fontList.get(this.fontListPos).intValue());
        this.binding.cityCountry.setTypeface(this.typeface);
        this.fontListPos++;
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                showUserInterDataBack(AdvanceCreateActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        AdvanceCreateActivity.this.m58xae7ec740(view);
                    }
                });
            }
        });
        this.binding.gcPreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg8, MotionEvent arg9) {
                int v0 = arg9.getAction() & 0xFF;
                if (v0 == 0) {
                    float v4 = arg8.getX() - arg9.getRawX();
                    AdvanceCreateActivity.this.xCoOrdinate = v4;
                    float v8_4 = arg8.getY() - arg9.getRawY();
                    AdvanceCreateActivity.this.yCoOrdinate = v8_4;
                    AdvanceCreateActivity.this.start.set(arg9.getX(), arg9.getY());
                    AdvanceCreateActivity.this.isOutSide = false;
                    AdvanceCreateActivity.this.mode = 1;
                    AdvanceCreateActivity.this.lastEvent = null;
                } else {
                    if (v0 == 1) {
                        AdvanceCreateActivity.this.isZoomAndRotate = false;
                        if (AdvanceCreateActivity.this.mode == 1) {
                            arg9.getX();
                            arg9.getY();
                        }
                    }

                    if (v0 != 2) {
                        if (v0 == 4) {
                            AdvanceCreateActivity.this.isOutSide = true;
                            AdvanceCreateActivity.this.mode = 0;
                            AdvanceCreateActivity.this.lastEvent = null;
                            AdvanceCreateActivity.this.mode = 0;
                            AdvanceCreateActivity.this.lastEvent = null;
                            return true;
                        }

                        if (v0 != 5) {
                            if (v0 == 6) {
                                AdvanceCreateActivity.this.mode = 0;
                                AdvanceCreateActivity.this.lastEvent = null;
                                return true;
                            }

                            return true;
                        }

                        AdvanceCreateActivity.this.oldDist = AdvanceCreateActivity.this.spacing(arg9);
                        if (AdvanceCreateActivity.this.oldDist > 10.0f) {
                            PointF v1 = AdvanceCreateActivity.this.mid;
                            AdvanceCreateActivity.this.midPoint(v1, arg9);
                            AdvanceCreateActivity.this.mode = 2;
                        }

                        AdvanceCreateActivity.this.lastEvent = new float[4];
                        float[] v8 = AdvanceCreateActivity.this.lastEvent;
                        v8[0] = arg9.getX(0);
                        float[] v8_1 = AdvanceCreateActivity.this.lastEvent;
                        v8_1[1] = arg9.getX(1);
                        float[] v8_2 = AdvanceCreateActivity.this.lastEvent;
                        v8_2[2] = arg9.getY(0);
                        float[] v8_3 = AdvanceCreateActivity.this.lastEvent;
                        v8_3[3] = arg9.getY(1);
                        AdvanceCreateActivity.this.d = AdvanceCreateActivity.this.rotation(arg9);
                        return true;
                    }

                    if (!AdvanceCreateActivity.this.isOutSide) {
                        if (AdvanceCreateActivity.this.mode == 1) {
                            AdvanceCreateActivity.this.isZoomAndRotate = false;
                            arg8.animate().x(arg9.getRawX() + AdvanceCreateActivity.this.xCoOrdinate).y(arg9.getRawY() + AdvanceCreateActivity.this.yCoOrdinate).setDuration(0L).start();
                        }

                        if (AdvanceCreateActivity.this.mode == 2 && arg9.getPointerCount() == 2) {
                            float v0_1 = AdvanceCreateActivity.this.spacing(arg9);
                            if (v0_1 > 10.0f) {
                                float v0_2 = v0_1 / AdvanceCreateActivity.this.oldDist * arg8.getScaleX();
                                arg8.setScaleX(v0_2);
                                arg8.setScaleY(v0_2);
                            }

                            if (AdvanceCreateActivity.this.lastEvent != null) {
                                AdvanceCreateActivity.this.newRot = AdvanceCreateActivity.this.rotation(arg9);
                                arg8.setRotation(arg8.getRotation() + (AdvanceCreateActivity.this.newRot - AdvanceCreateActivity.this.d));
                                return true;
                            }
                        }
                    }
                }

                return true;
            }
        });
        this.binding.ac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m59xfc3e3f41(view);
            }
        });
        this.binding.ac4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m60x49fdb742(view);
            }
        });
        this.binding.cityCountry.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View arg9, MotionEvent arg10) {
                int v0 = arg10.getAction() & 0xFF;
                if (v0 == 0) {
                    AdvanceCreateActivity.this.clickTime = System.currentTimeMillis();
                    float v4 = arg9.getX() - arg10.getRawX();
                    AdvanceCreateActivity.this.xCoOrdinate = v4;
                    float v9_4 = arg9.getY() - arg10.getRawY();
                    AdvanceCreateActivity.this.yCoOrdinate = v9_4;
                    AdvanceCreateActivity.this.start.set(arg10.getX(), arg10.getY());
                    AdvanceCreateActivity.this.isOutSide = false;
                    AdvanceCreateActivity.this.mode = 1;
                    AdvanceCreateActivity.this.lastEvent = null;
                } else {
                    if (v0 == 1) {
                        AdvanceCreateActivity.this.isZoomAndRotate = false;
                        if (System.currentTimeMillis() - AdvanceCreateActivity.this.clickTime < 100L) {
                            if (AdvanceCreateActivity.this.fontListPos == AdvanceCreateActivity.this.fontList.size()) {
                                AdvanceCreateActivity.this.fontListPos = 0;
                                int v0_1 = (int) (((Integer) AdvanceCreateActivity.this.fontList.get(AdvanceCreateActivity.this.fontListPos)));
                                AdvanceCreateActivity.this.typeface = ResourcesCompat.getFont(AdvanceCreateActivity.this, v0_1);
                            } else {
                                int v0_2 = (int) (((Integer) AdvanceCreateActivity.this.fontList.get(AdvanceCreateActivity.this.fontListPos)));
                                AdvanceCreateActivity.this.typeface = ResourcesCompat.getFont(AdvanceCreateActivity.this, v0_2);
                            }

                            AdvanceCreateActivity.this.binding.cityCountry.setTypeface(AdvanceCreateActivity.this.typeface);
                            ++AdvanceCreateActivity.this.fontListPos;
                        }

                        if (AdvanceCreateActivity.this.mode == 1) {
                            arg10.getX();
                            arg10.getY();
                        }
                    }

                    if (v0 != 2) {
                        if (v0 == 4) {
                            AdvanceCreateActivity.this.isOutSide = true;
                            AdvanceCreateActivity.this.mode = 0;
                            AdvanceCreateActivity.this.lastEvent = null;
                            AdvanceCreateActivity.this.mode = 0;
                            AdvanceCreateActivity.this.lastEvent = null;
                            return true;
                        }

                        if (v0 != 5) {
                            if (v0 == 6) {
                                AdvanceCreateActivity.this.mode = 0;
                                AdvanceCreateActivity.this.lastEvent = null;
                                return true;
                            }

                            return true;
                        }

                        AdvanceCreateActivity.this.oldDist = AdvanceCreateActivity.this.spacing(arg10);
                        if (AdvanceCreateActivity.this.oldDist > 10.0f) {
                            PointF v1 = AdvanceCreateActivity.this.mid;
                            AdvanceCreateActivity.this.midPoint(v1, arg10);
                            AdvanceCreateActivity.this.mode = 2;
                        }

                        AdvanceCreateActivity.this.lastEvent = new float[4];
                        float[] v9 = AdvanceCreateActivity.this.lastEvent;
                        v9[0] = arg10.getX(0);
                        float[] v9_1 = AdvanceCreateActivity.this.lastEvent;
                        v9_1[1] = arg10.getX(1);
                        float[] v9_2 = AdvanceCreateActivity.this.lastEvent;
                        v9_2[2] = arg10.getY(0);
                        float[] v9_3 = AdvanceCreateActivity.this.lastEvent;
                        v9_3[3] = arg10.getY(1);
                        AdvanceCreateActivity.this.d = AdvanceCreateActivity.this.rotation(arg10);
                        return true;
                    }

                    if (!AdvanceCreateActivity.this.isOutSide && AdvanceCreateActivity.this.mode == 1) {
                        AdvanceCreateActivity.this.isZoomAndRotate = false;
                        arg9.animate().x(arg10.getRawX() + AdvanceCreateActivity.this.xCoOrdinate).y(arg10.getRawY() + AdvanceCreateActivity.this.yCoOrdinate).setDuration(0L).start();
                        return true;


                    }
                }

                return true;
            }
        });
        this.binding.ac5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m61x97bd2f43(view);
            }
        });
        this.binding.ac3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m62xe57ca744(view);
            }
        });
        this.binding.ac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m63x333c1f45(view);
            }
        });
        this.binding.ac6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m64x80fb9746(view);
            }
        });
    }


    public void m58xae7ec740(View view) {
        onBackPressed();
    }


    public void m59xfc3e3f41(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        if (this.ac2) {
            this.ac2 = false;
            Glide.with((FragmentActivity) this).load(Common.pictureBitmap).into(this.binding.gcPreview);
            Glide.with((FragmentActivity) this).load(Common.mapBitmap).into(this.binding.mapPreview);
            Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.grey_2)).into(this.binding.ac2);
            return;
        }
        this.ac2 = true;
        Glide.with((FragmentActivity) this).load(Common.mapBitmap).into(this.binding.gcPreview);
        Glide.with((FragmentActivity) this).load(Common.pictureBitmap).into(this.binding.mapPreview);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.blue_2)).into(this.binding.ac2);
    }


    public void m60x49fdb742(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        if (this.ac4) {
            this.ac4 = false;
            this.binding.cityCountry.setVisibility(8);
            Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.grey_4)).into(this.binding.ac4);
            return;
        }
        this.ac4 = true;
        this.binding.cityCountry.setVisibility(0);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.blue_4)).into(this.binding.ac4);
    }


    public void m61x97bd2f43(View view) {
        if (this.isMapView) {
            this.isMapView = false;
            Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.add_image)).into(this.binding.mapPreview);
            Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.blue_5)).into(this.binding.ac5);
            Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.grey_3)).into(this.binding.ac3);
        }
    }


    public void m62xe57ca744(View view) {
        if (this.isMapView) {
            return;
        }
        this.isMapView = true;
        Glide.with((FragmentActivity) this).load(Common.mapBitmap).into(this.binding.mapPreview);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.grey_5)).into(this.binding.ac5);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.blue_3)).into(this.binding.ac3);
    }


    public void m63x333c1f45(View view) {
        isLayoutView = !isLayoutView;
        binding.layoutRl.setVisibility(isLayoutView ? View.VISIBLE : View.GONE);
        toggleButton(binding.ac1, isLayoutView, R.drawable.blue_1, R.drawable.grey_1);
    }

    private void toggleButton(ImageView button, boolean active, int blueRes, int greyRes) {
        Glide.with(this)
                .load(active ? blueRes : greyRes)
                .into(button);
    }

    public void m64x80fb9746(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1500) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        new ProcessAsyncTask().execute(new String[0]);
    }


    public class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Bitmap bitmap;
        Dialog dialog;
        String name;
        String path;

        public ProcessAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(AdvanceCreateActivity.this.getLayoutInflater());
            Dialog dialog = new Dialog(AdvanceCreateActivity.this);
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
            Glide.with((FragmentActivity) AdvanceCreateActivity.this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
            this.dialog.show();
        }

        @Override
        public String doInBackground(String[] strArr) {
            this.bitmap = AdvanceCreateActivity.this.getBitmapFromView();
            this.path = StorageUtils.create_folder(AdvanceCreateActivity.this.getResources().getString(R.string.app_name));
            this.name = "Image_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
            return null;
        }

        @Override
        public void onPostExecute(String str) {
            try {
                final String saveImageWithLocation = StorageUtils.saveImageWithLocation(AdvanceCreateActivity.this, this.bitmap, this.path, this.name, Double.parseDouble(Common.latTemplate), Double.parseDouble(Common.lonTemplate));
                StorageUtils.scanFile(AdvanceCreateActivity.this, saveImageWithLocation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        AdvanceCreateActivity.ProcessAsyncTask.this.m66x9ed5974c(saveImageWithLocation);
                    }
                }, 2000L);
            } catch (Exception unused) {
                final String saveImageWithLocation2 = StorageUtils.saveImageWithLocation(AdvanceCreateActivity.this, this.bitmap, this.path, this.name, 0.0d, 0.0d);
                StorageUtils.scanFile(AdvanceCreateActivity.this, saveImageWithLocation2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        AdvanceCreateActivity.ProcessAsyncTask.this.m67xcd87016b(saveImageWithLocation2);
                    }
                }, 2000L);
            }
        }


        public void m66x9ed5974c(final String str) {
            this.dialog.dismiss();
            if (str != null) {
                AdvanceCreateActivity.this.startActivity(new Intent(AdvanceCreateActivity.this, PreviewActivity.class).setFlags(536870912).putExtra("Path", str));
                AdvanceCreateActivity.this.finish();
            }
        }


        public void m67xcd87016b(final String str) {
            this.dialog.dismiss();
            if (str != null) {
                AdvanceCreateActivity.this.startActivity(new Intent(AdvanceCreateActivity.this, PreviewActivity.class).setFlags(536870912).putExtra("Path", str));
                AdvanceCreateActivity.this.finish();
            }
        }
    }


    public Bitmap getBitmapFromView() {
        Bitmap createBitmap = Bitmap.createBitmap(this.binding.mainImageRl.getWidth(), this.binding.mainImageRl.getHeight(), Bitmap.Config.ARGB_8888);
        this.binding.mainImageRl.draw(new Canvas(createBitmap));
        return createBitmap;
    }


    public void setTouchLis(boolean z) {
        this.binding.mapViewRl.setOnTouchListener(z ? new View.OnTouchListener() {

            @Override

            public boolean onTouch(View arg9, MotionEvent arg10) {
                int v0 = arg10.getAction() & 0xFF;
                if (v0 == 0) {
                    AdvanceCreateActivity.this.clickTime = System.currentTimeMillis();
                    float v4 = arg9.getX() - arg10.getRawX();
                    AdvanceCreateActivity.this.xCoOrdinate = v4;
                    float v9_4 = arg9.getY() - arg10.getRawY();
                    AdvanceCreateActivity.this.yCoOrdinate = v9_4;
                    AdvanceCreateActivity.this.start.set(arg10.getX(), arg10.getY());
                    AdvanceCreateActivity.this.isOutSide = false;
                    AdvanceCreateActivity.this.mode = 1;
                    AdvanceCreateActivity.this.lastEvent = null;
                } else {
                    if (v0 == 1) {
                        if (System.currentTimeMillis() - AdvanceCreateActivity.this.clickTime < 100L && !AdvanceCreateActivity.this.isMapView) {
                            AdvanceCreateActivity.this.galleryResult.launch(new Intent(AdvanceCreateActivity.this, GalleryActivity.class).setFlags(0x20000000));
                        }

                        AdvanceCreateActivity.this.isZoomAndRotate = false;
                        if (AdvanceCreateActivity.this.mode == 1) {
                            arg10.getX();
                            arg10.getY();
                        }
                    }

                    if (v0 != 2) {
                        if (v0 == 4) {
                            AdvanceCreateActivity.this.isOutSide = true;
                            AdvanceCreateActivity.this.mode = 0;
                            AdvanceCreateActivity.this.lastEvent = null;
                            AdvanceCreateActivity.this.mode = 0;
                            AdvanceCreateActivity.this.lastEvent = null;
                            return true;
                        }

                        if (v0 != 5) {
                            if (v0 == 6) {
                                AdvanceCreateActivity.this.mode = 0;
                                AdvanceCreateActivity.this.lastEvent = null;
                                return true;
                            }

                            return true;
                        }

                        AdvanceCreateActivity.this.oldDist = AdvanceCreateActivity.this.spacing(arg10);
                        if (AdvanceCreateActivity.this.oldDist > 10.0f) {
                            PointF v1 = AdvanceCreateActivity.this.mid;
                            AdvanceCreateActivity.this.midPoint(v1, arg10);
                            AdvanceCreateActivity.this.mode = 2;
                        }

                        AdvanceCreateActivity.this.lastEvent = new float[4];
                        float[] v9 = AdvanceCreateActivity.this.lastEvent;
                        v9[0] = arg10.getX(0);
                        float[] v9_1 = AdvanceCreateActivity.this.lastEvent;
                        v9_1[1] = arg10.getX(1);
                        float[] v9_2 = AdvanceCreateActivity.this.lastEvent;
                        v9_2[2] = arg10.getY(0);
                        float[] v9_3 = AdvanceCreateActivity.this.lastEvent;
                        v9_3[3] = arg10.getY(1);
                        AdvanceCreateActivity.this.d = AdvanceCreateActivity.this.rotation(arg10);
                        return true;
                    }

                    if (!AdvanceCreateActivity.this.isOutSide && AdvanceCreateActivity.this.mode == 1) {
                        AdvanceCreateActivity.this.isZoomAndRotate = false;
                        arg9.animate().x(arg10.getRawX() + AdvanceCreateActivity.this.xCoOrdinate).y(arg10.getRawY() + AdvanceCreateActivity.this.yCoOrdinate).setDuration(0L).start();
                        return true;


                    }
                }

                return true;
            }
        } : null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (Common.latTemplate == null || Common.lonTemplate == null) {
            return;
        }
        try {
            LatLng latLng = new LatLng(Double.parseDouble(Common.latTemplate), Double.parseDouble(Common.lonTemplate));
            this.mMap.clear();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(Common.BitmapFromVector(this, R.drawable.map_location));
            this.mMap.addMarker(markerOptions);
            this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    AdvanceCreateActivity.this.m57x1174fc9b();
                }
            }, 3000L);
        } catch (Exception unused) {
            this.binding.ac1.setEnabled(true);
            this.binding.ac2.setEnabled(true);
            this.binding.ac3.setEnabled(true);
            this.binding.ac4.setEnabled(true);
            this.binding.ac5.setEnabled(true);
            this.binding.ac6.setEnabled(true);
        }
    }


    public class AnonymousClass5 implements GoogleMap.SnapshotReadyCallback {
        AnonymousClass5() {
        }

        @Override
        public void onSnapshotReady(Bitmap bitmap) {
            Common.mapBitmap = bitmap;
            AdvanceCreateActivity.this.binding.mapView.setVisibility(8);
            Glide.with((FragmentActivity) AdvanceCreateActivity.this).load(bitmap).into(AdvanceCreateActivity.this.binding.mapPreview);
            AdvanceCreateActivity.this.setTouchLis(true);
            Resizer.getheightandwidth(AdvanceCreateActivity.this);
            Resizer.setSize(AdvanceCreateActivity.this.binding.mapViewRl, 345, 430, true);
            AdvanceCreateActivity advanceCreateActivity = AdvanceCreateActivity.this;
            AdvanceCreateActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(advanceCreateActivity, advanceCreateActivity.layoutArrayList));
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    AdvanceCreateActivity.AnonymousClass5.this.m65xa3ff0245();
                }
            }, 2000L);
        }


        public void m65xa3ff0245() {
            AdvanceCreateActivity.this.binding.ac1.setEnabled(true);
            AdvanceCreateActivity.this.binding.ac2.setEnabled(true);
            AdvanceCreateActivity.this.binding.ac3.setEnabled(true);
            AdvanceCreateActivity.this.binding.ac4.setEnabled(true);
            AdvanceCreateActivity.this.binding.ac5.setEnabled(true);
            AdvanceCreateActivity.this.binding.ac6.setEnabled(true);
        }
    }


    public void m57x1174fc9b() {
        this.mMap.snapshot(new AnonymousClass5());
    }


    public float rotation(MotionEvent motionEvent) {
        return (float) Math.toDegrees(Math.atan2(motionEvent.getY(0) - motionEvent.getY(1), motionEvent.getX(0) - motionEvent.getX(1)));
    }


    public float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (int) Math.sqrt((x * x) + (y * y));
    }


    public void midPoint(PointF pointF, MotionEvent motionEvent) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
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
        showDynamicNativeData(AdvanceCreateActivity.this,null, binding1.AdBanner, true);

        this.binding1.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m55x14446bb8(view);
            }
        });
        this.binding1.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdvanceCreateActivity.this.m56x6203e3b9(view);
            }
        });
    }


    public void m55x14446bb8(View view) {
        this.dialog.dismiss();
        finish();
    }


    public void m56x6203e3b9(View view) {
        this.dialog.dismiss();
    }
}
