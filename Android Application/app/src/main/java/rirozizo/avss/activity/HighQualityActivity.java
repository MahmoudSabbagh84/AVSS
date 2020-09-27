package rirozizo.avss.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import rirozizo.avss.R;


public class HighQualityActivity extends AppCompatActivity {
    public static final String TAG = "IMAGER";
    File mypath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        setContentView(R.layout.activity_imager);
        ImageView iv = (ImageView) findViewById(R.id.imageview);

        Bitmap bitmap = null;
        FTPClient con = new FTPClient();
        try {
            con.connect("192.168.0.101", 6000);
            if (con.login("pi", "pipipass")) {
                con.enterLocalPassiveMode();
                con.setFileType(FTP.BINARY_FILE_TYPE);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                boolean result = con.retrieveFile("image.jpg", out);
                out.close();
                if (result) Log.v(TAG, "download succeeded");
                con.logout();
                con.disconnect();
                Log.v(TAG, "disconnected");
                byte[] bitmapdata = out.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
                long time= System.currentTimeMillis();
                mypath = new File(Environment.getExternalStorageDirectory().getPath(), "avss-"+time+".jpg");

                FileOutputStream fos = null;
                Log.v(TAG, "File Output Stream initialized");
                try {
                    fos = new FileOutputStream(mypath);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    Log.v(TAG, "Image has been saved to Internal Storage");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            Log.v("download result", "failed");
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(mypath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String mime = "*/*";
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        if (mimeTypeMap.hasExtension(
                mimeTypeMap.getFileExtensionFromUrl(uri.toString())))
            mime = mimeTypeMap.getMimeTypeFromExtension(
                    mimeTypeMap.getFileExtensionFromUrl(uri.toString()));
        intent.setDataAndType(uri, mime);
        startActivity(intent);
        finish();
    }
}
