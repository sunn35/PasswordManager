package utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.supermacy.pm.R;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 15/2/16.
 */
public class ListViewBaseAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> values;

    public ListViewBaseAdapter(Context context, ArrayList<String> values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview, parent, false);
        TextView NameTextView = (TextView) rowView.findViewById(R.id.textViewNames);
        NameTextView.setText(values.get(position).toString());
        return rowView;
    }
}
