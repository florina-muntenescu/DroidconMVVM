package upday.droidconmvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import upday.droidconmvvm.model.Language;

public class LanguageSpinnerAdapter extends ArrayAdapter<Language> {

    public LanguageSpinnerAdapter(final Context context, final int resource,
                                  final List<Language> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, final View convertView, final ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.language_item, null);

            holder = new ViewHolder();
            holder.mText = (TextView) view.findViewById(android.R.id.text1);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Language language = getItem(position);
        holder.mText.setText(language.getName());

        return view;
    }

    private class ViewHolder {

        TextView mText;

        public ViewHolder() {
        }
    }
}
