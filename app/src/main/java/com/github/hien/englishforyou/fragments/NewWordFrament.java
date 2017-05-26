package com.github.hien.englishforyou.fragments;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.data.DataFribase;
import com.github.hien.englishforyou.data.DataSQL;
import com.github.hien.englishforyou.methods.MethodsFribase;
import com.github.hien.englishforyou.methods.MethodsSQLite;
import com.github.hien.englishforyou.object.Words;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by Administrator on 20/03/2017.
 */

public class NewWordFrament extends Fragment {
    private View view;// se dung de anh xa
    private AutoCompleteTextView edtArrWord;// cho nguoi dung nhap tu can tim va dong thoi goi y tu cho nguoi dung
    private TextView txvMorals,//hiển thị nghĩa từ
            txvWord,//hiển thj từ
            txvWordws,// hiển thị từ đồn nghĩa
            txvSpecies,//hiển thị loại từ
            txvdta,//hiển thị ví dụ về danh từ (anh)
            txvdtv,//hiển thị ví dụ về danh từ (việt)
            txvddta,//hiển thị ví dụ về động từ (anh)
            txvddtv,//hiển thị ví dụ về động từ (việt)
            txvtta,//hiển thị ví dụ về tính từ (anh)
            txvttv;//hiển thị ví dụ về tính từ (việt)
    private ImageView imgMusic, //nút nghe phát âm của từ
            imgIllustration, //hình anh ví dụ
            saveMusic;// nút lưu lại từ
    private Button button2;// nút tìm kiếm từ
    private ArrayAdapter adapter;// adapter chuyen đổi từ để sét từ gợi ý cho AutoCompleteTextView
    private Words words;// biến "từ mói' hoạt động dưới dang con chỏ
    private MethodsFribase methods;// đối tượng để tương tác với Database online
    private MediaPlayer mediaPlayer;//đối tượng để chạy file am thanh
    private MethodsSQLite methodsSQLite;// đối tượng để lưu từ vào sqlite
    private boolean chayNhac = false;//đối tượng kiểm tra xem nhạc đã tải xong chưa
    private InputMethodManager inputManager;
    private LinearLayout linearLayout;

    public NewWordFrament() {}//contructer để nhận giá trị cho biến Context

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_new_word, container, false);// biến view đc gán giá trị và dc trả về cho fament hiển thị lên
        edtArrWord = (AutoCompleteTextView) view.findViewById(R.id.edtArrWord);
        txvMorals = (TextView) view.findViewById(R.id.txvMorals);
        txvWord = (TextView) view.findViewById(R.id.txvWord);
        txvWordws = (TextView) view.findViewById(R.id.txvWordws);
        txvSpecies = (TextView) view.findViewById(R.id.txvSpecies);

        txvdta = (TextView) view.findViewById(R.id.txvdta);
        txvdtv = (TextView) view.findViewById(R.id.txvdtv);

        txvddta = (TextView) view.findViewById(R.id.txvddta);
        txvddtv = (TextView) view.findViewById(R.id.txvddtv);

        txvtta = (TextView) view.findViewById(R.id.txvtta);
        txvttv = (TextView) view.findViewById(R.id.txvttv);

        imgMusic = (ImageView) view.findViewById(R.id.imgMusic);
        imgIllustration = (ImageView) view.findViewById(R.id.imgIllustration);
        saveMusic = (ImageView) view.findViewById(R.id.saveMusic);

        button2 = (Button) view.findViewById(R.id.button2);
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_all);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                inputManager = (InputMethodManager)getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edtArrWord.getWindowToken(), 0);
                return true;
            }
        });

        methods = new MethodsFribase();
        methodsSQLite = new MethodsSQLite(getContext());

        adapter = new ArrayAdapter(getContext(), R.layout.item_edt, R.id.textViewItem, DataFribase.getInstance().getStringArrayList());
        edtArrWord.setAdapter(adapter);
        edtArrWord.clearFocus();
        edtArrWord.setThreshold(1);

        //setclick cho btntim
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputManager = (InputMethodManager)getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edtArrWord.getWindowToken(), 0);
                button2.setEnabled(false);//khoa nut tai khong cho tim kiếm
                chayNhac = false;// khoa 2 nút chạy nhạc và nút luu lại
                words = methods.sechWord(edtArrWord.getText().toString());// ngf nhập vàolấy ra từ theo Autucomplete mà người du
                if (words == null) {
                    Toast.makeText(getContext(), "Từ bạn nhập không được tìm thấy" ,Toast.LENGTH_LONG).show();
                } else {
                    setWord();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //tao 1 thead de tai file âm thanh
                            while (true) {
                                try {
                                    mediaPlayer = new MediaPlayer();///khoi tao
                                    mediaPlayer.setDataSource(words.getUrlMiusic());// set link lấy file nhạc ve
                                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//set audio
                                    mediaPlayer.prepare();// khong rõ để làm ji nhưng thiếu nso thì trường trình ko chạy
                                    chayNhac = true;//mở biến chơi nhạc
                                    break;//thoát vòng lặp
                                } catch (IOException e) {
                                }

                            }
                        }
                    }).start();//bắt đâu run
                }
                button2.setEnabled(true);// mở khóa cho btn tìm de có thể tìm từ mới
            }

        });

        return view;
    }


    private void setWord() {
        //set ảnh dc từ URL dung thư viện Picasso
        Picasso.with(getContext()).load(words.getUrlIamge()).placeholder(R.drawable.load).error(R.drawable.biloi).into(imgIllustration);
        //hien thi tu ma nguwoi dung tra
        txvWord.setText(words.getWord());
        //hien thị nghĩa tư đó
        txvMorals.setText(words.getArrProperties().get("nghia"));
        //kiểm tra xem từ đó có từ đòng nghĩa hay không nếu có hiển thị không thì thôi
        if (words.getArrProperties().containsKey("tudongnghia")) {
            txvWordws.setText(words.getArrProperties().get("tudongnghia"));
        } else {
            txvWordws.setText(" ");
        }

        txvSpecies.setText(words.getArrProperties().get("loai"));
        txvdta.setText(words.getArrProperties().get("dta"));
        txvdtv.setText(words.getArrProperties().get("dtv"));
        txvddta.setText(words.getArrProperties().get("ddta"));
        txvddtv.setText(words.getArrProperties().get("ddtv"));
        txvtta.setText(words.getArrProperties().get("tta"));
        txvttv.setText(words.getArrProperties().get("ttv"));
        //set nút chơi nhạc
        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chayNhac) {//kiểm tra xem đươc phép chwoi nhạc chưa
                    if (!mediaPlayer.isPlaying()) {// kiểm tra xem có tôn tại file nhạc đang chyaj không
                        mediaPlayer.start();// chưa có thì stat
                    } else {
                        mediaPlayer.pause();// có rồi thì thôi
                    }
                } else {
                    Toast.makeText(getContext(), "Đang tải nhạc vui lòng lưu lại sau!", Toast.LENGTH_LONG).show();
                }
            }
        });
        saveMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!DataSQL.getInstance().checkWords(words.getWord())) {
                        methodsSQLite.addContact(words, imgIllustration);
                    } else {
                        Toast.makeText(getContext(), "Từ này bạn đã lưu!", Toast.LENGTH_LONG).show();
                    }
                    DataSQL.getInstance().setDataSqlite(getContext());
            }
        });
    }




}
