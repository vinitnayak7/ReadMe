package com.lahacks.readme.spritz;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.spritzllc.android.SimpleSpritzSource;
import com.spritzllc.android.UrlSpritzSource;
import com.spritzllc.android.sdk.DefaultSpritzerHostAdapter;
import com.spritzllc.android.sdk.Spritzer;
import com.spritzllc.android.sdk.SpritzerHost;
import com.spritzllc.android.sdk.SpritzerHostAdapter;
import com.spritzllc.android.sdk.view.InlineSpritzer;

public class MainActivity extends Activity implements SpritzerHost {
    private Button btnHelloApp;
    private Button btnHelloWeb;
    private Spritzer fragmentSpritzer;
    private InlineSpritzer inlineSpritzer;
    private SpritzerHostAdapter spritzerHostAdapter;
    private RadioGroup rgSpritzStyle;

    @Override
    public SpritzerHostAdapter getSpritzerHostAdapter() {
        return spritzerHostAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inlineSpritzer = (InlineSpritzer) findViewById(R.id.isInlineSpritzer);

        rgSpritzStyle = (RadioGroup) findViewById(R.id.rgSpritzStyle);

        btnHelloWeb = (Button) findViewById(R.id.btnHelloWeb);
        btnHelloWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBtnHelloWebClick();
            }
        });

        // The HostAdapter is the glue between the Spritzer fragment and the activity.  You can use the
        // DefaultSpritzerHostAdapter as is, extend it, or implement the SpritzerHost interface yourself.
        spritzerHostAdapter = new DefaultSpritzerHostAdapter(this);

        // Now grab an instance of the Spritzer fragment.  The Spritzer fragment is configured for retention across
        // activity configuration changes, and the getInstance() method takes care or retrieving the existing instance
        // if it exists.
        fragmentSpritzer = Spritzer.getInstance(getFragmentManager());
    }

    private boolean isInlineEnabled() {
        int buttonId = rgSpritzStyle.getCheckedRadioButtonId();
        boolean useInline;

        switch (buttonId) {
            case R.id.rbInline:
                useInline = true;
                break;
            case R.id.rbFragment:
                useInline = false;
                break;
            default:
                useInline = true;
                break;
        }

        return useInline;
    }

    private void onBtnHelloWebClick() {
        UrlSpritzSource source = new UrlSpritzSource("http://sdk.spritzinc.com/sampleText/HelloWorld.html");
        String loadingMessage = "Hello via the web";

        if (isInlineEnabled()) {
            inlineSpritzer.spritz(source, loadingMessage);
        } else {
            fragmentSpritzer.spritz(source, loadingMessage);
        }
    }
}
