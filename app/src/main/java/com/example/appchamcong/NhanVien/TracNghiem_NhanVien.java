package com.example.appchamcong.NhanVien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.Answer;
import com.example.appchamcong.DTO.Question;
import com.example.appchamcong.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class TracNghiem_NhanVien extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_question;
    private TextView tv_content_question;
    private TextView tv_answer1,tv_answer2,tv_answer3,tv_answer4,ten_tk_tng;
    CircleImageView img_tk_tng;
    private List<Question> questionList;
    private Question mQuestion;
    private int currentQuestion = 0;
    private int cau = 1;
    private int dung=0;
    ImageView quaylai_tng;
    int idbo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac_nghiem_nhan_vien);
        AnhXa();
        CheckThongTin();

        Intent intent = getIntent();

        idbo = intent.getIntExtra("vong", 5);

        questionList = getListQuestion();
        if(questionList.isEmpty())
        {
            return;
        }
        Random random1 = new Random();
        Cursor cursor = BatDauActivity.database.Getdata("SELECT IDCH FROM CAUHOI WHERE IDBO = " + idbo + " ORDER BY IDCH DESC");
        cursor.moveToNext();
        int val = random1.nextInt(cursor.getCount() - 1);
        currentQuestion=val;
        setDataQuestion(questionList.get(currentQuestion));
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    private void CheckThongTin() {
        ten_tk_tng.setText(BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG());


        if (BatDauActivity.taiKhoanDTO.getHINHANH()!=null){
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_tk_tng.setImageBitmap(bitmap);
        }else {
            img_tk_tng.setImageResource(R.drawable.person);
        }
    }
    private void setDataQuestion(Question question) {
        if(question == null){
            return;
        }
        mQuestion = question;
        tv_answer1.setBackgroundResource(R.drawable.custom_cau);
        tv_answer2.setBackgroundResource(R.drawable.custom_cau);
        tv_answer3.setBackgroundResource(R.drawable.custom_cau);
        tv_answer4.setBackgroundResource(R.drawable.custom_cau);


        String titleQuestion = "Câu Hỏi " + cau;
        tv_question.setText(titleQuestion);
        tv_content_question.setText(question.getContent());
        tv_answer1.setText("a." +question.getAnswerList().get(0).getContent());
        tv_answer2.setText("b." +question.getAnswerList().get(1).getContent());
        tv_answer3.setText("c." +question.getAnswerList().get(2).getContent());
        tv_answer4.setText("d." +question.getAnswerList().get(3).getContent());
//        Toast.makeText(TracnghiemActivity.this, "sss : " + question.getAnswerList().get(0).getIsCorrect(), Toast.LENGTH_SHORT).show();


    }

    private void AnhXa() {
        quaylai_tng = findViewById(R.id.quaylai_tng);
        quaylai_tng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ten_tk_tng = findViewById(R.id.ten_tk_tng);
        img_tk_tng = findViewById(R.id.img_tk_tng);
        tv_question = findViewById(R.id.tv_question);
        tv_content_question = findViewById(R.id.tv_content_question);
        tv_answer1 = findViewById(R.id.tv_answer1);
        tv_answer2 = findViewById(R.id.tv_answer2);
        tv_answer3 = findViewById(R.id.tv_answer3);
        tv_answer4 = findViewById(R.id.tv_answer4);

        tv_answer1.setOnClickListener(this);
        tv_answer2.setOnClickListener(this);
        tv_answer3.setOnClickListener(this);
        tv_answer4.setOnClickListener(this);
    }
    private List<Question> getListQuestion(){
        List<Question> list = new ArrayList<>();

        Cursor cursor =BatDauActivity.database.Getdata("SELECT CAUHOI FROM DAPAN WHERE IDBO = " + idbo +" ORDER BY CAUHOI DESC");
        cursor.moveToNext();
        Cursor cursorbatdau =BatDauActivity.database.Getdata("SELECT CAUHOI FROM DAPAN WHERE IDBO = " + idbo);
        cursorbatdau.moveToNext();
        for(int i =cursorbatdau.getInt(0); i <= cursor.getInt(0);i++){
            List<Answer> answerList1 = new ArrayList<>();
            Cursor cursor1 = BatDauActivity.database.Getdata("SELECT * FROM DAPAN WHERE CAUHOI = " + i + " AND IDBO = " +idbo );
            Cursor cursor2 = BatDauActivity.database.Getdata("SELECT * FROM CAUHOI WHERE IDCH = " + i + " AND IDBO = " +idbo);
            while (cursor1.moveToNext())
            {
                answerList1.add(new Answer(
                        cursor1.getString(1),
                        cursor1.getString(3)
                ));
            }
            while (cursor2.moveToNext()){
                list.add(new Question(
                        cursor2.getString(1),answerList1));
            }

        }

        return list;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_answer1:
                tv_answer1.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer1,mQuestion,mQuestion.getAnswerList().get(0));
                break;
            case R.id.tv_answer2:
                tv_answer2.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer2,mQuestion,mQuestion.getAnswerList().get(1));
                break;
            case R.id.tv_answer3:
                tv_answer3.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer3,mQuestion,mQuestion.getAnswerList().get(2));

                break;
            case R.id.tv_answer4:
                tv_answer4.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer4,mQuestion,mQuestion.getAnswerList().get(3));

                break;
        }
    }
    private void checkAnswer(TextView textView,Question question,Answer answer){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(answer.getIsCorrect().equals("true")){
                    textView.setBackgroundResource(R.drawable.custom_cautraloi_dung);
                    dung = dung +1;
                    Toast.makeText(TracNghiem_NhanVien.this, " Số câu đúng: " + dung , Toast.LENGTH_SHORT).show();

                    nextQuestion();

                }
                else
                {
                    textView.setBackgroundResource(R.drawable.custom_cautraloi_sai);
                    showAnswerCorrect(questionList.get(currentQuestion));
                    nextQuestion();
                }

            }
        },500);
    }
    private void showAnswerCorrect(Question question) {
        if(question==null || question.getAnswerList() == null || question.getAnswerList().isEmpty()){
            return;
        }

        if(question.getAnswerList().get(0).isCorrect().equals("true")){
            tv_answer1.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }else if(question.getAnswerList().get(1).isCorrect().equals("true")){
            tv_answer2.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }else if(question.getAnswerList().get(2).isCorrect().equals("true")){
            tv_answer3.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }else {
            tv_answer4.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }
    }

    private void nextQuestion() {
        cau++;
        int cautoida = 0;
        if (idbo == 1)
        {
            cautoida = 15;
        }else if (idbo == 2)
        {
            cautoida =5;
        } else if (idbo == 3)
        {
            cautoida =5;
        }else if (idbo == 4)
        {
            cautoida = 10;
        }
        if(cau > cautoida){
            showDialog();
        }else {

            Random random1 = new Random();
            Cursor cursor = BatDauActivity.database.Getdata("SELECT IDCH FROM CAUHOI WHERE IDBO = " + idbo +" ORDER BY IDCH DESC");
            cursor.moveToNext();
            int val = random1.nextInt(cursor.getCount() - 1);
//            Toast.makeText(TracnghiemActivity.this, "so ngau nhien " + val, Toast.LENGTH_SHORT).show();
            currentQuestion = val;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    setDataQuestion(questionList.get(currentQuestion));
                    setDataQuestion(questionList.get(currentQuestion));

                }
            },1000);

        }

    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.chucmung,null);
        final TextView txt_diem = view.findViewById(R.id.txt_diem);
        int idtk = BatDauActivity.taiKhoanDTO.getMATK();

        BatDauActivity.database.INSRERTSOCAU(idtk,idbo,dung);
        if (idbo == 1)
        {
            txt_diem.setText(String.valueOf(dung) + "/15");
        }else if (idbo == 2)
        {
            txt_diem.setText(String.valueOf(dung) + "/5");
        } else if (idbo == 3)
        {
            txt_diem.setText(String.valueOf(dung) + "/5");
        }else if (idbo == 4)
        {
            txt_diem.setText(String.valueOf(dung) + "/10");
        }

        builder.setView(view);
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(TracNghiem_NhanVien.this, Game_NhanVien.class));

            }
        },5000);
    }

}