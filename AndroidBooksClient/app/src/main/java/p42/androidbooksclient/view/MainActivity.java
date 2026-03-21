package p42.androidbooksclient.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import p42.androidbooksclient.R;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import p42.androidbooksclient.db.Repository;
import p42.androidbooksclient.model.Author;
import p42.androidbooksclient.model.Book;
import p42.androidbooksclient.model.Tag;

public class MainActivity extends AppCompatActivity {
    public MainActivity(){
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHost.getNavController();
        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bnv,navController);
    }
}