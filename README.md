# RecyclerView, 简单封装RecyclerView，可以导入直接使用代码.
![网格布局](https://user-images.githubusercontent.com/13359093/209257893-127d6cbc-8def-4baa-8ba7-89a8753680a8.png)
![垂直线性布局](https://user-images.githubusercontent.com/13359093/209257911-7018a8fb-89f0-403e-af33-23abedd90655.png)
##### 1、图片加载使用    ImageLoader  也可以自行使用 Glide 或者其他图片加载第三方框架
``` 
 ImageView view = this.getView(viewId);
 ImageLoader.getInstance().displayImage(url, view, ImageLoaderEx.getDisplayImageOptions());
```
##### 2、设置多种类型的样式 
 LinearLayoutManager   线性布局垂直（ LinearLayoutManager.VERTICAL ）   或者横向 （LinearLayoutManager.HORIZONTAL）

 GridLayoutManager  网格布局  设置的数量 GridLayoutManager(this, 3)， 后面为int 参数，由自己设定为多少

 StaggeredGridLayoutManager  瀑布流布局  StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);  第一个int 整数参数为设置屏幕出现的纵向数量，第二个则为可设置横向显示或纵向显示。

```

        1. 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        2. 网格布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        
        3. 瀑布流
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);



```
##### 3、使用方法，直接设置自封装的 Adapter
```
this.recyclerAdapter = new RecyclerAdapter<String>(this, RollReycList,
                R.layout.item_address) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
//        recyclerView.addItemDecoration(new RecyclerItemDecoration(this));       

```
##### 5、封装继承Adapter的类 复制 粘贴直接可以使用
```
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected final int itemLayoutId;
    protected LayoutInflater inflater;
    protected List<T> listData;

    public RecyclerAdapter(Context context, List<T> listData, int itemLayoutId) {
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
        this.itemLayoutId = itemLayoutId;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(itemLayoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        convert(holder, listData.get(position), position);
    }

    /**
     * @param helper
     * @param item
     * @param position
     */
    public abstract void convert(RecyclerViewHolder helper, T item, int position);

    @Override
    public int getItemCount() {
        return listData.size();

    }

}

```
