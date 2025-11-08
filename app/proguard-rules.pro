# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep your custom Application class
-keep class mil.nga.geopackage.srs.SpatialReferenceSystemDao.**{ *; }

-keep class chin.pswm.gps.photo.location.map.activity.first_open.nav.**{ *; }
-keep class com.google.android.gms.** { *; }
-keep class com.google.firebase.** { *; }

-keep class mil.nga.geopackage.srs.SpatialReferenceSystemDao
-keep class chin.pswm.gps.photo.location.map_debug.MyApplication {
    <init>();
}

-keep public class mil.nga.geopackage.srs.SpatialReferenceSystemDao {
    public *;
}
-keep class org.json.**
-keepclassmembers,includedescriptorclasses class org.json.** { *; }
-keep class kotlin.Metadata
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type
# Keep `Companion` object fields of serializable classes.
# This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
   static <1>$Companion Companion;
}

# Keep `serializer()` on companion objects (both default and named) of serializable classes.
-if @kotlinx.serialization.Serializable class ** {
   static **$* *;
}
-keepclassmembers class <2>$<3> {
   kotlinx.serialization.KSerializer serializer(...);
}

# Keep `INSTANCE.serializer()` of serializable objects.
-if @kotlinx.serialization.Serializable class ** {
   public static ** INSTANCE;
}
-keepclassmembers class <1> {
   public static <1> INSTANCE;
   kotlinx.serialization.KSerializer serializer(...);
}

-dontwarn com.facebook.infer.annotation.Nullsafe$Mode
-dontwarn com.facebook.infer.annotation.Nullsafe

# Keep the GeoPackage library classes and their dependencies from being obfuscated
-keep class mil.nga.geopackage.** { *; }
-keep interface mil.nga.geopackage.** { *; }

# This rule is crucial for ORMLite's reflection-based DAO creation
-keep class com.j256.ormlite.dao.DaoManager { *; }
-keep class com.j256.ormlite.dao.** { *; }
-keep class com.j256.ormlite.field.** { *; }
-keep class com.j256.ormlite.table.** { *; }

# Explicitly keep the constructor for the SpatialReferenceSystemDao class
# This ensures ORMLite can find and call it using reflection
-keep class mil.nga.geopackage.srs.SpatialReferenceSystemDao {
    public <init>(com.j256.ormlite.support.ConnectionSource, java.lang.Class);
    public <init>(com.j256.ormlite.support.ConnectionSource);
}