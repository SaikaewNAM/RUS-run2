package appewtc.masterung.rusrun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit การประกาศตัวแปร
    private EditText nameEditText, userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton avata0RadioButton, avata1RadioButton, avata2RadioButton,
            avata3RadioButton, avata4RadioButton;
    private String nameString, userString, passwordString, AvataString;
    private static final String urlPHP = "http://swiftcodingthai.com/rus/add_user_master.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget การผูกตัวแปร
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        radioGroup = (RadioGroup) findViewById(R.id.radAvata);
        avata0RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avata1RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avata2RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avata3RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avata4RadioButton = (RadioButton) findViewById(R.id.radioButton5);

        //Radio Controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

                    case R.id.radioButton:
                        AvataString = "0";
                        break;
                    case R.id.radioButton2:
                        AvataString = "1";
                        break;
                    case R.id.radioButton3:
                        AvataString = "2";
                        break;
                    case R.id.radioButton4:
                        AvataString = "3";
                        break;
                    case R.id.radioButton5:
                        AvataString = "4";
                        break;

                }//switch

            }
        });


    }//Main Method


    public void clickSignUpSign(View view) {

        //Get Value From Edit Text เอาค่าที่เกิดจากการกรอกไปใส่ในตัวแปร
        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space การค้นหาช่องว่าง
        if (nameString.equals("") || userString.equals("") || passwordString.equals("")){

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง ค่ะ");

        } else if (checkChoose()) {
            //Check
            updateNewUserToServer();
        } else {
            //Un Check
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ยังไม่เลือก Avata", "กรุณาเลือก Avata ด้วยค่ะ");
        }





    }//clickSignUp

    private void updateNewUserToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("Avata", AvataString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                finish();
            }
        });

    }//update

    private boolean checkChoose() {

        boolean status = true;

        status = avata0RadioButton.isChecked() ||
        avata1RadioButton.isChecked() ||
        avata2RadioButton.isChecked() ||
        avata3RadioButton.isChecked() ||
        avata4RadioButton.isChecked();


        return status;
    }
}//Main Class
