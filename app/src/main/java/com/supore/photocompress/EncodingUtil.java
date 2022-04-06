package com.supore.photocompress;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

public class EncodingUtil {
    public static Bitmap createNormal(String content,int width,int height,Bitmap logo){
        try {
            if(content==null || "".equals(content)){
                return null;
            }
            Map<EncodeHintType,Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width*height];
            for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){
                    if(bitMatrix.get(x,y)){
                        pixels[y*width+x] = 0xff000000;
                    }else {
                        pixels[y*width+x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            if(logo!=null){
                bitmap = addLogo(bitmap,logo);
            }
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap addLogo(Bitmap src,Bitmap logo){
        if(src==null){
            return null;
        }
        int srcwidth = src.getWidth();
        int srcheight = src.getHeight();
        int logowidth = logo.getWidth();
        int logoheight = logo.getHeight();
        float scale = srcwidth*1.0f/5/logowidth;
        Bitmap bitmap = Bitmap.createBitmap(srcwidth,srcheight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src,0,0,null);
            canvas.scale(scale,scale,srcwidth/2,srcheight/2);
            canvas.drawBitmap(logo,(srcwidth-logowidth)/2,(srcheight-logoheight)/2,null);
            canvas.save();
            canvas.restore();
        }catch (Exception e){
            bitmap = null;
            e.printStackTrace();
        }
        return bitmap;
    }
}
