# NoPaginate
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-NoPaginate-blue.svg?style=flat)](https://android-arsenal.com/details/1/6300)
[![androidweekly.cn](https://img.shields.io/badge/androidweekly.cn-%23156-red.svg)](http://androidweekly.cn/android-dev-weekly-issue-156/)
 [ ![Download](https://api.bintray.com/packages/nonews/maven/nopaginate/images/download.svg) ](https://bintray.com/nonews/maven/nopaginate/_latestVersion)
 [![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=null)

Android pagination library, based on [@MarkoMilos](https://github.com/MarkoMilos) repository [Paginate](https://github.com/MarkoMilos/Paginate)





Loading Item           |  Error Item
:-------------------------:|:-------------------------:
![](https://github.com/NoNews/NoPaginate/blob/master/art/loading.jpg "Loading item")  | ![](https://github.com/NoNews/NoPaginate/blob/master/art/error.jpg "Error item")



### Gradle

```
compile 'ru.alexbykov:nopaginate:0.4.3'
```

### Install
```java
  Paginate paginate = new PaginateBuilder()
                .with(recyclerView)
                .setCallback(new OnLoadMore() {
                    @Override
                    public void onLoadMore() {
                       // http or db request
                    }
                })
                .setLoadingTriggerThreshold(5)
                .build();
```



If you use ```MVP``` or ```Clean Architecture```, don't forget implement ```PaginateView```.
You can see example of implementation with MVP [here](https://github.com/NoNews/NoPaginate/tree/master/sample/src/main/java/ru/alexbykov/pagination)

### Actions
```java
   paginate.showLoading(show);
   paginate.showError(show);
   paginate.setPaginateNoMoreItems(set);
   paginate.unSubscribe(); //Don't forget call it on onDestroy();
```

### Custom Loading and Error
For custom error and loaging item just implement the interfaces ```ErrorItem``` or ```LoadingItem```

#### Custom error:
```java
public class CustomErrorItem implements ErrorItem {

           @Override
           public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false);
               return new RecyclerView.ViewHolder(view) {
               };
           }

           @Override
           public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, final OnRepeatListener onRepeatListener) {
               Button btnRepeat = (Button) holder.itemView.findViewById(R.id.btnRepeat);
               btnRepeat.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if (onRepeatListener != null) {
                           onRepeatListener.onClickRepeat(); //call onLoadMore
                       }
                   }
               });
           }
}
```


#### Custom loading:
```java
public class CustomLoadingItem implements LoadingItem {
    LoadingItem DEFAULT = new LoadingItem() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        }
    };
}
```

#### Install with custom items

```java
  Paginate  paginate = new PaginateBuilder()
                .with(recyclerView)
                .setCallback(new OnLoadMore() {
                    @Override
                    public void onLoadMore() {

                    }
                })
                .setLoadingTriggerThreshold(5)
                .setCustomErrorItem(new CustomErrorItem())
                .setCustomLoadingItem(new CustomLoadingItem())
                .build();

```


#### Idea
This repository is a slightly modified version of [Paginate](https://github.com/MarkoMilos/Paginate) library.
Author: [@MarkoMilos](https://github.com/MarkoMilos)

We decided to modify it a little, so that developers could easily use it with MVP or Clean Architecture


#### Todo
1. Double-sided pagination
2. Delegate for ```Presenter``` or ```Interactor```, with implementation Limit/Offset and Page pagination
3. Unit tests
4. Refactoring

#### Contributing

If you find any bug, or you have suggestions, don't be shy to create [issues](https://github.com/NoNews/NoPaginate/issues) or make a [PRs](https://github.com/NoNews/NoPaginate/pulls) in the `develop` branch.
You can read contribution guidelines [here](https://github.com/NoNews/NoPaginate/blob/master/CONTRIBUTING.md)



#### My other libraries:
1. [NoPermission](https://github.com/NoNews/NoPermission) — Simple Android permission library, consist of only one class

### License
```
Copyright 2017 Alex Bykov
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
