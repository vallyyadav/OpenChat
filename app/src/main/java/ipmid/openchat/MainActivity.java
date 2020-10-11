package ipmid.openchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNumber, countryCode, editMessage;
    Button button, writeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = findViewById(R.id.noValue);
        countryCode = findViewById(R.id.countryCode);
        editMessage = findViewById(R.id.message);
        button = findViewById(R.id.submit);
        writeMsg = findViewById(R.id.openMessage);

        writeMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMessage.setVisibility(editMessage.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        Window window = this.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        parseData();

    }

    private void parseData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = countryCode.getText().toString().trim() + editNumber.getText().toString().trim();
                String msg = editMessage.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNo)) {
                    Toast.makeText(MainActivity.this, "Enter Value", Toast.LENGTH_SHORT).show();
                } else {

                    Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNo + "&text=" + msg);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }

            }
        });
    }
}