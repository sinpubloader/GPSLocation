package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class PG_GeometryUtils {
    public static boolean isInCircle(PointF pointF, float f, PointF pointF2) {
        return Math.sqrt((double) (((pointF.x - pointF2.x) * (pointF.x - pointF2.x)) + ((pointF.y - pointF2.y) * (pointF.y - pointF2.y)))) <= ((double) f);
    }

    public static boolean contains(List<PointF> list, PointF pointF) {
        int size = list.size() - 1;
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).y > pointF.y) != (list.get(size).y > pointF.y) && pointF.x < (((list.get(size).x - list.get(i).x) * (pointF.y - list.get(i).y)) / (list.get(size).y - list.get(i).y)) + list.get(i).x) {
                z = !z;
            }
            size = i;
        }
        return z;
    }

    public static void createRectanglePath(Path path, float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PointF(0.0f, 0.0f));
        arrayList.add(new PointF(f, 0.0f));
        arrayList.add(new PointF(f, f2));
        arrayList.add(new PointF(0.0f, f2));
        createPathWithCircleCorner(path, arrayList, f3);
    }

    public static void createRegularPolygonPath(Path path, float f, int i, float f2) {
        float f3 = f / 2.0f;
        createRegularPolygonPath(path, f, f3, f3, i, f2);
    }

    public static void createRegularPolygonPath(Path path, float f, float f2, float f3, int i, float f4) {
        float f5 = (float) (6.283185307179586d / i);
        ArrayList arrayList = new ArrayList();
        double d = f2;
        double d2 = f / 2.0f;
        double d3 = f3;
        arrayList.add(new PointF((float) ((Math.cos(0.0d) * d2) + d), (float) ((Math.sin(0.0d) * d2) + d3)));
        for (int i2 = 1; i2 < i; i2++) {
            double d4 = i2 * f5;
            arrayList.add(new PointF((float) ((Math.cos(d4) * d2) + d), (float) ((Math.sin(d4) * d2) + d3)));
        }
        createPathWithCircleCorner(path, arrayList, f4);
    }

    public static List<PointF> shrinkPathCollageUsingMap(List<PointF> list, float f, HashMap<PointF, PointF> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (PointF pointF : list) {
            PointF pointF2 = hashMap.get(pointF);
            arrayList.add(new PointF(pointF.x + (pointF2.x * f), pointF.y + (pointF2.y * f)));
        }
        return arrayList;
    }

    public static List<PointF> shrinkPathCollage_3_3(List<PointF> list, int i, float f, RectF rectF) {
        PointF pointF;
        PointF pointF2;
        float f2;
        float f3;
        ArrayList arrayList = new ArrayList();
        PointF pointF3 = list.get(i);
        if (i > 0) {
            pointF = list.get(i - 1);
        } else {
            pointF = list.get(list.size() - 1);
        }
        if (i < list.size() - 1) {
            pointF2 = list.get(i + 1);
        } else {
            pointF2 = list.get(0);
        }
        for (PointF pointF4 : list) {
            PointF pointF5 = new PointF();
            if (rectF != null) {
                f2 = ((rectF.left != 0.0f || pointF4.x >= pointF3.x) && (rectF.right != 1.0f || pointF4.x < pointF3.x)) ? f : f * 2.0f;
                f3 = ((rectF.top != 0.0f || pointF4.y >= pointF3.y) && (rectF.bottom != 1.0f || pointF4.y < pointF3.y)) ? f : 2.0f * f;
            } else {
                f2 = f;
                f3 = f2;
            }
            if (pointF.x == pointF2.x) {
                if (pointF.x < pointF3.x) {
                    if (pointF4.x <= pointF3.x) {
                        pointF5.x = pointF4.x + f2;
                    } else {
                        pointF5.x = pointF4.x - f2;
                    }
                } else if (pointF4.x < pointF3.x) {
                    pointF5.x = pointF4.x + f2;
                } else {
                    pointF5.x = pointF4.x - f2;
                }
                if (pointF4 == pointF || pointF4 == pointF2 || pointF4 == pointF3) {
                    if (pointF4 == pointF || pointF4 == pointF2) {
                        if (pointF4.y < pointF3.y) {
                            pointF5.y = pointF4.y - f;
                        } else {
                            pointF5.y = pointF4.y + f;
                        }
                    } else {
                        pointF5.y = pointF4.y;
                    }
                } else if (pointF4.y < pointF3.y) {
                    pointF5.y = pointF4.y + f3;
                } else {
                    pointF5.y = pointF4.y - f3;
                }
            }
            arrayList.add(pointF5);
        }
        return arrayList;
    }

    public static List<PointF> shrinkPath(List<PointF> list, float f, RectF rectF) {
        float f2;
        float f3;
        ArrayList arrayList = new ArrayList();
        if (f == 0.0f) {
            arrayList.addAll(list);
        } else {
            PointF pointF = new PointF(0.0f, 0.0f);
            for (PointF pointF2 : list) {
                pointF.x += pointF2.x;
                pointF.y += pointF2.y;
            }
            pointF.x /= list.size();
            pointF.y /= list.size();
            for (PointF pointF3 : list) {
                PointF pointF4 = new PointF();
                if (rectF != null) {
                    f2 = ((rectF.left != 0.0f || pointF3.x >= pointF.x) && (rectF.right != 1.0f || pointF3.x < pointF.x)) ? f : f * 2.0f;
                    f3 = ((rectF.top != 0.0f || pointF3.y >= pointF.y) && (rectF.bottom != 1.0f || pointF3.y < pointF.y)) ? f : 2.0f * f;
                } else {
                    f2 = f;
                    f3 = f2;
                }
                if (Math.abs(pointF.x - pointF3.x) >= 1.0f) {
                    if (pointF3.x < pointF.x) {
                        pointF4.x = pointF3.x + f2;
                    } else if (pointF3.x > pointF.x) {
                        pointF4.x = pointF3.x - f2;
                    }
                } else {
                    pointF4.x = pointF3.x;
                }
                if (Math.abs(pointF.y - pointF3.y) >= 1.0f) {
                    if (pointF3.y < pointF.y) {
                        pointF4.y = pointF3.y + f3;
                    } else if (pointF3.y > pointF.y) {
                        pointF4.y = pointF3.y - f3;
                    }
                } else {
                    pointF4.y = pointF3.y;
                }
                arrayList.add(pointF4);
            }
        }
        return arrayList;
    }

    public static List<PointF> commonShrinkPath(List<PointF> list, float f, Map<PointF, PointF> map) {
        boolean z;
        PointF pointF;
        PointF pointF2;
        ArrayList arrayList = new ArrayList();
        if (f == 0.0f) {
            arrayList.addAll(list);
        } else {
            ArrayList<PointF> jarvis = jarvis(list);
            for (int i = 0; i < list.size(); i++) {
                PointF pointF3 = list.get(i);
                Iterator<PointF> it = jarvis.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (pointF3 == it.next()) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                if (i == 0) {
                    pointF = list.get(list.size() - 1);
                } else {
                    pointF = list.get(i - 1);
                }
                if (i == list.size() - 1) {
                    pointF2 = list.get(0);
                } else {
                    pointF2 = list.get(i + 1);
                }
                PointF pointF4 = map.get(pointF3);
                PointF shrinkPoint = shrinkPoint(pointF3, pointF, pointF2, pointF4.x * f, pointF4.y * f, !z, !z);
                if (shrinkPoint != null) {
                    arrayList.add(shrinkPoint);
                } else {
                    arrayList.add(new PointF(0.0f, 0.0f));
                }
            }
        }
        return arrayList;
    }

    public static void createPathWithCubicCorner(Path path, List<PointF> list, float f) {
        path.reset();
        for (int i = 0; i < list.size(); i++) {
            if (f != 0.0f && list.size() >= 3) {
                PointF pointF = new PointF(list.get(i).x, list.get(i).y);
                PointF pointF2 = new PointF();
                PointF pointF3 = new PointF();
                if (i == 0) {
                    pointF2.x = list.get(list.size() - 1).x;
                    pointF2.y = list.get(list.size() - 1).y;
                } else {
                    int i2 = i - 1;
                    pointF2.x = list.get(i2).x;
                    pointF2.y = list.get(i2).y;
                }
                if (i == list.size() - 1) {
                    pointF3.x = list.get(0).x;
                    pointF3.y = list.get(0).y;
                } else {
                    int i3 = i + 1;
                    pointF3.x = list.get(i3).x;
                    pointF3.y = list.get(i3).y;
                }
                double d = f;
                PointF findPointOnSegment = findPointOnSegment(pointF, pointF2, d);
                PointF findPointOnSegment2 = findPointOnSegment(pointF, pointF3, d);
                PointF findMiddlePoint = findMiddlePoint(findPointOnSegment, findPointOnSegment2, pointF);
                if (i == 0) {
                    path.moveTo(findPointOnSegment.x, findPointOnSegment.y);
                } else {
                    path.lineTo(findPointOnSegment.x, findPointOnSegment.y);
                }
                path.cubicTo(findPointOnSegment.x, findPointOnSegment.y, findMiddlePoint.x, findMiddlePoint.y, findPointOnSegment2.x, findPointOnSegment2.y);
            } else if (i == 0) {
                path.moveTo(list.get(i).x, list.get(i).y);
            } else {
                path.lineTo(list.get(i).x, list.get(i).y);
            }
        }
    }

    private static boolean containPoint(List<PointF> list, PointF pointF) {
        for (PointF pointF2 : list) {
            if (pointF2 == pointF) {
                return true;
            }
            if (pointF2.x == pointF.x && pointF2.y == pointF.y) {
                return true;
            }
        }
        return false;
    }

    public static Map<PointF, PointF[]> createPathWithCircleCorner(Path path, List<PointF> list, List<PointF> list2, float f) {
        int i;
        boolean z;
        PointF[] pointFArr;
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        path.reset();
        int i2 = 3;
        PointF[] pointFArr2 = {list.get(0), list.get(0), list.get(0)};
        ArrayList<PointF> jarvis = jarvis(list);
        PointF[] pointFArr3 = pointFArr2;
        int i3 = 0;
        while (i3 < list.size()) {
            if (f == 0.0f || list.size() < i2) {
                i = i3;
                if (i == 0) {
                    path.moveTo(list.get(i).x, list.get(i).y);
                } else {
                    path.lineTo(list.get(i).x, list.get(i).y);
                }
            } else {
                if (!((list2 == null || list2.size() <= 0) ? true : containPoint(list2, list.get(i3)))) {
                    if (i3 == 0) {
                        path.moveTo(list.get(i3).x, list.get(i3).y);
                    } else {
                        path.lineTo(list.get(i3).x, list.get(i3).y);
                    }
                    if (i3 == list.size() - 1) {
                        path.lineTo(pointFArr3[1].x, pointFArr3[1].y);
                    }
                    i = i3;
                } else {
                    Iterator<PointF> it = jarvis.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        } else if (it.next() == list.get(i3)) {
                            z = false;
                            break;
                        }
                    }
                    PointF pointF = new PointF(list.get(i3).x, list.get(i3).y);
                    PointF pointF2 = new PointF();
                    PointF pointF3 = new PointF();
                    if (i3 == 0) {
                        pointF2.x = list.get(list.size() - 1).x;
                        pointF2.y = list.get(list.size() - 1).y;
                    } else {
                        int i4 = i3 - 1;
                        pointF2.x = list.get(i4).x;
                        pointF2.y = list.get(i4).y;
                    }
                    if (i3 == list.size() - 1) {
                        pointF3.x = list.get(0).x;
                        pointF3.y = list.get(0).y;
                    } else {
                        int i5 = i3 + 1;
                        pointF3.x = list.get(i5).x;
                        pointF3.y = list.get(i5).y;
                    }
                    PointF[] pointFArr4 = new PointF[i2];
                    double[] dArr = new double[2];
                    i = i3;
                    createArc(pointF, pointF2, pointF3, f, dArr, pointFArr4, z);
                    if (i == 0) {
                        pointFArr = pointFArr4;
                        path.moveTo(pointFArr[1].x, pointFArr[1].y);
                    } else {
                        pointFArr = pointFArr4;
                        path.lineTo(pointFArr[1].x, pointFArr[1].y);
                    }
                    path.arcTo(new RectF(pointFArr[0].x - f, pointFArr[0].y - f, pointFArr[0].x + f, pointFArr[0].y + f), (float) dArr[0], (float) dArr[1], false);
                    if (i == 0) {
                        pointFArr3 = pointFArr;
                    }
                    if (i == list.size() - 1) {
                        path.lineTo(pointFArr3[1].x, pointFArr3[1].y);
                    }
                    hashMap.put(list.get(i), pointFArr);
                }
            }
            i3 = i + 1;
            i2 = 3;
        }
        return hashMap;
    }

    public static void createPathWithCircleCorner(Path path, List<PointF> list, float f) {
        boolean z;
        path.reset();
        ArrayList<PointF> jarvis = jarvis(list);
        PointF[] pointFArr = null;
        for (int i = 0; i < list.size(); i++) {
            if (f != 0.0f && list.size() >= 3) {
                Iterator<PointF> it = jarvis.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next() == list.get(i)) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                PointF pointF = new PointF(list.get(i).x, list.get(i).y);
                PointF pointF2 = new PointF();
                PointF pointF3 = new PointF();
                if (i == 0) {
                    pointF2.x = list.get(list.size() - 1).x;
                    pointF2.y = list.get(list.size() - 1).y;
                } else {
                    int i2 = i - 1;
                    pointF2.x = list.get(i2).x;
                    pointF2.y = list.get(i2).y;
                }
                if (i == list.size() - 1) {
                    pointF3.x = list.get(0).x;
                    pointF3.y = list.get(0).y;
                } else {
                    int i3 = i + 1;
                    pointF3.x = list.get(i3).x;
                    pointF3.y = list.get(i3).y;
                }
                PointF[] pointFArr2 = new PointF[3];
                double[] dArr = new double[2];
                createArc(pointF, pointF2, pointF3, f, dArr, pointFArr2, z);
                if (i == 0) {
                    path.moveTo(pointFArr2[1].x, pointFArr2[1].y);
                } else {
                    path.lineTo(pointFArr2[1].x, pointFArr2[1].y);
                }
                path.arcTo(new RectF(pointFArr2[0].x - f, pointFArr2[0].y - f, pointFArr2[0].x + f, pointFArr2[0].y + f), (float) dArr[0], (float) dArr[1], false);
                if (i == 0) {
                    pointFArr = pointFArr2;
                }
                if (i == list.size() - 1) {
                    path.lineTo(pointFArr[1].x, pointFArr[1].y);
                }
            } else if (i == 0) {
                path.moveTo(list.get(i).x, list.get(i).y);
            } else {
                path.lineTo(list.get(i).x, list.get(i).y);
            }
        }
    }

    public static PointF findPointOnSegment(PointF pointF, PointF pointF2, double d) {
        if (d == 0.0d) {
            return new PointF(pointF.x, pointF.y);
        }
        PointF pointF3 = new PointF();
        double sqrt = (float) Math.sqrt(((pointF.x - pointF2.x) * (pointF.x - pointF2.x)) + ((pointF.y - pointF2.y) * (pointF.y - pointF2.y)));
        double abs = (Math.abs(pointF.x - pointF2.x) * d) / sqrt;
        double abs2 = (Math.abs(pointF.y - pointF2.y) * d) / sqrt;
        if (pointF.x > pointF2.x) {
            pointF3.x = (float) (pointF.x - abs);
        } else {
            pointF3.x = (float) (pointF.x + abs);
        }
        if (pointF.y > pointF2.y) {
            pointF3.y = (float) (pointF.y - abs2);
        } else {
            pointF3.y = (float) (pointF.y + abs2);
        }
        return pointF3;
    }

    public static PointF findMiddlePoint(PointF pointF, PointF pointF2, PointF pointF3) {
        return findMiddlePoint(pointF, pointF2, (float) (Math.sqrt(((pointF.x - pointF2.x) * (pointF.x - pointF2.x)) + ((pointF.y - pointF2.y) * (pointF.y - pointF2.y))) / 2.0d), pointF3);
    }

    public static PointF findMiddlePoint(PointF pointF, PointF pointF2, float f, PointF pointF3) {
        float f2 = pointF2.y - pointF.y;
        float f3 = pointF.x - pointF2.x;
        float f4 = (pointF2.x * pointF.y) - (pointF.x * pointF2.y);
        PointF[] findMiddlePoint = findMiddlePoint(pointF, pointF2, f);
        if (((pointF3.x * f2) + (pointF3.y * f3) + f4) * ((f2 * findMiddlePoint[0].x) + (f3 * findMiddlePoint[0].y) + f4) > Float.MIN_VALUE) {
            return findMiddlePoint[0];
        }
        return findMiddlePoint[1];
    }

    public static boolean createArc(PointF pointF, PointF pointF2, PointF pointF3, float f, double[] dArr, PointF[] pointFArr, boolean z) {
        pointFArr[0] = findPointOnBisector(pointF, pointF2, pointF3, f);
        double sqrt = Math.sqrt((((pointF.x - pointFArr[0].x) * (pointF.x - pointFArr[0].x)) + ((pointF.y - pointFArr[0].y) * (pointF.y - pointFArr[0].y))) - (f * f));
        pointFArr[1] = findPointOnSegment(pointF, pointF2, sqrt);
        pointFArr[2] = findPointOnSegment(pointF, pointF3, sqrt);
        double acos = Math.acos(f / Math.sqrt(((pointF.x - pointFArr[0].x) * (pointF.x - pointFArr[0].x)) + ((pointF.y - pointFArr[0].y) * (pointF.y - pointFArr[0].y))));
        double atan2 = Math.atan2(pointFArr[1].y - pointFArr[0].y, pointFArr[1].x - pointFArr[0].x);
        double atan22 = Math.atan2(pointFArr[2].y - pointFArr[0].y, pointFArr[2].x - pointFArr[0].x) - atan2;
        if (!z) {
            atan22 = acos * 2.0d;
        }
        dArr[0] = Math.toDegrees(atan2);
        dArr[1] = Math.toDegrees(atan22);
        double degrees = Math.toDegrees(acos * 2.0d);
        if (Math.abs(degrees - Math.abs(dArr[1])) > 1.0d) {
            dArr[1] = -degrees;
        }
        return false;
    }

    public static PointF findPointOnBisector(PointF pointF, PointF pointF2, PointF pointF3, float f) {
        double[] coefficients = getCoefficients(pointF, pointF2);
        double[] coefficients2 = getCoefficients(pointF, pointF3);
        double d = (coefficients2[0] * pointF2.x) + (coefficients2[1] * pointF2.y) + coefficients2[2];
        double d2 = (coefficients[0] * pointF3.x) + (coefficients[1] * pointF3.y) + coefficients[2];
        double d3 = coefficients[0];
        double d4 = coefficients[1];
        double sqrt = Math.sqrt((d3 * d3) + (d4 * d4));
        double d5 = coefficients2[0];
        double d6 = coefficients2[1];
        double sqrt2 = Math.sqrt((d5 * d5) + (d6 * d6));
        if (d2 > 0.0d) {
            if (d > 0.0d) {
                double d7 = f;
                return findIntersectPoint(coefficients[0], coefficients[1], (sqrt * d7) - coefficients[2], coefficients2[0], coefficients2[1], (d7 * sqrt2) - coefficients2[2]);
            }
            double d8 = f;
            return findIntersectPoint(coefficients[0], coefficients[1], (sqrt * d8) - coefficients[2], -coefficients2[0], -coefficients2[1], (sqrt2 * d8) + coefficients2[2]);
        } else if (d > 0.0d) {
            double d9 = f;
            return findIntersectPoint(-coefficients[0], -coefficients[1], (sqrt * d9) + coefficients[2], coefficients2[0], coefficients2[1], (d9 * sqrt2) - coefficients2[2]);
        } else {
            double d10 = f;
            return findIntersectPoint(-coefficients[0], -coefficients[1], (sqrt * d10) + coefficients[2], -coefficients2[0], -coefficients2[1], (d10 * sqrt2) + coefficients2[2]);
        }
    }

    public static double distanceToLine(double[] dArr, PointF pointF) {
        double d = dArr[0];
        double d2 = dArr[1];
        return Math.abs((((dArr[0] * pointF.x) + (dArr[1] * pointF.y)) + dArr[2]) / Math.sqrt((d * d) + (d2 * d2)));
    }

    public static PointF shrinkPoint(PointF pointF, PointF pointF2, PointF pointF3, float f, float f2, boolean z, boolean z2) {
        double[] coefficients = getCoefficients(pointF, pointF2);
        double[] coefficients2 = getCoefficients(pointF, pointF3);
        double d = coefficients[0];
        double d2 = coefficients[1];
        double sqrt = (f * Math.sqrt((d * d) + (d2 * d2))) - coefficients[2];
        double d3 = coefficients2[0];
        double d4 = coefficients2[1];
        double sqrt2 = (f2 * Math.sqrt((d3 * d3) + (d4 * d4))) - coefficients2[2];
        double d5 = coefficients[0];
        double d6 = coefficients[1];
        double sqrt3 = ((-f) * Math.sqrt((d5 * d5) + (d6 * d6))) - coefficients[2];
        double d7 = coefficients2[0];
        double d8 = coefficients2[1];
        double sqrt4 = ((-f2) * Math.sqrt((d7 * d7) + (d8 * d8))) - coefficients2[2];
        PointF findIntersectPoint = findIntersectPoint(coefficients[0], coefficients[1], sqrt, coefficients2[0], coefficients2[1], sqrt2);
        PointF findIntersectPoint2 = findIntersectPoint(coefficients[0], coefficients[1], sqrt, coefficients2[0], coefficients2[1], sqrt4);
        PointF findIntersectPoint3 = findIntersectPoint(coefficients[0], coefficients[1], sqrt3, coefficients2[0], coefficients2[1], sqrt2);
        PointF findIntersectPoint4 = findIntersectPoint(coefficients[0], coefficients[1], sqrt3, coefficients2[0], coefficients2[1], sqrt4);
        if (testShrunkPoint(coefficients, coefficients2, pointF2, pointF3, findIntersectPoint, z, z2)) {
            return findIntersectPoint;
        }
        if (testShrunkPoint(coefficients, coefficients2, pointF2, pointF3, findIntersectPoint2, z, z2)) {
            return findIntersectPoint2;
        }
        if (testShrunkPoint(coefficients, coefficients2, pointF2, pointF3, findIntersectPoint3, z, z2)) {
            return findIntersectPoint3;
        }
        if (testShrunkPoint(coefficients, coefficients2, pointF2, pointF3, findIntersectPoint4, z, z2)) {
            return findIntersectPoint4;
        }
        return null;
    }

    private static boolean testShrunkPoint(double[] dArr, double[] dArr2, PointF pointF, PointF pointF2, PointF pointF3, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        if (pointF3 != null && pointF3.x < Float.MAX_VALUE && pointF3.y < Float.MAX_VALUE) {
            double d = ((dArr[0] * pointF3.x) + (dArr[1] * pointF3.y) + dArr[2]) * ((dArr[0] * pointF2.x) + (dArr[1] * pointF2.y) + dArr[2]);
            double d2 = ((dArr2[0] * pointF3.x) + (dArr2[1] * pointF3.y) + dArr2[2]) * ((dArr2[0] * pointF.x) + (dArr2[1] * pointF.y) + dArr2[2]);
            boolean z5 = d > Double.MIN_VALUE;
            if (d2 > Double.MIN_VALUE) {
                z3 = z2;
                z4 = true;
            } else {
                z3 = z2;
                z4 = false;
            }
            if (z5 == z3 && z4 == z) {
                return true;
            }
        }
        return false;
    }

    public static PointF findIntersectPoint(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = (d * d5) - (d2 * d4);
        double d8 = (d5 * d3) - (d2 * d6);
        double d9 = (d * d6) - (d3 * d4);
        int i = (d7 > 0.0d ? 1 : (d7 == 0.0d ? 0 : -1));
        if (i == 0 && d8 == 0.0d) {
            return new PointF(Float.MAX_VALUE, Float.MAX_VALUE);
        }
        if (i != 0 || d8 == 0.0d) {
            return new PointF((float) (d8 / d7), (float) (d9 / d7));
        }
        return null;
    }

    public static double[] findBisector(PointF pointF, PointF pointF2, PointF pointF3) {
        double[] coefficients = getCoefficients(pointF, pointF2);
        double[] coefficients2 = getCoefficients(pointF, pointF3);
        double d = coefficients[0];
        double d2 = coefficients[1];
        double sqrt = Math.sqrt((d * d) + (d2 * d2));
        double d3 = coefficients2[0];
        double d4 = coefficients2[1];
        double sqrt2 = Math.sqrt((d3 * d3) + (d4 * d4));
        double d5 = coefficients[0];
        double d6 = coefficients2[0];
        double d7 = (d5 / sqrt) + (d6 / sqrt2);
        double d8 = coefficients[1];
        double d9 = coefficients2[1];
        double d10 = (d8 / sqrt) + (d9 / sqrt2);
        double d11 = coefficients[2];
        double d12 = coefficients2[2];
        double d13 = (d11 / sqrt) + (d12 / sqrt2);
        return (((((double) pointF2.x) * d7) + (((double) pointF2.y) * d10)) + d13) * (((((double) pointF3.x) * d7) + (((double) pointF3.y) * d10)) + d13) > Double.MIN_VALUE ? new double[]{(d5 / sqrt) - (d6 / sqrt2), (d8 / sqrt) - (d9 / sqrt2), (d11 / sqrt) - (d12 / sqrt2)} : new double[]{d7, d10, d13};
    }

    public static double[] getCoefficients(PointF pointF, PointF pointF2) {
        return new double[]{pointF2.y - pointF.y, pointF.x - pointF2.x, (pointF2.x * pointF.y) - (pointF.x * pointF2.y)};
    }

    public static PointF[] findMiddlePoint(PointF pointF, PointF pointF2, float f) {
        PointF[] pointFArr = new PointF[2];
        float f2 = pointF2.x - pointF.x;
        float f3 = pointF2.y - pointF.y;
        float f4 = (pointF2.x + pointF.x) / 2.0f;
        float f5 = (pointF2.y + pointF.y) / 2.0f;
        if (f2 == 0.0f) {
            pointFArr[0] = new PointF(pointF.x + f, f5);
            pointFArr[1] = new PointF(pointF.x - f, f5);
        } else if (f3 == 0.0f) {
            pointFArr[0] = new PointF(f4, pointF.y + f);
            pointFArr[1] = new PointF(f4, pointF.y - f);
        } else {
            float sqrt = (float) (f / Math.sqrt(((f3 * f3) / (f2 * f2)) + 1.0f));
            float f6 = (f3 / f2) * sqrt;
            pointFArr[0] = new PointF(f4 - f6, f5 + sqrt);
            pointFArr[1] = new PointF(f4 + f6, f5 - sqrt);
        }
        return pointFArr;
    }

    public static boolean CCW(PointF pointF, PointF pointF2, PointF pointF3) {
        return ((((int) pointF2.y) - ((int) pointF.y)) * (((int) pointF3.x) - ((int) pointF2.x))) - ((((int) pointF2.x) - ((int) pointF.x)) * (((int) pointF3.y) - ((int) pointF2.y))) < 0;
    }

    public static ArrayList<PointF> jarvis(List<PointF> list) {
        ArrayList<PointF> arrayList = new ArrayList<>();
        int size = list.size();
        if (size < 3) {
            for (PointF pointF : list) {
                arrayList.add(pointF);
            }
            return arrayList;
        }
        int[] iArr = new int[size];
        Arrays.fill(iArr, -1);
        int i = 0;
        for (int i2 = 1; i2 < size; i2++) {
            if (((int) list.get(i2).x) < ((int) list.get(i).x)) {
                i = i2;
            }
        }
        int i3 = i;
        while (true) {
            int i4 = (i3 + 1) % size;
            for (int i5 = 0; i5 < size; i5++) {
                if (CCW(list.get(i3), list.get(i5), list.get(i4))) {
                    i4 = i5;
                }
            }
            iArr[i3] = i4;
            if (i4 == i) {
                break;
            }
            i3 = i4;
        }
        for (int i6 = 0; i6 < size; i6++) {
            if (iArr[i6] != -1) {
                arrayList.add(list.get(i6));
            }
        }
        return arrayList;
    }
}
