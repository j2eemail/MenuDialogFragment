package com.lxs.menudialogfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MenuDialogFragment menuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuDialogFragment = new MenuDialogFragment();
    }

    public void menuDialogFragmentOnClick(View view) {
        menuDialogFragment.setMenu(new String[]{"按钮一", "按钮二", "按钮三", "按钮四"});
        menuDialogFragment.setCancelable(false);
        menuDialogFragment.setMenuOnItemClickListener(new MenuDialogFragment.MenuOnItemClickListener() {
            @Override
            public void menuOnItemClick(Button btn, int position) {
                Toast.makeText(getApplicationContext(), btn.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        menuDialogFragment.show(getSupportFragmentManager(), "menu");
    }
}
