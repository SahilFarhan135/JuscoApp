package com.example.tatatest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.wifi.WifiManager;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
//import static com.example.tatatest.EmployeeLogin.getLocalIpAddress;

public class EmployeeSignup extends AppCompatActivity {


    public static boolean emailValidat(String str) {
        String reg = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(str);
        return matcher.matches();


    }


    public static boolean passValidat(String str1) {
        String reg = "(?=^.{6,10}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$";
        Pattern pp = Pattern.compile(reg);
        Matcher matcher = pp.matcher(str1);
        return matcher.matches();
    }


        private EditText username, Upassword, Ucnfpassword, Uemail, id1;
    private Button signup;
    private TextView logo;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reff = database.getReference();
    Member member;
    String id, name, merge;
    TelephonyManager tm,no,opt;
    String Imei,imei,no1,optname;
    public static int REQUEST_PERMISSION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_signup);

        // Toast.makeText(ActivitySignIN.this, "Firebase connection valid", Toast.LENGTH_SHORT).show();

        logo = findViewById(R.id.etVLOGO);
        id1 = findViewById(R.id.etVisitoR);
        username = findViewById(R.id.etName);
        Upassword = findViewById(R.id.etVPassword);
        Ucnfpassword = findViewById(R.id.etVCnfPassword);
        Uemail = findViewById(R.id.etVEmail);
        signup = findViewById(R.id.btVSIGNUP);
        member= new Member();

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!= PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},0);

        }
        else{
            TelephonyManager manager=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
            if(manager==null){
                Toast.makeText(this,"HardWare Not Supported",Toast.LENGTH_SHORT).show();
            }
            else{
                signup.setEnabled(true);
                imei=manager.getImei();
                 //no1=manager.getLine1Number();
                 //optname=manager.getNetworkOperatorName();
               // Toast.makeText(this,imei,Toast.LENGTH_SHORT).show();
               // Toast.makeText(this,no1,Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,optname,Toast.LENGTH_SHORT).show();

            }
        }
        //
       // public String getIMEI(EmployeeSignup.this){
           // T

       // }






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                 id = id1.getText().toString();
                 name=username.getText().toString();
                String password = Upassword.getText().toString();
                String cPassword = Ucnfpassword.getText().toString();
                String email = Uemail.getText().toString();
               // String ip=getLocalIpAddress();
                member.setIpAddress(imei);
                member.setId(id1.getText().toString().trim());
                member.setName(username.getText().toString());
                member.setPassword(Upassword.getText().toString().trim());
                member.setEmail(Uemail.getText().toString().trim());
                 merge=name+id;

                reff=FirebaseDatabase.getInstance().getReference().child("employee");


                if (id.isEmpty() || password.isEmpty() || email.isEmpty() || !password.equals(cPassword) || !emailValidat(email) ||!passValidat(password))
                    Toast.makeText(EmployeeSignup.this, " Please Enter correct details", Toast.LENGTH_LONG).show();

                else {
                    reff.child(id).setValue(member);
                    // reff.child(String.valueOf(maxid+1)).setValue("member");

                    Toast.makeText(EmployeeSignup.this, " Successfully submited your details", Toast.LENGTH_LONG).show();



                    openActivity2();

                }


               // Toast.makeText(EmployeeSignup.this, " Your Ip address is"+ip, Toast.LENGTH_LONG).show();

            }

            private void openActivity2() {

                Intent intent = new Intent(EmployeeSignup.this, EmployeeLogin.class);
                startActivity(intent);
            }



        });

    }


   /* public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }*/


}