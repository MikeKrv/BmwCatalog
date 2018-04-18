package com.example.mike.bmwcatalog.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mike.bmwcatalog.R;
import com.example.mike.bmwcatalog.model.BmwModel;

import java.util.List;

/**
 * Created by Mike on 30.07.2017.
 */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ModelViewHolder> {

    private List<BmwModel> contents;
    private Context mContext;
    private int orientation;
    private ItemClickListener itemClickListener;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public static final int ORIENTATION_VERTICAL = 1;
    public static final int ORIENTATION_LANDSCAPE = 2;

    public CarsAdapter(Context context, List<BmwModel> contents, int orientation, ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.contents = contents;
        this.orientation = orientation;
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_HEADER;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public CarsAdapter.ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                if (orientation == ORIENTATION_VERTICAL) {
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.list_item_card_big, parent, false);
                } else if (orientation == ORIENTATION_LANDSCAPE) {
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.list_item_card_big_land, parent, false);
                }

                return new ModelViewHolder(view, itemClickListener) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new ModelViewHolder(view, itemClickListener) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        holder.bind(contents.get(position));
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }

    }


    public class ModelViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public ModelViewHolder(final View itemView, final ItemClickListener itemClickListener) {
            super(itemView);
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            v.animate().scaleX(0.94f).scaleY(0.94f).setDuration(200).setInterpolator(new DecelerateInterpolator());
                            v.setPressed(true);
                            break;

                        case MotionEvent.ACTION_CANCEL:
                            v.animate().scaleX(1).scaleY(1).setDuration(200).setInterpolator(new DecelerateInterpolator());
                            v.setPressed(false);
                            break;

                        case MotionEvent.ACTION_UP:
                            v.animate().scaleX(1).scaleY(1).setInterpolator(new DecelerateInterpolator());
                            if (v.isPressed()) {
                                v.performClick();
                                v.setPressed(false);
                            }

                            break;
                    }

                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClicked(ModelViewHolder.this);
                }
            });
            title = (TextView) itemView.findViewById(R.id.tvPreviewTitle);
            description = (TextView) itemView.findViewById(R.id.tvPreviewDescription);
            image = (ImageView) itemView.findViewById(R.id.previewImg);
        }

        public void bind(BmwModel model) {
            title.setText(model.getTitle());
            description.setText(model.getDescription());
            Glide.with(mContext)
                    .load(model.getImage())
                    .into(image);

        }
    }

    public interface ItemClickListener {
        void onItemClicked(ModelViewHolder viewHolder);
    }
}
