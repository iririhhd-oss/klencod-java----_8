package com.az4oi.tools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // الخلفية سوداء
        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(Color.parseColor("#050505"));

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 60, 40, 60);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        // عنوان التطبيق
        TextView titleText = new TextView(this);
        titleText.setText("Z-04I PYTHON TOOLS");
        titleText.setTextColor(Color.parseColor("#00FF66"));
        titleText.setTextSize(24);
        titleText.setGravity(Gravity.CENTER);
        titleText.setPadding(0, 0, 0, 20);
        layout.addView(titleText);

        // قناة التليجرام
        TextView channelText = new TextView(this);
        channelText.setText("📢 Channel: t.me/az_4oi");
        channelText.setTextColor(Color.parseColor("#00CC55"));
        channelText.setTextSize(14);
        channelText.setGravity(Gravity.CENTER);
        channelText.setPadding(0, 0, 0, 40);
        layout.addView(channelText);

        // زر التليجرام
        Button btnTelegram = createGreenButton("📢 زيارة قناة التليجرام");
        btnTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://t.me/az_4oi");
            }
        });
        layout.addView(btnTelegram);

        addSeparator(layout);

        // أزرار الأدوات التي تشغل السكربت فوراً في البايثون
        addButtonWithDirectPython(layout, "1. أداة تيك توك", "TikTok.py");
        addButtonWithDirectPython(layout, "2. جلب معلومات انستغرام", "lnstagram-login.py");
        addButtonWithDirectPython(layout, "3. يوزرات انستغرام مميزة", "يوزرات انستا مميزه (1).py");
        addButtonWithDirectPython(layout, "4. أداة التحميل والملفات", "10 اسيا حك ثكيل .py");
        addButtonWithDirectPython(layout, "5. فيس بوك ربط ألعاب", "فيس.py");

        scrollView.addView(layout);
        setContentView(scrollView);
    }

    private Button createGreenButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextColor(Color.parseColor("#00FF66"));
        button.setBackgroundColor(Color.parseColor("#111111"));
        button.setTextSize(16);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 15, 0, 15);
        button.setLayoutParams(params);
        return button;
    }

    private void addButtonWithDirectPython(LinearLayout parent, String title, final String scriptName) {
        Button btn = createGreenButton(title);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runInPydroid(scriptName);
            }
        });
        parent.addView(btn);
    }

    // تشغيل الملف مباشرة داخل Pydroid 3
    private void runInPydroid(String fileName) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), fileName);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "text/x-python");
            
            // التوجيه المباشر لتطبيق Pydroid 3
            intent.setPackage("ru.iiec.pydroid3");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        } catch (Exception e) {
            // في حال عدم وجود Pydroid 3، يفتح القائمة العامة للتطبيقات
            try {
                File file = new File(Environment.getExternalStorageDirectory(), fileName);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "text/x-python");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                Toast.makeText(this, "تأكد من وجود تطبيق Pydroid 3 وملف الأداة في الذاكرة!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void addSeparator(LinearLayout layout) {
        View line = new View(this);
        line.setBackgroundColor(Color.parseColor("#00FF66"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 2
        );
        params.setMargins(0, 30, 0, 30);
        layout.addView(line, params);
    }
}
