package kale.debug.log.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import kale.debug.log.R;
import kale.debug.log.model.LogBean;


/**
 * @author Kale
 * @date 2015/12/18
 */
public class LogDetailActivity extends AppCompatActivity {

    public static final String KEY_MESSAGE = "key_message";

    public static Intent withIntent(Activity activity, LogBean str) {
        return new Intent(activity, LogDetailActivity.class)
                .putExtra(KEY_MESSAGE, str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kale_log_detail_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        final TextView detailTv = (TextView) findViewById(R.id.log_detail_tv);
        LogBean log = (LogBean) intent.getSerializableExtra(KEY_MESSAGE);
        assert detailTv != null;
        detailTv.append("Time：" + "\n" + log.time + "\n\n");
        detailTv.append("Lev：" + "\n" + log.lev + "\n\n");
        detailTv.append("Tag：" + "\n" + log.tag + "\n\n");
        detailTv.append("Message：" + "\n" + log.msg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
