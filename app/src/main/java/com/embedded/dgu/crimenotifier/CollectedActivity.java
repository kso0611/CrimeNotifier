package com.embedded.dgu.crimenotifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CollectedActivity extends BaseActivity implements OnDateSetListener{

    private Button _showDate, _endDate, _searchButton;
    private TimePickerDialog mDialogAll;
    private EditText search;

    public RecyclerView mRecyclerView;
    public ListViewAdapter testAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int flag = 0;

    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        String text = getDateToString(millseconds);
        if(flag == 0) _showDate.setText(text);
        else _endDate.setText(text);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collected);
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        search = (EditText) findViewById(R.id.search);
        _searchButton = (Button) findViewById(R.id.searchButton);
        _searchButton.setOnClickListener(this);

        _showDate = (Button)findViewById(R.id.showDate);
        _showDate.setOnClickListener(this);
        _endDate = (Button)findViewById(R.id.endDate);
        _endDate.setOnClickListener(this);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        testAdapter = new ListViewAdapter(this, R.layout.listview_history);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(testAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        testAdapter.addItem(new HistoryData(0, "1호선 성추행", "40대 중년 남성이 카메라 사진 촬영", "2016-10-14 10:00"));
        testAdapter.addItem(new HistoryData(0, "2호선 성추행", "30대 청년 남성이 카메라 동영상 촬영", "2016-10-14 12:34"));
        testAdapter.addItem(new HistoryData(0, "3호선 성추행", "20대 청년 남성이 카메라 촬영", "2016-10-14 13:59"));
        testAdapter.addItem(new HistoryData(0, "4호선 폭행", "20대 청년 여성이 50대 중년 여성 폭행", "2016-10-15 07:22"));
        testAdapter.addItem(new HistoryData(0, "5호선 성폭행미수", "60대 노년 남성이 20대 청년 여성 성폭행미수", "2016-10-15 23:31"));
        testAdapter.addItem(new HistoryData(0, "6호선 절도", "40대 중년 여성이 금품 절도", "2016-10-16 18:20"));
        testAdapter.notifyDataSetChanged();
        testAdapter.dataChange();

        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("취소")
                .setSureStringId("확인")
                .setTitleStringId("날짜 선택")
                .setYearText("년")
                .setMonthText("월")
                .setDayText("일")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimary))
                .setWheelItemTextSize(12)
                .build();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showDate:
                mDialogAll.show(getSupportFragmentManager(), "all");
                flag = 0;
                break;
            case R.id.endDate:
                mDialogAll.show(getSupportFragmentManager(), "all");
                flag = 1;
                break;
            case R.id.searchButton:
                if(search.getText().length() < 1){
                    search.setError("2자 이상 입력하세요");
                    break;
                }else{
                    search.setError(null);
                    for(int i = 0; i < testAdapter.mListData.size(); i++){
                        if(!(testAdapter.mListData.get(i).title.contains(search.getText().toString())
                                || testAdapter.mListData.get(i).content.contains(search.getText().toString())))
                            testAdapter.mListData.remove(i);
                    }
                    testAdapter.dataChange();
                    testAdapter.notifyDataSetChanged();
                }
                break;
            default: break;
        }
    }

}
