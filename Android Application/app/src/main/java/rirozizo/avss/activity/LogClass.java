package rirozizo.avss.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import rirozizo.avss.R;

public class LogClass extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<LogData> data= Collections.emptyList();
    LogData current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public LogClass(Context context, List<LogData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.log_container, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        LogData current=data.get(position);
        myHolder.textLogs.setText(current.Logs);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textLogs;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textLogs= (TextView) itemView.findViewById(R.id.textLogs);
        }

    }

}
