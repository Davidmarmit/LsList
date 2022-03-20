package com.todo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.ViewHolder> {
    private ArrayList<Tarea> tareas;
    private String mText = "";

    // Constructor for initialization
    public TareaAdapter(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    @Override
    public TareaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarea tarea_item = tareas.get(position);

        if(tarea_item.getHecha()){
            holder.text.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Editar tarea num " + holder.getAdapterPosition());

                EditText input = new EditText(view.getContext());
                input.setText(tarea_item.getNombre());
                builder.setView(input);

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(input.getText().toString().isEmpty()){
                            Toast.makeText(view.getContext(),"Casi, pero no puedo poner una tarea vacia.", Toast.LENGTH_SHORT).show();
                        }else{
                            tarea_item.setNombre(input.getText().toString());
                            notifyItemChanged(holder.getAdapterPosition());
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }

        });

        holder.check.setChecked(tarea_item.getHecha());
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    holder.text.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    holder.text.setPaintFlags(holder.text.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG)); //?
                }
                tarea_item.setHecha(b);

            }
        });
        holder.text.setText(tarea_item.getNombre());
        holder.boton.setOnClickListener(view -> {
            this.tareas.remove(position);
            this.notifyItemRemoved(position);
            this.notifyItemRangeChanged(position,this.tareas.size());
        });
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    // Initializing the Views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public CheckBox check;
        public ImageView boton;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.task_text);
            boton = (ImageView) view.findViewById(R.id.delete);
            check = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
}

