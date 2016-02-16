package utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.supermacy.pm.R;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 15/2/16.
 */
public class ListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public ListViewAdapter(Context context, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview, parent, false);
        TextView NameTextView = (TextView) rowView.findViewById(R.id.textViewNames);
        NameTextView.setText(values.get(position).toString());
        return rowView;
    }
}
