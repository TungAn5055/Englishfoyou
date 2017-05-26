package com.github.hien.englishforyou.methods;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.github.hien.englishforyou.object.Sentence;
import com.github.hien.englishforyou.object.WordSave;
import com.github.hien.englishforyou.object.Words;

/**
 * Created by Administrator on 21/03/2017.
 */

public class MethodsExchange {
    WordSave wordSave;
    // convert from bitmap to byte array
    public  byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public  Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    // convert from Url to mp3
    public void downloadFile(String dwnload_file_path, String fileName,
                             String pathToSave) {
        int downloadedSize = 0;
        int totalSize = 0;
        try {
            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            File myDir;
            myDir = new File(pathToSave);
            myDir.mkdirs();
            String mFileName = fileName;
            File file = new File(myDir, mFileName);
            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            totalSize = urlConnection.getContentLength();
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }
            fileOutput.close();
        } catch ( Exception e) {
            System.out.println("dulietttt "+e.toString());
        }
    }
    // convert from mp3 to MediaPlayer
    public MediaPlayer getMusic(String s){
        String SDCardRoot = Environment.getExternalStorageDirectory()
                .toString();
        String audioFilePath = SDCardRoot + s;
        MediaPlayer mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(audioFilePath);

        } catch (IOException e) {

        }
        return mPlayer;
    }
    // chuyển dổi tư tim được về từ để luu trogn may
    public WordSave getWordSave(Words words, ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();// chuyenr ảnh lay vè từ mạng sang bitmap
        wordSave= new WordSave();//tao 1 từ lư trogn may
        wordSave.setImage(bitmap);//set gia trị cho anh của từ lutrong may
        wordSave.setWorld(words.getWord());//set tu tim dc
        // lay ra duong dan luu file am thanh o trong may cua tu tim duoc
        String SDCardRoot = Environment.getExternalStorageDirectory()
                .toString();
        new RetrieveFeedTask(words).execute();
        wordSave.setMusic( SDCardRoot + "/MyAudioFolder/"+words.getWord().trim()+".mp3");
        //chuyen tat ca cac thuoc tinh cua tu tim dc 1 doi tuong json
        String k="";
        for(Map.Entry<String, String> entry : words.getArrProperties().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            k=k+"{\"key\":\""+key+"\",\"va\":\""+value+"\"},";
        }
      k=k.substring(0, k.length()-1);
        k="s{\"arr\":["+k+"]}";
        wordSave.setInformation(k);
        //tra ve tu da chuyen doi
        return wordSave;
    }

//    public Sentence getSentence(Sentence sentence){
//
//        sentence= new Sentence();
//        sentence.setWord(sentence.getWord());
//        sentence.settranslate(sentence.gettranslate());
//        sentence.setUrlMiusic(sentence.getUrlMiusic());
//        return sentence;
//    }
    class RetrieveFeedTask extends AsyncTask<WordSave, String, String> {
       private Words words;

        public RetrieveFeedTask(Words save) {
            this.words = save;
        }

        public void downloadFile(String dwnload_file_path, String fileName,
                                 String pathToSave) {
            int downloadedSize = 0;
            int totalSize = 0;
            try {
                URL url = new URL(dwnload_file_path);
                HttpURLConnection urlConnection = (HttpURLConnection) url
                        .openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(false);
                urlConnection.connect();
                File myDir;
                myDir = new File(pathToSave);
                myDir.mkdirs();
                String mFileName = fileName;
                File file = new File(myDir, mFileName);
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                totalSize = urlConnection.getContentLength();
                byte[] buffer = new byte[1024];
                int bufferLength = 0;

                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }
                fileOutput.close();
            } catch ( Exception e) {
                System.out.println("dulietttt "+e.toString());
            }
        }


        @Override
        protected String doInBackground(WordSave... params) {
            String SDCardRoot = Environment.getExternalStorageDirectory()
                    .toString();
            downloadFile(words.getUrlMiusic(), words.getWord().trim()+".mp3",
                    SDCardRoot+"/MyAudioFolder");
            return null;
        }
    }
}
