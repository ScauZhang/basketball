package com.example.administrator.myapplication.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.example.administrator.myapplication.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Communicate_ListView_Adapter extends BaseAdapter {
    private static final int NO_PHOTO = 0;
    private static final int SINGLE_PHOTO = 1;
    private static final int MULTIPLE_PHOTO = 2;
    private static final int SINGLE_VIDEO = 3;
    public static final String MAP_SORT = "sort";
    public static final String MAP_HEAD_URL = "head_url";
    public static final String MAP_NAME = "name";
    public static final String MAP_TEXT = "text";
    public static final String MAP_PIC_LIST = "pic_list";
    public static final String MAP_VIDEO_URL = "video_url";

    public final class ListItemView{
        public ImageView imageVieww_head;
        public TextView textView_name;
        public TextView textView_text;
        public LinearLayout linearLayout_add;
    }

    private Context context;                        //运行上下文
//    Map  sort(0无图,1单图,2多图,3视频)  head_url  name  text  pic_list video_url;
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;

    public Communicate_ListView_Adapter(Context context, List<Map<String, Object>> listItems){
        this.context = context;
        this.listItems = listItems;
        this.listContainer = LayoutInflater.from(context);
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     //     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     //     */
    private static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String,Object> item = listItems.get(position);
        ListItemView  listItemView = null;
        if(convertView == null){
            convertView = listContainer.inflate(R.layout.communicate_listview_item1,null);
            listItemView = new ListItemView();
            listItemView.imageVieww_head = (ImageView)convertView.findViewById(R.id.head_view);
            listItemView.textView_name = (TextView)convertView.findViewById(R.id.name);
            listItemView.textView_text = (TextView)convertView.findViewById(R.id.text);
            listItemView.linearLayout_add = (LinearLayout)convertView.findViewById(R.id.add);
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView)convertView.getTag();
        }

        listItemView.imageVieww_head.setImageResource(R.mipmap.headpic);  //先填本地
        listItemView.textView_name.setText((String)item.get(MAP_NAME));
        listItemView.textView_text.setText((String)item.get(MAP_TEXT));
        listItemView.linearLayout_add.removeAllViews();

        int sort = (int)item.get(MAP_SORT);
        switch (sort){
            case NO_PHOTO:
                listItemView.linearLayout_add.setVisibility(View.GONE);
                break;
            case SINGLE_PHOTO:
                LinearLayout.LayoutParams layoutParams = new LayoutParams(dip2px(context,160), LayoutParams.WRAP_CONTENT);
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(0, dip2px(context, 10), 0, 0);
                imageView.setImageResource(R.mipmap.ic_launcher);
                listItemView.linearLayout_add.addView(imageView);
                listItemView.linearLayout_add.setVisibility(View.VISIBLE);
                break;
            case MULTIPLE_PHOTO:
                break;
            case SINGLE_VIDEO:
                break;
            default:break;
        }

        return convertView;
    }
}
