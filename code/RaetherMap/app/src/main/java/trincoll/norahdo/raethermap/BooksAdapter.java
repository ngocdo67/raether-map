package trincoll.norahdo.raethermap;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ngocdo67 on 3/1/18.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    private List<Book> bookList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, callNumber;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            callNumber = (TextView) view.findViewById(R.id.callNumber);
        }
    }

    public BooksAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.callNumber.setText(book.getCallNumber());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
