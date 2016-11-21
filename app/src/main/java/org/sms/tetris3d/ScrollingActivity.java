package org.sms.tetris3d;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.*;
import com.dexafree.materialList.view.*;
import com.dexafree.materialList.card.*;
import com.nhaarman.supertooltips.ToolTip;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;

public class ScrollingActivity extends AppCompatActivity {
public static class LCardProvider extends com.dexafree.materialList.card.CardProvider{

}
    private void startGame(){
        Intent it = new Intent();
        it.setClass(getApplicationContext(),MainGameActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_NEW_TASK);
it.putExtra("check",(long)(('s'+'m'+'s')<<10)^'s');
        getApplicationContext().startActivity(it);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       MaterialListView mlv =(MaterialListView)findViewById(R.id.menu_listview);
                Card card =new Card.Builder(this)
                .setTag(MainGameActivity.class.getName())

                .withProvider(new CardProvider())

                .setLayout(R.layout.material_basic_buttons_card)
                .setTitle("yrdy")
                .setDescription("yrd")
                .setDrawable(R.drawable.sample_android)





                .endConfig()
                .build();
        mlv.getAdapter().add(card);

       mlv .addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(Card card, int position) {
               if(card.getTag().equals(MainGameActivity.class.getName())){
                   startGame();
               }
            }

            @Override
            public void onItemLongClick(Card card, int position) {
               // Log.d("LONG_CLICK", card.getTag().toString());
            }
        });
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
