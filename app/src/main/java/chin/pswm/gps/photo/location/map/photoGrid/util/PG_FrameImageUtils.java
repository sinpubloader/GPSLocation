package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import chin.pswm.gps.photo.location.map.photoGrid.model.PG_TemplatePGItem;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0013"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_FrameImageUtils;", "", "()V", "FRAME_FOLDER", "", "getFRAME_FOLDER", "()Ljava/lang/String;", "FRAME_FOLDER_SELECT", "getFRAME_FOLDER_SELECT", "collage", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "frameName", "collage$app_release", "createTemplateItems", "loadFrameImages", "Ljava/util/ArrayList;", "context", "Landroid/content/Context;", "loadFrameImagesSelect", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_FrameImageUtils {
    public static final PG_FrameImageUtils INSTANCE = new PG_FrameImageUtils();
    private static final String FRAME_FOLDER = "frame";
    private static final String FRAME_FOLDER_SELECT = "frame_select";

    private PG_FrameImageUtils() {
    }

    public final String getFRAME_FOLDER() {
        return FRAME_FOLDER;
    }

    public final String getFRAME_FOLDER_SELECT() {
        return FRAME_FOLDER_SELECT;
    }

    public final PG_TemplatePGItem collage$app_release(String frameName) {
        Intrinsics.checkNotNullParameter(frameName, "frameName");
        PG_TemplatePGItem pG_TemplatePGItem = new PG_TemplatePGItem();
        pG_TemplatePGItem.setPreview(PG_PhotoUtils.INSTANCE.getASSET_PREFIX() + FRAME_FOLDER + '/' + frameName);
        pG_TemplatePGItem.setTitle(frameName);
        return pG_TemplatePGItem;
    }


 public final ArrayList<PG_TemplatePGItem> loadFrameImages(Context context) {
     Intrinsics.checkNotNullParameter(context, "context");
     ArrayList<PG_TemplatePGItem> arrayList = new ArrayList<>();
     try {
         String[] list = context.getAssets().list(FRAME_FOLDER);
         arrayList.clear();
         if (list != null && list.length > 0) {
             Iterator it = ArrayIteratorKt.iterator(list);
             while (it.hasNext()) {
                 String str = (String) it.next();
                 Intrinsics.checkNotNullExpressionValue(str, "str");
                 PG_TemplatePGItem createTemplateItems = createTemplateItems(str);
                 if (createTemplateItems != null) {
                     arrayList.add(createTemplateItems);
                 }
             }
             Collections.sort(arrayList, new PG_FrameImageUtils$$ExternalSyntheticLambda0(PG_FrameImageUtils$loadFrameImages$1.INSTANCE));
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     return arrayList;
 }
    public static final int loadFrameImages$lambda$0(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }
  public final ArrayList<PG_TemplatePGItem> loadFrameImagesSelect(Context context) {
      Intrinsics.checkNotNullParameter(context, "context");
      ArrayList<PG_TemplatePGItem> arrayList = new ArrayList<>();
      try {
          String[] list = context.getAssets().list(FRAME_FOLDER_SELECT);
          arrayList.clear();
          if (list != null && list.length > 0) {
              Iterator it = ArrayIteratorKt.iterator(list);
              while (it.hasNext()) {
                  String str = (String) it.next();
                  Intrinsics.checkNotNullExpressionValue(str, "str");
                  PG_TemplatePGItem createTemplateItems = createTemplateItems(str);
                  if (createTemplateItems != null) {
                      arrayList.add(createTemplateItems);
                  }
              }
              Collections.sort(arrayList, new PG_FrameImageUtils$$ExternalSyntheticLambda1(PG_FrameImageUtils$loadFrameImagesSelect$1.INSTANCE));
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      return arrayList;
  }
    public static final int loadFrameImagesSelect$lambda$1(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    private final PG_TemplatePGItem createTemplateItems(String str) {
        switch (str.hashCode()) {
            case -1797697869:
                if (str.equals("collage_2_10.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_10();
                }
                return null;
            case -1796774348:
                if (str.equals("collage_2_11.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_11();
                }
                return null;
            case 1188015986:
                if (str.equals("collage_2_0.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_0();
                }
                return null;
            case 1188939507:
                if (str.equals("collage_2_1.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_1();
                }
                return null;
            case 1189863028:
                if (str.equals("collage_2_2.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_2();
                }
                return null;
            case 1190786549:
                if (str.equals("collage_2_3.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_3();
                }
                return null;
            case 1191710070:
                if (str.equals("collage_2_4.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_4();
                }
                return null;
            case 1192633591:
                if (str.equals("collage_2_5.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_5();
                }
                return null;
            case 1193557112:
                if (str.equals("collage_2_6.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_6();
                }
                return null;
            case 1194480633:
                if (str.equals("collage_2_7.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_7();
                }
                return null;
            case 1195404154:
                if (str.equals("collage_2_8.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_8();
                }
                return null;
            case 1196327675:
                if (str.equals("collage_2_9.png")) {
                    return PG_TwoFrameImage.INSTANCE.collage_2_9();
                }
                return null;
            default:
                return null;
        }
    }
}
