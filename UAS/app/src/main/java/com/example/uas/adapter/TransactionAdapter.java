package com.example.uas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.NoteViewHolder> {
    private final OnItemClickCallback onItemClickCallback;

    public TransactionAdapter(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private final ArrayList<Transaction> listNotes = new ArrayList<>();

    public ArrayList<Transaction> getListNotes() {
        return listNotes;
    }

    public void setListNotes(ArrayList<Transaction> listNotes) {

        if (listNotes.size() > 0) {
            this.listNotes.clear();
        }
        this.listNotes.addAll(listNotes);
    }

    public void addItem(Transaction note) {
        this.listNotes.add(note);
        notifyItemInserted(listNotes.size() - 1);
    }

    public void updateItem(int position, Transaction note) {
        this.listNotes.set(position, note);
        notifyItemChanged(position, note);
    }

    public void removeItem(int position) {
        this.listNotes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNotes.size());
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tvTournament.setText(listNotes.get(position).getTournament());
        holder.tvDate.setText(listNotes.get(position).getDate());
        holder.tvDescription.setText(listNotes.get(position).getDescription());
        holder.tvPlatform.setText(listNotes.get(position).getPlatform());
        holder.cvNote.setOnClickListener(v -> onItemClickCallback.onItemClicked(listNotes.get(position), position));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTournament, tvDescription, tvDate, tvPlatform;
        final CardView cvNote;

        NoteViewHolder(View itemView) {
            super(itemView);
            tvTournament = itemView.findViewById(R.id.tv_item_tournament);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvPlatform = itemView.findViewById(R.id.tv_item_platform);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Transaction selectedNote, Integer position);
    }
}