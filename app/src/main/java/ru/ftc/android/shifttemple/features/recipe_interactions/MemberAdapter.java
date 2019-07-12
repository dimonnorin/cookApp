package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.Member;

public final class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder>
{
    private final List<User> members = new ArrayList<>();
    private final LayoutInflater inflater;
    private final MemberListener memberListener;

    MemberAdapter(Context context,  MemberListener memberListener) {
        inflater = LayoutInflater.from(context);
        this.memberListener = memberListener;
    }
    @Override
    public MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater
                .inflate(R.layout.member_item, parent, false);
        return new MemberHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberHolder ingredientHolder, int position) {
        ingredientHolder.bind(members.get(position));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void setItems(Collection<User> members) {
        this.members.clear();
        this.members.addAll(members);
        notifyDataSetChanged();
    }

    public void clearItems() {
        members.clear();
        notifyDataSetChanged();
    }

    public List<User> getMembers(){
        return members;
    }


    class MemberHolder extends RecyclerView.ViewHolder {
        private final TextView memberName;
        private final TextView memberPhone;
        private final ImageButton call;
        //private final Button button;


        MemberHolder(View view) {
            super(view);
            memberName = view.findViewById(R.id.member_name);
            memberPhone = view.findViewById(R.id.member_phone);
            call  = view.findViewById(R.id.call_icon);
        }
        void bind (final User member) {
            memberName.setText(member.getName());
            memberPhone.setText(member.getUserId());
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    memberListener.onCall(memberPhone.getText().toString());
                }
            });
        }

    }


    interface MemberListener{

        void onCall(String number);

    }
}
