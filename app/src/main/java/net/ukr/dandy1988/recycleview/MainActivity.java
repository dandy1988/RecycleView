package net.ukr.dandy1988.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new TaskAdapter(generateEvents()));
    }

    //создание списка ивентов
    public List<SwimEvent> generateEvents() {
        List<SwimEvent> events = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
           events.add(new SwimEvent("2020.1."+i, "event#"+i, 0));
        }
        return events;
    }

    //конфигурирование адаптера
    public static class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{
        //список ивентов
        private final List<SwimEvent> data;
        //конструктор адаптера
        public TaskAdapter(List<SwimEvent> data) {
            this.data = data;
        }

        @NonNull
        @Override
        //Создание вьюшки и засовывание ее во вью холдер и затем возвращение ее в адаптер
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.item_task, viewGroup, false);
            TaskViewHolder viewHolder = new TaskViewHolder(view);
            return viewHolder;
        }

        @Override
        //Сохранение данных
        public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
            SwimEvent swimEvent = data.get(i);
            taskViewHolder.setData(swimEvent);
        }

        @Override
        //подсчет количества элементов
        public int getItemCount() {
            return data.size();
        }
    }
    //вспомогательный класс для создание viewholder
    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTaskName;
        public TaskViewHolder(@NonNull View itemView) {
          super(itemView);
          itemView.setOnClickListener(this);// Передача Click выбранного ViewHolder
          tvTaskName = itemView.findViewById(R.id.tvTaskName);
          tvTaskName.setText("***********Item Task*********");
        }

        public void setData(SwimEvent swimEvent) {
            tvTaskName.setText(swimEvent.getDate()+": "+swimEvent.getDescription());
        }

        @Override
        public void onClick(View view) {

            tvTaskName.setText("Clicked!!!!");
        }
    }
}
