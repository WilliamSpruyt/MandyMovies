package tk.qwerky.mandymovies;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Movie_Detail_Fragment extends Fragment {

    public Movie_Detail_Fragment() {
    }

    public static Movie_Detail_Fragment newInstance() {
        return new Movie_Detail_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie__detail_, container, false);
    }
}