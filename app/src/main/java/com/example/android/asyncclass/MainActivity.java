package com.example.android.asyncclass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.edit_text_main);
        mButton = (Button) findViewById(R.id.btn_run);
        mTextView = (TextView) findViewById(R.id.tv_result);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass myClass = new MyClass();
                String sleep = mEditText.getText().toString();
                myClass.execute(sleep);
            }
        });
    }

    public class MyClass extends AsyncTaskClass<String,String,String>{
        ProgressDialog mProgressDialog;
        String resp;
        @Override
        protected String onPreExecute() {
            mProgressDialog = ProgressDialog.show(MainActivity.this,"Progress Dialog",
                    "Waiting for " + mEditText.getText().toString() + " seconds");
            String str = mEditText.getText().toString();
            return str;
        }


        @Override
        protected String doInBackground(String s) {
            int time = Integer.parseInt(s)*1000;
            try {
                Thread.sleep(time);
                resp = "Slept for " + s + " seconds.";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgressDialog.dismiss();
            mTextView.setText(s);
        }
    }
}
