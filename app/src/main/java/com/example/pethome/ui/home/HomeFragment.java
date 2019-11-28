package com.example.pethome.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.pethome.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    public void onStart() {

        super.onStart();
        List<pets> pets = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.review);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new petsAdapter(view.getContext(), (ArrayList<pets>) pets));

        pets.add(new pets(R.mipmap.pet001,"C_159684","貓","母","苓雅區",
                "虎斑","適合認養","高雄市壽山動物保護教育園區","07-5519059",
                "高雄市鼓山區萬壽路350號","本所動物皆採現場互動,面談後評估能否認養"));
        pets.add(new pets(R.mipmap.pet002,"D_964851","犬","公","大寮區",
                "黑","適合認養","高雄市壽山動物保護教育園區","07-5519059",
                "高雄市鼓山區萬壽路350號","本所動物皆採現場互動,面談後評估能否認養"));
        pets.add(new pets(R.mipmap.pet003,"D_784523","犬","公","彌陀區",
                "白","適合認養","高雄市燕巢動物保護關愛園區","07-6051002",
                "高雄市燕巢區師大路100號","本所動物皆採現場互動,面談後評估能否認養"));
        pets.add(new pets(R.mipmap.pet004,"C_365412","貓","母","三峽區",
                "虎斑","適合認養","新北市板橋區公立動物之家","02-89662158",
                "新北市板橋區板城路28-1號","電話預約後現場面談"));
        pets.add(new pets(R.mipmap.pet005,"D_849315","犬","公","鹿港區",
                "黑黃","適合認養","彰化縣流浪狗中途之家","04-8590638",
                "彰化縣員林鎮大峰里阿寶巷426號","無"));
        pets.add(new pets(R.mipmap.pet006,"D_123895","犬","公","左營區",
                "花","適合認養","高雄市壽山動物保護教育園區","07-5519059",
                "高雄市鼓山區萬壽路350號","本所動物皆採現場互動,面談後評估能否認養"));
        pets.add(new pets(R.mipmap.pet007,"C_854628","貓","母","旗山區",
                "黃","適合認養","高雄市壽山動物保護教育園區","07-5519059",
                "高雄市鼓山區萬壽路350號","本所動物皆採現場互動,面談後評估能否認養"));
        pets.add(new pets(R.mipmap.pet008,"C_964965","貓","公","台南東區",
                "黑","適合認養","臺南市動物之家善化站","06-5832399",
                "臺南市善化區東昌里東勢寮1~19號","本所動物皆採現場互動,面談後評估能否認養"));
        pets.add(new pets(R.mipmap.pet009,"C_951753","貓","母","斗六市",
                "棕白","適合認養","雲林縣流浪動物收容所","(05)5322735",
                "斗六市鎮東里文化路344號","無"));
        pets.add(new pets(R.mipmap.pet010,"C_546321","貓","公","內湖區",
                "虎斑","適合認養","新北市板橋區公立動物之家","02-89662158",
                "新北市板橋區板城路28-1號","1.前飼主認養後退回"+"\n"+"2.電話預約後現場面談"));

    }
}