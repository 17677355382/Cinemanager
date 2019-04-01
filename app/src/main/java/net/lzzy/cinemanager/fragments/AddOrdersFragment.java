package net.lzzy.cinemanager.fragments;

import android.content.Context;

import net.lzzy.cinemanager.R;

/**
 * Created by lzzy_gxy on 2019/3/27.
 * Description:
 */
public class AddOrdersFragment extends BaseFragment {
    private OnFragmentInteractionListener liatener;

    @Override
    protected void populate() {

    }



    @Override
    public int getLayoutRes() {
        return R.layout.add_fragment_orders;
    }

    @Override
    public void search(String kw) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            liatener.hidesSearch();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
           liatener=(OnFragmentInteractionListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"必须实现OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        liatener=null;
    }
}
