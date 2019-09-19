package epsilon.com;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;


import org.w3c.dom.Text;

import epsilon.com.R;

public class SearchableWithButtonView extends View {

    private boolean isDefaultIcon;

    final private Context m_Context;
    final private SearchView m_SearchView;

    private OnClickListener m_ClickListener;
    private TextWatcher m_TextWatcherListener;

    public class SearchableViewListener
            implements OnClickListener, TextWatcher {
        @Override
        public void onClick(View view) {

            if (android.support.v7.appcompat.R.
                    id.search_mag_icon == view.getId()) {

                if (!isDefaultIcon) {
                    setDefaultSearchIcon();
                    return;
                }

                m_ClickListener.onClick(view);
            }

            else setNavBackSearchIcon();
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            m_TextWatcherListener.beforeTextChanged(charSequence, i, i1, i2);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            setNavBackSearchIcon(); m_TextWatcherListener.onTextChanged(charSequence, i, i1, i2);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().isEmpty())
                setDefaultSearchIcon();

            m_TextWatcherListener.afterTextChanged(editable);
        }
    }

    public SearchableWithButtonView(Context context, int resId) {
        super(context); m_Context = context; m_ClickListener = null;
        m_SearchView = this.findSearchViewById(resId); isDefaultIcon = false;
    }

    public void setupSearchableWithButton() {
        ((ViewGroup)m_SearchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon).
                getParent()).setBackgroundColor(Color.parseColor("#ffffff"));

        this.setDefaultSearchIcon();
        this.setupIconifiedByDefault();

        m_SearchView.setQueryHint("TYPE HERE...");

        m_SearchView.setQuery("", false); getRootView().requestFocus();

        m_SearchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon).
                setOnClickListener(new SearchableViewListener());

        ViewGroup llSearchView = ((ViewGroup)m_SearchView.findViewById(
                android.support.v7.appcompat.R.id.search_mag_icon).getParent());

        EditText searchEditText = llSearchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text);

        searchEditText.setSelected(false);

        searchEditText.setOnClickListener(new SearchableViewListener());
        searchEditText.addTextChangedListener(new SearchableViewListener());
    }

    private void setupIconifiedByDefault() {
        m_SearchView.setIconified(false);
        m_SearchView.setIconifiedByDefault(false);
    }

    private void setDefaultSearchIcon() {
        this.isDefaultIcon = true;
        this.replaceSearchIcon(R.drawable.ic_dehaze_white_24dp);
    }

    private void setNavBackSearchIcon() {
        if (this.isDefaultIcon == true) {
            this.isDefaultIcon = false;
            this.replaceSearchIcon(R.drawable.ic_arrow_back_black_24dp);
            this.setupAnimation();
        }
    }

    private void replaceSearchIcon(int resDefaultIcon) {
        ((ImageView)m_SearchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon)).
                setImageDrawable(getResources().getDrawable(resDefaultIcon));
        this.setupAnimation();
    }

    public void CancelSearchQuery()
    {
        m_SearchView.setQuery("", false);
    }

    private void setupAnimation() {

        final ImageView searchIconView = m_SearchView.findViewById(
                android.support.v7.appcompat.R.id.search_mag_icon);

        int searchIconWidth = searchIconView.getWidth();
        int searchIconHeight = searchIconView.getHeight();

        RotateAnimation searchIconAnimation = new RotateAnimation(0f, 360f,
                searchIconWidth / 2, searchIconHeight / 2);
        searchIconAnimation.setInterpolator(new LinearInterpolator());
        searchIconAnimation.setRepeatCount(Animation.INFINITE);
        searchIconAnimation.setDuration(700);

        searchIconView.startAnimation(searchIconAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchIconView.setAnimation(null);
            }
        }, 700);

    }

    private SearchView findSearchViewById(int resId) {
        return ((Activity)m_Context).findViewById(resId);
    }

    public void setSearchButtonClickListener(@Nullable OnClickListener clickListener) {
        m_ClickListener = clickListener;
    }

    public void setTextWatchListener(@Nullable TextWatcher textWatchListener) {
        m_TextWatcherListener = textWatchListener;
    }
}
