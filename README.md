# ListScrollHelper
ListScrollHelper，一个通吃ListView、GridView、ScrollView、RecyclerView、NestedScrollView的滚动帮助类，不再重复写滚动监听，切换列表滚动控件，只需修改一句话！

### 导言

- Android的界面开发中，用得最多的滚动控件就是ListView\GridView啦，当然现在RecyclerView当道了，直接切换LayoutManager加上一些配置，就能改变列表样式。
当我们要监听列表的滚动和滚动方向就要添加滚动监听，例如场景：右下角一个圆形按钮，手指在列表上上滑时隐藏，下滑时显示，就要加上监听，做对应的操作，如果每次都去写，如果一改动还要全部改，这样就太low啦。
程序员可不能允许这样，开始封装啦。

- 笔者是这样遇到问题的，给rv最后做一个加载更多的条目进行加载更多，原本对rv做滚动监听，就可以了，突然要加上5.0的CoordinatorLayout的联动效果，给套上了一层NestedScrollView，滚动监听就失效了
擦，这咋回事，原来是嵌套后，添加了setNestedScrollingEnabled后，rv就放弃滚动，将滚动交给NestedScrollView了。要监听滚动就需要监听NestedScrollView的滚动，谷歌有提供滚动监听，简单。

- 修改很简单，但是，如果我还要判断当时是加入了NestedScrollView再进行滚动设置，代码就臃肿了，还要保存2个控件的成员变量，要是往后再套个滚动View或者换个控件，又要修改一番。如果还要加上监听上滑、下滑、到顶部、到底部，多个控件的监听方式堆积在一起，满屏幕都是，改动一处全部改。

- 作为强迫症版程序员，真是分分钟受不了啊，如果切换控件时，我只需要改变一句实现对象的代码进行切换多好呀，嗯？这不是多态和接口解耦吗？嗯，开始构思吧！

### 这里暂时先不讲解代码，后续再补上。

### 使用你的rv版本

- 注：MultiType 内部引用了 com.android.support:recyclerview-v7:26.1.0，如果你不想使用这个版本，可以使用 exclude 将它排除掉，再自行引入你选择的版本。示例如下：

```java
dependencies {
    compile('com.hzh:liba-list-scroll-helper:1.0.0', {
       exclude group: 'com.android.support'
    })
    compile 'com.android.support:recyclerview-v7:你选择的版本'
}
```

### 使用

- 第一步：导入库

- compile 'com.hzh:liba-list-scroll-helper:1.0.0'

- 第二步：继承或者"偷梁换柱"

  - 项目中使用的滚动控件类，如果继承系统控件类后，做了修改，则需要继承本库中对应的实现了IScrollableView接口的控件。

  - 如果是直接使用系统控件类，使用本库中的ScrollableViewFactory工厂类进行"偷梁换柱"（这里也是无奈呀，ListView、RecyclerView都没有实现对应的接口，不好封装，而且能提供一些系统控件的监听缺失，例如ScrollView没有滚动监听，ListView只有setScrollListener，没有add）。

- 第三步：创建ListScrollHelper对象，传入对应的控件的包裹类对象以及控件对象。使用ListScrollHelper对象的addListScrollListener()方法添加监听，复写需要监听的回调即可。

### 控件对应的包裹类和实现接口的控件关系如下

| 原始控件           | 包裹类         | 对应的控件类  |
| ------------------  |:--------------------------------:| -----------------------------:|
| ListView            | ScrollableListViewWrapper        | ScrollableListView            |
| GridView            | ScrollableGridViewWrapper        |   ScrollableGridView          |
| ScrollView          | ScrollableScrollViewWrapper      |    ScrollableScrollView       |
| RecyclerView        | ScrollableRecyclerViewWrapper    |    ScrollableRecyclerView     |
| NestedScrollView    | ScrollableNestedScrollViewWrapper|    ScrollableNestedScrollView |
| NestedScrollView    | ScrollableNestedScrollViewWrapper|    ScrollableNestedScrollView |

#### 使用工厂类替换

- 推荐在BaseActivity的onCreate上调用（必须在调用父类的onCreate之前喔！，记得调用install()方法）

```java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //必须在onCreate之前替换
        ScrollableViewFactory.create(this, new AppCompatScrollableReplaceAdapter()).install();
        super.onCreate(savedInstanceState);
        mRootLayout = LayoutInflater.from(this).inflate(onLayoutId(), null);
        setContentView(mRootLayout);
        setupToolBar();
        onFindView();
        onBindContent();
        setupScrollListener();
    }
```

- AppCompatScrollableReplaceAdapter这个适配器，是继承了ScrollableViewFactory的内部类Adapter的，兼容了AppCompatActivity的控件替换。
如果你的项目的v7包还没有AppCompatActivity，则使用ScrollableViewFactory.Adapter即可。就像这样：

```java
ScrollableViewFactory.create(this, new ScrollableViewFactory.Adapter()).install();
```

- 创建ListScrollHelper滚动帮助类

```java
//创建ListScrollHelper滚动帮助类
ListScrollHelper scrollHelper = new ListScrollHelper(new ScrollableListViewWrapper((ScrollableListView) mListView));
//添加滚动监听
scrollHelper.addListScrollListener(new IListScrollListener() {
            @Override
            public void onScrolledUp() {
                L.d(TAG, "onScrolledToUp --> 上滑");
            }

            @Override
            public void onScrolledDown() {
                L.d(TAG, "onScrolledToDown --> 下滑");
            }

            @Override
            public void onScrollTop() {
                L.d(TAG, "onScrollTop --> 到顶啦");
            }

            @Override
            public void onScrollBottom() {
                L.d(TAG, "onScrollBottom --> 到底啦");
            }
        });
```

### 如果想实现另外的滚动控件(新的官方控件、第三方控件等)的滚动监听，也很简单。
- 第一步：新建类，实现IScrollableView接口，继承你的滚动控件,该接口没有抽象方法要实现，只是一个标识，但是该类你可以做一些拓展，例如添加一些监听，例如本库中的ScrollableGridView，由于系统的GridView只提供了setScrollListener，而没有add，所以该类上复写了setScrollListener，内部持有单个监听器，对外开放add方法，用集合存储，实现addScrollListener()
注意：该类的类名建议Scrollable开头，再加上控件名，以便统一，例如：ScrollableGridView、ScrollableListView。

- 第二步：新建类，继承AbsScrollableViewWrapper，泛型传入第一步新建的控件类，复写方法，例如setup（）方法则是添加监听，moveToTop和smoothMoveToTop则是直接滚动到顶部和缓缓过渡到顶部，对象都是以多态和接口解耦，便于拓展。
注意：该类的类名也建议Scrollable开头，中间是控件名，最后是Wrapper结尾，代表是控件的包装类。

- 第三步，就是使用啦，是不是很简单呢，使用方式上面也说到啦。不过还是提一下。
同样，创建helper类，只是创建wrapper类是对应你的控件的wrapper类，同时传入控件对象。

```java
ListScrollHelper(new ScrollableGridViewWrapper((ScrollableGridView) mGridView))
```