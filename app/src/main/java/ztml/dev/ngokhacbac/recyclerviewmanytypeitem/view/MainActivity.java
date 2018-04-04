package ztml.dev.ngokhacbac.recyclerviewmanytypeitem.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ztml.dev.ngokhacbac.recyclerviewmanytypeitem.R;
import ztml.dev.ngokhacbac.recyclerviewmanytypeitem.presenter.BookPresenter;

public class MainActivity extends AppCompatActivity implements MainView {
    private BookAdaper mBookAdaper;
    private RecyclerView mRvBooks;
    private BookPresenter mBookPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvBooks = findViewById(R.id.rvBookInfor);
        mBookPresenter = new BookPresenter(this);
        mBookPresenter.loadData();

        mRvBooks.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Log.i("TAG", event.getAction() + "  -- ");
                return false;
            }
        });
    }

    @Override
    public void displayBooks(final List<Object> bookInfors) {
        mBookAdaper = new BookAdaper(bookInfors);
        mRvBooks.setAdapter(mBookAdaper);
        mRvBooks.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvBooks.scrollToPosition(4);
        mBookAdaper.setOnItemClickListenner(new BookAdaper.OnItemClickListenner() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i("TAG", position + "");
                bookInfors.remove(position);
                bookInfors.remove(position);
                mBookAdaper.notifyItemRangeRemoved(position,2);
                Toast.makeText(getApplicationContext(),"Buy is success",Toast.LENGTH_SHORT);
            }
        });
    }

}
