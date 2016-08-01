package com.lxs.menudialogfragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Description：
 * User: lixishuang
 * Date: 2016-07-29
 * Time: 09:24
 */
public class MenuDialogFragment extends DialogFragment implements View.OnClickListener {

    private MenuOnItemClickListener menuOnItemClickListener;
    private String[] array;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.menuViewAnim);
        View view = inflater.inflate(R.layout.menu_view, ((ViewGroup) window.findViewById(android.R.id.content)), false);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //取消按钮
        view.findViewById(R.id.menu_view_btn_cancel).setOnClickListener(this);
        ListView listview = (ListView) view.findViewById(R.id.menu_listview);
        MenuAdapter menuAdapter = new MenuAdapter(getContext(), array);
        listview.setAdapter(menuAdapter);
        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        array = null;
        menuOnItemClickListener = null;
    }

    public void setMenu(String[] array) {
        this.array = array;
    }

    public interface MenuOnItemClickListener {
        public void menuOnItemClick(Button btn, int position);
    }

    public void setMenuOnItemClickListener(MenuOnItemClickListener menuOnItemClickListener) {
        this.menuOnItemClickListener = menuOnItemClickListener;
    }

    public class MenuAdapter extends BaseAdapter {


        private LayoutInflater layoutInflater;
        private Context context;
        private String[] array;

        public MenuAdapter(Context context, String[] array) {
            layoutInflater = LayoutInflater.from(context);
            this.context = context;
            this.array = array;
        }

        @Override
        public int getCount() {
            return array.length;
        }

        @Override
        public String getItem(int position) {
            return array[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Holder holder = null;
            if (view == null) {
                view = layoutInflater.inflate(R.layout.menu_item_view, parent, false);
                holder = new Holder();
                holder.btn = (Button) view.findViewById(R.id.menu_item_view_btn);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            holder.btn.setText(array[position]);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuOnItemClickListener != null) {
                        menuOnItemClickListener.menuOnItemClick((Button) v, position);
                        dismiss();
                    } else {
                        Log.d("MenuDialogFragment", "must implement MenuOnItemClickListener");
                    }
                }
            });
            return view;
        }

        public class Holder {
            public Button btn;
        }
    }
}