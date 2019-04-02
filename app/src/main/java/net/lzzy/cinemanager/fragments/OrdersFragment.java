package net.lzzy.cinemanager.fragments;


import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import net.lzzy.cinemanager.R;
import net.lzzy.cinemanager.models.CinemaFactory;
import net.lzzy.cinemanager.models.Order;
import net.lzzy.cinemanager.models.OrderFactory;
import net.lzzy.sqllib.GenericAdapter;
import net.lzzy.sqllib.ViewHolder;

import java.util.List;

/**
 * Created by lzzy_gxy on 2019/3/26.
 * Description:
 */
public class OrdersFragment extends BaseFragment {


    private List<Order> orders;
    private OrderFactory factory=OrderFactory.getInstance();
    private GenericAdapter<Order> adapter;
    private Order order;
    private ListView lv;


    public OrdersFragment(){}
    public OrdersFragment(Order order){
            this.order=order;
    }

    @Override
    protected void populate() {
        lv = find(R.id.main_lv_order);
        /** 无数据视图 **/
        View empty=find(R.id.order_tv_none);
        lv.setEmptyView(empty);
        orders=factory.get();
        adapter=new GenericAdapter<Order>(getActivity(),R.layout.order_item,orders) {
            @Override
            public void populate(ViewHolder viewHolder, Order order) {
                String location= String.valueOf(CinemaFactory.getInstance()
                        .getById(order.getCinemaId().toString()));

                viewHolder.setTextView(R.id.order_item_movie,order.getMovie())
                        .setTextView(R.id.order_item_location,location);
            }

            @Override
            public boolean persistInsert(Order order) {
                return factory.addOrder(order);
            }

            @Override
            public boolean persistDelete(Order order) {
                return factory.delete(order);
            }
        };
        lv.setAdapter(adapter);
        if (order!=null){
            save(order);
        }
    }
    public void save(Order order){
        adapter.add(order);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_orders;
    }

    @Override
    public void search(String kw) {
        orders.clear();
        if (TextUtils.isEmpty(kw)){
            orders.addAll(factory.get());
        }else {
            orders.addAll(factory.searchOrders(kw));
        }
        adapter.notifyDataSetChanged();
    }

}
