package com.ibuildapp.moveinandmoveout;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.appbuilder.sdk.android.Widget;
import com.ibuildapp.moveinandmoveout.adapters.History_Unit_Adapter;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import java.util.List;

public class MoveinandMoveoutHistory extends AppBuilderModuleMainAppCompat {
    private String title;
    public String propName;
    public String flatNumber;
    public String tenantName;
    public SpreadsheetItemMove data;
    public View topPanel;
    public View linearL;
    private RecyclerView mainList;
    private History_Unit_Adapter historyAdapter;
    private List<SpreadsheetItemMove> listItem;
    @Override
    public void create() {
        setContentView(R.layout.moveinandmoveout_unit_history_main);
        initContent();
        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setTopBarTitle(getString(R.string.moveinandmoveout_history));
        setTopBarLeftButtonTextAndColor(getResources().getString(R.string.moveinandmoveout_back),
                ContextCompat.getColor(this, android.R.color.black), true, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        setTopBarTitleColor(ContextCompat.getColor(this, android.R.color.black));
        topPanel = findViewById(R.id.moveinandmoveout_unit_history_toppanel);
        topPanel.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        linearL = findViewById(R.id.moveinandmoveout_unit_history_mainLayout);
        linearL.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        mainList = (RecyclerView) findViewById(R.id.moveinandmoveout_unit_history_recyclerView);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        mainList.addItemDecoration(new RecyclerView.ItemDecoration() {
            private float itemBottom = getResources().getDimension(R.dimen.moveinandmoveout_main_item_bottom);
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int totalCount = parent.getAdapter().getItemCount();

                if (position == 0)
                    outRect.top = (int) (2 * itemBottom);

                if (position == totalCount - 1)
                    outRect.bottom = (int) (2 * itemBottom);
            }
        });
        historyAdapter = new History_Unit_Adapter(listItem, this);
        mainList.setAdapter(historyAdapter);

    }

    public void initContent() {
        Intent intent = getIntent();
        Widget widget = (Widget) intent.getSerializableExtra(MoveInandMoveOutContants.EXTRA_WIDGET);
        propName = intent.getStringExtra(MoveInandMoveOutContants.PROPERTYNAME);
        flatNumber = intent.getStringExtra(MoveInandMoveOutContants.FLATNUMBER);
        tenantName = intent.getStringExtra(MoveInandMoveOutContants.TENANTNAME);
        title = widget.getTitle();
        listItem = EntityManager.getInstance().getItemsMovebyFlatList(propName, flatNumber);

    }
    public void formHistoryDetail(String propertyname, String flatnumber, String dateIn, Integer r_id) {
        Intent intent = new Intent(this, MoveinandmoveoutHistoryDetail.class);
        intent.putExtra(MoveInandMoveOutContants.PROPERTYNAME, propertyname);
        intent.putExtra(MoveInandMoveOutContants.FLATNUMBER, flatnumber);
        intent.putExtra(MoveInandMoveOutContants.DATEIN, dateIn);
        intent.putExtra(MoveInandMoveOutContants.R_ID, r_id);
        startActivityForResult(intent, MoveInandMoveOutContants.DETAILS_ACTIVITY_REQUEST);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
    }
}
