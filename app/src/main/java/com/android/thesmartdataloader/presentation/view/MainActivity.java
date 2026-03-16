package com.android.thesmartdataloader.presentation.view;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.thesmartdataloader.R;
import com.android.thesmartdataloader.domain.callback.OnDataLoadedListener;
import com.android.thesmartdataloader.core.loggers.DebugLogger;
import com.android.thesmartdataloader.presentation.presenter.PersonPresenter;
import com.android.thesmartdataloader.domain.models.Person;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private List<Person> persons = new ArrayList<>();
    private final DebugLogger debugLogger = new DebugLogger();
    private final OnDataLoadedListener<Person> onLoaded = result -> {
        persons = result;
        debugLogger.log(String.format("Person loaded: %d", persons.size()));
    };
    @Inject
    PersonPresenter personPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        personPresenter.getData(new WeakReference<>(onLoaded));
        new Handler().postDelayed(personPresenter::clearAll, 500);

    }


    @Override
    protected void onDestroy() {
        personPresenter.shutdown();
        super.onDestroy();
    }
}