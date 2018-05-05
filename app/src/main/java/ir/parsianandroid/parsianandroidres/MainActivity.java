package ir.parsianandroid.parsianandroidres;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import ir.parsianandroid.parsianandroidres.Binder.CategoryRecyBinder;
import ir.parsianandroid.parsianandroidres.Binder.CommentRecyBinder;
import ir.parsianandroid.parsianandroidres.Binder.DrawerAdapter;
import ir.parsianandroid.parsianandroidres.Binder.FactorRowRecyBinder;
import ir.parsianandroid.parsianandroidres.Binder.ProductRecyBinder;
import ir.parsianandroid.parsianandroidres.Binder.RecyclerItemTouchHelper;
import ir.parsianandroid.parsianandroidres.Binder.TablesRecyBinder;
import ir.parsianandroid.parsianandroidres.Global.Constants;
import ir.parsianandroid.parsianandroidres.Global.Dialogs;
import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.Global.ItemClickSupport;
import ir.parsianandroid.parsianandroidres.Global.MyToast;
import ir.parsianandroid.parsianandroidres.Global.PromptDialog;
import ir.parsianandroid.parsianandroidres.Global.RetrofitInitialize;
import ir.parsianandroid.parsianandroidres.Interface.DeletageRecyBinderButtonClick;
import ir.parsianandroid.parsianandroidres.Models.DrawerItems;
import ir.parsianandroid.parsianandroidres.Models.FactorHead;
import ir.parsianandroid.parsianandroidres.Models.FactorRow;
import ir.parsianandroid.parsianandroidres.Models.ProductCategory;
import ir.parsianandroid.parsianandroidres.db.AppDatabase;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TCategoryKey;
import ir.parsianandroid.parsianandroidres.db.entity.TComments;
import ir.parsianandroid.parsianandroidres.db.entity.TProducts;
import ir.parsianandroid.parsianandroidres.db.entity.TTables;
import ir.parsianandroid.parsianandroidres.fragments.AboutMeDialogFragment;
import ir.parsianandroid.parsianandroidres.fragments.OpinionFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DeletageRecyBinderButtonClick, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    int index=0;
    public static final int REQUEST_APP_SETTINGS = 168;

    TabHost host;
    ImageButton btn_addnew;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView list_category;
    RecyclerView list_factorrows;
    RecyclerView list_tables;
    TextView txt_sum;
    TextView txt_lasttimestatus;
    TextView txt_table;
    ImageView img_toolbar;
    ImageButton btn_clear;
    private RecyclerView list_products;
    List<TCategory> categories;
    List <ProductCategory> products;
    List <TTables> tables;
    TextView txt_selectedcategory;
    FactorHead factorHead;
    List<FactorRow> rows;
    private Toolbar toolbar;
    FactorRowRecyBinder adapterrows;
    private Button btn_send;
    private ListView list_drawer;
    private TextView txt_drawerfullname;
    TextView txt_status;
    ImageView img_clearsearch;
    EditText txt_search;
    int CurrentCategory=0;
    List<DrawerItems> draweritems;
    WebView web_chat;
    private CoordinatorLayout coordinatorLayout;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > 22 && !GlobalUtils.hasPermissions(GlobalUtils.requiredPermissions)) {
            ActivityCompat.requestPermissions(MainActivity.this, GlobalUtils.requiredPermissions, REQUEST_APP_SETTINGS);
            // goToSettings();
        }
        GlobalUtils.SetFirstSettings(this);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        web_chat=findViewById(R.id.web_chat);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        btn_addnew = findViewById(R.id.btn_addnew);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        txt_drawerfullname= (TextView) findViewById(R.id.main_txt_fuulname);
        txt_selectedcategory= findViewById(R.id.txt_selectedcategory);
        //txt_version= (TextView) findViewById(R.id.main_txt_version);
        mSwipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        list_drawer= (ListView) findViewById(R.id.list_slidermenu);
        txt_status=findViewById(R.id.txt_status);
        btn_send =  findViewById(R.id.btn_send);
        img_toolbar =  findViewById(R.id.img_toolbar);
        img_clearsearch=  findViewById(R.id.img_clearsearch);
         txt_search=  findViewById(R.id.txt_search);

        img_toolbar.setColorFilter(getResources().getColor(R.color.colorAccentColor));
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(view ->    NewBoard());
        factorHead=new FactorHead();
        rows=new ArrayList<>();

        list_category=findViewById(R.id.list_category);
        GlobalUtils.setupLinerRecycler(list_category,MainActivity.this, LinearLayoutManager.HORIZONTAL,1);

        list_products=findViewById(R.id.list_product);
        GlobalUtils.setupLinerRecycler(list_products,MainActivity.this, LinearLayoutManager.VERTICAL,1);
        list_factorrows=findViewById(R.id.list_factorrows);
        GlobalUtils.setupLinerRecycler(list_factorrows,MainActivity.this, LinearLayoutManager.VERTICAL,1);
        list_tables=findViewById(R.id.list_tables);
        GlobalUtils.setupLinerRecycler(list_tables,MainActivity.this, LinearLayoutManager.VERTICAL,4);

        txt_sum=findViewById(R.id.txt_sum);
        txt_table=findViewById(R.id.txt_table);
        txt_lasttimestatus=findViewById(R.id.txt_lasttimestatus);
        btn_clear=findViewById(R.id.btn_clear);

        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        TabHost.TabSpec spec;
        //Tab 1
     /*   spec = host.newTabSpec("Chat");
        spec.setContent(R.id.tab_chat);
        spec.setIndicator("چت");
        host.addTab(spec);*/



        //Tab 2
        spec = host.newTabSpec("Tables");
        spec.setContent(R.id.tab_tables);
        spec.setIndicator("میزها");
        host.addTab(spec);

        spec = host.newTabSpec("Order");
        spec.setContent(R.id.tab_order);
        spec.setIndicator("سفارش");
        host.addTab(spec);

        //Tab 3

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            // Refresh items
            refreshTableItems();
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(list_factorrows);

        img_clearsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_search.setText("");
                hideKeyboard(v);
            }
        });
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FillProducts(CurrentCategory);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        host.setCurrentTabByTag("Order");
        txt_drawerfullname.setText(AppSettings.with(MainActivity.this).GeFullName());
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, (R.string.txt_open),(R.string.txt_close)) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View view) {
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        draweritems=DrawerItems.getItems(MainActivity.this);
        DrawerAdapter draweradapter=new DrawerAdapter(MainActivity.this,draweritems);
        list_drawer.setAdapter(draweradapter);





        list_drawer.setOnItemClickListener((parent, view, position, id) -> {
            //MyToast.makeText(MainActivity.this, draweritems.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            if(draweritems.get(position).getID()==101)
            {

            }
            else if(draweritems.get(position).getID()==102)
            {
                FragmentManager fm = getFragmentManager();
                OpinionFragment dialogFragment= new OpinionFragment("درباره ما");
                dialogFragment.show(fm, "AboutMe");
            }
            else if(draweritems.get(position).getID()==103)
            {

            }
            else if(draweritems.get(position).getID()==104)
            {
                FragmentManager fm = getFragmentManager();
                AboutMeDialogFragment dialogFragment= new AboutMeDialogFragment("درباره ما");
                dialogFragment.show(fm, "AboutMe");

            }
            else if(draweritems.get(position).getID()==105)
            {
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "از حساب کاربری خارج می شوید؟", Snackbar.LENGTH_LONG);
                snackbar.setAction("بله", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AppSettings.with(MainActivity.this).LogOutUser();
                        Intent i=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();

                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.yellow));
                snackbar.show();
            }
            else if(draweritems.get(position).getID()==106)
            {
                ExitSoft();

            }


            mDrawerLayout.closeDrawers();

        });
        FillCategory();
        if(categories.size()>0)
        {
            FillProducts(categories.get(0).getCode());
            txt_selectedcategory.setText(categories.get(0).getTitle());
            CurrentCategory=categories.get(0).getCode();
        }
        host.setOnTabChangedListener(tabId -> {
            if(tabId.equals("Tables"))
            {
                RefreshStatusTables();

                //FillTables();
            }
            else if(tabId.equals("Order"))
            {

            }
            else if(tabId.equals("Chat"))
            {
                LoadChatView();
            }

        });
        btn_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewBoard();
            }
        });
        adapterrows=new FactorRowRecyBinder(MainActivity.this,rows,MainActivity.this);
        list_factorrows.setAdapter(adapterrows);

        //list_factorrows.setOnItemClickListener((parent, view, position, id) -> ShowDialogComments(position));
        ItemClickSupport.addTo(list_category).setOnItemClickListener((recyclerView, position, v) ->
                {
                    txt_search.setText("");
                    txt_selectedcategory.setText(categories.get(position).getTitle());
                    CurrentCategory=categories.get(position).getCode();
                    FillProducts(categories.get(position).getCode());
                    hideKeyboard(v);
                }

        );
        ItemClickSupport.addTo(list_products).setOnItemClickListener((recyclerView, position, v) ->
                {

                    FactorRow.refreshFactorRow(rows,new FactorRow(products.get(position).getCode(),1,products.get(position).getCName(),products.get(position).getPrice(AppSettings.with(MainActivity.this).GetDefaultPrice()),""));
                    FillFactorRow();
                    for (int i = 0; i < rows.size(); i++) {
                        if(rows.get(i).getProductCode()==products.get(position).getCode()) {
                            list_factorrows.getLayoutManager().scrollToPosition(i);
                            break;
                        }

                    }

                }

        );
        ItemClickSupport.addTo(list_tables).setOnItemClickListener((recyclerView, position, v) ->
                {
                    if(AppSettings.with(MainActivity.this).GetDoubleTable()==1 && tables.get(position).getStatus() == Constants.Status_Table_Fill)
                    {
                        Dialog dlg=new Dialog(MainActivity.this);
                        dlg.setContentView(R.layout.dialog_selectfactortype);
                        TextView title=dlg.findViewById(R.id.txt_title);
                        Button btn_neworder=dlg.findViewById(R.id.btn_neworder);
                        Button btn_existorder=dlg.findViewById(R.id.btn_existorder);
                        title.setText("انتخاب وضعیت سفارش");
                        btn_neworder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                factorHead.setTableNumber(tables.get(position).getCode());
                                host.setCurrentTabByTag("Order");
                                MyToast.makeText(MainActivity.this, " " + tables.get(position).getTitle() + " انتخاب شد ", Toast.LENGTH_SHORT).show();
                                FillFactorHead();
                                dlg.dismiss();

                            }
                        });
                        btn_existorder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FetchFactor(0, tables.get(position).getCode());
                                dlg.dismiss();

                            }
                        });
                        dlg.show();

                    }
                    else
                    {
                        if (tables.get(position).getStatus() == Constants.Status_Table_Empty) {
                            factorHead.setTableNumber(tables.get(position).getCode());
                            host.setCurrentTabByTag("Order");
                            MyToast.makeText(this, " " + tables.get(position).getTitle() + " انتخاب شد ", Toast.LENGTH_SHORT).show();
                            FillFactorHead();
                        } else {
                            PromptDialog dlg = new PromptDialog();
                            dlg.SetonResultListenner(PromptDialog.Type_YES_NO, "توجه", "این میز پر می باشد،سفارش میز دریافت شود؟", MainActivity.this, new PromptDialog.onResultButtonClick() {
                                @Override
                                public void onResult(int code) {
                                    if (code == PromptDialog.Result_YES) {
                                        FetchFactor(0, tables.get(position).getCode());
                                    }

                                }
                            });
                        }
                    }
                }

        );
        btn_clear.setOnClickListener(v -> {
            rows.clear();
            FillFactorRow();
        });
        btn_send.setOnClickListener(v -> {


            if(rows.size()>0) {
                if(factorHead.getTableNumber()>0)
                {
                    if(AppSettings.with(MainActivity.this).GetAskPrintLocal()==1) {
                        PromptDialog dlg = new PromptDialog();
                        dlg.SetonResultListenner(PromptDialog.Type_YES_NO, "توجه", "آیا می خواهید این سفارش چاپ شود؟", MainActivity.this, new PromptDialog.onResultButtonClick() {
                            @Override
                            public void onResult(int code) {
                                if (code == PromptDialog.Result_YES) {
                                    SendFactor(1);
                                } else if (code == PromptDialog.Result_NO) {
                                    SendFactor(0);
                                }
                            }
                        });
                    }
                    else
                    {
                        SendFactor(1);
                    }

                }
                else
                {
                    MyToast.makeText(MainActivity.this, "لطفا شماره میز را مشخص نمایید", Toast.LENGTH_SHORT).show();
                    host.setCurrentTabByTag("Tables");
                }
            }
            else
            {
                MyToast.makeText(MainActivity.this, "سفارش خالی می باشد", Toast.LENGTH_SHORT).show();
            }

        });
        ClearBoard();
    }

    private void SendFactor(int print) {
        PromptDialog dlg=new PromptDialog();
        dlg.SetonResultListenner(PromptDialog.Type_YES_NO, "توجه", "مطمئن به ثبت سفارش می باشید؟", MainActivity.this, new PromptDialog.onResultButtonClick() {
            @Override
            public void onResult(int code) {
                if(code==PromptDialog.Result_YES)
                {
                    JSONObject obj = new JSONObject();
                    try {

                        obj.put("FactorHead", FactorHead.FactorHeadToJson(factorHead));
                        obj.put("FactorRows", FactorRow.FactorRowsToJson(rows));
                        obj.put("Print", print);
                    } catch (Exception ee) {

                    }

                    Dialogs dlg=new Dialogs(MainActivity.this);
                    dlg.ShowWaitDialog("صبور باشید","در حال ثبت و چاپ سفارش");
                    Call<ResponseBody> call = RetrofitInitialize.With(MainActivity.this).webServices.SaveFactor(obj.toString());
                    Callback<ResponseBody> callback = new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            dlg.StopWaitDialog();
                            if (response.isSuccessful()) {
                                try {


                                    JSONObject obj=new JSONObject(response.body().string());
                                    int fnum=obj.getInt("Num");
                                    int fishnum=obj.getInt("FishNum");
                                    String  messa=obj.getString("Message");
                                    factorHead.setFactorNumber(fnum);
                                    String msg=messa+"\n"+"شماره فاکتور: "+fnum;
                                    msg+="\n"+"شماره فیش: "+fishnum;
                                    msg+="\n"+"سفارش جدید ایجاد شود؟";
                                    PromptDialog dialog=new PromptDialog();
                                    dialog.SetonResultListenner(PromptDialog.Type_YES_NO, "توجه", msg, MainActivity.this, new PromptDialog.onResultButtonClick() {
                                        @Override
                                        public void onResult(int code) {
                                            if(code==PromptDialog.Result_YES)
                                            {
                                                ClearBoard();
                                            }
                                            else  if(code==PromptDialog.Result_NO)
                                            {

                                            }

                                        }
                                    });
                                    FillFactorHead();

                                } catch (Exception ee) {
                                    ee.printStackTrace();
                                    MyToast.makeText(MainActivity.this, "خطایی در حین پردازش اطلاعات", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                MyToast.makeText(MainActivity.this, "خطایی در حین انجام عملیات", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dlg.StopWaitDialog();
                            MyToast.makeText(MainActivity.this, "شما به سرور متصل نمی شوید", Toast.LENGTH_SHORT).show();

                        }
                    };
                    call.enqueue(callback);
                }
                else if(code==PromptDialog.Result_NO)
                {

                }
            }
        });

    }

    private void ExitSoft() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "از برنامه خارج می شوید؟", Snackbar.LENGTH_LONG);
        snackbar.setAction("بله", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.yellow));
        snackbar.show();
    }

    private void FetchFactor(int factornum,int tablenum) {
        Dialogs dlg=new Dialogs(MainActivity.this);
        dlg.ShowWaitDialog("صبور باشد","در حال دریافت داده ها");
        Call<ResponseBody> call = RetrofitInitialize.With(MainActivity.this).webServices.GatFactor(factornum,tablenum);
        Callback<ResponseBody> callback =new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dlg.StopWaitDialog();
                try
                {
                    if (response.isSuccessful()) {

                        ClearBoard();
                        JSONObject obj = new JSONObject(response.body().string());
                        Type objecttype = new TypeToken<FactorHead>() {
                        }.getType();
                        factorHead = new Gson().fromJson(obj.getString("FactorHead"), objecttype);

                        Type ListType = new TypeToken<List<FactorRow>>() {
                        }.getType();
                        rows = new Gson().fromJson(obj.getString("FactorRows"), ListType);
                        adapterrows = new FactorRowRecyBinder(MainActivity.this, rows, MainActivity.this);
                        list_factorrows.setAdapter(adapterrows);
                        FactorRow.With(MainActivity.this).RefreshLabels(rows);
                        FillFactorRow();
                        FillFactorHead();
                        host.setCurrentTabByTag("Order");


                    } else {
                        MyToast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ee)
                {
                    ee.printStackTrace();
                    MyToast.makeText(MainActivity.this, "خطایی در حین پردازش اطلاعات رخ داده است", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dlg.StopWaitDialog();
                MyToast.makeText(MainActivity.this, "شما به سرور متصل نمی شوید", Toast.LENGTH_SHORT).show();

            }
        };
        call.enqueue(callback);

    }

    @Override
    public void onBackPressed() {

        ExitSoft();
    }

    private void RefreshStatusTables() {

        Dialogs dialo=new Dialogs(MainActivity.this);
        dialo.ShowWaitDialog("صبور باشد","در حال دریافت وضعیت میزها...");
        Call<List<TTables>> call = RetrofitInitialize.With(MainActivity.this).webServices.TableStatus(0);
        //Object call = RetrofitInitialize.With(MainActivity.this).webServices.FetchData(code);

        Callback<List<TTables>> callback =new Callback<List<TTables>>() {
            @Override
            public void onResponse(Call<List<TTables>> call, Response<List<TTables>> response) {
                dialo.StopWaitDialog();
                try
                {
                    if (response.isSuccessful()) {
                        if(response.code()== HttpsURLConnection.HTTP_OK) {

                            for (int i = 0; i < response.body().size(); i++) {
                                AppDatabase.getInstance(MainActivity.this).tTablesKeyDao().updateStatus(response.body().get(i).getStatus(), response.body().get(i).getCode());
                            }
                            onItemsLoadComplete();
                            FillTables();
                        }

                    }
                    else
                    {
                        onItemsLoadComplete();

                        if(response.code()== HttpURLConnection.HTTP_CONFLICT)
                        {
                            PromptDialog dialog=new PromptDialog();
                            dialog.SetonResultListenner(PromptDialog.Type_YES_NO, "توجه", response.errorBody().string(), MainActivity.this, new PromptDialog.onResultButtonClick() {
                                @Override
                                public void onResult(int code) {
                                    if(code==PromptDialog.Result_YES)
                                    {
                                        //ClearBoard();
                                    }
                                    else  if(code==PromptDialog.Result_NO)
                                    {

                                    }

                                }
                            });

                        }
                        else
                        {
                            MyToast.makeText(MainActivity.this, "خطایی در حین انجام عملیات وضعیت میزها", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                catch (Exception ee)
                {
                    onItemsLoadComplete();
                    ee.printStackTrace();
                    MyToast.makeText(MainActivity.this, "خطایی در حین پردازش اطلاعات وضعیت میزها", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<TTables>> call, Throwable t) {
                onItemsLoadComplete();
                dialo.StopWaitDialog();

                MyToast.makeText(MainActivity.this, "شما به سرور متصل نمی شوید", Toast.LENGTH_SHORT).show();

            }

        };
        call.enqueue(callback);
    }

    public void ClearBoard()
    {

        if(tables!=null) {
            tables.clear();
            FillTables();
        }
        if(categories!=null) {
            categories.clear();
            FillCategory();
        }

        if(products!=null) {

            products.clear();

            if(categories.size()>0)
            {
                FillProducts(categories.get(0).getCode());
                txt_selectedcategory.setText(categories.get(0).getTitle());
                CurrentCategory=categories.get(0).getCode();
            }
        }

        factorHead.Initialize();
        rows.clear();
        FillFactorRow();
        FillFactorHead();
        txt_search.setText("");


    }
    public void FillFactorHead()
    {
        if(factorHead.getTableNumber()>0) {
            TTables table=AppDatabase.getInstance(MainActivity.this).tTablesKeyDao().getTable(factorHead.getTableNumber());
            if(table!=null) {
                txt_table.setText(table.getCode()+" - "+table.getTitle());
            }else
            {
                txt_table.setText("میز پیدا نشد");
            }
        }
        else
        {
            txt_table.setText("میز مشخص نشده");
        }
        if(factorHead.getFactorNumber()>0)
        {
            txt_status.setText("ویرایش سفارش");
        }
        else
        {
            txt_status.setText("سفارش جدید");
        }
    }
    public void FillCategory()
    {
        categories= AppDatabase.getInstance(MainActivity.this).tCategoryDao().getAll();
        CategoryRecyBinder adapter=new CategoryRecyBinder(MainActivity.this,categories);
        list_category.setAdapter(adapter);
        if(categories.size()>0)
        {
            FillProducts(categories.get(0).getCode());
            txt_selectedcategory.setText(categories.get(0).getTitle());
            CurrentCategory=categories.get(0).getCode();
        }
    }
    public void FillProducts(int tcode)
    {
        products=AppDatabase.getInstance(MainActivity.this).tProductDao().getByCategory(tcode,"%"+txt_search.getText().toString()+"%");

        //MyToast.makeText(this, products.size()+"", Toast.LENGTH_SHORT).show();
        ProductRecyBinder adapter=new ProductRecyBinder(MainActivity.this,products);
        list_products.setAdapter(adapter);
    }
    public void FillFactorRow()
    {

        adapterrows.notifyDataSetChanged();
        txt_sum.setText(" جمع: "+GlobalUtils.getCurrecncy(FactorRow.getFactorSum(rows)));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_getdata) {
            index=0;
            FetchData(Constants.Code_Update_Category);

        }
        else if(id==R.id.menu_addnew)
        {
            NewBoard();


        }
        else if(id==R.id.menu_drawer)
        {

            mDrawerLayout.openDrawer(Gravity.RIGHT);

        }
        return true;
    }
    public void NewBoard()
    {
        PromptDialog pd=new PromptDialog();
        pd.SetonResultListenner(PromptDialog.Type_YES_NO, "توجه", "آیا مطمئن به ایجاد سفارش جدید می باشد؟", MainActivity.this, new PromptDialog.onResultButtonClick() {
            @Override
            public void onResult(int code) {
                if(code==PromptDialog.Result_YES)
                {   ClearBoard();

                }
            }
        });

    }
    public void FetchData(int code)
    {
        Dialogs dlg=new Dialogs(MainActivity.this);
        dlg.ShowWaitDialog("صبور باشید","در حال دریافت اطلاعات");
        Call<ResponseBody> call = RetrofitInitialize.With(MainActivity.this).webServices.FetchData(code);
        //Object call = RetrofitInitialize.With(MainActivity.this).webServices.FetchData(code);

        Callback<ResponseBody> callback =new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dlg.StopWaitDialog();
                if(response.isSuccessful())
                {

                    try {
                        if (code == Constants.Code_Update_Category) {
                            AppDatabase.getInstance(MainActivity.this).tCategoryDao().ClearTable();
                            Type listType = new TypeToken<ArrayList<TCategory>>() {
                            }.getType();
                            AppDatabase.getInstance(MainActivity.this).tCategoryDao().insertAll(new Gson().fromJson(response.body().string(), listType));
                            FillCategory();
                        } else if (code == Constants.Code_Update_CategoryKey) {
                            AppDatabase.getInstance(MainActivity.this).tCategoryKeyDao().ClearTable();
                            Type listType = new TypeToken<ArrayList<TCategoryKey>>() {
                            }.getType();

                            AppDatabase.getInstance(MainActivity.this).tCategoryKeyDao().insertAll(new Gson().fromJson(response.body().string(), listType));

                        } else if (code == Constants.Code_Update_Goods) {
                            AppDatabase.getInstance(MainActivity.this).tProductDao().ClearTable();
                            Type listType = new TypeToken<ArrayList<TProducts>>() {
                            }.getType();
                            AppDatabase.getInstance(MainActivity.this).tProductDao().insertAll(new Gson().fromJson(response.body().string(), listType));
                        }
                        else if (code == Constants.Code_Update_Tables) {
                            AppDatabase.getInstance(MainActivity.this).tTablesKeyDao().ClearTable();
                            Type listType = new TypeToken<ArrayList<TTables>>() {
                            }.getType();
                            AppDatabase.getInstance(MainActivity.this).tTablesKeyDao().insertAll(new Gson().fromJson(response.body().string(), listType));
                            FillTables();
                        }
                        else if (code == Constants.Code_Update_Comments) {
                            AppDatabase.getInstance(MainActivity.this).tCommentsDao().ClearTable();
                            Type listType = new TypeToken<ArrayList<TComments>>() {
                            }.getType();
                            AppDatabase.getInstance(MainActivity.this).tCommentsDao().insertAll(new Gson().fromJson(response.body().string(), listType));

                        }
                        MyToast.makeText(MainActivity.this, "درج موفقیت آمیز داده ها", Toast.LENGTH_SHORT).show();
                        index++;
                        if(index==1)
                            FetchData(Constants.Code_Update_CategoryKey);
                        if(index==2)
                            FetchData(Constants.Code_Update_Goods);
                        if(index==3)
                            FetchData(Constants.Code_Update_Tables);
                        if(index==4)
                            FetchData(Constants.Code_Update_Comments);
                        if(index==5)
                            index=0;


                    }
                    catch (Exception ee)
                    {
                        ee.printStackTrace();
                        MyToast.makeText(MainActivity.this, "خطایی در حین پردازش اطلاعات"+" "+code, Toast.LENGTH_SHORT).show();
                    }


                }
                else
                {
                    MyToast.makeText(MainActivity.this, "خطایی در حین انجام عملیات"+" "+code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dlg.StopWaitDialog();
                MyToast.makeText(MainActivity.this, "شما به سرور متصل نمی شوید", Toast.LENGTH_SHORT).show();

            }
        };
        call.enqueue(callback);

    }

    private void FillTables() {
        tables=AppDatabase.getInstance(MainActivity.this).tTablesKeyDao().getAll();
        if(tables!=null) {
            TablesRecyBinder tableAdapter=new TablesRecyBinder(MainActivity.this,tables);
            list_tables.setAdapter(tableAdapter);
        }
    }


    @Override
    public void onRecyButtonClick(int Code, double count, int position) {
        hideKeyboard(getWindow().getDecorView().getRootView());
        if(Code==1 ||Code==2) {
            FactorRow.refreshFactorRow(rows, new FactorRow(rows.get(position).getProductCode(), count, rows.get(position).getLabel(), rows.get(position).getPrice(),""));
            FillFactorRow();

        }
        else if(Code==3)
        {
            rows.remove(position);
            FillFactorRow();

        }
        else if(Code==4)
        {
            ShowDialogComments(position);
        }

    }
    public void LoadChatView()
    {
        // js includes will not happen unless we enable JS
        web_chat.getSettings().setJavaScriptEnabled(true);

        // this allows for js alert windows
        web_chat.setWebChromeClient(new WebChromeClient());

        web_chat.loadUrl("file://"+ Environment.getExternalStorageDirectory()+"/ParsianAndroidRes/Webs/Chat/index.html");

    }
    public void ShowDialogComments(int rowindex)
    {
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("توضیحات");
        dialog.setContentView(R.layout.layout_comments);
        RecyclerView list_comments=dialog.findViewById(R.id.list_comments);
        Button btn_sabt=dialog.findViewById(R.id.btn_sabt);
        Button btn_one=dialog.findViewById(R.id.btn_one);
        Button btn_two=dialog.findViewById(R.id.btn_two);
        Button btn_three=dialog.findViewById(R.id.btn_three);
        Button btn_four=dialog.findViewById(R.id.btn_four);
        Button btn_five=dialog.findViewById(R.id.btn_five);
        Button btn_all=dialog.findViewById(R.id.btn_all);
        EditText txt_passage=dialog.findViewById(R.id.txt_passage);
        ImageButton btn_remove=dialog.findViewById(R.id.btn_remove);
        ImageButton btn_clear=dialog.findViewById(R.id.btn_clear);
        txt_passage.setText(rows.get(rowindex).getComment());
        txt_passage.setSelection(txt_passage.getText().length());
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rows.get(rowindex).setComment("");
                dialog.dismiss();
                FillFactorRow();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText("");
                txt_passage.requestFocus();
            }
        });
        GlobalUtils.setupLinerRecycler(list_comments,MainActivity.this, LinearLayoutManager.VERTICAL,3);
        List<TComments> listcomments=AppDatabase.getInstance(MainActivity.this).tCommentsDao().getAll();
        CommentRecyBinder adapter=new CommentRecyBinder(MainActivity.this,listcomments);
        list_comments.setAdapter(adapter);
        ItemClickSupport.addTo(list_comments).setOnItemClickListener((recyclerView, position, v) ->
                {
                    if(txt_passage.length()==0)
                        txt_passage.setText(listcomments.get(position).getComment());
                    else
                        txt_passage.setText(txt_passage.getText().toString()+"-"+listcomments.get(position).getComment());
                    txt_passage.setSelection(txt_passage.getText().length());

                    //txt_passage.requestFocus();
                }

        );
        btn_sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rows.get(rowindex).setComment(txt_passage.getText().toString());
                dialog.dismiss();
                FillFactorRow();
            }
        });
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText(txt_passage.getText().toString()+" "+btn_one.getText().toString());
                txt_passage.setSelection(txt_passage.getText().length());


            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText(txt_passage.getText().toString()+" "+btn_two.getText().toString());
                txt_passage.setSelection(txt_passage.getText().length());


            }
        });
        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText(txt_passage.getText().toString()+" "+btn_three.getText().toString());
                txt_passage.setSelection(txt_passage.getText().length());


            }
        });
        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText(txt_passage.getText().toString()+" "+btn_four.getText().toString());
                txt_passage.setSelection(txt_passage.getText().length());


            }
        });
        btn_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText(txt_passage.getText().toString()+" "+btn_five.getText().toString());
                txt_passage.setSelection(txt_passage.getText().length());


            }
        });
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_passage.setText(txt_passage.getText().toString()+" "+btn_all.getText().toString());
                txt_passage.setSelection(txt_passage.getText().length());


            }
        });


        dialog.show();
    }
    void refreshTableItems() {
        // Load items
        // ...
        RefreshStatusTables();

        // Load complete
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof FactorRowRecyBinder.ItemViewHolder) {
            // get the removed item name to display it in snack bar
            String name = rows.get(viewHolder.getAdapterPosition()).getLabel();

            // backup of removed item for undo purpose
            final FactorRow deletedItem = rows.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            adapterrows.removeItem(viewHolder.getAdapterPosition());
            FillFactorRow();
            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " در حال انجام حذف... ", Snackbar.LENGTH_LONG);
            snackbar.setAction("برگشت", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapterrows.restoreItem(deletedItem, deletedIndex);
                    FillFactorRow();
                }
            });
            snackbar.setActionTextColor(getResources().getColor(R.color.yellow));
            snackbar.show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void hideKeyboard(View view) {

        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
