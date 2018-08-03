package com.brulex.socket_client_ui;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {


    private Button button;
    private TextView textView;

    private EditText address;
    private EditText port;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.Button_get_id);
        address = findViewById(R.id.hostname);
        port = findViewById(R.id.port);
        message = findViewById(R.id.to_send_message);
        textView = findViewById(R.id.textView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Socket socket = null;
                PrintWriter out = null;
                BufferedReader in = null;

                try {
                    socket = new Socket( address.getText().toString(), Integer.valueOf(port.getText().toString()));
                    out = new PrintWriter(socket.getOutputStream(),true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out.println(message.getText().toString());
                    textView.setText(in.readLine());
                }catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(socket != null){
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(out != null) {
                        out.close();
                    }
                    if(in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
