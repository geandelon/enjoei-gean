package br.com.gean.enjoei.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gean on 13/12/2015.
 */
public class FontsOverride {
    private static final int BOLD = 1;
    private static final int ITALIC = 2;
    private static final int LIGHT = 4;
    private static final int CONDENSED = 4;
    private static final int THIN = 5;
    private static final int MEDIUM = 6;
    private static final int REGULAR = 7;
    private static final String MONTSERRAT_REGULAR = "ProximaNova-Regular.otf";
    private static final String MONTSERRAT_BOLD = "ProximaNova-Bold.otf";

    private Context context;

    public FontsOverride(Context context) {
        this.context = context;
    }

    public void loadFonts() {
        Map<String, Typeface> fontsMap = new HashMap<>();
        fontsMap.put("sans-serif", getTypeface(MONTSERRAT_REGULAR, REGULAR));
        fontsMap.put("sans-serif-bold", getTypeface(MONTSERRAT_BOLD, BOLD));
        fontsMap.put("sans-serif-italic", getTypeface(MONTSERRAT_REGULAR, ITALIC));
        fontsMap.put("sans-serif-light", getTypeface(MONTSERRAT_REGULAR, LIGHT));
        fontsMap.put("sans-serif-condensed", getTypeface(MONTSERRAT_REGULAR, CONDENSED));
        fontsMap.put("sans-serif-thin", getTypeface(MONTSERRAT_REGULAR, THIN));
        fontsMap.put("sans-serif-medium", getTypeface(MONTSERRAT_REGULAR, MEDIUM));
        overrideFonts(fontsMap);
    }

    private void overrideFonts(Map<String, Typeface> typefaces) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                final Field field = Typeface.class.getDeclaredField("sSystemFontMap");
                field.setAccessible(true);
                Map<String, Typeface> oldFonts = (Map<String, Typeface>) field.get(null);
                if (oldFonts != null) {
                    oldFonts.putAll(typefaces);
                } else {
                    oldFonts = typefaces;
                }
                field.set(null, oldFonts);
                field.setAccessible(false);
            } catch (Exception e) {
                Log.e("TypefaceUtil", "Cannot set custom fonts");
            }
        } else {
            setDefaultFont(context, "DEFAULT", MONTSERRAT_REGULAR);
            setDefaultFont(context, "MONOSPACE", MONTSERRAT_REGULAR);
            setDefaultFont(context, "SERIF", MONTSERRAT_REGULAR);
            setDefaultFont(context, "SANS_SERIF", MONTSERRAT_REGULAR);
        }
    }

    private Typeface getTypeface(String fontFileName, int fontType) {
        final Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontFileName);
        return Typeface.create(tf, fontType);
    }

    private static void setDefaultFont(Context context,
                                       String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    private static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
