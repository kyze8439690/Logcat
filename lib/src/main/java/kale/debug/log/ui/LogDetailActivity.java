package kale.debug.log.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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

    private TextView mDetailTextView;

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
        mDetailTextView = (TextView) findViewById(R.id.log_detail_tv);
        LogBean log = (LogBean) intent.getSerializableExtra(KEY_MESSAGE);
        assert mDetailTextView != null;
        mDetailTextView.append("Time：" + "\n" + log.time + "\n\n");
        mDetailTextView.append("Lev：" + "\n" + log.lev + "\n\n");
        mDetailTextView.append("Tag：" + "\n" + log.tag + "\n\n");
        mDetailTextView.append("Message：" + "\n" + log.msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kale_log_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_share) {
            String content = mDetailTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, content);
            startActivity(intent);
            return true;
        } else if (i == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
